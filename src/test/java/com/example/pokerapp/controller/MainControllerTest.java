package com.example.pokerapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.pokerapp.view.*;

import javafx.application.Platform;

public class MainControllerTest {
	
	@BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

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
		assertEquals("COINS: 1000", mc.mainView.getPlayerCoins(), "COINS: 1000 should be the correct message");
	}
	
	@Test
	public void placeCoinTest() {
		MainController mc = new MainController(new MockView());
		mc.placeCoin(5);
		assertEquals(5, mc.placedCoins);
		assertEquals("PLACED COINS: 5", mc.mainView.getPlacedCoins());
	}
	
	@Test
	public void placeCoinTestDoesntSurpassAntePlusCall() {
		MainController mc = new MainController(new MockView());
		mc.placeCoin(5);
		assertEquals(5, mc.placedCoins);
		assertEquals("PLACED COINS: 5", mc.mainView.getPlacedCoins());
		mc.placeCoin(329);
		assertEquals(5, mc.placedCoins);
		assertEquals("PLACED COINS: 5", mc.mainView.getPlacedCoins());
	}
	
	@Test
	public void removePlacedCoinsTest() {
		MainController mc = new MainController(new MockView());
		mc.removePlacedCoins();
		assertEquals(0, mc.placedCoins);
		assertEquals("PLACED COINS: 0", mc.mainView.getPlacedCoins());
	}
	
	@Test
	public void makeBetTest() {
		MainController mc = new MainController(new MockView());
		mc.placeCoin(5);
		mc.makeBet();
		assertNotEquals(0, mc.game.getTable().getAnteBet());
		assertEquals("COINS: 995", mc.mainView.getPlayerCoins(), "COINS: 995 should be the correct message");
		assertEquals(995, mc.game.getPlayer().getCoins(), "995 should be the correct message");
	}
	
	@Test
	public void cantMakeBetWhen0() {
		MainController mc = new MainController(new MockView());
		mc.makeBet();
		assertEquals(0, mc.game.getPlayer().getHand().size());
	}
	
	@Test
	public void updateViewAnteBetTest() {
		MainController mc = new MainController(new MockView());
		mc.placeCoin(5);
		mc.makeBet();
		assertEquals("ANTE: 5", mc.mainView.getAnteBet());
	}
	
	@Test
	public void viewGameCardsTest() {
		MainController mc = new MainController(new MockView());
		mc.viewGameCards();
	}
}
