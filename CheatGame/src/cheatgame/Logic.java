package cheatgame;

import cheatgame.card.CheatCard;
import cheatgame.card.GUICard;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Logic class for the game
 * 
 * @author Thomas Chapman 2019
 */
public class Logic {

    
    //Some variables needed throughout
    private CheatGame ui;

    private GroupOfCards middleStack;

    private GroupOfCards currentHand;
    private ArrayList<GroupOfCards> otherHands;

    private int valueOn = 0;
    private int oldValue = 0;


    private CheatPlayer currentPlayer;
    private CheatPlayer lastPlayer;
    private ArrayList<CheatPlayer> players;

    private Pane rootPane;

    private int numberOfPlayers;

    /**
     * Constructor to setup.
     * 
     * @param ui Main object with the UI
     * @param rootPane Pane to draw all the cards on
     * @param players Players participating
     */
    Logic(CheatGame ui, Pane rootPane, ArrayList<CheatPlayer> players) {
        this.ui = ui;
        this.rootPane = rootPane;
        this.numberOfPlayers = players.size();
        this.players = players;
    }

    /**
     * Start the game
     */
    public void start() {
        createCardGroups();
        middleStack.drawCardsOnPane();
    }

    /**
     * Load the images, and initialize them into their correct positions
     */
    public void createCardGroups() {
        //Image names are {1-52}.png and backV.png

        String imagesPath = "file:./src/cardImages/";

        Image backImage = new Image(imagesPath + "backV.png");

        //Load everything into the middle stack
        middleStack = new GroupOfCards(
                rootPane,
                rootPane.widthProperty().get() / 2,
                rootPane.heightProperty().get() / 2,
                2,
                false,
                false);

        for (int i = 1; i <= 13; i++) {
            CheatCard newCard = new CheatCard(
                    new Image(imagesPath + String.format("%s.png", i)),
                    backImage, Suit.SPADES, Value.values()[i - 1]);
            middleStack.addCard(newCard);

        }

        for (int i = 14; i <= 26; i++) {
            CheatCard newCard = new CheatCard(
                    new Image(imagesPath + String.format("%s.png", i)),
                    backImage, Suit.HEARTS, Value.values()[i - 14]);

            middleStack.addCard(newCard);

        }

        for (int i = 27; i <= 39; i++) {
            CheatCard newCard = new CheatCard(
                    new Image(imagesPath + String.format("%s.png", i)),
                    backImage, Suit.DIAMONDS, Value.values()[i - 27]);

            middleStack.addCard(newCard);

        }

        for (int i = 40; i <= 52; i++) {
            CheatCard newCard = new CheatCard(
                    new Image(imagesPath + String.format("%s.png", i)),
                    backImage, Suit.CLUBS, Value.values()[i - 40]);
            middleStack.addCard(newCard);

        }
        
        
        //Flip all the cards as they all need to be face down when given to the players
        middleStack.flip(false);

        //This is the face up hand shown on the screen.
        currentHand = new GroupOfCards(rootPane, rootPane.widthProperty().get() / 2,
                rootPane.heightProperty().get() - backImage.getHeight(),
                25, true, true);

        
        otherHands = new ArrayList(numberOfPlayers);

        //Creates the other groups that are shown on the top of the screen.
        //They are layed out evenly accross the top
        for (int i = 0; i < numberOfPlayers; i++) {

            double x = (rootPane.widthProperty().get() / numberOfPlayers * i) + 10;
            double y = 100;

            otherHands.add(
                    new GroupOfCards(
                            rootPane,
                            x,
                            y,
                            4,
                            false,
                            false
                    )
            );

            players.get(i).addHand(otherHands.get(i));

        }

        //Shuffle the middle stack
        middleStack.shuffle();

        //Deal them out to each player
        int numPerHand = 52 / players.size();

        
        //Move # of cards to each player
        for (int i = 0; i < players.size(); i++) {
            middleStack.moveTo(players.get(i).getHand(), numPerHand);
        }

        //Move one of each left over to the first player, then second, etc
        for (int i = 0; i < 52 - (numPerHand * players.size()); i++) {
            middleStack.moveTo(players.get(i).getHand(), 1);
        }

        //Setup some of the variables and setup the UI
        currentPlayer = players.get(0);
        currentPlayer.getHand().moveAllTo(currentHand);
        currentHand.flip(true);
        currentHand.refreshScreen();
        ui.changeWhosTurnItIs(currentPlayer.getPlayerID(), Value.values()[valueOn], "", 0, null);
    }

