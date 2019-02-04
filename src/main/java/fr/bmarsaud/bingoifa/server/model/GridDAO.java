package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.entity.Grid;

public class GridDAO implements DAO<Grid>{
    private BoxDAO boxDAO;

    public GridDAO() {
        boxDAO = new BoxDAO();
    }

    public Grid create(Grid grid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO Grid(date, shuffled) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, grid.getDate());
            statement.setBoolean(2, grid.isShuffled());
            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if(result.next()) {
                grid.setId(result.getInt("idGrid"));

                for(int i = 0; i < grid.getBoxes().size(); i++) {
                    Box box = boxDAO.createGridBox(grid.getBoxes().get(i), grid);
                    grid.getBoxes().get(i).setId(box.getId());
                }

                return grid;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(result != null) result.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean update(Grid grid) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE Grid SET date = ?, shuffled = ? WHERE idGrid = ?;");
            statement.setDate(1, grid.getDate());
            statement.setBoolean(2, grid.isShuffled());
            statement.setInt(3, grid.getId());

            if(statement.executeUpdate() > 0) {
                for(Box box : grid.getBoxes()) {
                    boxDAO.update(box);
                }

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean delete(Grid grid) {
        Connection connection = null;
        PreparedStatement statement = null;

        for(Box box : grid.getBoxes()) {
            if(!boxDAO.delete(box)) return false;
        }

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM Grid WHERE idGrid = ?;");
            statement.setInt(1, grid.getId());

            if(statement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Grid find(int gridId) {
        if(gridId == -1) return null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        Grid grid = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Grid WHERE idGrid = ?;");
            statement.setInt(1, gridId);

            result = statement.executeQuery();
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
        } finally {
            try {
                if(result != null) result.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return grid;
    }
}
