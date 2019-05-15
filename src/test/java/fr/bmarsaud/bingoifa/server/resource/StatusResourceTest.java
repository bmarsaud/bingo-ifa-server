package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class StatusResourceTest {
    private HttpServer server;
    private WebTarget target;

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
    public void testStatus() {
        Response response = target.path("status").request().get();
        assertEquals(200, response.getStatus());
        assertTrue(response.readEntity(Boolean.class));
    }
}
