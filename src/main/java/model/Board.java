package model;


import java.util.ArrayList;

public class Board {
    private ArrayList<CityNode> cities; //
    private ArrayList<Route> routes;    // ArrayList until I hardcode the size of the array


    private CityNode getCityNode(City city) {
        for (CityNode cityNode : cities) {
            if (cityNode.getCity() == city)
                return cityNode;
        }

        return null;
    }


    public ArrayList<Route> getRoutesDirectlyConnectingCities(City city1, City city2) {
        ArrayList<Route> routesConnecting = new ArrayList<>();

        for (Route route : routes) {
            if (route.doesConnectCities(city1, city2))
                routesConnecting.add(route);
        }

        return routesConnecting;
    }


    public Route getRoute(City city1, City city2, RouteColor color) {
        for (Route route : routes) {
            if (route.doesConnectCities(city1, city2) && route.getRouteColor() == color)
                return route;
        }

        return null;
    }


    // look at dijkstra's algorithm
    private int doesPlayerConnectCities(Player player, City startCity, City endCity, City [] passedCities) {
        CityNode startCityNode = getCityNode(startCity);

        for (Route route : startCityNode.getRoutes()) {
            City connectingCity = route.getConnectingCity(startCity);

            // make sure connecting city isn't in passed Cities

            // get connecting cities
            if (route.getPlayerClaimed() == player) {
                if (connectingCity == endCity)
                    return 0;
            }
        }
    }


    public boolean hasPlayerConnectedCities(Player player, City city1, City city2) {

    }


    private void addRoute(Route route) {
        City startCity = route.getStartCity();
        City stopCity = route.getStopCity();

        CityNode startCityNode = getCityNode(startCity);
        CityNode stopCityNode = getCityNode(stopCity);

        if (startCityNode != null)
            startCityNode.addRoute(route);

        if (stopCityNode != null)
            stopCityNode.addRoute(route);

        routes.add(route);
    }

    
    private void setupDefaultBoard() {
        // default cities
        for (City cityName : City.values()) {
            CityNode cityNode = new CityNode(cityName);
            cities.add(cityNode);
        }

        // default routes
        addRoute(new Route(City.San_Francisco, City.Los_Angeles, 3, RouteColor.YELLOW));
        addRoute(new Route(City.Los_Angeles, City.San_Francisco, 3, RouteColor.PURPLE));
        addRoute(new Route(City.San_Francisco, City.Portland, 5, RouteColor.GREEN));
        addRoute(new Route(City.Portland, City.San_Francisco, 5, RouteColor.PURPLE));
        addRoute(new Route(City.Portland, City.Seattle, 1, RouteColor.GREY));
        addRoute(new Route(City.Seattle, City.Portland, 1, RouteColor.GREY));
    }
    
    public Board() {
        cityNodes = new ArrayList<CityNode>();
        setupDefaultBoard();
    }
}
