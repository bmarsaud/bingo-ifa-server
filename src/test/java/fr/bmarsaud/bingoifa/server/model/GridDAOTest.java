package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.mock.BoxMock;

import static org.junit.Assert.assertEquals;

public class GridDAOTest {
    private Grid expectedGrid;
    private GridDAO gridDAO;

    @Before
    public void setup() {
        ArrayList<Box> boxes = new ArrayList<>(BoxMock.boxes);
        expectedGrid = new Grid(1, Date.valueOf(LocalDate.of(2019, 1, 26)), false, boxes);
        gridDAO = new GridDAO();
    }

    @Test
    public void testFind() {
        Grid grid = gridDAO.find(1);
        assertEquals(grid, expectedGrid);
    }
}
