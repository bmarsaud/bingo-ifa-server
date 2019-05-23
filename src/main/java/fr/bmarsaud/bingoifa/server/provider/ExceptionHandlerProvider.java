package fr.bmarsaud.bingoifa.server.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandlerProvider implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable cause) {
        if (cause instanceof WebApplicationException) {
            return ((WebApplicationException) cause).getResponse();
        } else {
            cause.printStackTrace();
            return Response.serverError().build();
        }
    }
}
