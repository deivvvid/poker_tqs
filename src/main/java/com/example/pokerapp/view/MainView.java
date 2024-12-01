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
	
	// Las cartas se representan dentro de contenedores
	private VBox cardsBox;
    private HBox dealerCardsBox;
    private HBox tableCardsBox;
    private HBox playerCardsBox;
    
    // El tamaño de la carta en la interfície
    private double cardHeight;
    
    // Textos para representar los coins del jugador, los coins puestos en mesa, y las apuestas, si se han realizado
    private Label playerCoins;
    private Label anteBet;
    private Label callBet;
    private Label placedCoins;
    
    // Contenedor principal
    private StackPane stackPane;
    
    // Controlador principal
    private MainController mc;
    
    // Ventana principal de la aplicación
    private Stage primaryStage;
    
    // Los valores de las fichas ordenados
    private int chipValue[] = {1, 5, 10, 25, 50};

    // Constructor para establecer la ventana principal
    public MainView(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }

    // Crear los elementos de la vista
    public void createViewElements() {
    	
    	// Creación de la imagen de fondo de la mesa, usando el resource
        BackgroundImage myBI = new BackgroundImage(
            new Image(getClass().getResource("/background.jpg").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );

        // En el contenedor principal, se pone el fondo de pantalla
        stackPane = new StackPane();
        stackPane.setBackground(new Background(myBI));
        
        // Se crean los contenedores para las cartas
        dealerCardsBox = new HBox(10);
        tableCardsBox = new HBox(10);
        playerCardsBox = new HBox(10);
        cardsBox = new VBox(10);
        cardHeight = 150;
    	dealerCardsBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	playerCardsBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	tableCardsBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    	dealerCardsBox.setMinHeight(cardHeight);
    	playerCardsBox.setMinHeight(cardHeight);
    	tableCardsBox.setMinHeight(cardHeight);
        cardsBox.setAlignment(Pos.CENTER);
        VBox.setMargin(dealerCardsBox, new javafx.geometry.Insets(50, 0, 0, 0));
        VBox.setMargin(playerCardsBox, new javafx.geometry.Insets(0, 0, 230, 0));

        // Y se meten todos dentro del contenedor cardsBox
        cardsBox.getChildren().addAll(dealerCardsBox, tableCardsBox, playerCardsBox);
        
        // Se crean varias label para representar los coins y las apuestas
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
        
        placedCoins = new Label("PLACED COINS: 0");
        placedCoins.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");
        StackPane.setAlignment(placedCoins, Pos.BOTTOM_CENTER);
        StackPane.setMargin(placedCoins, new javafx.geometry.Insets(0, 0, 100, 0));
        
        // Se crea el contenedor para visualizar las fichas, y se crean eventos para añadirle 
        // funcionalidad de clic con el controlador
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
                    mc.placeCoin(chipValue[coinIndex]);
                }
            });

            coinVBox.getChildren().add(coinImage);
        }

        // Se crean algunos botones para otras funcionalidades del controlador
        
        // Botón para hacer una apuesta
        Button makeBetButton = new Button("MAKE BET");
        makeBetButton.setPickOnBounds(true);
        makeBetButton.setStyle("-fx-font-size: 20px; -fx-background-color: #000000; -fx-text-fill: white;");
        makeBetButton.setOnAction(event -> {
            mc.makeBet();
        });
        
        // Botón para continuar a la siguiente ronda
        Button continueButton = new Button("CONTINUE");
        continueButton.setPickOnBounds(true);
        continueButton.setStyle("-fx-font-size: 20px; -fx-background-color: #000000; -fx-text-fill: white;");
        continueButton.setOnAction(event -> {
            mc.next();
        });
        
        // Botón para quitar los coins de la mesa
        Button removeCoinsButton = new Button("REMOVE COINS");
        removeCoinsButton.setPickOnBounds(true);
        removeCoinsButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: #ff0000;");
        removeCoinsButton.setOnAction(e -> {
            mc.removePlacedCoins();
        });
        
        // Botón para retirarse, después de hacer la apuesta ante
        Button retireButton = new Button("RETIRE");
        retireButton.setPickOnBounds(true);
        retireButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: #ff0000;");
        retireButton.setOnAction(e -> {
            mc.retire();
        });
        
        // Se ponen en contenedores ajustados a las posiciones indicadas
        VBox mainBox = new VBox (20);
        mainBox.getChildren().add(placedCoins);
        HBox buttonBox = new HBox(20, removeCoinsButton, makeBetButton, retireButton, continueButton);
        mainBox.getChildren().add(buttonBox);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        StackPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(mainBox, new javafx.geometry.Insets(20, 20, 20, 20));

        // Se establecen las dimensiones de algunas cajas, que si no bloquean los clics en otros objetos
        cardsBox.setMaxWidth(Region.USE_PREF_SIZE);
        cardsBox.setMaxHeight(Region.USE_PREF_SIZE);
        mainBox.setMaxWidth(Region.USE_PREF_SIZE);
        mainBox.setMaxHeight(Region.USE_PREF_SIZE);
        coinVBox.setMaxWidth(Region.USE_PREF_SIZE);
        coinVBox.setMaxHeight(Region.USE_PREF_SIZE);
        
        // Se añaden todos al stackPane
        stackPane.getChildren().addAll(cardsBox, playerCoins, anteBet, callBet, mainBox, coinVBox);
        StackPane.setAlignment(mainBox, Pos.BOTTOM_CENTER);
        
        // Crea una nueva escena que se muestra en la ventana principal, con un tamaño de 1280x720 píxeles.
        Scene scene = new Scene(stackPane, 1280, 720);

        // Establece la escena creada como la escena que se mostrará en la ventana principal.
        primaryStage.setScene(scene);

        // Establece el título de la ventana principal de la aplicación.
        primaryStage.setTitle("Casino Hold'em");

        // Muestra la ventana principal en pantalla.
        primaryStage.show();
    }

    // Método para rellenar el contenedor de las cartas del dealer
    public int populateDealerCardsBox(List<Image> images) {
        dealerCardsBox.getChildren().clear();
        for (Image i : images) {
            dealerCardsBox.getChildren().add(createImageView(i, false, false, 0));
        }
        return dealerCardsBox.getChildren().size();
    }

    // Método para rellenar el contenedor de las cartas de la mesa
    public int populateTableCardsBox(List<Image> images) {
        tableCardsBox.getChildren().clear();
        int j = 0;
        for (Image i : images) {
            tableCardsBox.getChildren().add(createImageView(i, true, true, j));
            j++;
        }
        return tableCardsBox.getChildren().size();
    }

    // Método para rellenar el contenedor de las cartas del player
    public int populatePlayerCardsBox(List<Image> images) {
        playerCardsBox.getChildren().clear();
        int j = 0;
        for (Image i : images) {
            playerCardsBox.getChildren().add(createImageView(i, true, false, j));
            j++;
        }
        return playerCardsBox.getChildren().size();
    }

    // Crear una imageView a partir de una imagen, establecerle un evento de clic para poder escoger la carta
    private ImageView createImageView(Image image, Boolean clickable, Boolean table,
    		int index) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(cardHeight);
        imageView.setPreserveRatio(true);
        if (clickable) {
	        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                int ret = mc.pickCard(table, index);
	                if (ret == 1) {
	                	imageView.setOnMouseClicked(null);
	                }
	            }
	        });
        }
        return imageView;
    }
    
    // Establece el texto que muestra las monedas del jugador, usando el valor proporcionado
    public void setPlayerCoins(String s) {
    	playerCoins.setText("COINS: " + s);
    }
    // Establece el texto que muestra las monedas apostadas, usando el valor proporcionado
    public void setPlacedCoins(String s) {
    	placedCoins.setText("PLACED COINS: " + s);
    }
    
    // Obtiene el texto que muestra las monedas apostadas.
    public String getPlacedCoins() {
    	return placedCoins.getText();
    }
    
    // Obtiene el texto que muestra las monedas del jugador.
    public String getPlayerCoins() {
    	return playerCoins.getText();
    }
    
    // Establece el controlador pasado por parámetros
    public void setMainController(MainController mc) {
    	this.mc = mc;
    }
    
    // Establece el texto que muestra la apuesta inicial (ante), usando el valor proporcionado
    public void setAnteBet(String s) {
    	anteBet.setText("ANTE: " + s);
    }
    
    // Obtiene el texto que muestra la apuesta inicial (ante)
    public String getAnteBet() {
    	return anteBet.getText();
    }
    
    // Establece el texto que muestra la apuesta de llamada (call), usando el valor proporcionado
    public void setCallBet(String s) {
    	callBet.setText("CALL: " + s);
    }
    
    // Obtiene el texto que muestra la apuesta de llamada (call)
    public String getCallBet() {
    	return callBet.getText();
    }
}