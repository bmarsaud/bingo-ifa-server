package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.server.Request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.auth.RequestHandler;

@Path("user")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoggedUser(@Context Request request) {
        RequestHandler requestHandler = new RequestHandler(request);
        if(!requestHandler.requestIsAuthenticated()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(requestHandler.getAuthenticatedUser()).build();
    }
}
