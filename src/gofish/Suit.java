/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

/**
 *
 * @author anoop
 */
public enum Suit {
    SPADE,
    HEART,
    DIAMOND,
    CLUB;
    
    /**
     * Converts the rank to a string. This is a character that corresponds to
     * this suit
     * 
     * @return the rank as a string.
     */
    @Override
    public String toString() {
        switch(this) {
            case SPADE:     return "\u2660";
            case HEART:     return "\u2665";
            case DIAMOND:   return "\u2666";
            case CLUB:      return "\u2663";
            default:        return "";
        }
    }
}
