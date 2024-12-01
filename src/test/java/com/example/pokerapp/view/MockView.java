package com.example.pokerapp.view;

import java.util.ArrayList;
import java.util.List;

import com.example.pokerapp.controller.MainController;

import javafx.scene.image.Image;

public class MockView implements IMainView {
	
	private String playerCoins;
	private String placedCoins;
	private MainController mc;
	private List<Image> top;
	private List<Image> mid;
	private List<Image> bot;
	private String anteBet;
	private String callBet;
	
	public MockView() {
		
	}
	
	public void createViewElements() {
		playerCoins = "COINS: 1000";
		placedCoins = "PLACED COINS: 0";
		anteBet = "ANTE: No bet";
		anteBet = "CALL: No bet";
		top = new ArrayList<Image>();
		mid = new ArrayList<Image>();
		bot = new ArrayList<Image>();
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
    
    public int populateTopHBox(List<Image> images) {
        top.clear();
        for (Image i : images) {
            top.add(i);
        }
        return top.size();
    }

    public int populateMiddleHBox(List<Image> images) {
        mid.clear();
        for (Image i : images) {
            mid.add(i);
        }
        return mid.size();
    }

    public int populateBottomHBox(List<Image> images) {
        bot.clear();
        for (Image i : images) {
            bot.add(i);
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
