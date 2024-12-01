package com.example.pokerapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private final List<Card> cards;
    
    public Deck() {
    		this.cards = new ArrayList<>();
    		initializeDeck();
    		if (this.cards.size() > 52) {
                throw new IllegalArgumentException("Too many cards in the deck.");
            }
    }
    
    private void initializeDeck() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.remove(0);
    }
    
    public void resetDeck() {
        initializeDeck();
        shuffle();
    }
    
    public List<Card> getCards() {
        return cards;
    }
}
