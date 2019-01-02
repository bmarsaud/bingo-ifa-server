package fr.bmarsaud.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.entity.Grid;

@Path("grid")
public class GridResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Grid getGrid() {
        Grid grid = new Grid();
        return grid;
    }
}
