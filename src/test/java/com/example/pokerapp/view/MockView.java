package com.example.pokerapp.view;

public class MockView implements IMainView {
	
	private String playerCoins = "Player coins: 1000";
	
	public MockView() {
		
	}
	
	public void setPlayerCoins(String s) {
		playerCoins = s;
	}
	
    public String getPlayerCoins() {
    	return playerCoins;
    }
}
