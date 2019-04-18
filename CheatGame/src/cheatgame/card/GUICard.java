/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheatgame.card;

import cheatgame.Suit;
import cheatgame.Value;
import cheatgame.card.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * GUICard for cards with images
 *
 * @author thomas
 */
public abstract class GUICard extends Card {

    //Variables used throughout
    boolean frontFacing = false;
    ImageView toBeShown;
    final ImageView frontFace;
    final ImageView backFace;
    boolean activated;

    /**
     * Constructor
     *
     * @param frontFace Image for front face
     * @param backFace Image for back face
     * @param suit
     * @param value
     */
    public GUICard(Image frontFace, Image backFace, Suit suit, Value value) {
        super(suit, value);
        this.frontFace = new ImageView(frontFace);
        this.backFace = new ImageView(backFace);
        this.toBeShown = this.backFace;

        this.frontFace.setOnMousePressed(e -> {
            onMousePressed();
        });
    }

    /**
     * Checks against both suit and value
     *
     * @param suit
     * @param value
     * @return returns true if there is a match.
     */
    public boolean isSuitAndValue(Suit suit, Value value) {
        return (suit == this.SUIT && value == this.VALUE);
    }

    /**
     * Checks against the value only
     *
     * @param value
     * @return true if the value is a match
     */
    @Override
    public boolean isValue(Value value) {
        return (value == this.VALUE);
    }

    /**
     *Get the image view of the card
     * 
     * @return imageView of side of card to be shown
     */
    public ImageView getImageView() {
        return toBeShown;
    }

    /**
     * If the card is activated from user selection
     * 
     * @return 
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Flip card depending on the boolean
     * 
     * @param frontFacing boolean
     */
    public void flip(boolean frontFacing) {

        if (this.frontFacing != frontFacing) {
            flip();
        }
    }

    /**
     * Sets the toBeShown imageview to the back or front.
     */
    public void flip() {

        activated = false;
        frontFacing = !frontFacing;
        if (frontFacing) {

            toBeShown = frontFace;

            backFace.setOpacity(0);
        } else {
            toBeShown = backFace;
            frontFace.setOpacity(0);
        }

        toBeShown.setOpacity(1);
    }

    public abstract void onMousePressed();

}
