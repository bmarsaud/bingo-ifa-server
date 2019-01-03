package fr.bmarsaud.bingoifa.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.bingoifa.server.entity.User;

@Path("user")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        User user = new User();
        return user;
    }
}
