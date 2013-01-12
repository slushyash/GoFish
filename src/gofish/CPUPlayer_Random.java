/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;


public class CPUPlayer_Random extends Player {
    
    @Override
    public Move chooseMove() {
        Player p = choosePlayer();
        Rank r = chooseRank();
        return new Move(p, r);
    }
    
    private Player choosePlayer() {
        Player[] all = game.getPlayers();
        int numPlayers = all.length;
        Player p = this;
        while (p == this) {
            p = all[(int) (Math.random() * numPlayers)];
        }
        return p;
    }
    
    private Rank chooseRank() {
        Card c = hand.get((int) (Math.random() * hand.size()));
        return c.getRank();
    }

    public CPUPlayer_Random(String n, Game g) {
        super(n, g);
    }
    
}
