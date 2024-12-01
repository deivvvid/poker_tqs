package com.example.pokerapp.model;

import java.util.*;

public class Game {
    private Deck deck;
    private Dealer dealer;
    private Player player;
    private Table table;

    public Game(int playerChips) {
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.player = new Player("Player1", new ArrayList<Card>(), playerChips);
        this.table = new Table();
        deck.shuffle();
    }

    public void startRound(int ante) {
        table.setAnteBet(ante);
        player.makeBet(ante);
        player.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        table.addCommunityCard(deck.dealCard());
        table.addCommunityCard(deck.dealCard());
        table.addCommunityCard(deck.dealCard());
    }

    public void playerCalls() {
    	if (table.getAnteBet() != 0) {
	        int callBet = table.getAnteBet() * 2;
	        table.setCallBet(callBet);
	        player.makeBet(callBet);
	        table.addCommunityCard(deck.dealCard());
	        table.addCommunityCard(deck.dealCard());
    	}
    }
    
    public Deck getDeck() {
        return deck;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public Table getTable() {
        return table;
    }
}