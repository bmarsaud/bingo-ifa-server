package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.controller.ControllerFactory;
import fr.bmarsaud.bingoifa.server.controller.GridController;
import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.mock.GridMock;
import fr.bmarsaud.bingoifa.server.mock.RequestMock;
import fr.bmarsaud.bingoifa.server.mock.UserMock;
import fr.bmarsaud.bingoifa.server.model.BoxDAO;
import fr.bmarsaud.bingoifa.server.model.DAOFactory;
import fr.bmarsaud.bingoifa.server.model.UserDAO;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GridResourceTest {
    private User user;
    private UserDAO userDAO;
    private BoxDAO boxDAO;
    private GridController gridController;

    private HttpServer server;
    private WebTarget target;

    public GridResourceTest() {
        user = UserMock.users.get(0);
        userDAO = DAOFactory.getUserDAO();
        boxDAO = DAOFactory.getBoxDAO();
        gridController = ControllerFactory.getGridController();
    }

    @Before
    public void setUp() throws Exception {
        server = BingoIFAServer.startServer();

        Client c = ClientBuilder.newClient();
        target = c.target(BingoIFAServer.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testGetGrid() {
        Grid expectedGrid = GridMock.grids.get(0);

        Response response = RequestMock.buildAuthRequest(target.path("grid/" + expectedGrid.getId()), Method.GET, user).get();
        assertEquals(200, response.getStatus());
        assertEquals(expectedGrid, response.readEntity(Grid.class));
    }

    @Test
    public void testCheckGridBox() {
        User user = new User("testCheckGrid", "password", gridController.generateNewGrid(), null, null);
        user = userDAO.create(user);

        Response response = RequestMock.buildAuthRequest(target.path("grid/" + user.getGrid().getId() + "/check/0"), Method.POST, user).post(null);
        user = userDAO.find(user.getId());
        assertEquals(200, response.getStatus());
        assertTrue(user.getGrid().getBoxes().get(0).isChecked());
    }

    @Test
    public void testUnauthorizedCheckGridBox() {
        User user = new User("tucgb", "password", gridController.generateNewGrid(), null, null);
        user = userDAO.create(user);

        Response response = RequestMock.buildAuthRequest(target.path("grid/0/check/0"), Method.POST, user).post(null);
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testCheckAlreadyCheckedGridBox() {
        User user = new User("tcacgb", "password", gridController.generateNewGrid(), null, null);
        user = userDAO.create(user);
        Box box = user.getGrid().getBoxes().get(0);
        box.setChecked(true);
        box.setCheckedTime(Time.valueOf(LocalTime.now()));
        boxDAO.update(box);

        Response response = RequestMock.buildAuthRequest(target.path("grid/" + user.getGrid().getId() + "/check/0"), Method.POST, user).post(null);
        assertEquals(304, response.getStatus());
    }

    @Test
    public void testCheckNotInRangeGridBox() {
        User user = new User("tcnoirgb", "password", gridController.generateNewGrid(), null, null);
        user = userDAO.create(user);

        Response response = RequestMock.buildAuthRequest(target.path("grid/" + user.getGrid().getId() + "/check/18"), Method.POST, user).post(null);
        assertEquals(400, response.getStatus());

        response = RequestMock.buildAuthRequest(target.path("grid/" + user.getGrid().getId() + "/check/-18"), Method.POST, user).post(null);
        assertEquals(400, response.getStatus());
    }
}
