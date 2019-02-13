package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.server.Request;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.auth.RequestHandler;
import fr.bmarsaud.bingoifa.server.controller.GridController;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.model.UserDAO;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private GridController gridController;
    private UserDAO userDAO;

    public UserResource() {
        gridController = new GridController();
        userDAO = new UserDAO();
    }

    @GET
    public Response getLoggedUser(@Context Request request) {
        User user = RequestHandler.getAuthenticatedUser(request);
        return Response.ok(user).build();
    }

    @GET
    @Path("/history")
    public Response getUserHistory(@Context Request request) {
        User user = RequestHandler.getAuthenticatedUser(request);
        return  Response.ok(user.getHistory()).build();
    }

    @GET
    @Path("/grid")
    public Response getGrid(@Context Request request) {
        User user = RequestHandler.getAuthenticatedUser(request);

        if(user.isGridOutdated()) {
            Grid grid = gridController.generateNewGrid();
            user.setGrid(grid);
            userDAO.update(user);
        }

        return  Response.ok(user.getGrid()).build();
    }
}
