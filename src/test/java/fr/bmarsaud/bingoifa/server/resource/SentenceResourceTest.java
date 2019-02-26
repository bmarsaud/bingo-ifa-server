package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.controller.GridController;
import fr.bmarsaud.bingoifa.server.entity.Sentence;
import fr.bmarsaud.bingoifa.server.mock.RequestMock;
import fr.bmarsaud.bingoifa.server.mock.SentenceMock;
import fr.bmarsaud.bingoifa.server.model.DAOFactory;
import fr.bmarsaud.bingoifa.server.model.SentenceDAO;

import static junit.framework.Assert.assertEquals;

public class SentenceResourceTest {
    private SentenceDAO sentenceDAO;

    private HttpServer server;
    private WebTarget target;

    public SentenceResourceTest() {
        sentenceDAO = DAOFactory.getSentenceDAO();
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
    public void testGetSentence() {
        Sentence expectedSentence = SentenceMock.sentences.get(0);

        Response response = target.path("sentence/" + expectedSentence.getId()).request().get();
        assertEquals(200, response.getStatus());
        assertEquals(expectedSentence, response.readEntity(Sentence.class));
    }

    @Test
    public void testGetUnknownSentence() {
        Response response = target.path("sentence/0").request().get();
        assertEquals(204, response.getStatus());
    }
}
