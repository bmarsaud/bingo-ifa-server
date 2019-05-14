package fr.bmarsaud.bingoifa.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Properties;

import fr.bmarsaud.bingoifa.server.config.ConfigManager;

public class BingoIFAServer {
    public static final String SCHEME = "http://";
    public static final String HOST = SCHEME + "localhost:8080";
    public static final String BASE_URI = HOST + "/bingo-ifa-server";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("fr.bmarsaud.bingoifa.server.resource");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("bingo-ifa-server started at " + BASE_URI + " !");
        System.out.println("Press a key to shutdown...");
        System.in.read();
        server.shutdown();
    }
}

