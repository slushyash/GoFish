/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

/**
 *
 * @author anoop
 */
public class Deck {
    
    private Card[] cards;
    
    /**
     * Creates 52 distinct new cards and puts them in the deck in order. This is
     * a standard deck of cards.
     */
    public Deck() {
        
        cards = new Card[52];
        int i = 0;
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cards[i] = new Card(r, s);
                i++;
            }
        }
    }
    
    /**
     * Swaps two of the cards in the deck. Used only in <code>shuffle()</code>.
     * 
     * @param i index of the first card to swap
     * @param j index of the second card to swap
     */
    private void swap(int i, int j) {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }
    
    /**
     * Rearranges the cards in a random order.
     */
    public void shuffle() {
        
        for (int i = 0; i < cards.length; i++) {
            int j = i + (int) (Math.random() * (cards.length - i));
            swap(i, j);
        }
    }
    
    /**
     * Removes the first card in the deck.
     * 
     * @return the card that was just removed
     */
    public Card removeTopCard() {
        
        Card[] temp = new Card[cards.length - 1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cards[i + 1];
        }
        
        Card c = cards[0];
        cards = temp;
        
        return c;
    }
    
    /**
     * Converts the deck to a string. Used only for testing purposes.
     * 
     * @return the deck as a string.
     */
    @Override
    public String toString() {
        String str = "";
        for (Card c : cards) {
            str += c.toString();
        }
        return str;
    }
    
    public boolean isEmpty() {
        return cards.length == 0;
    }
}
