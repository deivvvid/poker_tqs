package com.example.pokerapp.model;

import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class DealerTest {

	@Test
	public void testConstructor_emptyPlayer_noExceptionThrown() {
		Dealer dealer = new Dealer();
        assertNotNull(dealer);
        assertTrue(dealer.getHand().isEmpty());
	}
	
	@Test
	public void testConstructor_withHand_noExceptionThrown() {
		List<Card> hand = Arrays.asList(
	            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
	            new Card(Card.Suit.HEARTS, Card.Rank.J)
	    );
		
		Dealer dealer = new Dealer(hand);
        assertNotNull(dealer);
        assertEquals(2, dealer.getHand().size());
        assertTrue(dealer.getHand().contains(new Card(Card.Suit.DIAMONDS, Card.Rank.THREE)));
        assertTrue(dealer.getHand().contains(new Card(Card.Suit.HEARTS, Card.Rank.J)));
	}
	
	@Test
    public void testSetHand_validValue_setsHand() {
        Dealer dealer = new Dealer();

        List<Card> initialHand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K)
        );
        List<Card> newHand = Arrays.asList(
                new Card(Card.Suit.CLUBS, Card.Rank.Q),
                new Card(Card.Suit.SPADES, Card.Rank.J)
        );

        dealer.setHand(initialHand);
        assertEquals(2, dealer.getHand().size());
        assertTrue(dealer.getHand().contains(new Card(Card.Suit.HEARTS, Card.Rank.A)));
        assertTrue(dealer.getHand().contains(new Card(Card.Suit.DIAMONDS, Card.Rank.K)));

        dealer.setHand(newHand);
        assertEquals(2, dealer.getHand().size());
        assertTrue(dealer.getHand().contains(new Card(Card.Suit.CLUBS, Card.Rank.Q)));
        assertTrue(dealer.getHand().contains(new Card(Card.Suit.SPADES, Card.Rank.J)));
    }

    @Test
    public void testSetHand_nullOrEmptyValue_throwsException() {
    	Dealer dealer = new Dealer();
    	List<Card> hand = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> dealer.setHand(null));
        assertThrows(IllegalArgumentException.class, () -> dealer.setHand(hand));
        
    }
}
