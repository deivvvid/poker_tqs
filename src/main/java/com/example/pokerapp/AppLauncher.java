package com.example.pokerapp;

import com.example.pokerapp.controller.*;
import com.example.pokerapp.view.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView(primaryStage);
        MainController controller = new MainController(mainView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}