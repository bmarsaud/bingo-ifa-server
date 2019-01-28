package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.bmarsaud.bingoifa.server.entity.User;

public class UserDAO implements DAO<User> {
    private GridDAO gridDAO;

    public UserDAO() {
        gridDAO = new GridDAO();
    }

    public boolean create(User obj) {
        return false;
    }

    public boolean update(User obj) {
        return false;
    }

    public boolean delete(User obj) {
        return false;
    }

    public User find(int id) {
        return null;
    }

    public User findFromName(String name) {
        User user = null;

        try {
            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE login = ?;");
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();
            if(result.next()) {
                user = new User(
                        result.getInt("idUser"),
                        result.getString("login"),
                        result.getString("password"),
                        gridDAO.find(result.getInt("currentIdGrid"))
                );
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
