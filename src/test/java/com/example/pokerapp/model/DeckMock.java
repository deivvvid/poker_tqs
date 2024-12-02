package com.example.pokerapp.model;

import java.util.List;
import java.util.Random;

public class DeckMock implements IDeck {
	
	int next1 = 0;
	int next2 = 0;
	
	@Override
	public void shuffle() {
		Random random = new Random();
        Card.Suit randomSuit = Card.Suit.values()[random.nextInt(Card.Suit.values().length)];
        Card.Rank randomRank = Card.Rank.values()[random.nextInt(Card.Rank.values().length)];
	}

	@Override
	public Card dealCard() {
		Random random = new Random();
        Card.Suit randomSuit = Card.Suit.values()[random.nextInt(Card.Suit.values().length)];
        Card.Rank randomRank = Card.Rank.values()[random.nextInt(Card.Rank.values().length)];
		return new Card(Card.Suit.values()[next1], Card.Rank.values()[next2]);
	}

	@Override
	public void resetDeck() {
		next1 = 0;
		next2 = 0;
		Random random = new Random();
        Card.Suit randomSuit = Card.Suit.values()[random.nextInt(Card.Suit.values().length)];
        Card.Rank randomRank = Card.Rank.values()[random.nextInt(Card.Rank.values().length)];
	}

	@Override
	public List<Card> getCards() {
		return null;
	}

}
