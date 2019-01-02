package fr.bmarsaud;

import org.junit.Test;

import java.sql.SQLException;

import fr.bmarsaud.model.ConnectionFactory;

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
