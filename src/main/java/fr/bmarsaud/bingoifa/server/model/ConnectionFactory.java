package fr.bmarsaud.bingoifa.server.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;
import fr.bmarsaud.bingoifa.server.config.ConfigManager;

public class ConnectionFactory {
    private static ConfigManager configManager = new ConfigManager();
    private static HikariDataSource dataSource;

    /**
     * Create connection to the database and prepare connection pool
     */
    private static void createConnection() {
        HikariConfig config = new HikariConfig(configManager.getProperties("database"));
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
