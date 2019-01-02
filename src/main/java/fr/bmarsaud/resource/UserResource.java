package fr.bmarsaud.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.bmarsaud.entity.User;

@Path("resource")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        User user = new User();
        return user;
    }
}
