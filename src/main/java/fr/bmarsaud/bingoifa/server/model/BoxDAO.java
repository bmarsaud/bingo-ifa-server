package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.Box;

public class BoxDAO {
    private SentenceDAO sentenceDAO;

    public BoxDAO() {
        sentenceDAO = new SentenceDAO();
    }

    public ArrayList<Box> getGridBoxes(int gridId) {
        ArrayList<Box> boxes = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Box WHERE idGrid = ?;");
            statement.setInt(1, gridId);

            ResultSet result = statement.executeQuery();
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
        }

        return boxes;
    }
}
