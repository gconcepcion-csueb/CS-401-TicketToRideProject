package model;

import java.util.ArrayList;

public class CityNode {
    private City city;
    private ArrayList<Route> routes;

    public City getCity() {
        return city;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public CityNode(City city) {
        this.city = city;
        this.routes = new ArrayList<>();
    }
}
