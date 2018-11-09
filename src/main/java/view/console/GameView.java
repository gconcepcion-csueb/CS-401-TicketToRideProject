package view.console;


import model.*;

import java.util.ArrayList;
import java.util.Scanner;

import controller.*;


public class GameView implements GameController {
    private Game game;

    private BoardView boardView;

    private Scanner stdin = new Scanner(System.in);


    @Override
    public boolean getTrainCarCardDeckToDrawFrom(Player player) {
        boolean stop = false;
        int deckInput = -1;
        boolean deckType = false;

        while (!stop) {
            System.out.println("Which deck do you want to draw from? (0 : Face-Down, 1 : Face-Up)");

            deckInput = stdin.nextInt();

            switch (deckInput) {
                case 0:
                    deckType = true;
                    stop = true;
                    break;
                case 1:
                    deckType = false;
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }

        return deckType;
    }


    @Override
    public int getTrainCarCardToTakeFromFaceUpDeck(Player player, ArrayList<TrainCarCard> faceUpDeck) {

        return -1;
    }


    @Override
    public boolean getContinueDrawingFromTrainCardDecks(Player player, int cardsLeftToDraw) {
        boolean stop = false;
        int continueInput = -1;
        boolean canContinue = false;

        while (!stop) {
            System.out.println("Continue drawing? (0 : Continue, 1 : Stop)");

            continueInput = stdin.nextInt();

            switch (continueInput) {
                case 0:
                    canContinue = true;
                    stop = true;
                    break;
                case 1:
                    canContinue = false;
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }


        return canContinue;
    }


    @Override
    public Route getRouteToClaim(Player player) {
        return null;
    }


    @Override
    public TrainCarType getTrainCarTypeForClaimRoute(Player player) {
        return null;
    }


    @Override
    public DestinationTicketCard [] getDestinationCardsToKeepAndReturn(Player player, DestinationTicketCard [] cards) {
        return null;
    }


    @Override
    public TurnType getTurnType(Player player) {
        boolean stop = false;
        int turnTypeInput = -1;
        TurnType turnType = TurnType.DrawTrainCarCards;

        while (!stop) {
            System.out.println("What do you want to do? (0 : DrawTrainCarCards, 1 : Claim Route, 2 : Draw Destination Ticket Cards)");

            turnTypeInput = stdin.nextInt();

            switch (turnTypeInput) {
                case 0:
                    turnType = TurnType.DrawTrainCarCards;
                    stop = true;
                    break;
                case 1:
                    turnType = TurnType.ClaimRoute;
                    stop = true;
                    break;
                case 2:
                    turnType = TurnType.DrawDestinationTicketCards;
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }

        return turnType;
    }


    public GameView() {
        this.game = new Game(this);
        this.boardView = new BoardView(this.game.getBoard());
    }


    public static void main(String[] args) {
        GameView gameView = new GameView();

        gameView.boardView.display();
    }
}