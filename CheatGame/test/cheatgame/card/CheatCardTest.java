/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheatgame.card;

import cheatgame.Suit;
import cheatgame.Value;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LolFuckYouWindows
 */
public class CheatCardTest {

    /**
     * Test of isValue method, of class CheatCard.+
     * Should be Good
     */
    @Test
    public void goodTestIsValue() {
        System.out.println("isValue");
        Value value = Value.FIVE;
        CheatCard instance = new CheatCard(null, null, Suit.CLUBS, Value.FIVE);
        boolean expResult = true;
        boolean result = instance.isValue(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValue method, of class CheatCard.
     * Should be Bad
     */
    @Test
    public void badTestIsValue() {
        System.out.println("isValue");
        Value value = Value.TWO;
        CheatCard instance = new CheatCard(null, null, Suit.CLUBS, Value.ACE);
        boolean expResult = false;
        boolean result = instance.isValue(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValue method, of class CheatCard.
     * Should be good
     */
    @Test
    public void edgeCaseTestIsValue() {
        System.out.println("isValue");
        Value value = Value.KING;
        CheatCard instance = new CheatCard(null, null, Suit.CLUBS, Value.KING);
        boolean expResult = true;
        boolean result = instance.isValue(value);
        assertEquals(expResult, result);
    }

    
        /**
     * Test of isValue method, of class CheatCard.+
     * Should be Good
     */
    @Test
    public void goodTestIsSuitAndValue() {
        System.out.println("isValue");
        Suit suit = Suit.CLUBS;
        Value value = Value.FIVE;
        
        CheatCard instance = new CheatCard(null, null, Suit.CLUBS, Value.FIVE);
        boolean expResult = true;
        boolean result = instance.isSuitAndValue(suit,value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValue method, of class CheatCard.
     * Should be Bad
     */
    @Test
    public void badTestIsSuitAndValue() {
        System.out.println("isValue");
        Suit suit = Suit.DIAMONDS;
        Value value = Value.TWO;
        CheatCard instance = new CheatCard(null, null, Suit.CLUBS, Value.ACE);
        boolean expResult = false;
        boolean result = instance.isSuitAndValue(suit,value);
        assertEquals(expResult, result);
    }

}
