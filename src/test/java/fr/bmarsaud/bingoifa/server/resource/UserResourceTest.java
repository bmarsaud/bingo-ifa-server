package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.controller.ControllerFactory;
import fr.bmarsaud.bingoifa.server.controller.GridController;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.entity.HistoryLine;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.mock.RequestMock;
import fr.bmarsaud.bingoifa.server.mock.UserMock;
import fr.bmarsaud.bingoifa.server.model.DAOFactory;
import fr.bmarsaud.bingoifa.server.model.UserDAO;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class UserResourceTest {
    private UserDAO userDAO;
    private GridController gridController;

    private HttpServer server;
    private WebTarget target;


    public UserResourceTest() {
        userDAO = DAOFactory.getUserDAO();
        gridController = ControllerFactory.getGridController();
    }

    @Before
    public void setUp() throws Exception {
        BingoIFAServer.loadConfiguration();
        server = BingoIFAServer.startServer();

        Client c = ClientBuilder.newClient();
        target = c.target(BingoIFAServer.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testGetHistory() {
        User user = UserMock.users.get(0);
        ArrayList<HistoryLine> expectedHistory = user.getHistory();

        Response response = RequestMock.buildAuthRequest(target.path("user/history"), Method.GET, user).get();
        assertEquals(200, response.getStatus());
        assertEquals(expectedHistory, response.readEntity(new GenericType<ArrayList<HistoryLine>>() {}));
    }

    @Test
    public void testCurrentGrid() {
        User user = new User("getCurrentGrid", "password", gridController.generateNewGrid(), null, null);
        user = userDAO.create(user);

        Response response = RequestMock.buildAuthRequest(target.path("user/grid"), Method.GET, user).get();
        assertEquals(200, response.getStatus());
        assertEquals(user.getGrid(), response.readEntity(Grid.class));
    }

    @Test
    public void testGenerateGridIfNull() {
        User user = new User("generateGridIfNull", "password", null, null, null);
        user = userDAO.create(user);

        Response response = RequestMock.buildAuthRequest(target.path("user/grid"), Method.GET, user).get();

        assertEquals(200, response.getStatus());
        assertNotNull(response.readEntity(Grid.class));
    }

    @Test
    public void testGenerateNewGridIfOutdated() {
        User user = new User("generateNewGrid", "password", gridController.generateNewGrid(), null, null);
        user.getGrid().setDate(Date.valueOf(LocalDate.EPOCH));
        user = userDAO.create(user);

        Response response = RequestMock.buildAuthRequest(target.path("user/grid"), Method.GET, user).get();

        assertEquals(200, response.getStatus());
        assertNotEquals(user.getGrid(), response.readEntity(Grid.class));
    }
}
