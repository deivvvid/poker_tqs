package com.example.pokerapp.model;

public class Card {
	// Enum que representa los palos de las cartas (Corazones, Diamantes, Tréboles, Picas).
    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    
    // Enum que representa los rangos de las cartas (2 a As).
    public enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, J, Q, K, A }
    
    // Variables de instancia para el palo (Suit) y el rango (Rank) de la carta.
    private final Suit suit;
    private final Rank rank;
    
    // Constructor que valida que el palo y el rango no sean nulos y que sean válidos.
    public Card(Suit suit, Rank rank) {
    	try {
            if (suit == null || rank == null) {
                throw new IllegalArgumentException("Suit and rank cannot be null.");
            }
            
            // Verifica que los valores de suit y rank sean válidos.
            Card.Suit.valueOf(suit.name());
            Card.Rank.valueOf(rank.name());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Suit or Rank", e);
        }
        this.suit = suit;
        this.rank = rank;
    }
    
    // Método que devuelve el palo de la carta.
    public Suit getSuit() {
        return suit;
    }
    
    // Método que devuelve el rango de la carta.
    public Rank getRank() {
        return rank;
    }
    
    // Método que compara dos cartas para verificar si son iguales (mismo palo y rango).
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return suit == card.suit && rank == card.rank;
    }
    
    // Método que devuelve la ruta de la imagen asociada a la carta (nombre del rango y palo).
    public String getImagePath() {
    	return rank + "_" + suit + ".png";
    }

    // Método que devuelve una representación en formato de texto de la carta (ej. "As of Corazones").
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}