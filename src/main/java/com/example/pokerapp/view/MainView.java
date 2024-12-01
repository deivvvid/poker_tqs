package com.example.pokerapp.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.util.List;

public class MainView extends Application implements IMainView {
	
	/*
	 * 
	 if (reversed) {
		image = new Image(getClass().getResource("/reverse.png").toString());
	} else {
		image = new Image(getClass().getResource("/" + card.getImagePath()).toString());
	}
	 * 
	 */

    public HBox topHBox;
    public HBox middleHBox;
    public HBox bottomHBox;
    public VBox vBox;
    public double cardHeight;
    private Label playerCoins;
    public Label anteBet;
    public Label callBet;
    public StackPane stackPane;

    public void createViewElements(Stage primaryStage) {
        BackgroundImage myBI = new BackgroundImage(
            new Image(getClass().getResource("/background.jpg").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );

        stackPane = new StackPane();
        stackPane.setBackground(new Background(myBI));
        
        topHBox = new HBox(10);
        middleHBox = new HBox(10);
        bottomHBox = new HBox(10);
        vBox = new VBox(10);
        cardHeight = 200;
    	topHBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	bottomHBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	middleHBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	topHBox.setMinHeight(cardHeight);
    	bottomHBox.setMinHeight(cardHeight);
    	middleHBox.setMinHeight(cardHeight);

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(topHBox, new javafx.geometry.Insets(50, 0, 0, 0));
        VBox.setMargin(bottomHBox, new javafx.geometry.Insets(0, 0, 50, 0));

        vBox.getChildren().addAll(topHBox, middleHBox, bottomHBox);
        
        playerCoins = new Label("Coins: 1000");
        playerCoins.setStyle("-fx-font-size: 48px; -fx-text-fill: white;");
        StackPane.setAlignment(playerCoins, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(playerCoins, new javafx.geometry.Insets(20, 20, 20, 20));
        
        anteBet = new Label("Ante: No bet");
        anteBet.setStyle("-fx-font-size: 48px; -fx-text-fill: white;");
        StackPane.setAlignment(anteBet, Pos.BOTTOM_LEFT);
        StackPane.setMargin(anteBet, new javafx.geometry.Insets(20, 20, 20, 20));
        
        callBet = new Label("Call: No bet");
        callBet.setStyle("-fx-font-size: 48px; -fx-text-fill: white;");
        StackPane.setAlignment(callBet, Pos.BOTTOM_LEFT);
        StackPane.setMargin(callBet, new javafx.geometry.Insets(20, 20, 100, 20));

        VBox coinVBox = new VBox(10);
        coinVBox.setAlignment(Pos.TOP_RIGHT);
        StackPane.setAlignment(coinVBox, Pos.TOP_RIGHT);
        StackPane.setMargin(coinVBox, new javafx.geometry.Insets(20, 20, 0, 0));

        for (int i = 0; i < 5; i++) {
        	final int coinIndex = i;
        	ImageView coinImage = new ImageView(getClass().getResource("/CHIP_" + i + ".png").toExternalForm());
            coinImage.setFitWidth(80);
            coinImage.setFitHeight(80);

            coinImage.setOnMouseClicked(event -> {
                System.out.println("Coin clicked: " + coinIndex);
            });

            coinVBox.getChildren().add(coinImage);
        }

        stackPane.getChildren().addAll(vBox, playerCoins, anteBet, callBet, coinVBox);
        
        Button makeBetButton = new Button("MAKE BET");
        makeBetButton.setStyle("-fx-font-size: 24px; -fx-padding: 10px 20px; -fx-background-color: #000000; -fx-text-fill: white;");
        makeBetButton.setOnAction(event -> {
            System.out.println("Make Bet button clicked!");
        });
        
        Button removeCoinsButton = new Button("REMOVE COINS");
        removeCoinsButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: #ff0000;");
        removeCoinsButton.setOnAction(e -> {
            // Action for removing coins
            System.out.println("Removing coins...");
            // Logic for removing coins
        });
        
        Label placedCoinsLabel = new Label("PLACED COINS: 0");
        placedCoinsLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");
        StackPane.setAlignment(placedCoinsLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(placedCoinsLabel, new javafx.geometry.Insets(0, 0, 100, 0));

        VBox buttonBox = new VBox(placedCoinsLabel, removeCoinsButton, makeBetButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        StackPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(buttonBox, new javafx.geometry.Insets(0, 0, 20, 0));

        stackPane.getChildren().add(buttonBox);

        Scene scene = new Scene(stackPane, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Casino Hold'em");
        primaryStage.show();
    }

    public void populateTopHBox(List<ImageView> imageViews) {
        topHBox.getChildren().clear();
        for (ImageView iv : imageViews) {
            topHBox.getChildren().add(iv);
        }
    }

    public void populateMiddleHBox(List<ImageView> imageViews) {
        middleHBox.getChildren().clear();
        for (ImageView iv : imageViews) {
            middleHBox.getChildren().add(iv);
        }
    }

    public void populateBottomHBox(List<ImageView> imageViews) {
        bottomHBox.getChildren().clear();
        for (ImageView iv : imageViews) {
            bottomHBox.getChildren().add(iv);
        }
    }

    public ImageView createImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(cardHeight);
        imageView.setPreserveRatio(true);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Nothing");
            }
        });
        return imageView;
    }

    @Override
    public void start(Stage primaryStage) {
    	createViewElements(primaryStage);
    }
    
    public void startNewStage() {
    	createViewElements(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void setPlayerCoins(String s) {
    	playerCoins.setText(s);
    }
    
    public String getPlayerCoins() {
    	return playerCoins.getText();
    }
}