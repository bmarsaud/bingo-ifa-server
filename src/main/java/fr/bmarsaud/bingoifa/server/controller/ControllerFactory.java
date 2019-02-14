package fr.bmarsaud.bingoifa.server.controller;

public class ControllerFactory {
    private static GridController gridController;

    public static GridController getGridController() {
        if(gridController == null) gridController = new GridController();
        return gridController;
    }
}
