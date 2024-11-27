package com.example.pokerapp.model;

public class Player {
	
	// public enum Position { DEALER, SMALLBLIND, BIGBLIND, UTG, CUTOFF }
	 
	private String name;
    private Hand hand;
    private int coins;

    // Constructor with no initial name, hand, or coins
    public Player() {
        this.name = "Unnamed Player";
        this.hand = new Hand();
        this.coins = 0;
    }

    // Constructor with name, hand, and coins
    public Player(String name, Hand hand, int coins) {
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
    public Hand getHand() {
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
    public void setHand(Hand hand) {
    	if (hand == null)
    	{
    		throw new IllegalArgumentException("Hand has to have cards in it. ");
    	}
    	this.hand = hand;
    }
    
    //Change the hand's cards because of the change of turn
    /*
    public void changeTurn(Hand hand) {
    	if (hand == null)
    	{
    		throw new IllegalArgumentException("Hand has to have cards in it. ");
    	}
    }
    */

    // Add coins to the player's total
    public void addCoins(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative amount of coins.");
        }
        this.coins += amount;
    }

    // Deduct coins from the player's total
    public void makeBet(int amount) {
        if (amount < 0) {
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
