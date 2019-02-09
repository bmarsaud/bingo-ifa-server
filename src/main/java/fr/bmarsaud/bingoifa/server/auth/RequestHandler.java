package fr.bmarsaud.bingoifa.server.auth;

import org.glassfish.grizzly.http.server.Request;

import java.io.CharConversionException;
import java.sql.Timestamp;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.model.UserDAO;

public class RequestHandler {
    private UserDAO userDAO;
    private Request request;
    private boolean authenticated;
    private User authenticatedUser;

    public RequestHandler(Request request) {
        this.request = request;

        userDAO = new UserDAO();
        authenticated = false;
        authenticatedUser = null;

        handleRequest();
    }

    private void handleRequest() {
        String requestMethod = request.getMethod().toString();
        String requestUri = BingoIFAServer.HOST + request.getRequestURI();
        String requestUsername = request.getHeader("X-Authorization-Username");
        String requestTimestamp = request.getHeader("X-Authorization-Timestamp");
        String requestSignature = request.getHeader("X-Authorization-Signature");

        if(requestUsername != null && requestTimestamp != null && requestSignature != null) {
            User user = userDAO.findFromName(requestUsername);
            String signature = requestMethod + requestUri + requestUsername + requestTimestamp;

            Timestamp timestamp = Timestamp.valueOf(requestTimestamp);
            if(user != null && HMACManager.encode(user.getPassword(), signature).equals(requestSignature) && timestamp.after(user.getLastRequest())) {
                user.setLastRequest(timestamp);
                userDAO.update(user);

                authenticatedUser = user;
                authenticated = true;
            }
        }
    }

    public boolean requestIsAuthenticated() {
        return authenticated;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