    /**
     * Executes when the player clicks the "Play" button each turn.
     */
    public void nextTurn() {

        
        ArrayList<GUICard> activatedCards = new ArrayList();
        //Get cards the user is playing
        for (GUICard c : currentHand.showCards()) {
            if (c.isActivated()) {
                activatedCards.add(c);
            }
        }
        //If the user hasnt selected any cards then just return and they can keep playing.
        if (activatedCards.isEmpty()) {
            return;
        }
        //Otherwise set the current players last played cards for next turn
        currentPlayer.played(activatedCards);
        
        //If theres a win then the game is over. No need to continue
        if(checkForWin()){
            return;
        }

        //Moves the selected cards to the middle stack
        currentHand.moveTo(middleStack, activatedCards);

        
        //Flips the current players cards over and moves them back to their spot on the top
        int ind = players.indexOf(currentPlayer);
        currentHand.flip(false);
        currentHand.moveAllTo(players.get(ind).getHand());

        //Loopback on maxing out player size
        if (ind == players.size() - 1) {
            ind = -1;
        }
        
        //Setting up for next turn
        lastPlayer = currentPlayer;
        currentPlayer = players.get(ind + 1);
        currentPlayer.getHand().moveAllTo(currentHand);

        currentHand.flip(true);
        currentHand.refreshScreen();
        
        //Set up the next value. 
        oldValue = valueOn;
        
        //Loopback
        if (valueOn == Value.values().length - 1) {
            valueOn = 0;
        } else {
            valueOn += 1;
        }
        
        //Refresh the UI
        ui.changeWhosTurnItIs(currentPlayer.getPlayerID(), Value.values()[valueOn], lastPlayer.getPlayerID(), activatedCards.size(), Value.values()[oldValue]);
        middleStack.flip(false);
        middleStack.drawCardsOnPane();
    }
    
/**
 * Checks if the last player has won
 * @return True for win. false otherwise
 */
    public boolean checkForWin() {
        
        //First round call is ignored
        if(lastPlayer == null){
            return false;
        }
        
        //If player1's hand is empty, and it is about to become player3's turn,
        //Then player2 didnt press cheat, and player1 has won.
        if(lastPlayer.getHand().showCards().isEmpty()){
            
            //Sets UI stuff
            Label winner = new Label(lastPlayer.getPlayerID()+" has won the game");
            winner.setOpacity(1);
            winner.setFont(new Font(50));
            winner.setLayoutX(0);
            winner.setLayoutY(-75);
            winner.setPrefSize(800,800);
            rootPane.getChildren().add(winner);
            currentHand.flip(false);
            return true;
        }
        return false;
    }

    /**
     * Executes when the cheat button is pressed
     */
    public void cheatCalled() {
        boolean isCheating = false;
        
        //Checks the last player against the old values for cheating
        for(GUICard c : lastPlayer.getPlayed()){
            if(!c.isValue(Value.values()[oldValue])){
                isCheating = true;
                break;
            }
        }
        
        //Depending on the result, moves the cards as they should
        if(isCheating){
            middleStack.moveAllTo(lastPlayer.getHand());
            lastPlayer.getHand().refreshScreen();
            middleStack.refreshScreen();
        }else{
            middleStack.moveAllTo(currentHand);
            currentHand.flip(true);
            currentHand.refreshScreen();
            middleStack.refreshScreen();
            
        }
    }

}
