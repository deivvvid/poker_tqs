package com.example.pokerapp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class DeckTest {
	
	private Deck deck;

    @BeforeEach
    void setUp() {        
        deck = new Deck();
    }
    
    @Test
    void testDeckConstructor_initializesCardsList() {
        assertNotNull(deck.getCards(), "The cards list should not be null after constructor is called.");
    }
	
	@Test
    public void testConstructor() {
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
    public void testConstructor_tooManyCards() {
        assertDoesNotThrow(() -> deck = new Deck());
        assertEquals(52, deck.getCards().size(), "Too many cards in teh deck.");
    }
	
	@Test
    void testShuffle() {
        List<Card> originalOrder = new ArrayList<>(deck.getCards());
        deck.shuffle();
        assertEquals(originalOrder.size(), deck.getCards().size(), "El tamaño del mazo no debe cambiar al barajar.");
        assertNotEquals(originalOrder, deck.getCards(), "El orden de las cartas debería ser diferente tras barajar.");
    }
	
	@Test
    void testDealCard() {
        int initialSize = deck.getCards().size();
        Card dealtCard = deck.dealCard();
        assertEquals(initialSize - 1, deck.getCards().size(), "El tamaño del mazo debe disminuir en 1 tras repartir una carta.");
        assertFalse(deck.getCards().contains(dealtCard), "La carta repartida no debería estar en el mazo.");
    }
	
	@Test
	void testDealCard0() {
		Card c = deck.getCards().get(0);
	}
	
	@Test
    void testResetDeck() {
		List<Card> originalOrder = new ArrayList<>(deck.getCards());
        deck.resetDeck();
        assertEquals(52, deck.getCards().size(), "El mazo debe contener 52 cartas tras resetear.");
        assertEquals(originalOrder.size(), deck.getCards().size(), "El tamaño del mazo no debe cambiar al barajar.");
        assertNotEquals(originalOrder, deck.getCards(), "El orden de las cartas debería ser diferente tras barajar.");
    }
}
