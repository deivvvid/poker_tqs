package com.example.pokerapp.model;

import java.util.*;
import java.util.stream.Collectors;

import com.example.pokerapp.model.Card.Rank;

public class Game {
	public enum HandRank {
        ROYAL_FLUSH,
        STRAIGHT_FLUSH,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        THREE_OF_A_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD
    }
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
    	deck.resetDeck();
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
    
    public static HandRank classifyHand(List<Card> hand) {
        hand.sort(Comparator.comparingInt(c -> c.getRank().ordinal()));
        Map<Card.Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        Map<Card.Suit, Long> suitCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));
        boolean isFlush = suitCounts.containsValue(5L);
        boolean isStraight = isConsecutive(hand.stream()
                .map(c -> c.getRank().ordinal())
                .sorted()
                .collect(Collectors.toList()));
        if (isStraight && isFlush && hand.get(0).getRank() == Rank.TEN) return HandRank.ROYAL_FLUSH;
        if (isStraight && isFlush) return HandRank.STRAIGHT_FLUSH;
        if (rankCounts.containsValue(4L)) return HandRank.FOUR_OF_A_KIND;
        if (rankCounts.containsValue(3L) && rankCounts.containsValue(2L)) return HandRank.FULL_HOUSE;
        if (isFlush) return HandRank.FLUSH;
        if (isStraight) return HandRank.STRAIGHT;
        if (rankCounts.containsValue(3L)) return HandRank.THREE_OF_A_KIND;
        if (rankCounts.values().stream().filter(count -> count == 2L).count() == 2) return HandRank.TWO_PAIR;
        if (rankCounts.containsValue(2L)) return HandRank.ONE_PAIR;
        return HandRank.HIGH_CARD;
    }

    public static boolean isConsecutive(List<Integer> ranks) {
        ranks.sort(Integer::compareTo);

        Boolean aux = true;
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (ranks.get(i) + 1 != ranks.get(i + 1)) {
                aux = false; 
                break;
            }
        }
        if (aux) {
            return true;
        }
        List<Integer> adjustedRanks = new ArrayList<>();
        for (Integer rank : ranks) {
            if (rank == 12) {
                adjustedRanks.add(-1);
            } else {
                adjustedRanks.add(rank);
            }
        }
        adjustedRanks.sort(Integer::compareTo);

        for (int i = 0; i < adjustedRanks.size() - 1; i++) {
            if (adjustedRanks.get(i) + 1 != adjustedRanks.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static int compareHands(List<Card> hand1, List<Card> hand2) {
        HandRank rank1 = classifyHand(hand1);
        HandRank rank2 = classifyHand(hand2);

        if (rank1 != rank2) {
            return rank2.ordinal() - rank1.ordinal();
        }

        List<Integer> hand1Values = hand1.stream().map(
        		c -> c.getRank().ordinal()).sorted(Collections.reverseOrder()).collect(Collectors.toList());
        List<Integer> hand2Values = hand2.stream().map(
        		c -> c.getRank().ordinal()).sorted(Collections.reverseOrder()).collect(Collectors.toList());

        for (int i = 0; i < hand1Values.size(); i++) {
            int comparison = hand1Values.get(i) - hand2Values.get(i);
            if (comparison != 0) {
                return comparison;
            }
        }

        return 0;
    }
    
    public static List<Card> bestHand(List<Card> cards) {
        List<List<Card>> allCombinations = new ArrayList<>();
        generateCombinations(cards, 5, 0, new ArrayList<>(), allCombinations);

        List<Card> bestHand = null;
        HandRank bestRank = null;

        for (List<Card> combination : allCombinations) {
            HandRank currentRank = classifyHand(combination);

            if (bestHand == null || compareHands(combination, bestHand) > 0) {
                bestHand = combination;
                bestRank = currentRank;
            }
        }

        return bestHand;
    }

    private static void generateCombinations(List<Card> cards, int size, int start, 
    		List<Card> current, List<List<Card>> allCombinations) {
        if (current.size() == size) {
            allCombinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < cards.size(); i++) {
            current.add(cards.get(i));
            generateCombinations(cards, size, i + 1, current, allCombinations);
            current.remove(current.size() - 1);
        }
    }

    public static int payRatio(HandRank hr) {
    	switch(hr) {
    	case ROYAL_FLUSH:
    		return 100;
    	case STRAIGHT_FLUSH:
    		return 20;
    	case FOUR_OF_A_KIND:
    		return 10;
    	case FULL_HOUSE:
    		return 3;
    	case FLUSH:
    		return 2;
    	case STRAIGHT:
    		return 1;
    	case THREE_OF_A_KIND:
    		return 1;
    	case TWO_PAIR:
    		return 1;
    	case ONE_PAIR:
    		return 1;
    	case HIGH_CARD:
    		return 1;
    	default:
    		return 1;
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