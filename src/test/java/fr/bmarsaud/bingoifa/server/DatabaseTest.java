package fr.bmarsaud.bingoifa.server;

import org.junit.Test;

import java.sql.SQLException;

import fr.bmarsaud.bingoifa.server.model.ConnectionFactory;

public class DatabaseTest {

    @Test
    public void testConnection() {
        try {
            ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
