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
import fr.bmarsaud.bingoifa.server.entity.User;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
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
        //TODO: generate grid if grid is null or outdated
        return  Response.ok(user.getGrid()).build();
    }
}
