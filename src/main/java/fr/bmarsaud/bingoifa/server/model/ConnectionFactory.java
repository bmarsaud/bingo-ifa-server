package fr.bmarsaud.bingoifa.server.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;

public class ConnectionFactory {
    private static HikariDataSource dataSource;

    /**
     * Create connection to the database and prepare connection pool
     */
    private static void createConnection() {
        Properties properties = new Properties();
        String propertiesFileName = "/database.properties";
        if(BingoIFAServer.class.getResource(propertiesFileName) == null) propertiesFileName = "/default-database.properties";

        try {
            properties.load(BingoIFAServer.class.getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig(properties);
        dataSource = new HikariDataSource(config);
    }

    /**
     * Get a connection to the database using the connection pool
     * @return A valid connection to the database
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if(dataSource == null) createConnection();
        return dataSource.getConnection();
    }
}
