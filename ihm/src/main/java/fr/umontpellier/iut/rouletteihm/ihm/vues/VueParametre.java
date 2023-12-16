package fr.umontpellier.iut.rouletteihm.ihm.vues;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class VueParametre {

    @FXML
    private Pane parametrePane;
    @FXML
    private Label titleLabel;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView franceIcon;
    @FXML
    private ImageView ukIcon;
    @FXML
    private Label languesLabel;
    @FXML
    private ImageView disconnectButton;
    @FXML
    private ImageView quitButton;
    @FXML
    private Label disconnectLabel;

    private Stage primaryStage;
    private Stage stage;

    public VueParametre(Stage p) {
        primaryStage = p;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Vue-reglage.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            parametrePane = (Pane) root.lookup("#parametrePane");
            titleLabel = (Label) root.lookup("#Title");
            volumeSlider = (Slider) root.lookup("#volume");
            franceIcon = (ImageView) root.lookup("#buttonFrancais");
            ukIcon = (ImageView) root.lookup("#buttonAnglais");
            languesLabel = (Label) root.lookup("#languesLabel");
            disconnectButton = (ImageView) root.lookup("#disconnect");
            quitButton = (ImageView) root.lookup("#buttonQuit");
            disconnectLabel = (Label) root.lookup("#disconnectLabel");
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));

            quitButton.setOnMouseClicked(event -> {
                stage = (Stage) quitButton.getScene().getWindow();
                stage.close();
            });
            quitButton.setOnMouseEntered(event -> {
                addShadowEffect(quitButton);
                scaleButton(quitButton, 1.1);
            });

            quitButton.setOnMouseExited(event -> {
                removeShadowEffect(quitButton);
                scaleButton(quitButton, 1.0);
            });
            disconnectButton.setOnMouseEntered(event -> {
                addShadowEffect(disconnectButton);
                scaleButton(disconnectButton, 1.1);
                scaleLabel(disconnectLabel, 1.1);
            });

            disconnectButton.setOnMouseExited(event -> {
                removeShadowEffect(disconnectButton);
                scaleButton(disconnectButton, 1.0);
                scaleLabel(disconnectLabel, 1.0);
            });
            disconnectLabel.setOnMouseEntered(event -> {
                scaleLabel(disconnectLabel, 1.1);
                scaleButton(disconnectButton, 1.1);
            });

            disconnectLabel.setOnMouseExited(event -> {
                scaleLabel(disconnectLabel, 1.0);
                scaleButton(disconnectButton, 1.0);
            });

            HoverImage(franceIcon);
            HoverImage(ukIcon);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scaleLabel(Label label, double scaleFactor) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), label);
        scaleTransition.setToX(scaleFactor);
        scaleTransition.setToY(scaleFactor);
        scaleTransition.play();
    }
    private void addShadowEffect(ImageView button) {
        button.setEffect(new DropShadow());
    }

    private void removeShadowEffect(ImageView button) {
        button.setEffect(null);
    }

    private void scaleButton(ImageView button, double scaleFactor) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(scaleFactor);
        scaleTransition.setToY(scaleFactor);
        scaleTransition.play();
    }

    private void HoverImage(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);

        imageView.setOnMouseEntered(event -> {
            imageView.setEffect(dropShadow);
            scaleTransition.setFromX(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToY(1.2);
            scaleTransition.playFromStart();
        });

        imageView.setOnMouseExited(event -> {
            imageView.setEffect(null);
            scaleTransition.setFromX(1.2);
            scaleTransition.setToX(1.0);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToY(1.0);
            scaleTransition.playFromStart();
        });
    }

    public Pane getPane() {
        return parametrePane;
    }

    public void show() {
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
