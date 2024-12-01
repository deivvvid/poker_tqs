package com.example.pokerapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	// Lista que contiene las cartas del mazo
	private final List<Card> cards;
    
	// Constructor que inicializa el mazo y valida que no haya más de 52 cartas
    public Deck() {
    		this.cards = new ArrayList<>();
    		initializeDeck();
    		if (this.cards.size() > 52) {
                throw new IllegalArgumentException("Too many cards in the deck.");
            }
    }
    
    // Inicializa el mazo con todas las cartas posibles (por cada palo y rango)
    private void initializeDeck() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }
    
    // Mezcla las cartas del mazo de forma aleatoria
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Reparte una carta, eliminándola del mazo
    public Card dealCard() {
        return cards.remove(0);
    }
    
    // Restablece el mazo a su estado original y lo mezcla
    public void resetDeck() {
        initializeDeck();
        shuffle();
    }
    
    // Devuelve la lista actual de cartas en el mazo
    public List<Card> getCards() {
        return cards;
    }
}
