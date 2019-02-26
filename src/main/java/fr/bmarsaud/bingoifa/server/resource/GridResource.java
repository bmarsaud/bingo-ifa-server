package fr.bmarsaud.bingoifa.server.resource;

import org.glassfish.grizzly.http.server.Request;

import java.sql.Time;
import java.time.LocalTime;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.bmarsaud.bingoifa.server.auth.RequestHandler;
import fr.bmarsaud.bingoifa.server.controller.ControllerFactory;
import fr.bmarsaud.bingoifa.server.controller.GridController;
import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.model.BoxDAO;
import fr.bmarsaud.bingoifa.server.model.DAOFactory;
import fr.bmarsaud.bingoifa.server.model.GridDAO;

@Path("grid")
@Produces(MediaType.APPLICATION_JSON)
public class GridResource {
    private GridDAO gridDAO;
    private BoxDAO boxDAO;
    private GridController gridController;

    public GridResource() {
        gridDAO = DAOFactory.getGridDAO();
        boxDAO = DAOFactory.getBoxDAO();
        gridController = ControllerFactory.getGridController();
    }

    @GET
    @Path("/{gridId}")
    public Response getGrid(@Context Request request, @PathParam("gridId") int gridId) {
        RequestHandler.getAuthenticatedUser(request);
        return Response.ok(gridDAO.find(gridId)).build();
    }

    @POST
    @Path("/{gridId}/check/{boxPosition}")
    public Response checkGridBox(@Context Request request, @PathParam("gridId") int gridId, @PathParam("boxPosition") int boxPosition) {
        User user = RequestHandler.getAuthenticatedUser(request);

        if(user.getGrid().getId() != gridId) throw new NotAuthorizedException("You are not authorized to modify this grid.");
        if(boxPosition < 0 || boxPosition > 15) throw new BadRequestException("Box position must be in [0-15] range.");
        if(user.getGrid().getBoxes().get(boxPosition).isChecked()) return Response.status(Response.Status.NOT_MODIFIED).build();

        Box box = user.getGrid().getBoxes().get(boxPosition);
        box.setChecked(true);
        box.setCheckedTime(Time.valueOf(LocalTime.now()));
        boxDAO.update(box);

        return Response.ok().build();
    }

    @POST
    @Path("/{gridId}/shuffle/{boxPosition}")
    public Response shuffleGrid(@Context Request request, @PathParam("gridId") int gridId, @PathParam("boxPosition") int boxPosition) {
        User user = RequestHandler.getAuthenticatedUser(request);

        if(user.getGrid().getId() != gridId) throw new NotAuthorizedException("You are not authorized to modify this grid.");
        if(user.getGrid().isShuffled()) return Response.notModified().build();

        gridController.shuffleGrid(user.getGrid(), boxPosition);

        return Response.ok().build();
    }
}
