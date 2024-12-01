package com.example.pokerapp;

import com.example.pokerapp.controller.*;
import com.example.pokerapp.view.*;

public class AppLauncher {
    public static void main(String[] args) {
        MainController mc = new MainController(new MainView());
    }
}