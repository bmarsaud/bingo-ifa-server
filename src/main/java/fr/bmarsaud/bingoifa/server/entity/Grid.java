package fr.bmarsaud.bingoifa.server.entity;

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

    public Grid() {
        id = -1;
        date = null;
        shuffled = false;
        boxes = null;
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

    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Grid)) return false;
        if(obj == this) return true;

        Grid grid = (Grid) obj;
        return grid.getId() == id && grid.getDate().equals(date) && grid.getBoxes().equals(boxes);
    }
}
