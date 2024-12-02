package com.example.pokerapp.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

public class GameTest {
	
	private Game game;
	private Table tableMock;
	
	@BeforeEach
	void setUp() {
		tableMock = mock(Table.class);
		game = new Game(1000, new DeckMock(), tableMock);
	}
	
	@Test
    void testGameConstructorInitializationAndGetters() {
        assertNotNull(game.getDeck(), "Deck should be initialized");
        assertNotNull(game.getDealer(), "Dealer should be initialized");
        assertNotNull(game.getPlayer(), "Player should be initialized");
        assertNotNull(game.getTable(), "Table should be initialized");
    }
	
	@Test
	void testStartRound() {
		when(tableMock.getAnteBet()).thenReturn(5);
		when(tableMock.getCommunityCards()).thenReturn(Arrays.asList(new Card(Card.Suit.HEARTS, Card.Rank.A),
		        new Card(Card.Suit.DIAMONDS, Card.Rank.K),
		        new Card(Card.Suit.CLUBS, Card.Rank.Q)));
		game.startRound(5);
		verify(tableMock).setAnteBet(5);
		assertEquals(5, game.getTable().getAnteBet(), "Ante bet should be set to 5");
		assertEquals(1000 - 5, game.getPlayer().getCoins(), "Player coins should be set to 1000 - 5");
		assertEquals(2, game.getPlayer().getHand().size(), "Player hand should be size 2");
		assertEquals(2, game.getDealer().getHand().size(), "Dealer hand should be size 2");
		assertEquals(3, game.getTable().getCommunityCards().size(), "Table cards should be size 3");
	}
	
	@Test
	void testPlayerCalls() {
		when(tableMock.getAnteBet()).thenReturn(5);
		when(tableMock.getCallBet()).thenReturn(5*2);
		game.startRound(5);
		verify(tableMock).setAnteBet(5);
		game.playerCalls();
		when(tableMock.getCommunityCards()).thenReturn(Arrays.asList(new Card(Card.Suit.HEARTS, Card.Rank.A),
		        new Card(Card.Suit.DIAMONDS, Card.Rank.K),
		        new Card(Card.Suit.CLUBS, Card.Rank.Q),
		        new Card(Card.Suit.CLUBS, Card.Rank.Q),
		        new Card(Card.Suit.CLUBS, Card.Rank.Q)));
		assertNotEquals(0, game.getTable().getAnteBet(), "Ante bet should be not 0");
		assertEquals(5*2, game.getTable().getCallBet(), "Call bet should be 5*2");
		assertEquals(1000 - 5 - 2*5, game.getPlayer().getCoins(), "Player coins should be set to 1000 - 5 - 2*5");
		assertEquals(5, game.getTable().getCommunityCards().size(), "Table cards should be size 5");
	}
	
