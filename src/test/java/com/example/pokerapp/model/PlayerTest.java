package com.example.pokerapp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class PlayerTest {

    @Test
    public void testConstructor_emptyPlayer_noExceptionThrown() {
        Player player = new Player();
        assertNotNull(player);
        assertEquals(0, player.getCoins());
        assertTrue(player.getHand().getCards().isEmpty());
    }
    
    @Test
    public void testConstructor_withNameHandAndCoins_noExceptionThrown() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.A),
                new Card(Card.Suit.DIAMONDS, Card.Rank.K)
        ));
        Player player = new Player("John Doe", hand, 100);

        assertNotNull(player);
        assertEquals("John Doe", player.getName());
        assertEquals(100, player.getCoins());
        assertEquals(2, player.getHand().getCardCount());
    }

    @Test
    public void testConstructor_nullOrEmptyName_throwsException() {
        Hand hand = new Hand();
        assertThrows(IllegalArgumentException.class, () -> new Player(null, hand, 100));
        assertThrows(IllegalArgumentException.class, () -> new Player("", hand, 100));
        assertThrows(IllegalArgumentException.class, () -> new Player("   ", hand, 100));
    }

    @Test
    public void testConstructor_negativeCoins_throwsException() {
        Hand hand = new Hand();
        assertThrows(IllegalArgumentException.class, () -> new Player(hand, -10));
    }
    
    @Test
    public void testConstructor_nullOrEmptyHand_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Player("John", null, 100));

        Hand emptyHand = new Hand();
        assertThrows(IllegalArgumentException.class, () -> new Player("John", emptyHand, 100));
    }


    @Test
    public void testSetCoins_validValue_setsCoins() {
        Player player = new Player();
        player.setCoins(200);
        assertEquals(200, player.getCoins());
    }

    @Test
    public void testSetCoins_negativeValue_throwsException() {
        Player player = new Player();
        assertThrows(IllegalArgumentException.class, () -> player.setCoins(-50));
    }
    
    @Test
    public void testSetName_validName_setsName() {
        Player player = new Player();
        player.setName("Alice");
        assertEquals("Alice", player.getName());
    }

    @Test
    public void testSetName_nullOrEmptyName_throwsException() {
        Player player = new Player();
        assertThrows(IllegalArgumentException.class, () -> player.setName(null));
        assertThrows(IllegalArgumentException.class, () -> player.setName(""));
        assertThrows(IllegalArgumentException.class, () -> player.setName("   "));
    }
    
    @Test
    public void testSetHand_validValue_setsHand() {
    	 Player player = new Player();

         Hand initialHand = new Hand(Arrays.asList(
                 new Card(Card.Suit.HEARTS, Card.Rank.A),
                 new Card(Card.Suit.DIAMONDS, Card.Rank.K)
         ));
         Hand newHand = new Hand(Arrays.asList(
                 new Card(Card.Suit.CLUBS, Card.Rank.Q),
                 new Card(Card.Suit.SPADES, Card.Rank.J)
         ));

         player.setHand(initialHand);
         assertEquals(2, player.getHand().getCardCount());
         assertTrue(player.getHand().getCards().contains(new Card(Card.Suit.HEARTS, Card.Rank.A)));

         player.setHand(newHand);
         assertEquals(2, player.getHand().getCardCount());
         assertTrue(player.getHand().getCards().contains(new Card(Card.Suit.CLUBS, Card.Rank.Q)));
    }

    @Test
    public void testSetHand_negativeValue_throwsException() {
    	Player player = new Player();
        assertThrows(IllegalArgumentException.class, () -> player.setHand(null));
    }

    @Test
    public void testAddCoins_validValue_addsCoins() {
        Player player = new Player();
        player.addCoins(50);
        assertEquals(50, player.getCoins());
    }

    @Test
    public void testAddCoins_negativeValue_throwsException() {
        Player player = new Player();
        assertThrows(IllegalArgumentException.class, () -> player.addCoins(-30));
    }

    @Test
    public void testMakeBet_validValue_deductsCoins() {
        Player player = new Player();
        player.setCoins(100);
        player.deductCoins(40);
        assertEquals(60, player.getCoins());
    }

    @Test
    public void testMakeBet_moreThanAvailable_throwsException() {
        Player player = new Player();
        player.setCoins(50);
        assertThrows(IllegalArgumentException.class, () -> player.deductCoins(60));
    }

    @Test
    public void testMakeBet_negativeValue_throwsException() {
        Player player = new Player();
        player.setCoins(50);
        assertThrows(IllegalArgumentException.class, () -> player.deductCoins(-10));
    }

    @Test
    public void testToString() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.A),
                new Card(Card.Suit.DIAMONDS, Card.Rank.K)
        ));
        Player player = new Player("Bob", hand, 50);

        String expectedOutput = "Player{name='Bob', hand=Hand{cards=[A of HEARTS, K of DIAMONDS]}, coins=50}";
        assertEquals(expectedOutput, player.toString());
    }
}
