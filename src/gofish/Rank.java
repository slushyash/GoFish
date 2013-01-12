/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

/**
 *
 * @author anoop
 */
public enum Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;
    
    /**
     * Converts the rank to a string. This is the text that would appear on a
     * standard playing card.
     * 
     * @return the rank as a string.
     */
    @Override
    public String toString() {
        switch (this) {
            case ACE:   return "A";
            case TWO:   return "2";
            case THREE: return "3";
            case FOUR:  return "4";
            case FIVE:  return "5";
            case SIX:   return "6";
            case SEVEN: return "7";
            case EIGHT: return "8";
            case NINE:  return "9";
            case TEN:   return "10";
            case JACK:  return "J";
            case QUEEN: return "Q";
            case KING:  return "K";
            default:    return "";
        }
    }
}
