package com.example.pokerapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.example.pokerapp.view.*;

public class MainControllerTest {

	@Test
	public void constructorTest() {
		MainController mc = new MainController(new MockView());
		assertNotNull(mc.mainView, "mainView should not be null.");
		assertNotNull(mc.game, "mainView should not be null.");
		assertEquals(1000, mc.startPlayerCoins, "startPlayerCoins should be 1000");
		assertEquals(0, mc.placedCoins, "placedCoins should be 0");
	}
	
	@Test
	public void updateViewPlayerCoinsTest() {
		MainController mc = new MainController(new MockView());
		assertEquals("Player coins: 1000", mc.mainView.getPlayerCoins(), "Player coins: 1000 should be the correct message");
	}
	
	@Test
	public void placeCoinTest() {
		MainController mc = new MainController(new MockView());
		mc.placeCoin(5);
		mc.mainView.setPlacedCoins(String.valueOf(mc.placedCoins));
		assertEquals(5, mc.placedCoins);
		assertEquals("5", mc.mainView.getPlacedCoins());
	}
	
	@Test
	public void removePlacedCoinsTest() {
		MainController mc = new MainController(new MockView());
		mc.removePlacedCoins();
		mc.mainView.setPlacedCoins(String.valueOf(mc.placedCoins));
		assertEquals(0, mc.placedCoins);
		assertEquals("0", mc.mainView.getPlacedCoins());
	}
}
