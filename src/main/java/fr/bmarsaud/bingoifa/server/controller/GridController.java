package fr.bmarsaud.bingoifa.server.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import fr.bmarsaud.bingoifa.server.entity.Box;
import fr.bmarsaud.bingoifa.server.entity.Grid;
import fr.bmarsaud.bingoifa.server.entity.Sentence;
import fr.bmarsaud.bingoifa.server.model.DAOFactory;
import fr.bmarsaud.bingoifa.server.model.GridDAO;
import fr.bmarsaud.bingoifa.server.model.SentenceDAO;

public class GridController {
    private SentenceDAO sentenceDAO;
    private GridDAO gridDAO;

    public GridController() {
        sentenceDAO = DAOFactory.getSentenceDAO();
        gridDAO = DAOFactory.getGridDAO();
    }

    public Grid generateNewGrid() {
        Random random = new Random();

        ArrayList<Sentence> sentences = new ArrayList<>(sentenceDAO.findAll().values());
        ArrayList<Box> boxes = new ArrayList<>();

        for(int i = 0; i < 16; i++) {
            Sentence sentence;
            do {
                sentence = sentences.get(random.nextInt(sentences.size()));
            } while(!sentence.isActivated());
            sentences.remove(sentence);

            boxes.add(new Box(sentence, i, false, null));
        }

        Date date = Date.valueOf(LocalDate.now());
        Grid grid = new Grid(date, false, boxes);
        grid = gridDAO.create(grid);

       return grid;
    }

    public void shuffleGrid(Grid grid, int boxPosition) {
        Random random = new Random();

        ArrayList<Sentence> sentences = new ArrayList<>(sentenceDAO.findAll().values());
        sentences.remove(grid.getBoxes().get(boxPosition).getSentence());

        Sentence newSentence = sentences.get(random.nextInt(sentences.size()));

        grid.getBoxes().get(boxPosition).setSentence(newSentence);
        grid.setShuffled(true);

        gridDAO.update(grid);
    }
}
