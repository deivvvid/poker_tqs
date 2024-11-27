package com.example.pokerapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class HandTest {
	
	private Hand hand;
	
	@Test
    public void testConstructorEmpty() {
        Hand hand = new Hand(0);
        assertTrue(hand.getCards().isEmpty(), "Empty hand should contain no cards.");	
    }
	
	@Test
    public void testFullDeckConstructorCreates52Cards() {
        Hand fullDeckHand = new Hand(1);

        List<Card> cards = fullDeckHand.getCards();
        assertEquals(Hand.MAX_HAND_SIZE, cards.size(), "Full deck should contain exactly 52 cards.");

        int index = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                assertEquals(new Card(suit, rank), cards.get(index),
                        "Expected card at position " + index + " is not correct.");
                index++;
            }
        }
    }
	
	@Test
	public void testAddsCardCorrectly() {
		Hand hand = new Hand(0);
		Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
		hand.addCard(card);
		assertTrue(hand.getCards().contains(card), "Hand card list should contain the card.");
	}
	
	@Test
    public void testGetCardsReturnsCopyOfList() {
        Hand hand = new Hand(0);

        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        hand.addCard(card);

        List<Card> returnedCards = hand.getCards();
        returnedCards.remove(card);

        assertEquals(1, hand.getCards().size(), "Original hand list should remain unchanged.");
        assertTrue(hand.getCards().contains(card), "Original hand list should still contain the card.");
    }

    @Test
    public void testGetCardsReturnsCorrectSnapshot() {
        Hand hand = new Hand(0);

        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        hand.addCard(card);
        List<Card> snapshot = hand.getCards();

        Card anotherCard = new Card(Card.Suit.DIAMONDS, Card.Rank.K);
        hand.addCard(anotherCard);

        assertEquals(1, snapshot.size(), "Snapshot should not reflect changes after its creation.");
        assertFalse(snapshot.contains(anotherCard), "Snapshot should not contain newly added cards.");
    }
}
