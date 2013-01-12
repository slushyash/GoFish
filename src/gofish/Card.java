/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gofish;

/**
 *
 * @author anoop
 */
public class Card {
    
    private Rank rank;
    private Suit suit;
    
    /**
     * Creates a Card. Sets its rank and suit.
     * 
     * @param r rank to set
     * @param s suit to set
     */
    public Card(Rank r, Suit s) {
        rank = r;
        suit = s;
    }
    
    /**
     * Converts the card into a string.
     * @return the card as a string.
     */
    @Override
    public String toString() {
        return "[" + rank.toString() + suit.toString() + "]";
    }

    /**
     * @return the rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }
}
