package controller;


import java.util.ArrayList;

import model.*;

public interface GameController {
    public abstract boolean getTrainCarCardDeckToDrawFrom(Player player);
    public abstract int getTrainCarCardToTakeFromFaceUpDeck(Player player, ArrayList<TrainCarCard> faceUpDeck);
    public abstract boolean getContinueDrawingFromTrainCardDecks(Player player, int cardsLeftToDraw);

    public abstract Route getRouteToClaim(Player player);
    public abstract TrainCarType getTrainCarTypeForClaimRoute(Player player);

    public abstract DestinationTicketCard [] getDestinationCardsToKeepAndReturn(Player player, DestinationTicketCard [] cards);

    public abstract TurnType getTurnType(Player player);
}