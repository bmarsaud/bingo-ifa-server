package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import fr.bmarsaud.bingoifa.server.entity.User;
import fr.bmarsaud.bingoifa.server.mock.UserMock;

public class UserDAOTest {
    private UserDAO userDAO;

    @Before
    public void setup() {
        userDAO = DAOFactory.getUserDAO();
    }

    @Test
    public void testGetRealUserByName() {
        User expectedUser = UserMock.users.get(0);
        assertEquals(expectedUser, userDAO.findFromName(expectedUser.getLogin()));
    }

    @Test
    public void testGetNonexistentUserByName() {
        assertNull(userDAO.findFromName("doesnotexists"));
    }

    @Test
    public void testFind() {
        User expectedUser = UserMock.users.get(0);
        assertEquals(expectedUser, userDAO.find(expectedUser.getId()));
    }

    @Test
    public void testFindNonexistent() {
        assertNull(userDAO.find(UserMock.nonExistentUser.getId()));
    }

    @Test
    public void testCreate() {
        User expectedUser = UserMock.toCreateUser;
        expectedUser = userDAO.create(expectedUser);

        assertEquals(expectedUser, userDAO.find(expectedUser.getId()));
    }

    @Test
    public void testUpdate() {
        User expectedUser = UserMock.toCreateUser;
        expectedUser = userDAO.create(expectedUser);

        expectedUser.setLogin("modifiedLogin");

        boolean result = userDAO.update(expectedUser);
        assertTrue(result);
        assertEquals(expectedUser, userDAO.find(expectedUser.getId()));
    }

    @Test
    public void testUpdateNonExistent() {
        User expectedUser = UserMock.nonExistentUser;
        assertFalse(userDAO.update(expectedUser));
    }

    @Test
    public void testDelete() {
        User user = UserMock.toCreateUser;
        user = userDAO.create(user);

        assertTrue(userDAO.delete(user));
    }

    @Test
    public void testDeleteNonexistent() {
        User user = UserMock.nonExistentUser;
        assertFalse(userDAO.delete(user));
    }
}
