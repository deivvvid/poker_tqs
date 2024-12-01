package com.example.pokerapp.view;

import com.example.pokerapp.controller.MainController;

public interface IMainView {
	public void setPlayerCoins(String s);
    public String getPlayerCoins();
    public void setMainController(MainController mc);
    public void setPlacedCoins(String s);
    public String getPlacedCoins();
    public void createViewElements();
}
