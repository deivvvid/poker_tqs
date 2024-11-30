package com.example.pokerapp.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.util.List;

import groovy.util.ObservableList;

public class MainView extends Application {
	
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

    public void createViewElements(Stage primaryStage) {
        BackgroundImage myBI = new BackgroundImage(
            new Image(getClass().getResource("/background.jpg").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );

        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(myBI));

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(topHBox, new javafx.geometry.Insets(50, 0, 0, 0));
        VBox.setMargin(bottomHBox, new javafx.geometry.Insets(0, 0, 50, 0));

        vBox.getChildren().addAll(topHBox, middleHBox, bottomHBox);

        stackPane.getChildren().add(vBox);

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
    	createViewElements(primaryStage);
        List<ImageView> imageViews = List.of(
                createImageView(new Image(getClass().getResource("/FOUR_CLUBS.png").toString())),
                createImageView(new Image(getClass().getResource("/FIVE_CLUBS.png").toString())),
                createImageView(new Image(getClass().getResource("/SIX_CLUBS.png").toString()))
        );
        List<ImageView> imageViews2 = List.of(
                createImageView(new Image(getClass().getResource("/A_CLUBS.png").toString())),
                createImageView(new Image(getClass().getResource("/FIVE_CLUBS.png").toString())),
                createImageView(new Image(getClass().getResource("/SIX_CLUBS.png").toString()))
        );
        populateBottomHBox(imageViews2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}