package com.example.pokerapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.pokerapp.model.*;
import com.example.pokerapp.view.*;

import javafx.scene.image.Image;

public class MainController {

	// Propiedades de la clase que incluyen la vista, el juego, las fichas del jugador, y el estado del juego.
	public IMainView mainView;
	public Game game;
	public int startPlayerCoins = 1000;  // Fichas iniciales del jugador
    public int placedCoins;  // Fichas apostadas por el jugador
    public int stage;  // Etapa actual del juego
    public List<Card> selectedCards;  // Cartas seleccionadas por el jugador
	
    // Constructor que inicializa los elementos necesarios para el juego y la vista.
	public MainController(IMainView mv) {
		mainView = mv;  // Asocia la vista
        mainView.setMainController(this);  // Establece el controlador en la vista
        game = new Game(startPlayerCoins);  // Crea el juego con el saldo inicial
        mainView.createViewElements();  // Inicializa los elementos de la vista
        updateViewPlayerCoins();  // Actualiza la cantidad de fichas del jugador en la vista
        stage = 0;  // Establece la etapa inicial
        placedCoins = 0;  // Inicializa las fichas apostadas
        selectedCards = new ArrayList<Card>();  // Inicializa la lista de cartas seleccionadas
	}
	
	// Actualiza la vista con las fichas del jugador.
    public void updateViewPlayerCoins() {
        mainView.setPlayerCoins(String.valueOf(game.getPlayer().getCoins()));  // Muestra el saldo del jugador
    }

    // Actualiza la vista con la apuesta inicial (ante bet) en la mesa.
    public void updateViewAnteBet() {
        mainView.setAnteBet(String.valueOf(game.getTable().getAnteBet()));  // Muestra la apuesta inicial
    }

    // Actualiza la vista con la apuesta de llamada (call bet).
    public void updateViewCallBet() {
        mainView.setCallBet(String.valueOf(game.getTable().getCallBet()));  // Muestra la apuesta de llamada
    }
	
 // Permite al jugador colocar fichas, validando si puede realizar la apuesta en la etapa actual.
    public void placeCoin(int value) {
        // Verifica si el jugador puede colocar fichas en la etapa actual y si tiene suficientes fichas
        System.out.println(stage == 0 && (placedCoins + value) * 3 <= game.getPlayer().getCoins());
        System.out.println(stage);
        System.out.println(game.getPlayer().getCoins());
        if (stage == 0 && (placedCoins + value) * 3 <= game.getPlayer().getCoins()) {
            placedCoins += value;  // Añade las fichas apostadas
            mainView.setPlacedCoins(String.valueOf(placedCoins));  // Actualiza la vista con las fichas apostadas
        }
    }
	
    // Elimina las fichas apostadas si la etapa es correcta (o si ya se ha llegado al final del juego).
    public void removePlacedCoins() {
        if (stage == 0 || stage >= 8) {
            placedCoins = 0;  // Reinicia las fichas apostadas
            mainView.setPlacedCoins("0");  // Actualiza la vista
        }
    }
	
    // Permite al jugador seleccionar una carta de la mesa o de su mano.
    public int pickCard(Boolean table, int index) {
        if (stage > 1) {
            if (table) {
            	// Muestra la carta seleccionada de la mesa
                System.out.println(game.getTable().getCommunityCards().get(index));  
                // Añade la carta seleccionada
                selectedCards.add(game.getTable().getCommunityCards().get(index));  
            } else {
            	// Muestra la carta seleccionada de la mano del jugador
                System.out.println(game.getPlayer().getHand().get(index));  
                // Añade la carta seleccionada
                selectedCards.add(game.getPlayer().getHand().get(index));  
            }
            if (stage >= 6) {
                selectWinner();  // Si la etapa es suficiente, selecciona al ganador
            }
            stage += 1;  // Avanza a la siguiente etapa
            return 1;
        }
        return 0;  // No se seleccionó carta si la etapa no es adecuada
    }
	
    // Avanza al siguiente estado del juego, reiniciando las apuestas y cartas si es necesario.
    public void next() {
        if (stage >= 7) {
            stage += 1;  // Avanza la etapa
            game.getTable().resetTable();  // Reinicia la mesa
            game.getPlayer().getHand().clear();  // Limpia las cartas del jugador
            game.getDealer().getHand().clear();  // Limpia las cartas del dealer
            updateViewPlayerCoins();  // Actualiza las fichas del jugador
            updateViewAnteBet();  // Actualiza la apuesta inicial
            updateViewCallBet();  // Actualiza la apuesta de llamada
            stage = 0;  // Reinicia la etapa
            removePlacedCoins();  // Elimina las fichas apostadas
            selectedCards = new ArrayList<Card>();  // Reinicia las cartas seleccionadas
            viewGameCards();  // Muestra las cartas en la vista
        }
    }
	
