package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.entity.Grid;

public class BoxDAO implements DAO<Box> {
    private SentenceDAO sentenceDAO;

    public BoxDAO() {
        sentenceDAO = DAOFactory.getSentenceDAO();
    }

    public Box create(Box box) {
        return null;
    }

    public Box createGridBox(Box box, Grid grid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO Box(position, checked, checkTime, idGrid, idSentence) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, box.getPosition());
            statement.setBoolean(2, box.isChecked());
            statement.setTime(3, box.getCheckedTime());
            statement.setInt(4, grid.getId());
            statement.setInt(5, box.getSentence().getId());
            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if(result.next()) {
                box.setId(result.getInt("idBox"));
                return box;
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

    public boolean update(Box box) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE Box SET position = ?, checked = ?, checkTime = ?, idSentence = ? WHERE idBox = ?");
            statement.setInt(1, box.getPosition());
            statement.setBoolean(2, box.isChecked());
            statement.setTime(3, box.getCheckedTime());
            statement.setInt(4, box.getSentence().getId());
            statement.setInt(5, box.getId());

            if(statement.executeUpdate() > 0) return true;
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

    public boolean delete(Box box) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM Box WHERE idBox = ?");
            statement.setInt(1, box.getId());

            if(statement.executeUpdate() > 0) return true;
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

    public Box find(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        Box box = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Box WHERE idBox = ?");
            statement.setInt(1, id);

            result = statement.executeQuery();
            if(result.next()) {
                return new Box(
                        result.getInt("idBox"),
                        sentenceDAO.find(result.getInt("idSentence")),
                        result.getInt("position"),
                        result.getBoolean("checked"),
                        result.getTime("checkTime")
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

        return null;
    }

    public ArrayList<Box> getGridBoxes(int gridId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        ArrayList<Box> boxes = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Box WHERE idGrid = ?;");
            statement.setInt(1, gridId);

            result = statement.executeQuery();
            while(result.next()) {
                boxes.add(new Box(
                        result.getInt("idBox"),
                        sentenceDAO.find(result.getInt("idSentence")),
                        result.getInt("position"),
                        result.getBoolean("checked"),
                        result.getTime("checkTime")
                ));
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

        return boxes;
    }
}
