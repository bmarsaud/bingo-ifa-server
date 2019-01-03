package fr.bmarsaud.bingoifa.server.entity;

import java.util.ArrayList;

public class History {
    private User user;
    private ArrayList<HistoryLine> history;

    public History(User user, ArrayList<HistoryLine> history) {
        this.user = user;
        this.history = history;
    }

    public History() {
        user = null;
        history = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<HistoryLine> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<HistoryLine> history) {
        this.history = history;
    }
}
