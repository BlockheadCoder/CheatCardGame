/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheatgame.card;

import cheatgame.Suit;
import cheatgame.Value;
import javafx.scene.image.Image;

/**
 * Cheat specific card. 
 *
 * @author thomas
 */
public class CheatCard extends GUICard {

    /**
     * Constructor 
     * 
     * @param frontFace Image for front face
     * @param backFace Image for back face
     * @param suit 
     * @param value  
     */
    public CheatCard(Image frontFace, Image backFace, Suit suit, Value value) {
        super(frontFace, backFace, suit, value);

        this.frontFace.setOnMousePressed(e -> {
            onMousePressed();
        });
        

    }

    /**
     * Checks against given value
     * 
     * @param value to check against
     * @return true if values are equal
     */
    @Override
    public boolean isValue(Value value) {
        return (value == this.VALUE);
    }


    /**
     * String representation of the card
     * @return 
     */
    @Override
    public String toString() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (this.SUIT + ": " + this.VALUE);
    }

    /**
     * Moves the card up a bit to signify that it is selected
     */
    @Override
    public void onMousePressed() {
        int diff = -40;
        if (activated) {
            diff = 40;
        }
        this.frontFace.setLayoutY(this.frontFace.getLayoutY() + diff);
        activated = !activated;
    }
}
