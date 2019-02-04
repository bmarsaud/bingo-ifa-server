package fr.bmarsaud.bingoifa.server.entity;

import java.sql.Timestamp;

public class HistoryLine {
    private int id;
    private Timestamp date;
    private Grid grid;
    private int rank;

    public HistoryLine(int id, Timestamp date, Grid grid, int rank) {
        this.id = id;
        date.setNanos(0);
        this.date = date;
        this.grid = grid;
        this.rank = rank;
    }

    public HistoryLine(Timestamp date, Grid grid, int rank) {
        this.id = -1;
        date.setNanos(0);
        this.date = date;
        this.grid = grid;
        this.rank = rank;
    }

    public HistoryLine() {
        id = -1;
        date = null;
        grid = null;
        rank = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        date.setNanos(0);
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

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof HistoryLine)) return false;
        if (obj == this) return true;

        HistoryLine historyLine = (HistoryLine) obj;
        return historyLine.getId() == id && historyLine.getDate().equals(date) && historyLine.getGrid().getId() == grid.getId() && historyLine.getRank() == rank;
    }

    public String toString() {
        return "HistoryLine{id=" + id + ", date=" + date + ", gridId=" + grid.getId() + ", rank=" + rank + "}" ;
    }
}
