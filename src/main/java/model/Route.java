package model;


public class Route {
    private RouteColor color;
    private int numberOfCars;
    private City startCity;
    private City stopCity;
    private Player playerClaimed;
    
    
    public RouteColor getRouteColor() {
        return color;
    }


    public City getStartCity() {
        return startCity;
    }


    public City getStopCity() {
        return stopCity;
    }


    public City getConnectingCity(City firstCity) {
        if (firstCity == startCity)
            return stopCity;
        else if (firstCity == stopCity)
            return startCity;
        return null;
    }
    

    public int getNumberOfCars() {
        return numberOfCars;
    }
    
    
    public Player getPlayerClaimed() {
        return playerClaimed;
    }
    
    
    public void setPlayerClaimed(Player player) {
        playerClaimed = player;
    }


    public boolean isClaimed() {
        return playerClaimed != null;
    }


    public boolean isValidTrainCarType(TrainCarType type) {
        if (type == TrainCarType.WILDCARD || color == RouteColor.GREY)
            return true;

        return type.name() == color.name();
    }


    public boolean doesConnectCities(City city1, City city2) {
        return (startCity == city1 && stopCity == city2) || (startCity == city2 && stopCity == city1);
    }
    
    
    public Route(City startCity, City stopCity, int numberOfCars, RouteColor color) {
        this.startCity = startCity;
        this.stopCity = stopCity;
        this.numberOfCars = numberOfCars;
        this.color = color;
    }
}
