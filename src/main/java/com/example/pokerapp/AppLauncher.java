package com.example.pokerapp;

import com.example.pokerapp.controller.*;
import com.example.pokerapp.view.*;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppLauncher extends Application {
	
	// Este método inicializa la aplicación y configura la ventana principal.
    @Override
    public void start(Stage primaryStage) {
    	// Establece el icono de la ventana con la imagen "CHIP_1.png" desde los recursos del proyecto.
    	primaryStage.getIcons().add(new Image(getClass().getResource("/CHIP_1.png").toString()));
    	
    	// Crea una instancia de la vista principal (MainView) y la asocia con la ventana primaria.
        MainView mainView = new MainView(primaryStage);
        
        // Crea el controlador principal (MainController) y lo asocia con la vista.
        MainController controller = new MainController(mainView);
    }

 // Método principal que lanza la aplicación JavaFX.
    public static void main(String[] args) {
    	// Llama a la función launch para iniciar la aplicación JavaFX.
        launch(args);
    }
}