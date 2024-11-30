package com.example.pokerapp.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.testfx.framework.junit5.ApplicationTest;

public class MainViewTest extends ApplicationTest {

	private MainView mainView;

    @Override
    public void start(Stage stage) {
        mainView = new MainView();
        mainView.start(stage);
    }
    
    @Test
    void testGetTopHBox() {
        HBox topHBox = mainView.topHBox;
        assertNotNull(topHBox, "Top HBox should not be null");
    }

    @Test
    void testGetMiddleHBox() {
        HBox middleHBox = mainView.middleHBox;
        assertNotNull(middleHBox, "Middle HBox should not be null");
    }

    @Test
    void testGetBottomHBox() {
        HBox bottomHBox = mainView.bottomHBox;
        assertNotNull(bottomHBox, "Bottom HBox should not be null");
    }

    @Test
    void testGetVBox() {
        VBox vBox = mainView.vBox;
        assertNotNull(vBox, "VBox should not be null");
    }

    @Test
    void testGetCardHeight() {
    	Platform.runLater(() -> {
            try {
            	double cardHeight = mainView.cardHeight;
                assertEquals(200, cardHeight, "Card height should be 200");
            } catch (Exception e) {
                e.printStackTrace();
                fail("Error during UI update");
            }
        });
    }
    
    @Test
    public void testCreateImageView() {
        Image image = mock(Image.class);
        ImageView imageView = mainView.createImageView(image);

        assertNotNull(imageView, "ImageView should not be null");
        assertEquals(200, imageView.getFitHeight(), "ImageView should have the correct height");
        assertTrue(imageView.isPreserveRatio(), "ImageView should preserve aspect ratio");
    }

    @Test
    public void testPopulateTopHBox() {
        Platform.runLater(() -> {
            try {
            	ImageView iv1 = mainView.createImageView(new Image(getClass().getResource("/FOUR_CLUBS.png").toString()));
            	ImageView iv2 = mainView.createImageView(new Image(getClass().getResource("/FIVE_CLUBS.png").toString()));
                List<ImageView> imageViews = List.of(
                        iv1,
                        iv2
                );
            	mainView.populateTopHBox(imageViews);
                assertEquals(2, mainView.topHBox.getChildren().size(), "Top HBox should contain 2 image views");
                assertTrue(mainView.topHBox.getChildren().contains(iv1), "Top HBox should contain the first image view");
                assertTrue(mainView.topHBox.getChildren().contains(iv2), "Top HBox should contain the second image view");
            } catch (Exception e) {
                e.printStackTrace();
                fail("Error during UI update");
            }
        });
    }

    @Test
    public void testPopulateMiddleHBox() {
        Platform.runLater(() -> {
            try {
            	ImageView iv1 = mainView.createImageView(new Image(getClass().getResource("/FOUR_CLUBS.png").toString()));
            	ImageView iv2 = mainView.createImageView(new Image(getClass().getResource("/FIVE_CLUBS.png").toString()));
                List<ImageView> imageViews = List.of(
                        iv1,
                        iv2
                );
            	mainView.populateMiddleHBox(imageViews);
            	HBox middleHBox = mainView.middleHBox;
                assertEquals(2, middleHBox.getChildren().size(), "Middle HBox should contain 2 image views");
                assertTrue(middleHBox.getChildren().contains(iv1), "Middle HBox should contain the first image view");
                assertTrue(middleHBox.getChildren().contains(iv2), "Middle HBox should contain the second image view");
            } catch (Exception e) {
                e.printStackTrace();
                fail("Error during UI update");
            }
        });
    }

    @Test
    public void testPopulateBottomHBox() {
        Platform.runLater(() -> {
            try {
            	ImageView iv1 = mainView.createImageView(new Image(getClass().getResource("/FOUR_CLUBS.png").toString()));
            	ImageView iv2 = mainView.createImageView(new Image(getClass().getResource("/FIVE_CLUBS.png").toString()));
                List<ImageView> imageViews = List.of(
                        iv1,
                        iv2
                );
                mainView.populateBottomHBox(imageViews);
                HBox bottomHBox = mainView.bottomHBox;
                assertEquals(2, bottomHBox.getChildren().size(), "Bottom HBox should contain 2 image views");
                assertTrue(bottomHBox.getChildren().contains(iv1), "Bottom HBox should contain the first image view");
                assertTrue(bottomHBox.getChildren().contains(iv2), "Bottom HBox should contain the second image view");
            } catch (Exception e) {
                e.printStackTrace();
                fail("Error during UI update");
            }
        });
    }

    @Test
    public void testCreateViewElements() {
        assertNotNull(mainView.vBox, "VBox should be initialized");
        assertNotNull(mainView.topHBox, "Top HBox should be initialized");
        assertNotNull(mainView.middleHBox, "Middle HBox should be initialized");
        assertNotNull(mainView.bottomHBox, "Bottom HBox should be initialized");
    }

    @Test
    public void testAlignmentAndMargins() {
        assertEquals(Pos.CENTER, mainView.vBox.getAlignment(), "VBox should have CENTER alignment");
        assertEquals(50.0, mainView.vBox.getMargin(mainView.topHBox).getTop(), "Top margin should be 50px");
        assertEquals(50.0, mainView.vBox.getMargin(mainView.bottomHBox).getBottom(), "Bottom margin should be 50px");
    }
}