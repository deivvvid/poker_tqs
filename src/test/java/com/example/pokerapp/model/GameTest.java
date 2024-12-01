package com.example.pokerapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
	@Test
    void testGameInitialization() {
        Game game = new Game(1000);

        assertNotNull(game.getDeck(), "Deck should be initialized");
        assertNotNull(game.getDealer(), "Dealer should be initialized");
        assertNotNull(game.getPlayer(), "Player should be initialized");
        assertNotNull(game.getTable(), "Table should be initialized");
    }
}
