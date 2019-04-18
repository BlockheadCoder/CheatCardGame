/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheatgame.card;

import cheatgame.Suit;
import cheatgame.Value;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author thomas
 */
public class CheatCard extends GUICard{
   
   

   public CheatCard(Image frontFace, Image backFace, Suit suit, Value value){
      super(frontFace, backFace, suit, value);
      
      this.frontFace.setOnMousePressed(e -> {
         onMousePressed();
      });
      
   }
   
   @Override
   public boolean isSuitAndValue(Suit suit, Value value){
      return (suit == this.SUIT && value == this.VALUE);
   }
   
   
   
   public ImageView getImageView() {
      return toBeShown;
   }
   
   public void flip(boolean frontFacing){
      this.frontFacing = frontFacing;
      
      if(frontFacing){
         toBeShown = frontFace;
      }else{
         toBeShown = backFace;
      }
   }
   
   public void flip(){
      frontFacing = !frontFacing;
      if(frontFacing){
         toBeShown = frontFace;
      }else{
         toBeShown = backFace;
      }
   }
   
   @Override
   public String toString() {
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      return(this.SUIT + ": "+this.VALUE);
   }

   @Override
   public void onMousePressed() {
      int diff = -40;
      if(activated){
         diff = 40;
      }
      this.frontFace.setLayoutY(this.frontFace.getLayoutY() + diff);
      activated = !activated;
   }
   
   
}
