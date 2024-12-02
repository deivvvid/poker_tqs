package com.example.pokerapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.pokerapp.model.Card;
import com.example.pokerapp.model.Dealer;
import com.example.pokerapp.model.Game;
import com.example.pokerapp.model.Player;
import com.example.pokerapp.model.Table;
import com.example.pokerapp.view.*;

import javafx.application.Platform;

public class MainControllerTest {

	private MainController controller;
    private IMainView mainView;
    private Game mockGame;
    private Player mockPlayer;
    private Table mockTable;
    private Dealer mockDealer;

    @BeforeEach
    void setUp() {
        // Mock de la vista
        mainView = mock(IMainView.class);

        // Mock del modelo del juego
        mockGame = mock(Game.class);
        mockPlayer = mock(Player.class);
        mockTable = mock(Table.class);
        mockDealer = mock(Dealer.class);

        // Configuración inicial del juego
        when(mockGame.getPlayer()).thenReturn(mockPlayer);
        when(mockGame.getTable()).thenReturn(mockTable);
        when(mockGame.getDealer()).thenReturn(mockDealer);

        // Inicializa el controlador con el mock de la vista
        controller = new MainController(mainView);
        controller.game = mockGame; // Reemplaza el juego con el mock
    }

    @Test
    void testUpdateViewPlayerCoins() {
        when(mockPlayer.getCoins()).thenReturn(500); // Mock de fichas del jugador
        controller.updateViewPlayerCoins();
        verify(mainView).setPlayerCoins("500"); // Verifica que se actualizó la vista
    }

    @Test
    void testUpdateViewAnteBet() {
        when(mockTable.getAnteBet()).thenReturn(100); // Mock de la apuesta inicial
        controller.updateViewAnteBet();
        verify(mainView).setAnteBet("100"); // Verifica que se actualizó la vista
    }

    @Test
    void testUpdateViewCallBet() {
        when(mockTable.getCallBet()).thenReturn(200); // Mock de la apuesta de llamada
        controller.updateViewCallBet();
        verify(mainView).setCallBet("200"); // Verifica que se actualizó la vista
    }

    @Test
    void testPlaceCoin() {
        when(mockPlayer.getCoins()).thenReturn(1000); // Mock de fichas del jugador
        controller.stage = 0; // Etapa inicial
        controller.placedCoins = 0;

        controller.placeCoin(100);

        assertEquals(100, controller.placedCoins); // Verifica fichas colocadas
        verify(mainView).setPlacedCoins("100"); // Verifica la actualización de la vista
    }

    @Test
    void testRemovePlacedCoins() {
        controller.placedCoins = 200;
        controller.stage = 8; // Etapa que permite reiniciar las fichas

        controller.removePlacedCoins();

        assertEquals(0, controller.placedCoins); // Verifica que se reiniciaron las fichas
        verify(mainView).setPlacedCoins("0"); // Verifica que la vista se actualizó
    }

    @Test
    void testPickCard() {
        // Mock de las cartas
        Card mockCard = new Card(Card.Suit.HEARTS, Card.Rank.A);
        when(mockTable.getCommunityCards()).thenReturn(List.of(mockCard));
        controller.stage = 2; // Etapa que permite seleccionar carta

        int result = controller.pickCard(true, 0);

        assertEquals(1, result); // Verifica que se seleccionó una carta
        assertTrue(controller.selectedCards.contains(mockCard)); // Verifica que la carta se añadió
    }

    @Test
    void testNext() {
        controller.stage = 7; // Etapa final
        controller.next();

        assertEquals(0, controller.stage); // Verifica que la etapa se reinicia
        verify(mockTable).resetTable(); // Verifica que la mesa se reinicia
        verify(mainView, times(1)).setPlacedCoins(anyString()); // Verifica reinicio de apuestas
    }

    @Test
    void testMakeBet_Initial() {
        controller.stage = 0; // Etapa inicial
        controller.placedCoins = 100;

        controller.makeBet();

        assertEquals(1, controller.stage); // Verifica cambio de etapa
        verify(mockGame).startRound(100); // Verifica inicio de la ronda
    }

    @Test
    void testRetire() {
        controller.stage = 1; // Etapa de retirada

        controller.retire();

        assertEquals(0, controller.stage); // Verifica reinicio de etapa
        verify(mockTable).resetTable(); // Verifica reinicio de la mesa
        verify(mainView, times(1)).setPlacedCoins(anyString()); // Verifica reinicio de fichas
    }
}
