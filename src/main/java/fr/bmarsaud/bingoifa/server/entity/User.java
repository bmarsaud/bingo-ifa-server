package fr.bmarsaud.bingoifa.server.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private Grid grid;

    public User(int id, String login, String password, Grid grid) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.grid = grid;
    }

    public User() {
        id = -1;
        login = null;
        password = null;
        grid = null;
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
}
