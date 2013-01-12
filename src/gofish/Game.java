/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author anoop
 */
public class Game {

    public Deck deck;
    public Player[] players;
    public int numPlayers;
    public Map<Player,Set<Rank>> info;
    
    public Game(int num) {
        
        this.numPlayers = num;
        info = new HashMap<Player,Set<Rank>>();
        
        setupGame();
        playGame();
    }
    
    /**
     * Sets up a game. Creates deck and players. Deals cards to players.
     * 
     * @param numPlayers number of players to create.
     */
    private void setupGame() {
        
        int cardsPerPlayer;
        
        if (numPlayers == 2) {
            cardsPerPlayer = 7;
        } else if (numPlayers > 2) {
            cardsPerPlayer = 5;
        } else {
            System.out.println("A game of Go Fish must have at least 2 players.");
            return;
        }
        
        // create a new deck and shuffle it
        deck = new Deck();
        deck.shuffle();
        
        Scanner input = new Scanner(System.in);
        players = new Player[numPlayers];
        
        /*
        // create user player
        System.out.print("Enter your first name: ");
        String userName = input.nextLine();
        players[0] = new UserPlayer(userName, this);
        info.put(players[0], new HashSet<Rank>());
        System.out.println();
        */
        
        // create computer players
        for (int i = 0; i < numPlayers; i++) {
            if(i % 2 == 0) {
                players[i] = new CPUPlayer_Smart("Smarty" + (i + 1), this);
            } else {
                players[i] = new CPUPlayer_Random("Randy" + (i + 1), this);
            }
            info.put(players[i], new HashSet<Rank>());
        }
        
        // print players' names
        System.out.println("These are the players' names. Use these names to refer to them.");
        for (Player p : players) {
            System.out.println(p.getName());
        }
        System.out.println();
        
        // deal cards to the players
        for (int j = 0; j < cardsPerPlayer; j++) {
            for (Player p : players) {
                dealCard(p);
            }
        }
    }
    
    
    /**
     * Deals a card from the deck to a player.
     * 
     * @param p the player to deal the card to.
     */
    public Card dealCard(Player p) {
        Card c = deck.removeTopCard();
        p.gainCard(c);
        return c;
    }
    
    public Player[] getPlayers() {
        return players;
    }

    private void playGame() {
        
        int whoseTurn = 0;
        int totalNumTurns = 0;
        int noRetryNumTurns = 0;
        boolean gameInProgress = true;
        
        while (gameInProgress) {
            
            // print everyone's hands
            System.out.println("~~~~Everyone's hands");
            for (Player p : players) {
                System.out.println("~~~~" + p.getName() + ": " + p.getHandStr());
            }
            System.out.println();
            
            // print what everyone knows about each others' hands
            System.out.println("~~~~Common knowledge");
            for (Player p : players) {
                System.out.println("~~~~" + p.getName() + ": " + info.get(p));
            }
            System.out.println();
            
            boolean anotherTurn = players[whoseTurn].play();
            totalNumTurns++;
            
            for (Player p : players) {
                if (p.isHandEmpty()) {
                    gameInProgress = false;
                }
            }
            if (deck.isEmpty()) {
                gameInProgress = false;
            }
            if (!anotherTurn) {
                whoseTurn++;
                noRetryNumTurns++;
                whoseTurn %= players.length;
            }
        }
        
        printGameSummary();
        
    }
    
    public void addInfo(Player p, Rank r) {
        Set<Rank> ranksThatPlayerHas = info.get(p);
        ranksThatPlayerHas.add(r);
    }
    
    public void removeInfo(Player p, Rank r) {
        Set<Rank> ranksThatPlayerHas = info.get(p);
        ranksThatPlayerHas.remove(r);
    }

    private void printGameSummary() {
        
        System.out.println("GAME OVER");
        System.out.println();
        
        // find the winner(s)
        // print number of books of each player
        System.out.println("Number of books each player has:");
        ArrayList<Player> winners = new ArrayList<Player>();
        int maxBooks = -1;
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.numBooks());
            if (p.numBooks() > maxBooks) {
                winners = new ArrayList<Player>();
                maxBooks = p.numBooks();
            }
            if (p.numBooks() >= maxBooks) {
                winners.add(p);
            }
        }
        System.out.println();
        
        // print winner's name(s)
        if (winners.size() == 1) {
            System.out.println("The winner is " + winners.get(0).getName());
        } else {
            System.out.print("The winners are ");
            for (int i = 0; i < winners.size(); i++) {
                System.out.print(winners.get(i).getName());
                if (i < winners.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.println();
        
        // print players' accuracies
        
        System.out.println("Players' accuracies:");
        for (Player p : players) {
            System.out.println(p.getName() + ": " + Math.round(p.getAccuracy() * 100) + "%");
        }
    }
}