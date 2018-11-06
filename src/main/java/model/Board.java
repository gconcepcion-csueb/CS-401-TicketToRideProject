package model;


import java.util.ArrayList;

public class Board {
    private ArrayList<Route> routes;    // ArrayList until I hardcode the size of the array


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


    private void addRoute(Route route) {
        routes.add(route);
    }

    
    private void setupDefaultBoard() {
        // default routes
        addRoute(new Route(City.San_Francisco, City.Los_Angeles, 3, RouteColor.YELLOW));
        addRoute(new Route(City.Los_Angeles, City.San_Francisco, 3, RouteColor.PURPLE));
        addRoute(new Route(City.San_Francisco, City.Portland, 5, RouteColor.GREEN));
        addRoute(new Route(City.Portland, City.San_Francisco, 5, RouteColor.PURPLE));
        addRoute(new Route(City.Portland, City.Seattle, 1, RouteColor.GREY));
        addRoute(new Route(City.Seattle, City.Portland, 1, RouteColor.GREY));
    }
    
    public Board() {
        setupDefaultBoard();
    }
}
