package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.entity.HistoryLine;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.mock.RequestMock;
import fr.bmarsaud.bingoifa.server.mock.UserMock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class UserResourceTest {
    private HttpServer server;
    private WebTarget target;

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
    public void testGetHistory() {
        User user = UserMock.users.get(0);
        ArrayList<HistoryLine> expectedHistory = user.getHistory();

        Response response = RequestMock.buildAuthRequest(target.path("user/history"), Method.GET, user).get();
        assertEquals(200, response.getStatus());
        assertEquals(expectedHistory, response.readEntity(new GenericType<ArrayList<HistoryLine>>() {}));
    }

    @Test
    public void testCurrentGrid() {
        User user = UserMock.users.get(0);
        Grid expectedGrid = user.getGrid();

        Response response = RequestMock.buildAuthRequest(target.path("user/grid"), Method.GET, user).get();
        assertEquals(200, response.getStatus());
        assertEquals(expectedGrid, response.readEntity(Grid.class));
    }
}
