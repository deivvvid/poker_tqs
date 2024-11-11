package com.example.pokerapp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardTest {

	@Test
	public void testConstructor_validValues_noExceptionThrown() {
	    Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
	    assertNotNull(card);
	    assertEquals(Card.Suit.HEARTS, card.getSuit());
	    assertEquals(Card.Rank.A, card.getRank());
	}
	
	@Test
	public void testConstructor_nullRank_throwsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Card(Card.Suit.HEARTS, null);
	    });
	}
    
    @Test
    public void testConstructor_nullValues_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Card(null, Card.Rank.A));
        assertThrows(IllegalArgumentException.class, () -> new Card(Card.Suit.HEARTS, null));
        assertThrows(IllegalArgumentException.class, () -> new Card(null, null));
    }
    
    @ParameterizedTest
    @EnumSource(Card.Suit.class)
    public void testBoundaryValues(Card.Suit suit) {
        Card minCard = new Card(suit, Card.Rank.TWO);
        Card maxCard = new Card(suit, Card.Rank.A);
        
        assertNotNull(minCard);
        assertNotNull(maxCard);
    }
    
    @Test
    public void testEquals_sameCards_returnsTrue() {
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card card2 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        assertEquals(card1, card2);
    }
    
    @Test
    public void testEquals_differentCards_returnsFalse() {
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card card2 = new Card(Card.Suit.CLUBS, Card.Rank.K);
        assertNotEquals(card1, card2);
    }
    
    @Test
    public void testToString() {
        Card card = new Card(Card.Suit.SPADES, Card.Rank.J);
        assertEquals("J of SPADES", card.toString());
    }
}