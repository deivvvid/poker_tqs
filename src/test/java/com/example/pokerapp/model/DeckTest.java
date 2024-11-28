package com.example.pokerapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class DeckTest {
	
	@Test
    public void testFullDeckConstructorCreates52Cards() {
        Deck deck = new Deck();

        List<Card> cards = deck.getCards();
        assertEquals(52, cards.size(), "Full deck should contain exactly 52 cards.");

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
	public void testClearCardsCorrectly() {
		Deck deck = new Deck();
		deck.clearCards();
		assertEquals(0, deck.getCards().size(), "Should be size 0 because list is cleared.");
	}
	
	@Test
	public void testAddsAllCardsCorrectly() {
		Deck deck = new Deck();
		deck.addAllCardsToDeck();
		assertEquals(104, deck.getCards().size(), "Should be size 104 because we added 2 all cards two times, in constructor"
				+ "and in addAllCardsToDeck() method.");
	}
	
	@Test
	public void testAddsCardCorrectly() {
		Deck deck = new Deck();
		deck.clearCards();
		Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
		deck.addCard(card);
		assertTrue(deck.getCards().contains(card), "Deck card list should contain the card.");
	}
	
	@Test
	public void testRemoveCardCorrectly() {
		Deck deck = new Deck();
		Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
		deck.removeCard(card);
		assertFalse(deck.getCards().contains(card), "Deck card list should NOT contain the card.");
	}
	
	@Test
    public void testGetCardsReturnsCopyOfList() {
        Deck deck = new Deck();

        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        deck.addCard(card);

        List<Card> returnedCards = deck.getCards();

        assertEquals(53, deck.getCards().size(), "Original deck list should remain unchanged.");
    }

    @Test
    public void testGetCardsReturnsCorrectSnapshot() {
        Deck deck = new Deck();
        deck.clearCards();

        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        deck.addCard(card);
        List<Card> snapshot = deck.getCards();

        Card anotherCard = new Card(Card.Suit.DIAMONDS, Card.Rank.K);
        deck.addCard(anotherCard);

        assertEquals(1, snapshot.size(), "Snapshot should not reflect changes after its creation.");
        assertFalse(snapshot.contains(anotherCard), "Snapshot should not contain newly added cards.");
    }
}
