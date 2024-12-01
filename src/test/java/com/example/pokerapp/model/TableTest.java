package com.example.pokerapp.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void testAddCommunityCard() {
        Table table = new Table();
        Card card = new Card(Card.Suit.DIAMONDS, Card.Rank.A);
        table.addCommunityCard(card);
        List<Card> communityCards = table.getCommunityCards();

        assertEquals(1, communityCards.size());
        assertEquals(card, communityCards.get(0));
    }
    
    @Test
    public void testAddCommunityCard_correctlyAddsCard() {
    	Table table = new Table();
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.A);
        int quantityBefore = table.getCommunityCards().size();
        
        assertDoesNotThrow(() -> table.addCommunityCard(card));
        assertEquals(quantityBefore + 1, table.getCommunityCards().size(), "Card added incorrectly.");
    }

    @Test
    void testSetAndGetAnteBet() {
        Table table = new Table();
        table.setAnteBet(100);
        assertEquals(100, table.getAnteBet());
    }

    @Test
    void testSetAndGetCallBet() {
        Table table = new Table();
        table.setCallBet(200);
        assertEquals(200, table.getCallBet());
    }

    @Test
    void testResetTable() {
        Table table = new Table();
        table.addCommunityCard(new Card(Card.Suit.DIAMONDS, Card.Rank.A));
        table.setAnteBet(100);
        table.setCallBet(200);

        table.resetTable();

        assertTrue(table.getCommunityCards().isEmpty());
        assertEquals(0, table.getAnteBet());
        assertEquals(0, table.getCallBet());
    }
}