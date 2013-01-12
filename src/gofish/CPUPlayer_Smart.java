/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;

/**
 *
 * @author yash
 */
public class CPUPlayer_Smart extends Player {
    
    public CPUPlayer_Smart(String n, Game g) {
        super(n, g);
    }

    @Override
    public Move chooseMove() {
        
        // all the moves that are guaranteed to work
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        
        // check if you know that anyone has a card that you want
        for (Card c : hand) {
            for (Player p : game.getPlayers()) {
                if (p == this) {
                    continue;
                }
                if (game.info.get(p).contains(c.getRank())) {
                    possibleMoves.add(new Move(p, c.getRank()));
                }
            }
        }
        
        System.out.println("~~Possible Moves: " + possibleMoves);
        
        Move m;
        if (possibleMoves.size() == 0) {
            
            // act like Randy
            
            // choose an other player randomly, assign to p
            Player[] all = game.getPlayers();
            int numPlayers = all.length;
            Player p = this;
            while (p == this) {
                p = all[(int) (Math.random() * numPlayers)];
            }
            
            // choose a rank from the hand randomly, assign to r
            Card c = hand.get((int) (Math.random() * hand.size()));
            Rank r = c.getRank();
            
            return new Move(p, r);
            
        } else {
            
            // choose a random element from possibleMoves
            return possibleMoves.get((int) (Math.random() * possibleMoves.size()));
            
        }
    }
}
