package cheatgame;

import cheatgame.card.Card;
import java.util.ArrayList;

public class CheatPlayer extends Player {

	private GroupOfCards hand;
	private Logic logicHandler;

   public CheatPlayer(String name) {
      super(name);
   }
   
   public void addHand(GroupOfCards hnd){
      hand=hnd;
      hand.setName(this.getPlayerID());
   }

	public GroupOfCards getHand(){
      return hand;
   }

}