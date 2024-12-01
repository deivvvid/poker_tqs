package com.example.pokerapp.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
	
	private Game game;
	
	@BeforeEach
	void setUp() {
		game = new Game(1000);
	}
	
	@Test
    void testGameConstructorInitializationAndGetters() {
        assertNotNull(game.getDeck(), "Deck should be initialized");
        assertNotNull(game.getDealer(), "Dealer should be initialized");
        assertNotNull(game.getPlayer(), "Player should be initialized");
        assertNotNull(game.getTable(), "Table should be initialized");
    }
	
	@Test
	void testStartRound() {
		game.startRound(5);
		assertEquals(5, game.getTable().getAnteBet(), "Ante bet should be set to 5");
		assertEquals(1000 - 5, game.getPlayer().getCoins(), "Player coins should be set to 1000 - 5");
		assertEquals(2, game.getPlayer().getHand().size(), "Player hand should be size 2");
		assertEquals(2, game.getDealer().getHand().size(), "Dealer hand should be size 2");
		assertEquals(3, game.getTable().getCommunityCards().size(), "Table cards should be size 3");
	}
	
	@Test
	void testPlayerCalls() {
		game.startRound(5);
		game.playerCalls();
		assertNotEquals(0, game.getTable().getAnteBet(), "Ante bet should be not 0");
		assertEquals(5*2, game.getTable().getCallBet(), "Call bet should be 5*2");
		assertEquals(1000 - 5 - 2*5, game.getPlayer().getCoins(), "Player coins should be set to 1000 - 5 - 2*5");
		assertEquals(5, game.getTable().getCommunityCards().size(), "Table cards should be size 5");
	}
}
