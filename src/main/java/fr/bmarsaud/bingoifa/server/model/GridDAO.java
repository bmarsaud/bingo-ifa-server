package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.bmarsaud.bingoifa.server.entity.Grid;

public class GridDAO {
    private BoxDAO boxDAO;

    public GridDAO() {
        boxDAO = new BoxDAO();
    }

    public Grid find(int gridId) {
        if(gridId == -1) return null;
        Grid grid = null;

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT  * FROM Grid WHERE idGrid = ?;");
            statement.setInt(1, gridId);

            ResultSet result = statement.executeQuery();
            if(result.next()) {
                grid = new Grid(
                    gridId,
                    result.getDate("date"),
                    result.getBoolean("shuffled"),
                    boxDAO.getGridBoxes(gridId)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grid;
    }
}
