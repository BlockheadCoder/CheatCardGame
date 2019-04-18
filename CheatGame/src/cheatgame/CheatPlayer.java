package cheatgame;

import cheatgame.card.GUICard;
import java.util.ArrayList;

/**
 * CheatPlayer extends Player. Adds some cheat specific methods.
 * 
 * @author Thomas Chapman 2019
 */
public class CheatPlayer extends Player {

    private GroupOfCards hand;
    private Logic logicHandler;
    
    private ArrayList<GUICard> lastPlayed;
    
    /**
     * Constructor
     * @param name Name of player
     */
    public CheatPlayer(String name) {
        super(name);
        lastPlayed = new ArrayList();
    }

    
    /**
     * Sets the GroupOfCards representing the players hand
     * 
     * @param hnd Hand of cards
     */
    public void addHand(GroupOfCards hnd) {
        hand = hnd;
        hand.setName(this.getPlayerID());
    }

    /**
     * Gets the hand of the player
     * @return Hand
     */
    public GroupOfCards getHand() {
        return hand;
    }

    /**
     * Last cards this player played
     * 
     * @param lastPlayed Card list
     */
    public void played(ArrayList<GUICard> lastPlayed){
        this.lastPlayed = lastPlayed;
    }
    
    /**
     * Gets last cards played 
     * @return cards played
     */
    public ArrayList<GUICard> getPlayed(){
        return this.lastPlayed;
    }

}
