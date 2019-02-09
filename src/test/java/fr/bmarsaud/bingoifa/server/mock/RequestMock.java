package fr.bmarsaud.bingoifa.server.mock;

import org.glassfish.grizzly.http.Method;

import java.sql.Timestamp;
import java.time.Instant;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import fr.bmarsaud.bingoifa.server.auth.HMACManager;
import fr.bmarsaud.bingoifa.server.entity.User;

public class RequestMock {
    public static Invocation.Builder buildAuthRequest(WebTarget target, Method method, User user) {
        String timestamp = Timestamp.from(Instant.now()).toString();
        String signature = HMACManager.encode(user.getPassword(), method + target.getUri().toString() + user.getLogin() + timestamp);

        return target.request()
                .header("X-Authorization-Timestamp", timestamp)
                .header("X-Authorization-Username", user.getLogin())
                .header("X-Authorization-Signature", signature);
    }
}
