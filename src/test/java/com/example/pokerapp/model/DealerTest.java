package com.example.pokerapp.model;

import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class DealerTest {

	@Test
	public void testConstructor_emptyDealer_noExceptionThrown() {
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
	public void testConstructor_withHandOversized_ExceptionThrown() {
		List<Card> hand = Arrays.asList(
	            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
	            new Card(Card.Suit.HEARTS, Card.Rank.J),
	            new Card(Card.Suit.CLUBS, Card.Rank.K)
	    );

	    assertThrows(IllegalArgumentException.class, () -> new Dealer(hand));
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
    public void testSetHand_withHandOversized_throwsException() {
    	Dealer dealer = new Dealer();

        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.SEVEN)
        );
       
        assertThrows(IllegalArgumentException.class, () -> dealer.setHand(hand));
    }
    
    @Test
    public void testSetHand_nullOrEmptyValue_throwsException() {
    	Dealer dealer = new Dealer();
    	List<Card> hand = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> dealer.setHand(null));
        assertThrows(IllegalArgumentException.class, () -> dealer.setHand(hand));
    }
    
    @Test
    public void testAddCard_validCard_addsCardToHand() {
        Dealer dealer = new Dealer();
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);

        dealer.addCard(card);

        assertEquals(1, dealer.getHand().size());
        assertTrue(dealer.getHand().contains(card));
    }

    @Test
    public void testAddCard_nullCard_throwsException() {
        Dealer dealer = new Dealer();

        assertThrows(IllegalArgumentException.class, () -> dealer.addCard(null));
    }

    @Test
    public void testAddCard_handIsFull_throwsException() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.A);
        Card card2 = new Card(Card.Suit.DIAMONDS, Card.Rank.K);

        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThrows(IllegalArgumentException.class, () -> dealer.addCard(new Card(Card.Suit.SPADES, Card.Rank.Q)));
        assertEquals(2, dealer.getHand().size());
    }
    
    @Test
    public void testToString() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K)
        );
        Dealer dealer = new Dealer(hand);

        String expectedOutput = "Dealer{hand=[A of HEARTS, K of DIAMONDS]}";
        assertEquals(expectedOutput, dealer.toString());
    }
}
