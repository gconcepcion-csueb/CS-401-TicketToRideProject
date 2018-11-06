package model;


public class DestinationTicketCard {
    private City city1;
    private City city2;
    private int pointValue;

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getPointValue() {
        return pointValue;
    }

    public DestinationTicketCard(City city1, City city2, int pointValue) {
        this.city1 = city1;
        this.city2 = city2;
        this.pointValue = pointValue;
    }
}
