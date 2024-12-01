package com.example.pokerapp.model;

import java.util.*;

public class Table {
    private List<Card> communityCards;
    private int anteBet;
    private int callBet;

    public Table() {
        this.communityCards = new ArrayList<>();
        this.anteBet = 0;
        this.callBet = 0;
    }

    public void addCommunityCard(Card card) {
    	int quantity_communty_cards_before = communityCards.size();
        communityCards.add(card);
        if (quantity_communty_cards_before >= communityCards.size()) {
        	throw new IllegalArgumentException("Card added incorrrectly.");
        }
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void setAnteBet(int amount) {
        anteBet = amount;
    }

    public int getAnteBet() {
        return anteBet;
    }

    public void setCallBet(int amount) {
        callBet = amount;
    }

    public int getCallBet() {
        return callBet;
    }

    public void resetTable() {
        communityCards.clear();
        anteBet = 0;
        callBet = 0;
    }
}