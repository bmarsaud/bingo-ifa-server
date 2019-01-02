package fr.bmarsaud.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.entity.History;

@Path("history")
public class HistoryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public History getHistory() {
        History history = new History();
        return history;
    }
}
