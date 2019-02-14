package fr.bmarsaud.bingoifa.server.mock;

import java.util.List;

import fr.bmarsaud.bingoifa.server.entity.Sentence;

public class SentenceMock {
    public static List<Sentence> sentences = List.of(
        new Sentence(1, "First sentence", 0, 0, true),
        new Sentence(2, "Second sentence", 0, 0, true),
        new Sentence(3, "Third sentence", 0, 0, true),
        new Sentence(4, "Fourth sentence", 0, 0, true),
        new Sentence(5, "Fifth sentence", 0, 0, true),
        new Sentence(6, "Sixth sentence", 0, 0, true),
        new Sentence(7, "Seventh sentence", 0, 0, true),
        new Sentence(8, "Eighth sentence", 0, 0, true),
        new Sentence(9, "Ninth sentence", 0, 0, true),
        new Sentence(10, "Tenth sentence", 0, 0, true),
        new Sentence(11, "Eleventh sentence", 0, 0, true),
        new Sentence(12, "Twelfth sentence", 0, 0, true),
        new Sentence(13, "Thirteenth sentence", 0, 0, true),
        new Sentence(14, "Fourteenth sentence", 0, 0, true),
        new Sentence(15, "Fifteenth sentence", 0, 0, true),
        new Sentence(16, "Sixteenth sentence", 0, 0, true),
        new Sentence(17, "Seventeenth sentence", 0, 0, true)
    );

    public static Sentence dummySentence = new Sentence("Dummy text", 1, 1, false);
    public static Sentence nonexistentSentence = new Sentence(-1, "Sentence does not exists", 0, 0, false);
}
