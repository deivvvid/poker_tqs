package com.example.pokerapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.pokerapp.model.*;
import com.example.pokerapp.view.*;

import javafx.scene.image.Image;

public class MainController {

	public IMainView mainView;
	public Game game;
	public int startPlayerCoins = 1000;
	public int placedCoins;
	public int stage;
	public List<Card> selectedCards;
	
	public MainController(IMainView mv) {
		mainView = mv;
		mainView.setMainController(this);
		game = new Game(startPlayerCoins);
		mainView.createViewElements();
		updateViewPlayerCoins();
		stage = 0;
		placedCoins = 0;
		selectedCards = new ArrayList<Card>();
	}
	
	public void updateViewPlayerCoins() {
		mainView.setPlayerCoins(String.valueOf(game.getPlayer().getCoins()));
	}
	
	public void updateViewAnteBet() {
		mainView.setAnteBet(String.valueOf(game.getTable().getAnteBet()));
	}
	
	public void updateViewCallBet() {
		mainView.setCallBet(String.valueOf(game.getTable().getCallBet()));
	}
	
	public void placeCoin(int value) {
		System.out.println(stage == 0 && (placedCoins + value) * 3 <= game.getPlayer().getCoins());
		System.out.println(stage);
		System.out.println(game.getPlayer().getCoins());
		if (stage == 0 && (placedCoins + value) * 3 <= game.getPlayer().getCoins()) {
			placedCoins += value;
			mainView.setPlacedCoins(String.valueOf(placedCoins));
		}
	}
	
	public void removePlacedCoins() {
		if (stage == 0 || stage >= 8) {
			placedCoins = 0;
			mainView.setPlacedCoins("0");
		}
	}
	
	public int pickCard(Boolean table, int index) {
		if (stage > 1) {
			if (table) {
				System.out.println(game.getTable().getCommunityCards().get(index));
				selectedCards.add(game.getTable().getCommunityCards().get(index));
			} else {
				System.out.println(game.getPlayer().getHand().get(index));
				selectedCards.add(game.getPlayer().getHand().get(index));
			}
			if (stage >= 6) {
				selectWinner();
			}
			stage += 1;
			return 1;
		}
		return 0;
	}
	
	public void next() {
		if (stage >= 7) {
			stage+=1;
			game.getTable().resetTable();
			game.getPlayer().getHand().clear();
			game.getDealer().getHand().clear();
			updateViewPlayerCoins();
			updateViewAnteBet();
			updateViewCallBet();
			stage = 0;
			removePlacedCoins();
			selectedCards = new ArrayList<Card>();
			viewGameCards();
		}
	}
	
	public void selectWinner() {
		List<Card> dealerCombinations = new ArrayList<>();
		dealerCombinations.addAll(game.getTable().getCommunityCards());
		dealerCombinations.addAll(game.getDealer().getHand());
		List<Card> bestHand = Game.bestHand(dealerCombinations);
		List<Card> minHand = Arrays.asList(
	            new Card(Card.Suit.HEARTS, Card.Rank.FOUR),
	            new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR),
	            new Card(Card.Suit.CLUBS, Card.Rank.THREE),
	            new Card(Card.Suit.SPADES, Card.Rank.TWO),
	            new Card(Card.Suit.HEARTS, Card.Rank.FIVE)
	        );
		System.out.println(Game.classifyHand(selectedCards));
		System.out.println(Game.classifyHand(bestHand));
		System.out.println(Game.compareHands(bestHand, minHand));
		System.out.println(Game.compareHands(selectedCards, bestHand));
		if (Game.compareHands(bestHand, minHand) < 0 || Game.compareHands(selectedCards, bestHand) > 0) {
			game.getPlayer().addCoins(game.getTable().getAnteBet() * 
					Game.payRatio(Game.classifyHand(selectedCards)));
			game.getPlayer().addCoins(game.getTable().getCallBet());
			System.out.println("Player Wins");
		} else if (Game.compareHands(selectedCards, bestHand) == 0) {
			game.getPlayer().addCoins(game.getTable().getAnteBet());
			game.getPlayer().addCoins(game.getTable().getCallBet());
			System.out.println("Tie");
		} else {
			System.out.println("Player Loose");
		}
		List<Image> g = new ArrayList<Image>();
		for (Card c : game.getDealer().getHand()) {
			g.add(new Image(getClass().getResource("/" + c.getImagePath()).toString()));
		}
		mainView.populateTopHBox(g);
	}
	
	public void makeBet() {
		if (stage == 0) {
			if (placedCoins > 0) {
				game.startRound(placedCoins);
				updateViewPlayerCoins();
				updateViewAnteBet();
				viewGameCards();
				stage = 1;
			}
		} else if (stage == 1) {
			game.playerCalls();
			updateViewPlayerCoins();
			updateViewCallBet();
			viewGameCards();
			stage = 2;
		}
	}
	
	public void retire() {
		if (stage == 1) {
			game.getTable().resetTable();
			game.getPlayer().getHand().clear();
			game.getDealer().getHand().clear();
			updateViewPlayerCoins();
			updateViewAnteBet();
			updateViewCallBet();
			stage = 0;
			removePlacedCoins();
			selectedCards = new ArrayList<Card>();
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
