/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;

/**
 *
 * @author anoop
 */
public abstract class Player {

    protected ArrayList<Card> hand;
    private ArrayList<Rank> books;
    private String name;
    protected Game game;
    
    private int numAsks = 0;
    private int numSuccess = 0;

    /**
     * Creates a new player with an empty hand and the given name.
     *
     * @param n the name of the player.
     */
    public Player(String n, Game g) {
        hand = new ArrayList<Card>();
        name = n;
        books = new ArrayList<Rank>();
        game = g;
    }

    /**
     * Adds the given card to this player's hand.
     *
     * @param c the card to add.
     */
    public void gainCard(Card c) {
        hand.add(c);
    }
    
    public void gainCards(ArrayList<Card> cards) {
        hand.addAll(cards);
    }
    
    public ArrayList<Card> removeRank(Rank r) {
        
        ArrayList<Card> result = new ArrayList<Card>();
        
        for (Card c : hand) {
            if (c.getRank() == r) {
                result.add(c);
            }
        }
        
        for (Card c : result) {
            hand.remove(c);
        }
        
        return result;
    }
    
    /**
     * @return the hand
     */
    public String getHandStr() {
        String str = "";
        for (Card c : hand) {
            str += c.toString();
        }
        return str;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Play one turn.
     * 
     * @return  will this player get another turn?
     */
    public boolean play() {
        
        // check what kind of player this is
        boolean isHuman = this instanceof UserPlayer;
        boolean isCPU = !isHuman;
        
        System.out.println(getName().toUpperCase() + "'S TURN");
        waitASec();
        System.out.println();
        
        // print hand, if human
        if(isHuman) {
            System.out.println("~~Your hand: " + getHandStr());
            waitASec();
            System.out.println();
        }
        
        // choose move (player + rank);
        Move m = chooseMove();
        Player p = m.getPlayerToAsk();
        Rank r = m.getRankToAskFor();
        
        // ask player for rank
        System.out.println(getName() + ": \"" + p.getName() + ", do you have any " + r + "'s?\"");
        waitASec();
        game.addInfo(this, r);
        
        numAsks++;
        
        if (p.hasRank(r)) {
            
            numSuccess++;
            
            System.out.println(p.getName() + ": \"Yes, I do.\"");
            waitASec();
            
            // transfer all cards of rank r from other player to this player
            ArrayList<Card> transfer = p.removeRank(r);
            game.removeInfo(p, r);
            System.out.print(p.getName() + " gives ");
            for (Card c : transfer) {
                System.out.print(c);
            }
            System.out.println(" to " + getName());
            waitASec();
            this.gainCards(transfer);
            
            makeBooks();
            System.out.println();
            return true;
        } else {
            System.out.println(p.getName() + ": \"Go fish!\"");
            waitASec();
            
            // draw a card from the deck ("Go Fish")
            Card c = game.dealCard(this);

            System.out.print(getName() + " draws ");
            System.out.println(isHuman ? c : "a card.");
            
            waitASec();
            
            // check if whatever was drawn was the card just asked for
            if (r == c.getRank()) {
                System.out.println(getName() + " just drew " + c + ", which is of the previously requested rank.");
                makeBooks();
                System.out.println();
                return true;
            } else {
                makeBooks();
                System.out.println();
                return false;
            }
        }
    }
    
    public abstract Move chooseMove();
    
    public void makeBooks() {
        for (Rank r : Rank.values()) {
            if (countRank(r) == 4) {
                books.add(r);
                removeRank(r);
                System.out.println(name + " has made a book of " + r + "'s");
                waitASec();
                game.info.get(this).remove(r);
            }
        }
    }

    public boolean hasRank(Rank r) {
        for (Card c : hand) {
            if (c.getRank() == r) {
                return true;
            }
        }
        return false;
    }
    
    public int countRank (Rank r) {
        int count = 0;
        for (Card c : hand) {
            if (c.getRank() == r) {
                count++;
            }
        }
        return count;
    }
    
    public int numBooks() {
        return books.size();
    }
    
    public boolean isHandEmpty() {
        return hand.isEmpty();
    }
    
    public void waitASec() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            System.out.println("How did the code end up here?");
        }
    }
    
    public double getAccuracy () {
        return (double) numSuccess / numAsks;
    }
}
