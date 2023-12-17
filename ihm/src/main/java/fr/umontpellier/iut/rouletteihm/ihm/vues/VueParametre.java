package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.animation.ScaleTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.io.IOException;

public class VueParametre {

    private static VueParametre instance;
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
    private IntegerProperty langue;
    @FXML
    private ImageView valideNom;
    @FXML
    private ImageView valideSolde;
    @FXML
    private ImageView music ;



    private VueParametre(Stage p, GestionMusique gestionmusique) {
        primaryStage = p;

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Vue-reglage.fxml"));

            parametrePane = (Pane) root.lookup("#parametrePane");
            titleLabel = (Label) root.lookup("#Title");
            volumeSlider = (Slider) root.lookup("#volume");
            langue = new SimpleIntegerProperty();
            music = (ImageView) root.lookup("#music");
            franceIcon = (ImageView) root.lookup("#buttonFrancais");
            creerBindingsLangue(franceIcon, 1);
            ukIcon = (ImageView) root.lookup("#buttonAnglais");
            creerBindingsLangue(ukIcon, 0);
            languesLabel = (Label) root.lookup("#languesLabel");
            disconnectButton = (ImageView) root.lookup("#disconnect");
            quitButton = (ImageView) root.lookup("#buttonQuit");
            disconnectLabel = (Label) root.lookup("#disconnectLabel");
            valideNom = (ImageView) root.lookup("#valideNom");
            valideSolde = (ImageView) root.lookup("#valideSolde");
            HoverImage(valideNom);
            HoverImage(valideSolde);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));

            // -- Media-- //
            Image playImage = new Image("images/button-music-On.png");
            Image pauseImage = new Image("images/button-music-Off.png");
            music.setImage(playImage);

            // Ajout d'un événement au bouton de musique
            music.setOnMouseClicked(event -> {
                if (gestionmusique.getStatus()) {
                    gestionmusique.mettrePauseMusique();
                    // Changer l'image en mode pause
                    music.setImage(pauseImage);
                } else {
                    gestionmusique.lireMusiqueProgressivement(gestionmusique.getVolume());
                    // Changer l'image en mode play
                    music.setImage(playImage);
                }
            });

            volumeSlider.setMin(0);
            volumeSlider.setMax(10);
            volumeSlider.setValue(2);

            // Liaison entre le Slider et la propriété volume du MediaPlayer
            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volumeValue = newValue.doubleValue() / 10.0;
                gestionmusique.setVolume(volumeValue);
            });


            quitButton.setOnMouseClicked(event -> {
                // sons bouton quit //
                GestionMusique sonsBoutonQuit = new GestionMusique();
                String cheminAudioBouton = "ihm/src/main/resources/musique/sonsBouton.mp3";
                sonsBoutonQuit.setMusique(cheminAudioBouton);
                sonsBoutonQuit.setVolume(0.2);
                sonsBoutonQuit.lireMusique();
                sonsBoutonQuit.remettreMusiqueAuDebut();

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
            HoverImage(valideNom);
            HoverImage(valideSolde);
            HoverImage(music);
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

    private void creerBindingsLangue(ImageView imageLangue, int choixLangue){
        EventHandler<MouseEvent> changeLangue = mouseEvent -> {

            if(choixLangue==0) {
                titleLabel.setText("Settings");
                disconnectLabel.setText("Log off");
                languesLabel.setText("Languages :");
                langue.set(0);
            }
            else {
                titleLabel.setText("Réglages");
                disconnectLabel.setText("Se déconnecter");
                languesLabel.setText("Langues :");
                langue.set(1);
            }
        };

        imageLangue.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, changeLangue);
    }

    public IntegerProperty getLangueProperty() {
        return langue;
    }

    public static VueParametre getInstance(Stage p, GestionMusique gestionmusique) {
        if (instance == null) {
            instance = new VueParametre(p, gestionmusique);
        }
        return instance;
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
