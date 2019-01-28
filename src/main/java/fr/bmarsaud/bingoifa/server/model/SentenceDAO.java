package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import fr.bmarsaud.bingoifa.server.entity.Sentence;

public class SentenceDAO {
    private static HashMap<Integer, Sentence> sentences;

    public SentenceDAO() {
        sentences = new HashMap<>();
    }

    public Sentence find(int sentenceId) {
        if(sentences.containsKey(sentenceId)) return sentences.get(sentenceId);
        Sentence sentence = null;

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Sentence WHERE idSentence = ?;");
            statement.setInt(1, sentenceId);

            ResultSet result = statement.executeQuery();
            if(result.next()) {
                sentence = new Sentence(
                    sentenceId,
                    result.getString("label"),
                    result.getInt("upVotes"),
                    result.getInt("downVotes"),
                    result.getBoolean("activated")
                );
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sentence;
    }
}
