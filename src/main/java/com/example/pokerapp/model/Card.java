package com.example.pokerapp.model;

public class Card {
    
    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, J, Q, K, A }
    
    private final Suit suit;
    private final Rank rank;
    
    public Card(Suit suit, Rank rank) {
    	try {
            if (suit == null || rank == null) {
                throw new IllegalArgumentException("Suit and rank cannot be null.");
            }

            Card.Suit.valueOf(suit.name());
            Card.Rank.valueOf(rank.name());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Suit or Rank", e);
        }
        this.suit = suit;
        this.rank = rank;
    }
    
    public Suit getSuit() {
        return suit;
    }
    
    public Rank getRank() {
        return rank;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return suit == card.suit && rank == card.rank;
    }
    
    public String getImagePath() {
    	return rank + "_" + suit + ".png";
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}