/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

/**
 *
 * @author anoop
 */
public class Move {
    
    private Player playerToAsk;
    private Rank rankToAskFor;
    
    public Move(Player p, Rank r) {
        playerToAsk = p;
        rankToAskFor = r;
    }
    
    public Player getPlayerToAsk() {
        return playerToAsk;
    }
    
    public Rank getRankToAskFor() {
        return rankToAskFor;
    }
    
    @Override
    public String toString() {
        return playerToAsk.getName() + " - " + rankToAskFor;
    }
}
