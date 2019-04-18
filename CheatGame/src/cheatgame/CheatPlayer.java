package cheatgame;

import cheatgame.card.Card;
import cheatgame.card.GUICard;
import java.util.ArrayList;

public class CheatPlayer extends Player {

    private GroupOfCards hand;
    private Logic logicHandler;
    
    private ArrayList<GUICard> lastPlayed;
    
    public CheatPlayer(String name) {
        super(name);
        lastPlayed = new ArrayList();
    }

    public void addHand(GroupOfCards hnd) {
        hand = hnd;
        hand.setName(this.getPlayerID());
    }

    public GroupOfCards getHand() {
        return hand;
    }
    
    public void newTurn(){
        lastPlayed.clear();
    }
    
    public void played(ArrayList<GUICard> lastPlayed){
        this.lastPlayed = lastPlayed;
    }
    
    public ArrayList<GUICard> getPlayed(){
        return this.lastPlayed;
    }

}
