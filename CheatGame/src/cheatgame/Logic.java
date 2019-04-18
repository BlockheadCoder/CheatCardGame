package cheatgame;

import cheatgame.card.CheatCard;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Logic {

   private CheatGame ui;

   private GroupOfCards middleStack;

   private GroupOfCards currentHand;
   private ArrayList<GroupOfCards> otherHands;

   private int valueOn = 0;
   
   private CheatPlayer currentPlayer;
   private ArrayList<CheatPlayer> players;

   private Pane rootPane;

   private int numberOfPlayers;

   Logic(CheatGame ui, Pane rootPane, ArrayList<CheatPlayer> players) {
      this.ui = ui;
      this.rootPane = rootPane;
      this.numberOfPlayers = players.size();
      this.players = players; 
   }

   public void start() {
      createCardGroups();
      middleStack.drawCardsOnPane();
   }

   public void createCardGroups() {
      //Image names are {1-52}.png and backV.png

      String imagesPath = "file:./src/cardImages/";

      Image backImage = new Image(imagesPath + "backV.png");

      middleStack = new GroupOfCards(
            rootPane,
            rootPane.widthProperty().get() / 2,
            rootPane.heightProperty().get() / 2,
            2,
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

      middleStack.flip(false);

      currentHand = new GroupOfCards(rootPane, rootPane.widthProperty().get() / 2,
            rootPane.heightProperty().get() - backImage.getHeight(),
            25, true);

      otherHands = new ArrayList(numberOfPlayers);

      
      
      double distance = rootPane.widthProperty().get()/2-50;
      double cX = (rootPane.widthProperty().get()-backImage.getWidth())/2;
      double cY = rootPane.heightProperty().get()/2;
      double splitBy = 180 / (numberOfPlayers - 1);
      
      
      
      for(int i = 0; i < numberOfPlayers; i++){
         
         double angle = ((splitBy * i) + 180) * Math.PI / 180;
         double x = distance * Math.cos(angle) + cX;
         double y = distance * Math.sin(angle) + cY;
         
         otherHands.add(
               new GroupOfCards(
                     rootPane, 
                     x,
                     y,
                     4,
                     false
               )
         );
         
         players.get(i).addHand(otherHands.get(i));
         
      }
      
      
      
      
      int numPerHand = 52/players.size();
      
      for(int i = 0; i < players.size(); i++){
         middleStack.moveTo(players.get(i).getHand(), numPerHand);
      }
      
      for(int i = 0; i < 52-(numPerHand*players.size()); i++){
         middleStack.moveTo(players.get(i).getHand(), 1);
      }
      
      currentPlayer = players.get(0);
      currentPlayer.getHand().moveAllTo(currentHand);
      currentHand.flip(true);
      currentHand.refreshScreen();
      ui.changeWhosTurnItIs(players.get(0).getPlayerID(), Value.values()[valueOn]);
      valueOn+=1;
   }
   
   public void nextTurn(){
      int ind = players.indexOf(currentPlayer);
      currentHand.flip(false);
      currentHand.moveAllTo(players.get(ind).getHand());
      
      if(ind == players.size() - 1){
         ind = -1;
      }
      currentPlayer = players.get(ind+1);
      
      currentPlayer.getHand().moveAllTo(currentHand);
      
            currentHand.flip(true);
      currentHand.refreshScreen();
      
      
      
      ui.changeWhosTurnItIs(currentPlayer.getPlayerID(), Value.values()[valueOn]);
      
      if(valueOn == Value.values().length-1){
         valueOn = 0;
      }else{
         valueOn+=1;
      }
      
      
   }
   
   public void cheatCalled(){
      
   }

}
