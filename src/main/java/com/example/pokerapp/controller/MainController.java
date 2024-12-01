package com.example.pokerapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.pokerapp.model.*;
import com.example.pokerapp.view.*;

import javafx.scene.image.Image;

public class MainController {

	public IMainView mainView;
	public Game game;
	public int startPlayerCoins = 1000;
	public int placedCoins = 0;
	
	public MainController(IMainView mv) {
		mainView = mv;
		mainView.setMainController(this);
		game = new Game(startPlayerCoins);
		mainView.createViewElements();
		updateViewPlayerCoins();
	}
	
	public void updateViewPlayerCoins() {
		mainView.setPlayerCoins(String.valueOf(game.getPlayer().getCoins()));
	}
	
	public void updateViewAnteBet() {
		mainView.setAnteBet(String.valueOf(game.getTable().getAnteBet()));
	}
	
	public void placeCoin(int value) {
		if ((placedCoins + value) * 3 <= game.getPlayer().getCoins()) {
			placedCoins += value;
			mainView.setPlacedCoins(String.valueOf(placedCoins));
		}
	}
	
	public void removePlacedCoins() {
		placedCoins = 0;
		mainView.setPlacedCoins("0");
	}
	
	public void makeBet() {
		if (placedCoins > 0) {
			game.startRound(placedCoins);
			updateViewPlayerCoins();
			updateViewAnteBet();
			viewGameCards();
		}
	}
	
	public int viewGameCards() {
		int sizeList = 0;
		List<Image> a = new ArrayList<Image>();
		for (Card c : game.getDealer().getHand()) {
			a.add(new Image(getClass().getResource("/reverse.png").toString()));
		}
		sizeList+=mainView.populateTopHBox(a);
		List<Image> b = new ArrayList<Image>();
		for (Card c : game.getTable().getCommunityCards()) {
			b.add(new Image(getClass().getResource("/" + c.getImagePath()).toString()));
		}
		sizeList+=mainView.populateMiddleHBox(b);
		List<Image> d = new ArrayList<Image>();
		for (Card c : game.getPlayer().getHand()) {
			d.add(new Image(getClass().getResource("/" + c.getImagePath()).toString()));
		}
		sizeList+=mainView.populateBottomHBox(d);
		return sizeList;
	}
}
