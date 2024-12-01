package com.example.pokerapp.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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

import com.example.pokerapp.controller.MainController;
import com.example.pokerapp.model.Card;

public class MainView implements IMainView {
	
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
    private Label anteBet;
    public Label callBet;
    public StackPane stackPane;
    private MainController mc;
    private Stage primaryStage;
    private int chipValue[] = {1, 5, 10, 25, 50};
    private Label placedCoins;
    
    public MainView(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }

    public void createViewElements() {
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
        cardHeight = 150;
    	topHBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	bottomHBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	middleHBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	topHBox.setMinHeight(cardHeight);
    	bottomHBox.setMinHeight(cardHeight);
    	middleHBox.setMinHeight(cardHeight);

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(topHBox, new javafx.geometry.Insets(50, 0, 0, 0));
        VBox.setMargin(bottomHBox, new javafx.geometry.Insets(0, 0, 230, 0));

        vBox.getChildren().addAll(topHBox, middleHBox, bottomHBox);
        
        playerCoins = new Label("COINS: 1000");
        playerCoins.setStyle("-fx-font-size: 48px; -fx-text-fill: white;");
        StackPane.setAlignment(playerCoins, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(playerCoins, new javafx.geometry.Insets(20, 20, 20, 20));
        
        anteBet = new Label("ANTE: NO BET");
        anteBet.setStyle("-fx-font-size: 48px; -fx-text-fill: white;");
        StackPane.setAlignment(anteBet, Pos.BOTTOM_LEFT);
        StackPane.setMargin(anteBet, new javafx.geometry.Insets(20, 20, 20, 20));
        
        callBet = new Label("CALL: NO BET");
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
            
            coinImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	System.out.println("Coin clicked: " + coinIndex);
                    mc.placeCoin(chipValue[coinIndex]);
                }
            });

            coinVBox.getChildren().add(coinImage);
        }

        Button makeBetButton = new Button("MAKE BET");
        makeBetButton.setPickOnBounds(true);
        makeBetButton.setStyle("-fx-font-size: 24px; -fx-padding: 10px 20px; -fx-background-color: #000000; -fx-text-fill: white;");
        makeBetButton.setOnAction(event -> {
            System.out.println("Make Bet button clicked!");
            mc.makeBet();
        });
        
        Button removeCoinsButton = new Button("REMOVE COINS");
        removeCoinsButton.setPickOnBounds(true);
        removeCoinsButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: #ff0000;");
        removeCoinsButton.setOnAction(e -> {
        	System.out.println("Removing placed coins!");
            mc.removePlacedCoins();
        });
        
        placedCoins = new Label("PLACED COINS: 0");
        placedCoins.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");
        StackPane.setAlignment(placedCoins, Pos.BOTTOM_CENTER);
        StackPane.setMargin(placedCoins, new javafx.geometry.Insets(0, 0, 100, 0));

        VBox buttonBox = new VBox(placedCoins, removeCoinsButton, makeBetButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        StackPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(buttonBox, new javafx.geometry.Insets(0, 0, 20, 0));

        //vBox.setStyle("-fx-background-color: lightblue;");
        //buttonBox.setStyle("-fx-background-color: lightcoral;");
        //coinVBox.setStyle("-fx-background-color: lightgray;");
        vBox.setMaxWidth(Region.USE_PREF_SIZE);
        vBox.setMaxHeight(Region.USE_PREF_SIZE);
        buttonBox.setMaxWidth(Region.USE_PREF_SIZE);
        buttonBox.setMaxHeight(Region.USE_PREF_SIZE);
        coinVBox.setMaxWidth(Region.USE_PREF_SIZE);
        coinVBox.setMaxHeight(Region.USE_PREF_SIZE);
        stackPane.getChildren().addAll(vBox, playerCoins, anteBet, callBet, buttonBox, coinVBox);
        Scene scene = new Scene(stackPane, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Casino Hold'em");
        primaryStage.show();
        /*List<ImageView> a = List.of(createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())));
        List<ImageView> b = List.of(createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())));
        List<ImageView> c = List.of(createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())),
        		createImageView(new Image(getClass().getResource("/reverse.png").toString())));
        populateBottomHBox(a);
        populateTopHBox(b);
        populateMiddleHBox(c);*/
    }

    public int populateTopHBox(List<Image> images) {
        topHBox.getChildren().clear();
        for (Image i : images) {
            topHBox.getChildren().add(createImageView(i));
        }
        return topHBox.getChildren().size();
    }

    public int populateMiddleHBox(List<Image> images) {
        middleHBox.getChildren().clear();
        for (Image i : images) {
            middleHBox.getChildren().add(createImageView(i));
        }
        return middleHBox.getChildren().size();
    }

    public int populateBottomHBox(List<Image> images) {
        bottomHBox.getChildren().clear();
        for (Image i : images) {
            bottomHBox.getChildren().add(createImageView(i));
        }
        return bottomHBox.getChildren().size();
    }

    private ImageView createImageView(Image image) {
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
    
    public void setPlayerCoins(String s) {
    	playerCoins.setText("COINS:" + s);
    }
    
    public void setPlacedCoins(String s) {
    	placedCoins.setText("PLACED COINS: " + s);
    }
    
    public String getPlacedCoins() {
    	return placedCoins.getText();
    }
    
    public String getPlayerCoins() {
    	return playerCoins.getText();
    }
    
    public void setMainController(MainController mc) {
    	this.mc = mc;
    }
    
    public void setAnteBet(String s) {
    	anteBet.setText("ANTE: " + s);
    }
    
    public String getAnteBet() {
    	return anteBet.getText();
    }
}