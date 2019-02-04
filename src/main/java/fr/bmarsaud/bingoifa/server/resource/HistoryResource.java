package fr.bmarsaud.bingoifa.server.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.bingoifa.server.entity.HistoryLine;

@Path("history")
public class HistoryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HistoryLine> getHistory() {
        ArrayList<HistoryLine> history = new ArrayList<>();
        return history;
    }
}
