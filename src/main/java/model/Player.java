package model;


import java.util.ArrayList;

public class Player {
    private String name;
    private int points = 0;

    private ArrayList<TrainCarCard> trainCarCards;
    private ArrayList<DestinationTicketCard> destinationTicketCards;


    public String getName() {
        return name;
    }


    public int getPoints() {
        return points;
    }


    /**
     * @return the trainCarCards
     */
    public ArrayList<TrainCarCard> getTrainCarCards() {
        return trainCarCards;
    }


    /**
     * @return the destinationTicketCards
     */
    public ArrayList<DestinationTicketCard> getDestinationTicketCards() {
        return destinationTicketCards;
    }


    public void addPoints(int points) {
        this.points += points;
    }


    public void addDestinationTicketCard(DestinationTicketCard card) {
        destinationTicketCards.add(card);
    }


    public int getNumberOfTrainCarCardsOfType(TrainCarType type) {
        int numCards = 0;

        for (TrainCarCard card : trainCarCards) {
            if (card.getType() == type)
                numCards++;
        }

        return numCards;
    }

    // add/remove train car card
    public void addTrainCarCard(TrainCarCard card) {
        trainCarCards.add(card);
    }

    
    public void removeTrainCarCard(TrainCarCard card) {
        trainCarCards.remove(card);
    }


    public void removeNumberOfTrainCarCardsOfType(int numberOfCards, TrainCarType type) {
        for (TrainCarCard card : trainCarCards) {
            if (numberOfCards == 0)
                break;

            if (card.getType() == type) {
                trainCarCards.remove(card);
                numberOfCards--;
            }
        }
    }


    public Player(String name) {
        this.name = name;
    }
}
