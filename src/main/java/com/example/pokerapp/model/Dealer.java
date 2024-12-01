package com.example.pokerapp.model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private List<Card> hand;

    public Dealer() {
        this.hand = new ArrayList<>();
    }

    public Dealer(List<Card> hand) {
        if (hand == null || hand.isEmpty()) {
            throw new IllegalArgumentException("Hand cannot be null or empty.");
        }
        this.hand = new ArrayList<>(hand);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        if (hand == null || hand.isEmpty()) {
            throw new IllegalArgumentException("Hand cannot be null or empty.");
        }
        this.hand = new ArrayList<>(hand);
    }

    public void addCard(Card card) {
        if (hand.size() >= 2) {
            throw new IllegalArgumentException("Cannot add a card because the hand is full.");
        }
        if (card == null) {
            throw new IllegalArgumentException("Cannot add a null card.");
        }
        hand.add(card);
    }

    @Override
    public String toString() {
        return "Dealer{hand=" + hand + "}";
    }
}
