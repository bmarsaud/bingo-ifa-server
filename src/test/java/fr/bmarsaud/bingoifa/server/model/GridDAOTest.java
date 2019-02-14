package fr.bmarsaud.bingoifa.server.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.mock.BoxMock;
import fr.bmarsaud.bingoifa.server.mock.GridMock;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridDAOTest {
    private GridDAO gridDAO;

    @Before
    public void setup() {
        gridDAO = DAOFactory.getGridDAO();
    }

    @Test
    public void testFind() {
        Grid expectedGrid = GridMock.grids.get(0);
        assertEquals(expectedGrid, gridDAO.find(expectedGrid.getId()));
    }

    @Test
    public void testFindNonexistent() {
        assertNull(gridDAO.find(-1));
    }

    @Test
    public void testCreate() {
        Grid expectedGrid = new Grid(Date.valueOf(LocalDate.of(2019, 02, 1)), false, new ArrayList<>(BoxMock.toCreateBoxes));
        expectedGrid = gridDAO.create(expectedGrid);
        assertEquals(expectedGrid, gridDAO.find(expectedGrid.getId()));
    }

    @Test
    public void testUpdate() {
        Grid expectedGrid = new Grid(Date.valueOf(LocalDate.now()), false, new ArrayList<>());
        expectedGrid = gridDAO.create(expectedGrid);

        expectedGrid.setShuffled(true);

        boolean result = gridDAO.update(expectedGrid);
        assertTrue(result);
        assertEquals(expectedGrid, gridDAO.find(expectedGrid.getId()));
    }

    @Test
    public void testUpdateNonexistent() {
        Grid expectedGrid = GridMock.nonexistentGrid;
        assertFalse(gridDAO.update(expectedGrid));
    }

    @Test
    public void testDelete() {
        Grid grid = GridMock.toDeleteGrid;
        grid = gridDAO.create(grid);

        boolean result = gridDAO.delete(grid);
        assertTrue(result);
        assertNull(gridDAO.find(grid.getId()));
    }

    @Test
    public void testDeleteNonexistent() {
        Grid grid = GridMock.nonexistentGrid;
        assertFalse(gridDAO.delete(grid));
    }
}