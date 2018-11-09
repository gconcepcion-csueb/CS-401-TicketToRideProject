package view.console;


import java.util.ArrayList;

import model.*;


public class BoardView {
    private Board board;
    private ArrayList<RouteView> routeViews = new ArrayList<>();


    public void display() {
        System.out.println("BOARD:");
        for (RouteView routeView : routeViews) {
            routeView.display();
        }
    }


    public BoardView(Board board) {
        this.board = board;

        for (Route route : board.getRoutes()) {
            RouteView routeView = new RouteView(route);
            routeViews.add(routeView);
        }
    }
}