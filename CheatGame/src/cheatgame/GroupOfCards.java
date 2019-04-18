/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package cheatgame;

import cheatgame.card.Card;
import cheatgame.card.GUICard;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you
 * might want to subclass this more than once. The group of cards has a maximum
 * size attribute which is flexible for reuse.
 *
 * @author dancye
 * @modified Thomas Chapman April 18 2019
 *
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<GUICard> cards;
    private Label numOfCards;
    private Label nameLabel;
    private boolean disableLabel;
    private double xPos;
    private double yPos;
    private boolean shouldSort = true;

    private final double spaceBetweenCards;
    Pane rootPane;

    /**
     * Constructor for GroupOfCards
     *
     *
     * @param rootPane Parent pane for all the ImageViews
     * @param x X position of the hand on the pane
     * @param y Y position of the hand on the pane
     * @param spaceBetweenCards Distance between cards
     * @param disableLabel Certain groups of cards need the label disabled
     * @param shouldSort Certain groups of cards need their cards sorted
     */
    public GroupOfCards(Pane rootPane, double x, double y,
            double spaceBetweenCards, boolean disableLabel, boolean shouldSort) {
        this.rootPane = rootPane;
        this.xPos = x;
        this.yPos = y;
        cards = new ArrayList();
        this.spaceBetweenCards = spaceBetweenCards;
        this.disableLabel = disableLabel;
        if (!disableLabel) {
            numOfCards = new Label();
            nameLabel = new Label();

            numOfCards.setLayoutX(xPos);
            numOfCards.setLayoutY(yPos - 20);
            numOfCards.setFont(new Font(30));

            nameLabel.setLayoutX(xPos);
            nameLabel.setLayoutY(yPos - 80);
            nameLabel.setFont(new Font(20));
            updateLabel();
        }
        this.shouldSort = shouldSort;
    }
    
    /**
     * Adds card to the hand
     * 
     * @param card card to be added
     */
    public void addCard(GUICard card) {
        cards.add(card);
        refreshScreen();
    }

    /**
     * Sets the name of the hold for the label
     * @param name 
     */
    public void setName(String name) {
        nameLabel.setText(name);
    }
    
    /**
     * Calls the correct methods to refresh the gui elements
     */
    public void refreshScreen() {
        if (shouldSort) {
            sort();
        }

        drawCardsOnPane();
        updateLabel();
    }

    /**
     * Remove specific card from the Group
     * 
     * @param suit Suit to match
     * @param value Value to match
     * @return Card that was removed
     */
    public Card removeCard(Suit suit, Value value) {
        for (GUICard c : cards) {
            if (c.isSuitAndValue(suit, value)) {
                cards.remove(c);
                refreshScreen();
                return c;
            }

        }
        return null;
    }

    /**
     * Moves all cards in this group to GOC
     * 
     * @param goc Target group
     */
    public void moveAllTo(GroupOfCards goc) {
        int size = cards.size();
        for (int i = 0; i < size; i++) {
            System.out.println("I: " + i);
            goc.addCard(cards.get(0));
            cards.remove(cards.get(0));
        }
        //Refresh specific elements of the GUI for both groups
        goc.drawCardsOnPane();
        drawCardsOnPane();
//      refreshScreen();
    }

    /**
     * Move specific cards to another group
     * 
     * @param goc target
     * @param cardsToBeMoved GUICards to be moved
     */
    public void moveTo(GroupOfCards goc, ArrayList<GUICard> cardsToBeMoved) {
        for (GUICard c : cardsToBeMoved) {
            goc.addCard(c);
            cards.remove(c);

        }
        refreshScreen();
        goc.refreshScreen();
    }

    /**
     * Moves first X elements out of this group into GOC
     * @param goc target Group
     * @param x number of cards to move
     */
    public void moveTo(GroupOfCards goc, int x) {
        for (int i = 0; i < x; i++) {
            goc.addCard(cards.get(0));
            cards.remove(0);
            refreshScreen();
        }
    }

    /**
     * Updates labels text
     */
    public void updateLabel() {
        if (!disableLabel) {
            numOfCards.setText("x" + cards.size());

            numOfCards.toFront();

            nameLabel.toFront();
        }
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<GUICard> showCards() {
        return cards;
    }

    /**
     * Sorts the current group of cards and refreshes GUI
     */
    public void sort() {
        this.cards.sort((GUICard o1, GUICard o2) -> (o1.getValue().compareTo(o2.getValue())));
        drawCardsOnPane();
    }

    /**
     * Moves and aligns each card in this group to where they are supposed to be.
     */
    public void drawCardsOnPane() {
        
        //Delete all old cards on the rootPane
        cards.forEach((c) -> {
            rootPane.getChildren().remove(c.getImageView());
        });

        int forLimit = getSize();

        //Add imageView again. Redraw because each card may be flipped, meaning a new image view
        for (int i = 0; i < forLimit; i++) {
            rootPane.getChildren().add(cards.get(i).getImageView());

            cards.get(i).getImageView().setLayoutX(
                    xPos
                    + (spaceBetweenCards * (i - forLimit / 2))
            );

            cards.get(i).getImageView().setLayoutY(
                    yPos - cards.get(i).getImageView().getImage().getHeight() / 2);
        }

        
        if (!disableLabel) {
            if (!rootPane.getChildren().contains(numOfCards)) {
                rootPane.getChildren().add(numOfCards);
            }
            if (!rootPane.getChildren().contains(nameLabel)) {
                rootPane.getChildren().add(nameLabel);
            }
            updateLabel();
        }
    }

    /**
     * Flips all cards from front to back
     * 
     * @param frontFacing if false then flip to back
     */
    public void flip(boolean frontFacing) {
        for (GUICard c : cards) {
            System.out.println("Flipping " + c);
            c.flip(frontFacing);
        }
    }

    /**
     * Shuffles group of cards
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return cards.size();
    }

    

}
