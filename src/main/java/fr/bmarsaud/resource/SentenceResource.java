package fr.bmarsaud.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.entity.Sentence;

@Path("sentence")
public class SentenceResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Sentence getSentence() {
        Sentence sentence = new Sentence();
        return sentence;
    }
}