 // Selecciona al ganador comparando la mano del jugador con la mejor mano del dealer.
    public void selectWinner() {
        List<Card> dealerCombinations = new ArrayList<>();
        dealerCombinations.addAll(game.getTable().getCommunityCards());  // Añade las cartas comunitarias al dealer
        dealerCombinations.addAll(game.getDealer().getHand());  // Añade las cartas del dealer
        List<Card> bestHand = Game.bestHand(dealerCombinations);  // Obtiene la mejor mano del dealer
        List<Card> minHand = Arrays.asList(  // Define una mano mínima de referencia
                new Card(Card.Suit.HEARTS, Card.Rank.FOUR),
                new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR),
                new Card(Card.Suit.CLUBS, Card.Rank.THREE),
                new Card(Card.Suit.SPADES, Card.Rank.TWO),
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE)
            );
        // Compara las manos del jugador, dealer y una mano mínima
        System.out.println(Game.classifyHand(selectedCards));
        System.out.println(Game.classifyHand(bestHand));
        System.out.println(Game.compareHands(bestHand, minHand));
        System.out.println(Game.compareHands(selectedCards, bestHand));
        
        // Si la mano del jugador es mejor que la del dealer, el jugador gana
        if (Game.compareHands(bestHand, minHand) < 0 || Game.compareHands(selectedCards, bestHand) > 0) {
            game.getPlayer().addCoins(game.getTable().getAnteBet() * 
                    Game.payRatio(Game.classifyHand(selectedCards)));  // El jugador gana la apuesta
            game.getPlayer().addCoins(game.getTable().getCallBet());  // El jugador gana la apuesta de llamada
            System.out.println("Player Wins");
        } else if (Game.compareHands(selectedCards, bestHand) == 0) {  // Si es empate
            game.getPlayer().addCoins(game.getTable().getAnteBet());
            game.getPlayer().addCoins(game.getTable().getCallBet());
            System.out.println("Tie");
        } else {
            System.out.println("Player Loose");  // Si el jugador pierde
        }
        
        // Actualiza las cartas del dealer en la vista
        List<Image> g = new ArrayList<Image>();
        for (Card c : game.getDealer().getHand()) {
        	// Carga las imágenes de las cartas
            g.add(new Image(getClass().getResource("/" + c.getImagePath()).toString()));  
        }
        mainView.populateDealerCardsBox(g);  // Actualiza la caja de cartas del dealer
    }
	
    // Realiza la apuesta inicial o la llamada según la etapa del juego.
    public void makeBet() {
        if (stage == 0) {
            if (placedCoins > 0) {
                game.startRound(placedCoins);  // Comienza la ronda con la apuesta inicial
                updateViewPlayerCoins();  // Actualiza las fichas del jugador
                updateViewAnteBet();  // Actualiza la apuesta inicial
                viewGameCards();  // Muestra las cartas
                stage = 1;  // Avanza a la siguiente etapa
            }
        } else if (stage == 1) {
            game.playerCalls();  // El jugador hace la llamada
            updateViewPlayerCoins();  // Actualiza las fichas del jugador
            updateViewCallBet();  // Actualiza la apuesta de llamada
            viewGameCards();  // Muestra las cartas
            stage = 2;  // Avanza a la siguiente etapa
        }
    }
	
 // Permite al jugador retirarse y reiniciar el juego.
    public void retire() {
        if (stage == 1) {
            game.getTable().resetTable();  // Reinicia la mesa
            game.getPlayer().getHand().clear();  // Limpia las cartas del jugador
            game.getDealer().getHand().clear();  // Limpia las cartas del dealer
            updateViewPlayerCoins();  // Actualiza las fichas del jugador
            updateViewAnteBet();  // Actualiza la apuesta inicial
            updateViewCallBet();  // Actualiza la apuesta de llamada
            stage = 0;  // Reinicia la etapa
            removePlacedCoins();  // Elimina las fichas apostadas
            selectedCards = new ArrayList<Card>();  // Reinicia las cartas seleccionadas
            viewGameCards();  // Muestra las cartas
        }
    }

    // Muestra las cartas del juego (dealer, mesa, jugador).
    public int viewGameCards() {
        int sizeList = 0;
        
        // Muestra las cartas del dealer
        List<Image> a = new ArrayList<Image>();
        for (Card c : game.getDealer().getHand()) {
        	// Muestra las cartas del dealer ocultas, es decir, al revés
            a.add(new Image(getClass().getResource("/reverse.png").toString()));  
        }
        sizeList += mainView.populateDealerCardsBox(a);  // Actualiza la caja de cartas del dealer
        
        // Muestra las cartas de la mesa
        List<Image> b = new ArrayList<Image>();
        for (Card c : game.getTable().getCommunityCards()) {
        	// Muestra las cartas de la mesa
            b.add(new Image(getClass().getResource("/" + c.getImagePath()).toString()));  
        }
        sizeList += mainView.populateTableCardsBox(b);  // Actualiza la caja de cartas de la mesa
        
        // Muestra las cartas del jugador
        List<Image> d = new ArrayList<Image>();
        for (Card c : game.getPlayer().getHand()) {
        	// Muestra las cartas del jugador
            d.add(new Image(getClass().getResource("/" + c.getImagePath()).toString()));  
        }
        sizeList += mainView.populatePlayerCardsBox(d);  // Actualiza la caja de cartas del jugador
        
        return sizeList;  // Devuelve el tamaño total de las cartas mostradas
    }
}