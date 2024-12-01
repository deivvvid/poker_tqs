package com.example.pokerapp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardTest {

	@Test
    void constructor_ShouldCreateCard_WhenSuitAndRankAreValid() {
        Card.Suit suit = Card.Suit.HEARTS;
        Card.Rank rank = Card.Rank.TWO;
        Card card = new Card(suit, rank);
        assertEquals(suit, card.getSuit());
        assertEquals(rank, card.getRank());
        
        Card.Suit suit2 = Card.Suit.SPADES;
        Card.Rank rank2 = Card.Rank.A;
        Card card2 = new Card(suit2, rank2);
        assertEquals(suit2, card2.getSuit());
        assertEquals(rank2, card2.getRank());
    }
    
	@Test
	void constructor_ShouldCreateCard_WithValidSuitsAndRanks() {
	    for (Card.Suit suit : Card.Suit.values()) {
	        for (Card.Rank rank : Card.Rank.values()) {
	        	if ((suit == Card.Suit.HEARTS && rank == Card.Rank.A) || 
                    (suit == Card.Suit.SPADES && rank == Card.Rank.TWO)) {
                    continue;
                }
	            Card card = new Card(suit, rank);
	            assertEquals(suit, card.getSuit());
	            assertEquals(rank, card.getRank());
	        }
	    }
	}
	
    @Test
    public void testConstructor_nullValues_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Card(null, Card.Rank.A));
        assertThrows(IllegalArgumentException.class, () -> new Card(Card.Suit.HEARTS, null));
        assertThrows(IllegalArgumentException.class, () -> new Card(null, null));
    }
    
    @Test
    void constructor_ShouldThrowException_WhenInvalidSuitOrRank() {
       assertThrows(IllegalArgumentException.class, () -> new Card(Card.Suit.valueOf("INVALID"), Card.Rank.A));
       assertThrows(IllegalArgumentException.class, () -> new Card(Card.Suit.HEARTS, Card.Rank.valueOf("INVALID")));
    }
    
    @ParameterizedTest
    @EnumSource(Card.Suit.class)
    public void testBoundaryValues(Card.Suit suit) {
        Card minCard = new Card(suit, Card.Rank.TWO);
        Card maxCard = new Card(suit, Card.Rank.A);
        
        assertNotNull(minCard);
        assertNotNull(maxCard);
    }
    
    @ParameterizedTest
    @EnumSource(Card.Rank.class)
    public void testBoundaryValuesWithRanks(Card.Rank rank) {
        Card minCard = new Card(Card.Suit.HEARTS, rank);
        Card maxCard = new Card(Card.Suit.SPADES, rank);
        
        assertNotNull(minCard);
        assertNotNull(maxCard);
    }
    
    @Test
    void getSuit_ShouldReturnCorrectSuit() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card.Suit suit = card.getSuit();
        assertEquals(Card.Suit.HEARTS, suit, "getSuit() should return the correct suit");
    }

    @Test
    void getRank_ShouldReturnCorrectRank() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card.Rank rank = card.getRank();
        assertEquals(Card.Rank.A, rank, "getRank() should return the correct rank");
    }

    @Test
    void getSuitAndRank_ShouldWorkWithAllValidCombinations() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(suit, rank);
                assertEquals(suit, card.getSuit(), "getSuit() should return the correct suit");
                assertEquals(rank, card.getRank(), "getRank() should return the correct rank");
            }
        }
    }
    
    @Test
    public void testEquals_sameCards_returnsTrue() {
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card card2 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        assertTrue(card1.equals(card2), "The cards should be equal.");
    }

    @Test
    public void testEquals_sameObject_returnsTrue() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        assertTrue(card.equals(card), "An object should always be equal to itself.");
    }

    @Test
    public void testEquals_differentCards_returnsFalse() {
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card card2 = new Card(Card.Suit.CLUBS, Card.Rank.K);
        assertFalse(card1.equals(card2), "Cards with different suit and rank should not be equal.");
    }

    @Test
    public void testEquals_null_returnsFalse() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        assertFalse(card.equals(null), "A card should not be equal to null.");
    }

    @Test
    public void testEquals_differentClass_returnsFalse() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Object obj = new Object();
        assertFalse(card.equals(obj), "A card should not be equal to an object of a different class.");
    }

    
    @Test
    public void testGetImagePath() {
        Card card = new Card(Card.Suit.SPADES, Card.Rank.J);
        assertEquals("J_SPADES.png", card.getImagePath());
    }
    
    @Test
    public void testToString() {
        Card card = new Card(Card.Suit.SPADES, Card.Rank.J);
        assertEquals("J of SPADES", card.toString());
    }
}