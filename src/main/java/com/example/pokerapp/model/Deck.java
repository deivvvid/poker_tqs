package com.example.pokerapp.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	private final List<Card> cards;
    
    public Deck() {
    		this.cards = new ArrayList<>();
    		addAllCardsToDeck();
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void removeCard(Card card) {
    	cards.remove(card);
    }
    
    public void addAllCardsToDeck() {
    	for (int i = 0; i < Card.Suit.values().length; i++) {
			for (int j = 0; j < Card.Rank.values().length; j++) {
    			this.cards.add(new Card(Card.Suit.values()[i], (Card.Rank.values()[j])));
    		}
		}
    }
    
    public void clearCards() {
    	cards.clear();
    }
    
    public List<Card> getCards() {
    	return new ArrayList<>(cards);
    }

}
