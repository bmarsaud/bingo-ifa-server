package fr.bmarsaud.bingoifa.server.mock;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.bmarsaud.bingoifa.server.entity.Grid;

public class GridMock {
    public static List<Grid> grids = List.of(
            new Grid(1, Date.valueOf(LocalDate.of(2019, 1, 26)), false, new ArrayList<>(BoxMock.boxes)),
            new Grid(2, Date.valueOf(LocalDate.of(2019, 2, 1)), false, new ArrayList<>(BoxMock.toCreateBoxes))
    );

    public static Grid nonexistentGrid = new Grid();

    public static Grid toDeleteGrid = new Grid(Date.valueOf(LocalDate.now()), false, new ArrayList<>(BoxMock.toDeleteBoxes));
}