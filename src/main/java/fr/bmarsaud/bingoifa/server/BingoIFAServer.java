package fr.bmarsaud.bingoifa.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import fr.bmarsaud.bingoifa.server.config.ConfigManager;

public class BingoIFAServer {
    public static String SCHEME;
    public static String HOST;
    public static String BASE_URI;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("fr.bmarsaud.bingoifa.server.resource");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Load host configuration
     */
    public static void loadConfiguration() {
        Properties properties = new ConfigManager().getProperties("host");
        SCHEME = properties.getProperty("host.scheme");
        HOST = SCHEME + "://" + properties.getProperty("host.name") + ":" + properties.getProperty("host.port", "80");
        BASE_URI = HOST + "/" + properties.getProperty("host.baseUri");
    }

    public static void main(String[] args) throws IOException {
        BingoIFAServer.loadConfiguration();

        final HttpServer server = startServer();
        System.out.println("bingo-ifa-server started at " + BASE_URI + " !");
        System.out.println("Press a key to shutdown...");
        System.in.read();
        server.shutdown();
    }
}

