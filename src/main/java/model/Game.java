package model;


import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

import controller.GameController;


public class Game {
    private final int NUMBER_OF_TRAIN_CARDS_PER_TYPE = 12;
    private final int NUMBER_OF_WILDCARDS = 14;

    private final int MAX_TRAIN_CAR_CARDS_TO_DRAW = 2;
    private final int MAX_DESTINATION_CARDS_TO_DRAW = 3;
    
    private Board board;
    private ArrayList<Player> players;
    
    private ArrayList<TrainCarCard> trainCarCards;
    private ArrayList<DestinationTicketCard> destinationTicketCards;
    
    private ArrayDeque<TrainCarCard> faceDownTrainCarCardDeck;
    private ArrayList<TrainCarCard> faceUpTrainCarCardDeck;
    
    private ArrayDeque<DestinationTicketCard> destinationTicketCardDeck;

    private GameController gameController;

    private int currentPlayer = 0;  // current player whose turn it is
    private boolean isPlaying = false;


    /**
     * 
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Calculates points
     * @param n number of train cars
     * @return points
     */
    private int calculatePoints(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            default:
                return 0;
        }
    }


    public TrainCarCard playerDrawTrainCarCardFromFaceDownDeck(Player player) {
        if (faceDownTrainCarCardDeck.isEmpty())
            return null;

        TrainCarCard cardDrawn = faceDownTrainCarCardDeck.pop();
        player.addTrainCarCard(cardDrawn);

        return cardDrawn;
    }


    public TrainCarCard playerDrawTrainCarCardFromFaceUpDeck(Player player, int cardIndex) {
        if (faceUpTrainCarCardDeck.isEmpty())
            return null;

        TrainCarCard cardDrawn = faceUpTrainCarCardDeck.get(cardIndex);

        if (cardDrawn == null)
            return null;

        faceUpTrainCarCardDeck.remove(cardIndex);       // remove selected card
        player.addTrainCarCard(cardDrawn);              // give card to player

        if (!faceDownTrainCarCardDeck.isEmpty()) {      // replace the card
            TrainCarCard replacementCard = faceDownTrainCarCardDeck.pop();  // get a replacement card
            faceUpTrainCarCardDeck.add(replacementCard);                    // add card back to the face up deck
        }

        return cardDrawn;
    }


    public void turnDrawTrainCarCardsFromFaceUpDeck(Player player) {
        
    }


    public void turnDrawTrainCarCards(Player player) {
        int cardsDrawn = 0;
        boolean stopDrawingCards = false;

        while (cardsDrawn < MAX_TRAIN_CAR_CARDS_TO_DRAW && !stopDrawingCards) {
            boolean deckToDraw = gameController.getTrainCarCardDeckToDrawFrom(player);


            cardsDrawn++;
        }
    }


    public boolean playerClaimRoute(Player player, City city1, City city2, RouteColor routeColor, TrainCarType trainCarType) {
        // check if route is valid
        Route routeToClaim = board.getRoute(city1, city2, routeColor);

        if (routeToClaim == null)
            return false;

        // check if route is claimed
        if (routeToClaim.isClaimed())
            return false;

        // check if train car type can be used
        if (!routeToClaim.isValidTrainCarType(trainCarType))
            return false;

        int numberOfCarsToClaim = routeToClaim.getNumberOfCars();
        // check if player has enough train car cards
        if (player.getNumberOfTrainCarCardsOfType(trainCarType) < numberOfCarsToClaim)
            return false;

        // claim
        int pointsEarned = calculatePoints(numberOfCarsToClaim);
        routeToClaim.setPlayerClaimed(player);
        player.removeNumberOfTrainCarCardsOfType(numberOfCarsToClaim, trainCarType);    // remove cards from player
        player.addPoints(pointsEarned);

        return true;
    }


    public void turnClaimRoute(Player player) {

    }


    public DestinationTicketCard [] drawDestinationCards() {
        DestinationTicketCard [] cardsToReturn = new DestinationTicketCard[MAX_DESTINATION_CARDS_TO_DRAW];

        for (int i = 0; i < MAX_DESTINATION_CARDS_TO_DRAW; i++) {
            if (destinationTicketCardDeck.isEmpty())
                break;
            DestinationTicketCard cardDrawn = destinationTicketCardDeck.pop();
            cardsToReturn[i] = cardDrawn;
        }

        return cardsToReturn;
    }


    public void playerKeepAndReturnDestinationCards(Player player, DestinationTicketCard [] cardsToKeep, DestinationTicketCard [] cardsToReturn) {
        for (DestinationTicketCard cardToReturn : cardsToReturn)
            destinationTicketCardDeck.addLast(cardToReturn);    // add to bottom of the deck

        for (DestinationTicketCard cardToKeep : cardsToKeep)
            player.addDestinationTicketCard(cardToKeep);
    }


    /**
     * 
     * @param player
     */
    public void turnDrawDestinationCards(Player player) {
        DestinationTicketCard [] cards = drawDestinationCards();

        // show player the cards they have drawn (through the view)
        // get the cards the player wants to keep and return (through the controller)
        DestinationTicketCard [] cardsToReturn = gameController.getDestinationCardsToKeepAndReturn(player, cards);
        DestinationTicketCard [] cardsToKeep = new DestinationTicketCard[MAX_DESTINATION_CARDS_TO_DRAW - 1];

        // keep and return cards
        playerKeepAndReturnDestinationCards(player, cardsToKeep, cardsToReturn);
    }


    /**
     * 
     * @param player
     */
    public void takeTurnForPlayer(Player player) {
        // get the type of turn from the player (through the controller?)
        TurnType turnType = gameController.getTurnType(player);

        switch (turnType) {
            case DrawTrainCarCards:
                turnDrawTrainCarCards(player);
                break;
            case ClaimRoute:
                turnClaimRoute(player);
                break;
            case DrawDestinationTicketCards:
                turnDrawDestinationCards(player);
                break;
        }
    }


    public void addPlayer(String name) {
        Player newPlayer = new Player(name);
        players.add(newPlayer);
    }


    // setup Game class
    private void createAndAddTrainCarCards() {
        for (TrainCarType type : TrainCarType.values()) {
            int numCards = (type == TrainCarType.WILDCARD) ?
                    NUMBER_OF_WILDCARDS : NUMBER_OF_TRAIN_CARDS_PER_TYPE;

            for (int i = 0; i < numCards; i++) {
                TrainCarCard card = new TrainCarCard(type);
                trainCarCards.add(card);
            }
        }
    }


    private void createAndAddDestinationTicketCard(City city1, City city2, int pointValue) {
        DestinationTicketCard card = new DestinationTicketCard(city1, city2, pointValue);
        destinationTicketCards.add(card);
    }


    private void createAndAddDestinationTicketCards() {
        // these rely on the cities that are on the board
        createAndAddDestinationTicketCard(City.Los_Angeles, City.New_York_City, 100);
        createAndAddDestinationTicketCard(City.San_Francisco, City.Miami, 150);
        createAndAddDestinationTicketCard(City.Saint_Louis, City.Montreal, 75);
        createAndAddDestinationTicketCard(City.Seattle, City.Kansas_City, 100);
    }


    private void setupTrainCarCardDecks() {
        faceDownTrainCarCardDeck.clear();
        faceUpTrainCarCardDeck.clear();

        ArrayList<TrainCarCard> tempDeck = new ArrayList<>(trainCarCards);
        Collections.shuffle(tempDeck);

        faceDownTrainCarCardDeck.addAll(tempDeck);
    }


    private void setupDestinationTicketCardDeck() {
        destinationTicketCardDeck.clear();

        ArrayList<DestinationTicketCard> tempDeck = new ArrayList<>(destinationTicketCards);
        Collections.shuffle(tempDeck);

        destinationTicketCardDeck.addAll(tempDeck);
    }
    
    
    public Game(GameController gameController) {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.trainCarCards = new ArrayList<>();
        this.destinationTicketCards = new ArrayList<>();

        this.gameController = gameController;

        faceDownTrainCarCardDeck = new ArrayDeque<>();
        faceUpTrainCarCardDeck = new ArrayList<>();

        destinationTicketCardDeck = new ArrayDeque<>();
        
        createAndAddTrainCarCards();
        setupTrainCarCardDecks();
        createAndAddDestinationTicketCards();
        setupDestinationTicketCardDeck();
    }
}
