package com.example.pokerapp.view;

import java.util.ArrayList;
import java.util.List;

import com.example.pokerapp.controller.MainController;

import javafx.scene.image.Image;

public class MockView implements IMainView {
	
	private String playerCoins;
	private String placedCoins;
	private MainController mc;
	private List<String> top;
	private List<String> mid;
	private List<String> bot;
	private String anteBet;
	private String callBet;
	
	public MockView() {
		
	}
	
	public void createViewElements() {
		playerCoins = "COINS: 1000";
		placedCoins = "PLACED COINS: 0";
		anteBet = "ANTE: No bet";
		anteBet = "CALL: No bet";
		top = new ArrayList<String>();
		mid = new ArrayList<String>();
		bot = new ArrayList<String>();
	}
	
	public void setPlayerCoins(String s) {
		playerCoins = "COINS: " + s;
	}
	
	public void setPlacedCoins(String s) {
    	placedCoins = "PLACED COINS: " + s;
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
    
    public int populateDealerCardsBox(List<String> strings) {
        top.clear();
        for (String s : strings) {
            top.add(s);
        }
        return top.size();
    }

    public int populateTableCardsBox(List<String> strings) {
        mid.clear();
        for (String s : strings) {
            mid.add(s);
        }
        return mid.size();
    }

    public int populatePlayerCardsBox(List<String> strings) {
        bot.clear();
        for (String s : strings) {
            bot.add(s);
        }
        return bot.size();
    }
    
    public void setAnteBet(String s) {
    	anteBet = "ANTE: " + s;
    }
    
    public String getAnteBet() {
    	return anteBet;
    }
    
    public void setCallBet(String s) {
    	anteBet = "ANTE: " + s;
    }
    
    public String getCallBet() {
    	return anteBet;
    }
}
