package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import fr.bmarsaud.bingoifa.server.entity.Sentence;

import static org.junit.Assert.assertEquals;

public class SentenceDAOTest {
    private Sentence expectedSentence;
    private SentenceDAO sentenceDAO;

    @Before
    public void setup() {
        sentenceDAO = new SentenceDAO();
        expectedSentence = new Sentence(1, "Bastien perd à CurveFever", 0, 0, true);
    }

    @Test
    public void testFindSentence() {
        Sentence sentence = sentenceDAO.find(1);
        assertEquals(sentence, expectedSentence);
    }
}
