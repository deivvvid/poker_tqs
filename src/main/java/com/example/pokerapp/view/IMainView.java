package com.example.pokerapp.view;

import java.util.List;

import com.example.pokerapp.controller.MainController;

import javafx.scene.image.Image;

public interface IMainView {
	public void setPlayerCoins(String s);
    public String getPlayerCoins();
    public void setMainController(MainController mc);
    public void setPlacedCoins(String s);
    public String getPlacedCoins();
    public void createViewElements();
    public int populateTopHBox(List<Image> images);
    public int populateMiddleHBox(List<Image> images);
    public int populateBottomHBox(List<Image> images);
    public void setAnteBet(String s);
    public String getAnteBet();
}
