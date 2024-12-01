package com.example.pokerapp.view;

import com.example.pokerapp.controller.MainController;

public class MockView implements IMainView {
	
	private String playerCoins;
	private String placedCoins;
	private MainController mc;
	
	public MockView() {
		
	}
	
	public void createViewElements() {
		playerCoins = "Player coins: 1000";
		placedCoins = "0";
	}
	
	public void setPlayerCoins(String s) {
		playerCoins = s;
	}
	
	public void setPlacedCoins(String s) {
    	placedCoins = s;
    }
	
    public String getPlayerCoins() {
    	return playerCoins;
    }
    
    public String getPlacedCoins() {
    	return placedCoins;
    }
    
    public void setMainController(MainController mc) {
    	this.mc = mc;
    }
}
