package view.console;

import model.*;

/**
 * RouteView
 */
public class RouteView {
    private Route route;
    
    public void display() {
        City startCity = route.getStartCity();
        City stopCity = route.getStopCity();
        int numberOfCars = route.getNumberOfCars();
        Player playerClaimed = route.getPlayerClaimed();
        boolean isClaimed = route.isClaimed();

        StringBuilder routeString = new StringBuilder();
        String playerClaimedName;

        for (int i = 0; i < numberOfCars; i++) {
            char claimedChar = isClaimed ? 'X' : '_';
            routeString.append("[" + claimedChar + "]");
        }

        if (playerClaimed != null)
            playerClaimedName = playerClaimed.getName();
        else
            playerClaimedName = "NONE";

        System.out.println(startCity.name() + " " + routeString + " " + stopCity.name() + " : " + playerClaimedName);
    }


    public RouteView(Route route) {
        this.route = route;
    }
}