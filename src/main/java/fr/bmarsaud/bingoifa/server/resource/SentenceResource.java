package fr.bmarsaud.bingoifa.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.entity.Sentence;
import fr.bmarsaud.bingoifa.server.model.DAOFactory;
import fr.bmarsaud.bingoifa.server.model.SentenceDAO;

@Path("sentence")
@Produces(MediaType.APPLICATION_JSON)
public class SentenceResource {
    private SentenceDAO sentenceDAO;

    public SentenceResource() {
        sentenceDAO = DAOFactory.getSentenceDAO();
    }

    @GET
    @Path("/{sentenceId}")
    public Response getSentence(@PathParam("sentenceId") int sentenceId) {
        Sentence sentence = sentenceDAO.find(sentenceId);
        if(sentence == null) return Response.noContent().build();

        return Response.ok(sentence).build();
    }
}
