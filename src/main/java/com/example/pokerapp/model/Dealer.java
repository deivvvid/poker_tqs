package com.example.pokerapp.model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

	// Lista que representa la mano del dealer
    private List<Card> hand;

    // Constructor por defecto, inicializa la mano vacía
    public Dealer() {
        this.hand = new ArrayList<>();
    }

    // Constructor que valida y establece una mano específica
    public Dealer(List<Card> hand) {
    	// Verifica que la mano no sea nula ni vacía
        if (hand == null || hand.isEmpty()) { 
            throw new IllegalArgumentException("Hand cannot be null or empty.");
        }
        
        // Verifica que la mano no tenga más de 2 cartas
        if (hand.size() > 2) { 
            throw new IllegalArgumentException("Hand cannot contain more than 2 cards.");
        }
        
        // Asigna una nueva lista con las cartas proporcionadas
        this.hand = new ArrayList<>(hand);
    }

    // Obtiene la mano del dealer
    public List<Card> getHand() {
        return hand;
    }

    // Establece una nueva mano del dealer validando que sea correcta
    public void setHand(List<Card> hand) {
    	// Verifica que la mano no sea nula ni vacía
        if (hand == null || hand.isEmpty()) {
            throw new IllegalArgumentException("Hand cannot be null or empty.");
        }
        
        // Verifica que la mano no tenga más de 2 cartas
        if (hand.size() > 2) {
            throw new IllegalArgumentException("Hand cannot contain more than 2 cards.");
        }
        
        // Asigna una nueva lista con las cartas proporcionadas
        this.hand = new ArrayList<>(hand);
    }

    // Añade una carta a la mano, si hay espacio
    public void addCard(Card card) {
    	// Verifica si ya hay 2 cartas en la mano
        if (hand.size() >= 2) {
            throw new IllegalArgumentException("Cannot add a card because the hand is full.");
        }
        // Verifica que la carta no sea nula
        if (card == null) {
            throw new IllegalArgumentException("Cannot add a null card.");
        }
        // Añade la carta a la mano
        hand.add(card);
    }

 // Devuelve una representación en cadena de la mano del dealer
    @Override
    public String toString() {
        return "Dealer{hand=" + hand + "}";
    }
}
