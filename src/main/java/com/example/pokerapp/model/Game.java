package com.example.pokerapp.model;

import java.util.*;
import java.util.stream.Collectors;

import com.example.pokerapp.model.Card.Rank;

public class Game {
	
	// Enum que representa los diferentes tipos de combinaciones posibles en una mano de poker.
	public enum HandRank {
	    ROYAL_FLUSH,       // Escalera Real
	    STRAIGHT_FLUSH,    // Escalera de color
	    FOUR_OF_A_KIND,    // Póker (cuatro iguales)
	    FULL_HOUSE,        // Full
	    FLUSH,             // Color
	    STRAIGHT,          // Escalera
	    THREE_OF_A_KIND,   // Trío
	    TWO_PAIR,          // Doble pareja
	    ONE_PAIR,          // Pareja
	    HIGH_CARD          // Carta alta
	}
	
	private Deck deck;       // El mazo de cartas
	private Dealer dealer;   // El dealer (repartidor)
	private Player player;   // El jugador
	private Table table;     // La mesa del juego

	// Constructor que inicializa el mazo, dealer, jugador y la mesa. Baraja las cartas.
    public Game(int playerChips) {
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.player = new Player("Player1", new ArrayList<Card>(), playerChips);
        this.table = new Table();
        deck.shuffle();
    }

    // Comienza una ronda, repartiendo cartas y colocando las cartas comunitarias en la mesa.
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

    // El jugador hace una apuesta de igualar la apuesta anterior (llamar).
    public void playerCalls() {
    	if (table.getAnteBet() != 0) {
	        int callBet = table.getAnteBet() * 2;
	        table.setCallBet(callBet);
	        player.makeBet(callBet);
	        table.addCommunityCard(deck.dealCard());
	        table.addCommunityCard(deck.dealCard());
    	}
    }
    
	 // Clasifica la mano recibida, evaluando las combinaciones posibles de cartas
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

    // Verifica si las cartas forman una secuencia consecutiva, tomando en cuenta el caso del As
    public static boolean isConsecutive(List<Integer> ranks) {
    	
    	// Ordena las cartas por su valor numérico
        ranks.sort(Integer::compareTo);

        Boolean aux = true;
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (ranks.get(i) + 1 != ranks.get(i + 1)) { // Verifica si las cartas son consecutivas
                aux = false; 
                break;
            }
        }
        if (aux) {
            return true;
        }
        List<Integer> adjustedRanks = new ArrayList<>();
        
        // Ajusta el As si es necesario para ser considerado como 1
        for (Integer rank : ranks) {
            if (rank == 12) {
                adjustedRanks.add(-1);
            } else {
                adjustedRanks.add(rank);
            }
        }
        adjustedRanks.sort(Integer::compareTo);

        for (int i = 0; i < adjustedRanks.size() - 1; i++) {
            if (adjustedRanks.get(i) + 1 != adjustedRanks.get(i + 1)) { // Verifica si las cartas son consecutivas
                return false;
            }
        }
        return true;
    }

    // Compara dos manos y devuelve un valor indicando cuál es mejor (usando las clasificaciones)
    public static int compareHands(List<Card> hand1, List<Card> hand2) {
    	
    	// Clasifica cada mano
        HandRank rank1 = classifyHand(hand1);
        HandRank rank2 = classifyHand(hand2);

        // Compara el rango de las manos, si son rangos diferentes ya retorna cual es mejor
        if (rank1 != rank2) {
            return rank2.ordinal() - rank1.ordinal();
        }

        // Si no, iremos a hacer el desempate
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
    
    // Determina la mejor mano de 5 cartas entre todas las combinaciones posibles de cartas
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

        // Devuelve la mejor mano encontrada
        return bestHand;
    }

    // Genera todas las combinaciones posibles de cartas de un tamaño específico (5 cartas)
    public static void generateCombinations(List<Card> cards, int size, int start, 
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

    // Retorna la relación de pago asociada con el tipo de mano
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
    
    // Métodos getter para acceder a las propiedades del mazo, dealer, jugador y mesa.
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