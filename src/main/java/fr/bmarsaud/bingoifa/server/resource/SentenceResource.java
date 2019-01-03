package fr.bmarsaud.bingoifa.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.bingoifa.server.entity.Sentence;

@Path("sentence")
public class SentenceResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Sentence getSentence() {
        Sentence sentence = new Sentence();
        return sentence;
    }
}
