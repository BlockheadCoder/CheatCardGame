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
 *
 * @author thomas
 */
public abstract class GUICard extends Card {

    boolean frontFacing = false;
    ImageView toBeShown;
    final ImageView frontFace;
    final ImageView backFace;
    boolean activated;

    public GUICard(Image frontFace, Image backFace, Suit suit, Value value) {
        super(suit, value);
        this.frontFace = new ImageView(frontFace);
        this.backFace = new ImageView(backFace);
        this.toBeShown = this.backFace;

        this.frontFace.setOnMousePressed(e -> {
            onMousePressed();
        });
    }

    public boolean isSuitAndValue(Suit suit, Value value) {
        return (suit == this.SUIT && value == this.VALUE);
    }

    @Override
    public boolean isValue(Value value) {
        return (value == this.VALUE);
    }

    public ImageView getImageView() {
        return toBeShown;
    }

    public boolean isActivated() {
        return activated;
    }

    public void flip(boolean frontFacing) {

        if (this.frontFacing != frontFacing) {
            flip();
        }
    }

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
