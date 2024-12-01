package com.example.pokerapp.model;

import java.util.List;
import java.util.ArrayList;


public class Player {
	 
	// Nombre del jugador
	private String name;
	
	// Mano del jugador (lista de cartas)
	private List<Card> hand;
	
	// Cantidad de monedas del jugador
    private int coins;

    // Constructor por defecto
    public Player() {
        this.name = "Uknown Player";
        this.hand = new ArrayList<>();
        this.coins = 0;
    }

    // Constructor que inicializa el jugador con un nombre, mano y monedas
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

    // Obtiene el nombre del jugador
    public String getName() {
        return name;
    }

    // Establece el nombre del jugador con validación
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    // Obtiene la mano del jugador
    public List<Card> getHand() {
        return hand;
    }

    // Obtiene la cantidad de monedas del jugador
    public int getCoins() {
        return coins;
    }

    // Establece las monedas del jugador
    public void setCoins(int coins) {
        if (coins < 0) {
            throw new IllegalArgumentException("Coins cannot be negative.");
        }
        this.coins = coins;
    }

    // Establece la mano del jugador
    public void setHand(List<Card> hand) {
    	if (hand == null || hand.size() == 0)
    	{
    		throw new IllegalArgumentException("Hand has to have cards in it. ");
    	}
    	this.hand = hand;
    }

    // Añade monedas al jugador
    public void addCoins(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot add a negative amount of coins.");
        }
        this.coins += amount;
        
        if (amount > this.coins) {
            throw new IllegalArgumentException("Coins added incorrrectly.");
        }
    }
    
    // Añade una carta a la mano del jugador con validación de espacio
    public void addCard(Card card) {
    	if (hand.size() > 1) {
            throw new IllegalArgumentException("Cannot add a card because the hand is full.");
        }
    	
    	if (card == null) {
            throw new IllegalArgumentException("Cannot add a null card.");
    	}
        hand.add(card);
    }

    // Quita coins al jugador
    public void makeBet(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot deduct a negative amount of coins.");
        }
        if (amount > coins) {
            throw new IllegalArgumentException("Cannot deduct more coins than the player has.");
        }
        this.coins -= amount;
    }

    // Representación del jugador en formato de cadena
    @Override
    public String toString() {
		 return "Player{" +
	             "name='" + name + '\'' +
	             ", hand=" + hand +
	             ", coins=" + coins +
	             '}';
    }
}
