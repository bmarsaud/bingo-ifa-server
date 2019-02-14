package fr.bmarsaud.bingoifa.server.model;

public class DAOFactory {
    private static UserDAO userDAO;
    private static GridDAO gridDAO;
    private static BoxDAO boxDAO;
    private static SentenceDAO sentenceDAO;
    private static HistoryLineDAO historyLineDAO;

    public static UserDAO getUserDAO() {
        if(userDAO == null) userDAO = new UserDAO();
        return userDAO;
    }

    public static GridDAO getGridDAO() {
        if(gridDAO == null) gridDAO = new GridDAO();
        return gridDAO;
    }

    public static BoxDAO getBoxDAO() {
        if(boxDAO == null) boxDAO = new BoxDAO();
        return boxDAO;
    }

    public static SentenceDAO getSentenceDAO() {
        if(sentenceDAO == null) sentenceDAO = new SentenceDAO();
        return sentenceDAO;
    }

    public static HistoryLineDAO getHistoryLineDAO() {
        if(historyLineDAO == null) historyLineDAO = new HistoryLineDAO();
        return historyLineDAO;
    }
}
