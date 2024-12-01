package com.example.pokerapp.view;

import java.util.List;

import com.example.pokerapp.controller.MainController;

import javafx.scene.image.Image;

// Interfície de la vista para poder usar un mock de la vista o la vista
// en el controlador
public interface IMainView {
	public void setPlayerCoins(String s);
    public String getPlayerCoins();
    public void setMainController(MainController mc);
    public void setPlacedCoins(String s);
    public String getPlacedCoins();
    public void createViewElements();
    public int populateDealerCardsBox(List<Image> images);
    public int populateTableCardsBox(List<Image> images);
    public int populatePlayerCardsBox(List<Image> images);
    public void setAnteBet(String s);
    public String getAnteBet();
    public void setCallBet(String s);
    public String getCallBet();
}
