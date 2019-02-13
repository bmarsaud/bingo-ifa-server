package fr.bmarsaud.bingoifa.server.auth;

import org.glassfish.grizzly.http.server.Request;

import java.sql.Timestamp;

import javax.ws.rs.NotAuthorizedException;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.model.UserDAO;

public class RequestHandler {
    public static User getAuthenticatedUser(Request request)  {
        String requestMethod = request.getMethod().toString();
        String requestUri = BingoIFAServer.HOST + request.getRequestURI();
        String requestUsername = request.getHeader("X-Authorization-Username");
        String requestTimestamp = request.getHeader("X-Authorization-Timestamp");
        String requestSignature = request.getHeader("X-Authorization-Signature");

        if(requestUsername != null && requestTimestamp != null && requestSignature != null) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findFromName(requestUsername);
            String signature = requestMethod + requestUri + requestUsername + requestTimestamp;

            Timestamp timestamp = Timestamp.valueOf(requestTimestamp);
            if(user != null && HMACManager.encode(user.getPassword(), signature).equals(requestSignature) && (user.getLastRequest() == null || timestamp.after(user.getLastRequest()))) {
                user.setLastRequest(timestamp);
                userDAO.update(user);

                return user;
            } else {
                throw new NotAuthorizedException("Bad auth signature.");
            }
        } else {
            throw new NotAuthorizedException("Auth information missing.");
        }
    }
}
