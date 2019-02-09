package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import fr.bmarsaud.bingoifa.server.entity.User;

public class UserDAO implements DAO<User> {
    private GridDAO gridDAO;
    private HistoryLineDAO historyLineDAO;

    public UserDAO() {
        gridDAO = new GridDAO();
        historyLineDAO = new HistoryLineDAO();
    }

    public User create(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO User(login, password, currentIdGrid, lastRequest) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            if(user.getGrid() != null) statement.setInt(3, user.getGrid().getId());
            else statement.setNull(3, Types.INTEGER);
            statement.setTimestamp(4, user.getLastRequest());
            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if(result.next()) {
                user.setId(result.getInt("idUser"));
                return user;
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

    public boolean update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE User SET login = ?, password = ?, currentIdGrid = ?, lastRequest = ? WHERE idUser = ?;");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            if(user.getGrid() != null) statement.setInt(3, user.getGrid().getId());
            else statement.setNull(3, Types.INTEGER);
            statement.setTimestamp(4, user.getLastRequest());
            statement.setInt(5, user.getId());

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

    public boolean delete(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM User WHERE idUser = ?;");
            statement.setInt(1, user.getId());

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

    public User find(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM User WHERE idUser = ?;");
            statement.setInt(1, id);

            result = statement.executeQuery();
            if(result.next()) {
                return new User(
                        result.getInt("idUser"),
                        result.getString("login"),
                        result.getString("password"),
                        gridDAO.find(result.getInt("currentIdGrid")),
                        result.getTimestamp("lastRequest"),
                        historyLineDAO.getHistoryFromUserId(result.getInt("idUser"))
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

    public User findFromName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        User user = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM User WHERE login = ?;");
            statement.setString(1, name);

            result = statement.executeQuery();
            if(result.next()) {
                user = new User(
                        result.getInt("idUser"),
                        result.getString("login"),
                        result.getString("password"),
                        gridDAO.find(result.getInt("currentIdGrid")),
                        result.getTimestamp("lastRequest"),
                        historyLineDAO.getHistoryFromUserId(result.getInt("idUser"))
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

        return user;
    }
}
