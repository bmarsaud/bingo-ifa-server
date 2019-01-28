package fr.bmarsaud.bingoifa.server.mock;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import fr.bmarsaud.bingoifa.server.entity.Box;

public class BoxMock {
    public static List<Box> boxes = List.of(
        new Box(1, SentenceMock.sentences.get(0), 1, false, null),
        new Box(2, SentenceMock.sentences.get(1), 2, false, null),
        new Box(3, SentenceMock.sentences.get(2), 3, false, null),
        new Box(4, SentenceMock.sentences.get(3), 4, true, Time.valueOf(LocalTime.of(12, 34, 2))),
        new Box(5, SentenceMock.sentences.get(4), 5, false, null),
        new Box(6, SentenceMock.sentences.get(5), 6, false, null),
        new Box(7, SentenceMock.sentences.get(6), 7, true, Time.valueOf(LocalTime.of(8, 21, 57))),
        new Box(8, SentenceMock.sentences.get(7), 8, false, null),
        new Box(9, SentenceMock.sentences.get(8), 9, false, null),
        new Box(10, SentenceMock.sentences.get(9), 10, false, null),
        new Box(11, SentenceMock.sentences.get(10), 11, true, Time.valueOf(LocalTime.of(9, 2, 28))),
        new Box(12, SentenceMock.sentences.get(11), 12, true, Time.valueOf(LocalTime.of(17, 38, 46))),
        new Box(13, SentenceMock.sentences.get(12), 13, false, null),
        new Box(14, SentenceMock.sentences.get(13), 14, false, null),
        new Box(15, SentenceMock.sentences.get(14), 15, false, null),
        new Box(16, SentenceMock.sentences.get(15), 16, false, null)
    );
}
