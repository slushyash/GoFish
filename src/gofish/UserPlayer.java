/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.Scanner;


public class UserPlayer extends Player {
    
    @Override
    public Move chooseMove() {
        Player p = choosePlayer();
        Rank r = chooseRank();
        return new Move(p, r);
    }
    
    public Player choosePlayer() {
        Scanner input = new Scanner(System.in);
        String str;
        boolean found;
        while (true) {
            System.out.print("~~" + getName() + ", who will you ask? ");    // this
            str = input.nextLine();
            found = false;
            for (Player p : game.getPlayers()) {
                if (p.getName().toUpperCase().equals(str.toUpperCase())) {
                    found = true;
                    if (this != p) {
                        return p;
                    } else {
                        System.out.println("~~You cannot ask yourself. Try again.");  // this
                        waitASec();
                    }
                }
            }
            if (!found) {
                System.out.println("~~None of the players is named " + str + ". Try again."); // this
                waitASec();
            }
        }
    }
    
    public Rank chooseRank() {
        Scanner input = new Scanner(System.in);
        String str;
        boolean found;
        while (true) {
            System.out.print("~~" + getName() + ", what will you ask for? ");   // this
            str = input.nextLine();
            found = false;
            for (Rank r : Rank.values()) {
                if (r.toString().equals(str.toUpperCase())) {
                    found = true;
                    if (hasRank(r)) {
                        return r;
                    } else {
                        System.out.println("~~You don't have any cards in your hand of rank " + r + ". Try again.");  // this
                        waitASec();
                    }
                }
            }
            if (!found) {
                System.out.println("~~There is no rank called " + str + ". Try again.");  // this
                waitASec();
            }
        }
    }

    public UserPlayer(String n, Game g) {
        super(n, g);
    }
}
