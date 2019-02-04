package fr.bmarsaud.bingoifa.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.xml.transform.Result;

import fr.bmarsaud.bingoifa.server.entity.Sentence;

public class SentenceDAO implements DAO<Sentence>{
    private static HashMap<Integer, Sentence> sentences;

    public SentenceDAO() {
        sentences = new HashMap<>();
    }

    public Sentence create(Sentence sentence) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();

            statement = connection.prepareStatement("INSERT INTO Sentence(label, downVotes, upVotes, activated) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sentence.getLabel());
            statement.setInt(2, sentence.getDownVotes());
            statement.setInt(3, sentence.getUpVotes());
            statement.setBoolean(4, sentence.isActivated());
            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if(result.next()) {
                sentence.setId(result.getInt("idSentence"));
                sentences.put(sentence.getId(), sentence);

                return sentence;
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

    public boolean update(Sentence sentence) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE Sentence SET label = ?, downVotes = ?, upVotes = ?, activated = ? WHERE idSentence = ?");
            statement.setString(1, sentence.getLabel());
            statement.setInt(2, sentence.getDownVotes());
            statement.setInt(3, sentence.getUpVotes());
            statement.setBoolean(4, sentence.isActivated());
            statement.setInt(5, sentence.getId());

            if(statement.executeUpdate() > 0) {
                sentences.put(sentence.getId(), sentence);
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

    public boolean delete(Sentence sentence) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM Sentence WHERE idSentence = ?");
            statement.setInt(1, sentence.getId());

            if(statement.executeUpdate() > 0) {
                sentences.remove(sentence.getId());
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

    public Sentence find(int sentenceId) {
        if(sentences.containsKey(sentenceId)) return sentences.get(sentenceId);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        Sentence sentence = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Sentence WHERE idSentence = ?;");
            statement.setInt(1, sentenceId);

            result = statement.executeQuery();
            if(result.next()) {
                sentence = new Sentence(
                    sentenceId,
                    result.getString("label"),
                    result.getInt("upVotes"),
                    result.getInt("downVotes"),
                    result.getBoolean("activated")
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

        return sentence;
    }
}