	@Test
    public void testRoyalFlush() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TEN),
            new Card(Card.Suit.HEARTS, Card.Rank.J),
            new Card(Card.Suit.HEARTS, Card.Rank.Q),
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.A)
        );
        assertEquals(Game.HandRank.ROYAL_FLUSH, Game.classifyHand(hand), "Expected ROYAL_FLUSH, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testStraightFlush() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE),
            new Card(Card.Suit.HEARTS, Card.Rank.FOUR),
            new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
            new Card(Card.Suit.HEARTS, Card.Rank.SIX)
        );
        assertEquals(Game.HandRank.STRAIGHT_FLUSH, Game.classifyHand(hand), "Expected STRAIGHT_FLUSH, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testFourOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.K),
            new Card(Card.Suit.SPADES, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.TWO)
        );
        assertEquals(Game.HandRank.FOUR_OF_A_KIND, Game.classifyHand(hand), "Expected FOUR_OF_A_KIND, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testFullHouse() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.K),
            new Card(Card.Suit.SPADES, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.TWO)
        );
        assertEquals(Game.HandRank.FULL_HOUSE, Game.classifyHand(hand), "Expected FULL_HOUSE, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testFlush() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.FOUR),
            new Card(Card.Suit.HEARTS, Card.Rank.SIX),
            new Card(Card.Suit.HEARTS, Card.Rank.EIGHT),
            new Card(Card.Suit.HEARTS, Card.Rank.TEN)
        );
        assertEquals(Game.HandRank.FLUSH, Game.classifyHand(hand), "Expected FLUSH, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testStraight() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TWO),
            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
            new Card(Card.Suit.CLUBS, Card.Rank.FOUR),
            new Card(Card.Suit.SPADES, Card.Rank.FIVE),
            new Card(Card.Suit.HEARTS, Card.Rank.SIX)
        );
        assertEquals(Game.HandRank.STRAIGHT, Game.classifyHand(hand), "Expected STRAIGHT, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.K),
            new Card(Card.Suit.SPADES, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE)
        );
        assertEquals(Game.HandRank.THREE_OF_A_KIND, Game.classifyHand(hand), "Expected THREE_OF_A_KIND, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testTwoPair() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.TWO),
            new Card(Card.Suit.SPADES, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE)
        );
        assertEquals(Game.HandRank.TWO_PAIR, Game.classifyHand(hand), "Expected TWO_PAIR, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testOnePair() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.DIAMONDS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.TWO),
            new Card(Card.Suit.SPADES, Card.Rank.THREE),
            new Card(Card.Suit.HEARTS, Card.Rank.FOUR)
        );
        assertEquals(Game.HandRank.ONE_PAIR, Game.classifyHand(hand), "Expected ONE_PAIR, but got: " + Game.classifyHand(hand));
    }

    @Test
    public void testHighCard() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TWO),
            new Card(Card.Suit.CLUBS, Card.Rank.FOUR),
            new Card(Card.Suit.DIAMONDS, Card.Rank.SIX),
            new Card(Card.Suit.HEARTS, Card.Rank.EIGHT),
            new Card(Card.Suit.SPADES, Card.Rank.TEN)
        );
        assertEquals(Game.HandRank.HIGH_CARD, Game.classifyHand(hand), "Expected HIGH_CARD, but got: " + Game.classifyHand(hand));
    }
    
    @Test
    public void testConsecutiveRanks() {
        List<Integer> ranks = Arrays.asList(2, 3, 4, 5, 6);
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive, but got: " + ranks);
    }

    @Test
    public void testNonConsecutiveRanks() {
        List<Integer> ranks = Arrays.asList(2, 4, 5, 6, 7);
        assertFalse(Game.isConsecutive(ranks), "Expected ranks to not be consecutive, but got: " + ranks);
    }

    @Test
    public void testConsecutiveRanksWithLowAce() {
        List<Integer> ranks = Arrays.asList(12, 0, 1, 2, 3);
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive with low Ace, but got: " + ranks);
    }

    @Test
    public void testConsecutiveRanksWithHighAce() {
        List<Integer> ranks = Arrays.asList(10, 11, 12, 13, 14);
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive with high Ace, but got: " + ranks);
    }

    @Test
    public void testSingleCard() {
        List<Integer> ranks = Arrays.asList(5);
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive, but got: " + ranks);
    }

    @Test
    public void testEmptyList() {
        List<Integer> ranks = Arrays.asList();
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive (empty list), but got: " + ranks);
    }

    @Test
    public void testConsecutiveRanksWithWrapAroundAce() {
        List<Integer> ranks = Arrays.asList(12, 0, 1, 2, 3);
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive with wrap-around Ace, but got: " + ranks);
    }

    @Test
    public void testLargeListOfConsecutiveRanks() {
        // Test a larger list of consecutive ranks (e.g., 1 to 10)
        List<Integer> ranks = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertTrue(Game.isConsecutive(ranks), "Expected ranks to be consecutive, but got: " + ranks);
    }

    @Test
    public void testConsecutiveRanksWithJumps() {
        List<Integer> ranks = Arrays.asList(3, 5, 6, 7, 8);
        assertFalse(Game.isConsecutive(ranks), "Expected ranks to not be consecutive, but got: " + ranks);
    }
    
    @Test
    public void testCompareRoyalFlushVsStraightFlush() {
        List<Card> royalFlush = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TEN),
            new Card(Card.Suit.HEARTS, Card.Rank.J),
            new Card(Card.Suit.HEARTS, Card.Rank.Q),
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.A)
        );

        List<Card> straightFlush = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
            new Card(Card.Suit.HEARTS, Card.Rank.SIX),
            new Card(Card.Suit.HEARTS, Card.Rank.SEVEN),
            new Card(Card.Suit.HEARTS, Card.Rank.EIGHT),
            new Card(Card.Suit.HEARTS, Card.Rank.NINE)
        );

        int result = Game.compareHands(royalFlush, straightFlush);
        assertTrue(result > 0, "Royal Flush should beat a Straight Flush.");
    }

    @Test
    public void testCompareTwoPairs() {
        List<Card> hand1 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.A),
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.CLUBS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE)
        );

        List<Card> hand2 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.CLUBS, Card.Rank.Q),
            new Card(Card.Suit.HEARTS, Card.Rank.Q),
            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE)
        );

        int result = Game.compareHands(hand1, hand2);
        assertTrue(result > 0, "Hand with Aces should beat hand with Queens.");
    }

    @Test
    public void testCompareFullHouse() {
        List<Card> hand1 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.TWO),
            new Card(Card.Suit.DIAMONDS, Card.Rank.TWO),
            new Card(Card.Suit.CLUBS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.K)
        );

        List<Card> hand2 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.THREE),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE),
            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
            new Card(Card.Suit.CLUBS, Card.Rank.Q),
            new Card(Card.Suit.HEARTS, Card.Rank.Q)
        );

        int result = Game.compareHands(hand1, hand2);
        assertTrue(result > 0, "Full House of Kings should beat Full House of Queens.");
    }

    @Test
    public void testCompareOnePair() {
        List<Card> hand1 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.THREE),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE),
            new Card(Card.Suit.DIAMONDS, Card.Rank.TWO),
            new Card(Card.Suit.CLUBS, Card.Rank.FOUR),
            new Card(Card.Suit.HEARTS, Card.Rank.FIVE)
        );

        List<Card> hand2 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.FOUR),
            new Card(Card.Suit.HEARTS, Card.Rank.FOUR),
            new Card(Card.Suit.DIAMONDS, Card.Rank.THREE),
            new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
            new Card(Card.Suit.HEARTS, Card.Rank.SIX)
        );

        int result = Game.compareHands(hand1, hand2);
        assertTrue(result < 0, "Hand with pair of Fours should beat hand with pair of Threes.");
    }

    @Test
    public void testCompareHighCard() {
        List<Card> hand1 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.FOUR),
            new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
            new Card(Card.Suit.DIAMONDS, Card.Rank.SIX),
            new Card(Card.Suit.CLUBS, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE)
        );

        List<Card> hand2 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.SEVEN),
            new Card(Card.Suit.HEARTS, Card.Rank.EIGHT),
            new Card(Card.Suit.DIAMONDS, Card.Rank.NINE),
            new Card(Card.Suit.CLUBS, Card.Rank.TEN),
            new Card(Card.Suit.HEARTS, Card.Rank.J)
        );

        int result = Game.compareHands(hand1, hand2);
        assertTrue(result < 0, "Hand with higher high card (Jack) should win.");
    }
    
    @Test
    public void testCompareTwoPairsOfAces() {
        List<Card> hand1 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.A),
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE),
            new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE)
        );

        List<Card> hand2 = Arrays.asList(
            new Card(Card.Suit.CLUBS, Card.Rank.A),
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE),
            new Card(Card.Suit.CLUBS, Card.Rank.FIVE),
            new Card(Card.Suit.HEARTS, Card.Rank.TWO)
        );

        int result = Game.compareHands(hand1, hand2);
        assertTrue(result > 0, "Hand with higher kicker (3) should win.");
    }
    
    @Test
    public void testRoyalFlushWithSevenCards() {
        List<Card> hand = Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TEN),
            new Card(Card.Suit.HEARTS, Card.Rank.J),
            new Card(Card.Suit.HEARTS, Card.Rank.Q),
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.A),
            new Card(Card.Suit.CLUBS, Card.Rank.THREE),
            new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR)
        );

        List<Card> bestHand = Game.bestHand(hand);
        assertEquals(5, bestHand.size(), "The best hand should contain 5 cards.");
        assertTrue(bestHand.containsAll(Arrays.asList(
            new Card(Card.Suit.HEARTS, Card.Rank.TEN),
            new Card(Card.Suit.HEARTS, Card.Rank.J),
            new Card(Card.Suit.HEARTS, Card.Rank.Q),
            new Card(Card.Suit.HEARTS, Card.Rank.K),
            new Card(Card.Suit.HEARTS, Card.Rank.A)
        )), "The best hand should be a Royal Flush.");
    }
    
    @Test
    void testRoyalFlushMultiplier() {
        assertEquals(100, Game.payRatio(Game.HandRank.ROYAL_FLUSH));
    }

    @Test
    void testStraightFlushMultiplier() {
        assertEquals(20, Game.payRatio(Game.HandRank.STRAIGHT_FLUSH));
    }

    @Test
    void testFourOfAKindMultiplier() {
        assertEquals(10, Game.payRatio(Game.HandRank.FOUR_OF_A_KIND));
    }

    @Test
    void testFullHouseMultiplier() {
        assertEquals(3, Game.payRatio(Game.HandRank.FULL_HOUSE));
    }

    @Test
    void testFlushMultiplier() {
        assertEquals(2, Game.payRatio(Game.HandRank.FLUSH));
    }

    @Test
    void testStraightMultiplier() {
        assertEquals(1, Game.payRatio(Game.HandRank.STRAIGHT));
    }

    @Test
    void testThreeOfAKindMultiplier() {
        assertEquals(1, Game.payRatio(Game.HandRank.THREE_OF_A_KIND));
    }

    @Test
    void testTwoPairMultiplier() {
        assertEquals(1, Game.payRatio(Game.HandRank.TWO_PAIR));
    }

    @Test
    void testOnePairMultiplier() {
        assertEquals(1, Game.payRatio(Game.HandRank.ONE_PAIR));
    }

    @Test
    void testHighCardMultiplier() {
        assertEquals(1, Game.payRatio(Game.HandRank.HIGH_CARD));
    }
}
