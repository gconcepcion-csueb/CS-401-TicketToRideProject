package model;


import java.util.ArrayList;

public class Player {
    private int points = 0;

    private ArrayList<TrainCarCard> trainCarCards;
    private ArrayList<DestinationTicketCard> destinationTicketCards;
    
    // add/remove train car card
    public void addTrainCarCard(TrainCarCard card) {
        trainCarCards.add(card);
    }
    
    public void removeTrainCarCard(TrainCarCard card) {
        trainCarCards.remove(card);
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


    public void addPoints(int points) {
        this.points += points;
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
}
