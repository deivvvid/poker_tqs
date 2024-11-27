package com.example.pokerapp.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	
	private final List<Card> cards;

    public static final int MAX_HAND_SIZE = 52;
    
    public Hand(int full) {
    	if (full == 1) {
    		this.cards = new ArrayList<>();
    		for (int i = 0; i < Card.Suit.values().length; i++) {
    			for (int j = 0; j < Card.Rank.values().length; j++) {
        			this.cards.add(new Card(Card.Suit.values()[i], (Card.Rank.values()[j])));
        		}
    		}
    	} else {
    		this.cards = new ArrayList<>();
    	}
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public List<Card> getCards() {
    	return new ArrayList<>(cards);
    }

}
