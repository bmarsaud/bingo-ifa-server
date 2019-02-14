package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.mock.BoxMock;
import fr.bmarsaud.bingoifa.server.mock.GridMock;
import fr.bmarsaud.bingoifa.server.mock.SentenceMock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BoxDAOTest {
    private BoxDAO boxDAO;

    @Before
    public void setup() {
        boxDAO = DAOFactory.getBoxDAO();
    }

    @Test
    public void testGetGridBoxes() {
        ArrayList<Box> expectedBoxes = new ArrayList<>(BoxMock.boxes);
        assertEquals(expectedBoxes, boxDAO.getGridBoxes(1));
    }

    @Test
    public void testGetNonexistentGridBoxes() {
        ArrayList<Box> expectedBoxes = new ArrayList<>();
        assertEquals(expectedBoxes, boxDAO.getGridBoxes(-1));
    }

    @Test
    public void testFind() {
        Box expectedBox = BoxMock.boxes.get(14);
        assertEquals(expectedBox, boxDAO.find(expectedBox.getId()));
    }

    @Test
    public  void testFindNonexistent() {
        Box expectedBox = BoxMock.nonexistentBox;
        assertNull(boxDAO.find(expectedBox.getId()));
    }

    @Test
    public void testCreateGridBox() {
        Box box = new Box(SentenceMock.sentences.get(0), 2, true, Time.valueOf(LocalTime.now()));
        box = boxDAO.createGridBox(box, GridMock.grids.get(1));

        assertEquals(box, boxDAO.find(box.getId()));
        boxDAO.delete(box);
    }

    @Test
    public void testUpdateBox() {
        Box box = BoxMock.toCreateBox;
        box = boxDAO.createGridBox(box, GridMock.grids.get(1));

        box.setSentence(SentenceMock.sentences.get(8));
        box.setChecked(true);

        boolean result = boxDAO.update(box);
        assertTrue(result);
        assertEquals(boxDAO.find(box.getId()), box);
        boxDAO.delete(box);
    }

    @Test
    public void testUpdateNonexistentBox() {
        Box box = BoxMock.nonexistentBox;
        box.setPosition(5);

        boolean result = boxDAO.update(box);
        assertFalse(result);
    }

    @Test
    public void testDeleteBox() {
        Box box = BoxMock.toCreateBox;
        box = boxDAO.createGridBox(box, GridMock.grids.get(1));

        boolean result = boxDAO.delete(box);
        assertTrue(result);
        assertNull(boxDAO.find(box.getId()));
    }

    @Test
    public void testDeleteNonexistentBox() {
        Box box = BoxMock.nonexistentBox;

        boolean result = boxDAO.delete(box);
        assertFalse(result);
    }
}
