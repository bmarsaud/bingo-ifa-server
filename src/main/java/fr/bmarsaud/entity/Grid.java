package fr.bmarsaud.entity;

import java.sql.Date;
import java.util.ArrayList;

public class Grid {
    private int id;
    private Date date;
    private boolean shuffled;
    private ArrayList<Box> boxes;

    public Grid(int id, Date date, boolean shuffled, ArrayList<Box> boxes) {
        this.id = id;
        this.date = date;
        this.shuffled = shuffled;
        this.boxes = boxes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }
}
