package fr.bmarsaud.bingoifa.server.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;
    private Grid grid;
    private Timestamp lastRequest;
    private ArrayList<HistoryLine> history;

    public User(int id, String login, String password, Grid grid, Timestamp lastRequest, ArrayList<HistoryLine> history) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.grid = grid;
        this.lastRequest = lastRequest;
        this.history = history;
    }

    public User(String login, String password, Grid grid, Timestamp lastRequest, ArrayList<HistoryLine> history) {
        this.id = -1;
        this.login = login;
        this.password = password;
        this.grid = grid;
        this.lastRequest = lastRequest;
        this.history = history;
    }

    public User() {
        id = -1;
        login = null;
        password = null;
        grid = null;
        history = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Timestamp getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(Timestamp lastRequest) {
        this.lastRequest = lastRequest;
    }

    public ArrayList<HistoryLine> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<HistoryLine> history) {
        this.history = history;
    }

    public String toString() {
        return "User{id=" + id + ", login=" + login + ", password=" + password + ", grid=" + grid + ", lastRequest=" + lastRequest +", history=" + history + "}";
    }

    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof User)) return false;
        if(obj == this) return true;

        User user = (User) obj;
        return user.getId() == id && user.getLogin().equals(login) && user.getPassword().equals(password) && (user.getGrid() == null && grid == null || user.getGrid().equals(grid)) && user.getHistory().equals(history);
    }
}
