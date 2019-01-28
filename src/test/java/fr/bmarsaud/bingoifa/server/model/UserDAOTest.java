package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import fr.bmarsaud.bingoifa.server.entity.User;

public class UserDAOTest {
    private User expectedUser;
    private UserDAO userDAO;

    @Before
    public void setup() {
        expectedUser = new User(1, "bmarsaud", "bmarsaud", null);
        userDAO = new UserDAO();
    }

    @Test
    public void testGetUserByName() {
        User user = userDAO.findFromName("bmarsaud");
        assertEquals(user, expectedUser);
    }
}
