package com.example.pokerapp.model;

import java.util.*;

public class Table {
	// Lista que almacena las cartas comunitarias en la mesa.
    private List<Card> communityCards;
    
    // Apuesta inicial (ante) en la mesa.
    private int anteBet;
    
    // Apuesta de llamada (call) en la mesa.
    private int callBet;

    // Inicializa las variables
    public Table() {
        this.communityCards = new ArrayList<>();
        this.anteBet = 0;
        this.callBet = 0;
    }

    // Añade una carta a la lista de cartas comunitarias
    public void addCommunityCard(Card card) {
        communityCards.add(card);
    }

    // Obtiene las cartas comunitarias
    public List<Card> getCommunityCards() {
        return communityCards;
    }

    // Establece el ante a la cantidad del parámetro
    public void setAnteBet(int amount) {
        anteBet = amount;
    }

    // Obtiene el ante establecido
    public int getAnteBet() {
        return anteBet;
    }

    // Establece el call a la cantidad del parámetro
    public void setCallBet(int amount) {
        callBet = amount;
    }

    // Obtiene el call establecido
    public int getCallBet() {
        return callBet;
    }

    // Reinicia la mesa estableciendo las variables a 0 y la lista de cartas vacía
    public void resetTable() {
        communityCards.clear();
        anteBet = 0;
        callBet = 0;
    }
}