package fr.bmarsaud.bingoifa.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Properties;

import fr.bmarsaud.bingoifa.server.config.ConfigManager;
import fr.bmarsaud.bingoifa.server.model.ConnectionFactory;

public class BingoIFAServer {
    public static String BASE_URI;
    public static String PUBLIC_URI;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("fr.bmarsaud.bingoifa.server.resource", "fr.bmarsaud.bingoifa.server.provider");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Load host configuration
     */
    public static void loadConfiguration() {
        Properties properties = new ConfigManager().getProperties("host");
        String scheme = properties.getProperty("host.scheme");
        String host = scheme + "://" + properties.getProperty("host.name") + ":" + properties.getProperty("host.port", "80");
        BASE_URI = host + "/" + properties.getProperty("host.baseUri");
        PUBLIC_URI = properties.getProperty("host.publicUri", host);
    }

    /**
     * Test database connection
     */
    public static void testDatabaseConnection() {
        try {
            ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        BingoIFAServer.loadConfiguration();
        BingoIFAServer.testDatabaseConnection();
        BingoIFAServer.startServer();

        System.out.println("bingo-ifa-server started at " + BASE_URI + " !");
    }
}

