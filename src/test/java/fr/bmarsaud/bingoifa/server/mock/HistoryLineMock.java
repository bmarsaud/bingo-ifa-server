package fr.bmarsaud.bingoifa.server.mock;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import fr.bmarsaud.bingoifa.server.entity.HistoryLine;

public class HistoryLineMock {
    public static final List<HistoryLine> history = List.of(
      new HistoryLine(1, Timestamp.valueOf(LocalDateTime.of(2019, 2, 5, 12, 15, 35)), GridMock.grids.get(0), 1),
      new HistoryLine(2, Timestamp.valueOf(LocalDateTime.of(2019, 1, 18, 16, 16, 5)), GridMock.grids.get(0), 8)
    );

    public static final HistoryLine nonExistentHistoryLine = new HistoryLine();

    public static final HistoryLine toCreateHistoryLine = new HistoryLine(Timestamp.from(Instant.now()), GridMock.grids.get(1), 10);
}
