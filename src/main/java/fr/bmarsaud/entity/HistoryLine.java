package fr.bmarsaud.entity;

import java.sql.Date;

public class HistoryLine {
    private int id;
    private Date date;
    private Grid grid;
    private int rank;

    public HistoryLine(int id, Date date, Grid grid, int rank) {
        this.id = id;
        this.date = date;
        this.grid = grid;
        this.rank = rank;
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

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
