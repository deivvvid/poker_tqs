package com.example.pokerapp.model;

import java.util.List;

public interface IDeck {
    void shuffle();
    Card dealCard();
    void resetDeck();
    List<Card> getCards();
}