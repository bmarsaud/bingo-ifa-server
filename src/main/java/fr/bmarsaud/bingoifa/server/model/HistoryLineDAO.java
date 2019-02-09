package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.HistoryLine;
import fr.bmarsaud.bingoifa.server.entity.User;

public class HistoryLineDAO implements DAO<HistoryLine> {
    private GridDAO gridDAO;

    public HistoryLineDAO() {
        gridDAO = new GridDAO();
    }

    @Override
    public HistoryLine find(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM HistoryLine WHERE idHistoryLine = ?;");
            statement.setInt(1, id);

            result = statement.executeQuery();
            if(result.next()) {
                return new HistoryLine(
                        result.getInt("idHistoryLine"),
                        result.getTimestamp("date"),
                        gridDAO.find(result.getInt("idGrid")),
                        result.getInt("rank")
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

    public ArrayList<HistoryLine> getHistoryFromUserId(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        ArrayList<HistoryLine> history = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM HistoryLine WHERE idUser = ?;");
            statement.setInt(1, userId);

            result = statement.executeQuery();
            while(result.next()) {
                history.add(new HistoryLine(
                        result.getInt("idHistoryLine"),
                        result.getTimestamp("date"),
                        gridDAO.find(result.getInt("idGrid")),
                        result.getInt("rank")
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

        return history;
    }

    public HistoryLine createUserHistoryLine(HistoryLine historyLine, User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO HistoryLine(date, rank, idgrid, iduser) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, historyLine.getDate());
            statement.setInt(2, historyLine.getRank());
            if(historyLine.getGrid() != null) statement.setInt(3, historyLine.getGrid().getId());
            else statement.setNull(3, Types.INTEGER);
            statement.setInt(4, user.getId());
            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if(result.next()) {
                historyLine.setId(result.getInt("idHistoryLine"));
                return historyLine;
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

    @Override
    public HistoryLine create(HistoryLine obj) {
        return null;
    }

    @Override
    public boolean update(HistoryLine historyLine) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE HistoryLine SET date = ?, rank = ?, idGrid = ? WHERE idHistoryLine = ?;");
            statement.setTimestamp(1, historyLine.getDate());
            statement.setInt(2, historyLine.getRank());
            if(historyLine.getGrid() != null) statement.setInt(3, historyLine.getGrid().getId());
            else statement.setNull(3, Types.INTEGER);
            statement.setInt(4, historyLine.getId());

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

    @Override
    public boolean delete(HistoryLine historyLine) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM HistoryLine WHERE idHistoryLine = ?;");
            statement.setInt(1, historyLine.getId());

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
}
