package com.example.pokerapp.controller;

import com.example.pokerapp.model.*;
import com.example.pokerapp.view.*;

public class MainController {

	public IMainView mainView;
	public Game game;
	public int startPlayerCoins = 1000;
	public int placedCoins = 0;
	
	public MainController(IMainView mv) {
		mainView = mv;
		game = new Game(startPlayerCoins);
		updateViewPlayerCoins();
	}
	
	public void updateViewPlayerCoins() {
		mainView.setPlayerCoins("Player coins: " + game.getPlayer().getCoins());	
	}
}
