package fr.bmarsaud.bingoifa.server.model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.HistoryLine;
import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.mock.HistoryLineMock;
import fr.bmarsaud.bingoifa.server.mock.UserMock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HistoryLineDAOTest {
    private HistoryLineDAO historyLineDAO;
    private UserDAO userDAO;

    public HistoryLineDAOTest() {
        historyLineDAO = new HistoryLineDAO();
        userDAO = new UserDAO();
    }

    @Test
    public void testFind() {
        HistoryLine expectedLine = HistoryLineMock.history.get(0);
        assertEquals(expectedLine, historyLineDAO.find(expectedLine.getId()));
    }

    @Test
    public void testFindNonExistent() {
        assertNull(historyLineDAO.find(HistoryLineMock.nonExistentHistoryLine.getId()));
    }

    @Test
    public void testGetHistoryFromUser() {
        ArrayList<HistoryLine> expectedHistory = new ArrayList<>(HistoryLineMock.history);
        assertEquals(expectedHistory, historyLineDAO.getHistoryFromUserId(UserMock.users.get(0).getId()));
    }

    @Test
    public void testGetHistoryFromNonexistentUser() {
        ArrayList<HistoryLine> expectedHistory = new ArrayList<>();
        assertEquals(expectedHistory, historyLineDAO.getHistoryFromUserId(UserMock.nonExistentUser.getId()));
    }

    @Test
    public void testCreateUserHistoryLine() {
        User user = UserMock.toCreateUser;
        user = userDAO.create(user);

        HistoryLine expectedHistoryLine = HistoryLineMock.toCreateHistoryLine;
        expectedHistoryLine = historyLineDAO.createUserHistoryLine(expectedHistoryLine, user);

        assertEquals(expectedHistoryLine, historyLineDAO.find(expectedHistoryLine.getId()));
    }

    @Test
    public void testCreateNonexistentUserHistoryLine() {
        User user = UserMock.nonExistentUser;
        HistoryLine expectedHistoryLine = HistoryLineMock.toCreateHistoryLine;

        assertNull(historyLineDAO.createUserHistoryLine(expectedHistoryLine, user));
    }

    @Test
    public void testUpdate() {
        User user = UserMock.toCreateUser;
        user = userDAO.create(user);

        HistoryLine expectedHistoryLine = HistoryLineMock.toCreateHistoryLine;
        expectedHistoryLine = historyLineDAO.createUserHistoryLine(expectedHistoryLine, user);

        expectedHistoryLine.setRank(1);
        boolean result = historyLineDAO.update(expectedHistoryLine);
        assertTrue(result);
        assertEquals(expectedHistoryLine, historyLineDAO.find(expectedHistoryLine.getId()));
    }

    @Test
    public void testUpdateNonexistent() {
        HistoryLine historyLine = HistoryLineMock.nonExistentHistoryLine;
        assertFalse(historyLineDAO.update(historyLine));
    }

    @Test
    public void testDelete() {
        User user = UserMock.toCreateUser;
        user = userDAO.create(user);

        HistoryLine historyLine = HistoryLineMock.toCreateHistoryLine;
        historyLine = historyLineDAO.createUserHistoryLine(historyLine, user);

        boolean result = historyLineDAO.delete(historyLine);
        assertTrue(result);
        assertNull(historyLineDAO.find(historyLine.getId()));
    }

    @Test
    public void testDeleteNonExistent() {
        HistoryLine historyLine = HistoryLineMock.nonExistentHistoryLine;
        assertFalse(historyLineDAO.delete(historyLine));
    }
}
