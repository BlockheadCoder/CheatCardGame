/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package cheatgame;

import cheatgame.card.CheatCard;
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
 */
public class GroupOfCards {

   //The group of cards, stored in an ArrayList
   private ArrayList<GUICard> cards;
   private Label numOfCards;
   private Label nameLabel;
   private boolean disableLabel;
   private double xPos;
   private double yPos;

   private final double spaceBetweenCards;
   Pane rootPane;

   public GroupOfCards(Pane rootPane, double x, double y, double spaceBetweenCards, boolean disableLabel) {
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
   }

   public void addCard(GUICard card) {
//      System.out.println("MyX POS IS : "+this.xPos);
      cards.add(card);
      refreshScreen();
   }

   public void setName(String name) {
      nameLabel.setText(name);
   }

   public void refreshScreen() {
      drawCardsOnPane();
      updateLabel();
   }

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

   public void moveAllTo(GroupOfCards goc) {
      int size = cards.size();
      for (int i = 0; i < size; i++) {
         System.out.println("I: "+i);
         goc.addCard(cards.get(0));
         cards.remove(cards.get(0));
      }
      drawCardsOnPane();
//      refreshScreen();
   }

   public void moveTo(GroupOfCards goc, ArrayList<Card> cardsToBeMoved) {

   }

   public void moveTo(GroupOfCards goc, int x) {
      for (int i = 0; i < x; i++) {
//         if(cards.isEmpty()){
//            return;
//         }
         goc.addCard(cards.get(0));
         cards.remove(0);
         refreshScreen();
      }
   }

   public void updateLabel() {
      if (!disableLabel) {
         numOfCards.setText("x" + cards.size());

         numOfCards.toFront();

         nameLabel.toFront();
         

//         System.out.println("Labels");
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

   public void drawCardsOnPane() {

//      System.out.println("Removed: "+rootPane.getChildren().removeAll(cards));
      cards.forEach((c) -> {
         rootPane.getChildren().remove(c.getImageView());
      });

//      int forLimit = (getSize() < 10 ? getSize() : 10);
      int forLimit = getSize();

//      System.out.println(rootPane.getWidth());
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

   public void flip(boolean flipped) {
      for (GUICard c : cards) {
         System.out.println("Flipping " + c);
         c.flip(flipped);
      }
   }

   public void shuffle() {
      Collections.shuffle(cards);
   }

   /**
    * @return the size of the group of cards
    */
   public int getSize() {
      return cards.size();
   }

   /**
    * @return the xPos
    */
   public double getxPos() {
      return xPos;
   }

   /**
    * @return the yPos
    */
   public double getyPos() {
      return yPos;
   }

   public void setxPos(int pos) {
      xPos = pos;
   }

   public void setyPos(int pos) {
      yPos = pos;
   }

}
