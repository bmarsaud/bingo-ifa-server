package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.mock.BoxMock;

import static org.junit.Assert.assertEquals;

public class BoxDAOTest {
    private BoxDAO boxDAO;
    private ArrayList<Box> expectedBoxes;

    @Before
    public void setup() {
        boxDAO = new BoxDAO();
        expectedBoxes = new ArrayList<>(BoxMock.boxes);
    }

    @Test
    public void testGridBoxes() {
        ArrayList<Box> boxes = boxDAO.getGridBoxes(1);
        assertEquals(boxes, expectedBoxes);
    }
}
