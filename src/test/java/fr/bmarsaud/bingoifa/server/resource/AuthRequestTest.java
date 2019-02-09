package fr.bmarsaud.bingoifa.server.resource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.auth.HMACManager;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.mock.RequestMock;
import fr.bmarsaud.bingoifa.server.mock.UserMock;

import static org.junit.Assert.assertEquals;

public class AuthRequestTest {
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
    public void testAuthRequest() {
        User user = UserMock.users.get(0);
        Response response = RequestMock.buildAuthRequest(target.path("user"), Method.GET, user).get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testSameAuthRequestFail() {
        User user = UserMock.users.get(0);
        Invocation.Builder builder = RequestMock.buildAuthRequest(target.path("user"), Method.GET, user);

        assertEquals(200, builder.get().getStatus());
        assertEquals(401, builder.get().getStatus());
    }

    @Test
    public void testBadPassword() {
        User user = new User(1, "bmarsaud", "badpassword", null, null, null);
        Invocation.Builder builder = RequestMock.buildAuthRequest(target.path("user"), Method.GET, user);

        assertEquals(401, builder.get().getStatus());
    }

    @Test
    public void testBadUsername() {
        User user = new User(-1, "badLogin", "password", null, null, null);
        Invocation.Builder builder = RequestMock.buildAuthRequest(target.path("user"), Method.GET, user);

        assertEquals(401, builder.get().getStatus());
    }
}
