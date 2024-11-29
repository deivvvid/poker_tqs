package com.example.pokerapp.model;

import java.util.List;
import java.util.ArrayList;


public class Player {
	
	// public enum Position { DEALER, SMALLBLIND, BIGBLIND, UTG, CUTOFF }
	 
	private String name;
	private List<Card> hand;
    private int coins;

    // Constructor with no initial name, hand, or coins
    public Player() {
        this.name = "Uknown Player";
        this.hand = new ArrayList<>();
        this.coins = 0;
    }

    // Constructor with name, hand, and coins
    public Player(String name, List<Card> hand, int coins) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (hand == null) {
            throw new IllegalArgumentException("Hand cannot be null.");
        }
        if (coins < 0) {
            throw new IllegalArgumentException("Coins cannot be negative.");
        }
        this.name = name;
        this.hand = hand;
        this.coins = coins;
    }

    // Get player's name
    public String getName() {
        return name;
    }

    // Set player's name
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    // Get the player's hand
    public List<Card> getHand() {
        return hand;
    }

    // Get the player's coins
    public int getCoins() {
        return coins;
    }

    // Set the player's coins
    public void setCoins(int coins) {
        if (coins < 0) {
            throw new IllegalArgumentException("Coins cannot be negative.");
        }
        this.coins = coins;
    }
    
    // Set the player hand (for the following hands of the game)
    public void setHand(List<Card> hand) {
    	if (hand == null || hand.size() == 0)
    	{
    		throw new IllegalArgumentException("Hand has to have cards in it. ");
    	}
    	this.hand = hand;
    }

    // Add coins to the player's total
    public void addCoins(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot add a negative amount of coins.");
        }
        this.coins += amount;
    }
    
    public void addCard(Card card) {
    	if (hand.size() > 1) {
            throw new IllegalArgumentException("Cannot add a card because the hand is full.");
        }
    	
    	if (card == null) {
            throw new IllegalArgumentException("Cannot add a null card.");
    	}
        hand.add(card);
    }

    // Deduct coins from the player's total
    public void makeBet(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot deduct a negative amount of coins.");
        }
        if (amount > coins) {
            throw new IllegalArgumentException("Cannot deduct more coins than the player has.");
        }
        this.coins -= amount;
    }


    @Override
    public String toString() {
		 return "Player{" +
	             "name='" + name + '\'' +
	             ", hand=" + hand +
	             ", coins=" + coins +
	             '}';
    }
}
