package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import fr.bmarsaud.bingoifa.server.entity.Sentence;
import fr.bmarsaud.bingoifa.server.mock.SentenceMock;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SentenceDAOTest {
    private SentenceDAO sentenceDAO;

    @Before
    public void setup() {
        sentenceDAO = DAOFactory.getSentenceDAO();
    }

    @Test
    public void testFind() {
        Sentence expectedSentence = SentenceMock.sentences.get(0);
        Sentence sentence = sentenceDAO.find(expectedSentence.getId());
        assertEquals(sentence, expectedSentence);
    }

    @Test
    public void testFindNonexistent() {
        Sentence sentence = sentenceDAO.find(-1);
        assertNull(sentence);
    }

    @Test
    public void testCreateNew() {
        Sentence sentence = SentenceMock.dummySentence;
        sentence = sentenceDAO.create(sentence);

        assertEquals(sentence, sentenceDAO.find(sentence.getId()));
    }

    @Test
    public void testUpdate() {
        Sentence sentence = SentenceMock.dummySentence;
        sentence = sentenceDAO.create(sentence);

        sentence.setLabel("Updated label");
        sentence.setActivated(false);

        boolean result = sentenceDAO.update(sentence);
        assertTrue(result);
        assertEquals(sentence, sentenceDAO.find(sentence.getId()));
    }

    @Test
    public void testNonexistentUpdate() {
        Sentence sentence = SentenceMock.nonexistentSentence;
        sentence.setLabel("Updated label");

        boolean result = sentenceDAO.update(sentence);
        assertFalse(result);
    }

    @Test
    public void testDelete() {
        Sentence sentence = SentenceMock.dummySentence;
        sentence = sentenceDAO.create(sentence);

        boolean result = sentenceDAO.delete(sentence);
        assertTrue(result);
        assertNull(sentenceDAO.find(sentence.getId()));
    }

    @Test
    public void testNonexistentDelete() {
        Sentence sentence = SentenceMock.nonexistentSentence;

        boolean result = sentenceDAO.delete(sentence);
        assertFalse(result);
    }
}
