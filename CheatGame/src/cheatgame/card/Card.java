/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package cheatgame.card;

import cheatgame.Suit;
import cheatgame.Value;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the code 
 * should remember to add themselves as a modifier.
 * @author dancye, 2018
 * @modified Thomas Chapman 2019
 */
public abstract class Card 
{
    //default modifier for child classes
    
    /**
     * Students should implement this method for their specific children classes 
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
   
   final Suit SUIT;
   final Value VALUE;
    
   public Card(Suit suit, Value value){
      this.SUIT = suit;
      this.VALUE = value;
   }
   
   public Suit getSuit(){
       return SUIT;
   }
   public Value getValue(){
       return VALUE;
   }
   
   public abstract boolean isValue(Value value);
   
    @Override
    public abstract String toString();
    
}
