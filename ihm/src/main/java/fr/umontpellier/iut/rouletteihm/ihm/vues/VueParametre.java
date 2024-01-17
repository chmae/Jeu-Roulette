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

import java.io.IOException;
/**
 * Vue associée à la fenêtre de réglages.
 */
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
    private Label modificationProfilLabel;
    @FXML
    private Label nomModifLabel;
    @FXML
    private Label soldeModifLabel;
    @FXML
    private ImageView disconnectButton;
    @FXML
    private ImageView quitButton;
    @FXML
    private Label disconnectLabel;

    private Stage primaryStage;
    private Stage stage;
    private IntegerProperty langue;
    private static VueParametre instance;
    @FXML
    private ImageView valideNom;
    @FXML
    private ImageView valideSolde;
    @FXML
    private ImageView music;


    /**
     * Constructeur
     *
     * @param p Stage de la fenêtre principale
     */
    private VueParametre(Stage p, GestionMusique gestionmusique) {
        primaryStage = p;

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Vue-reglage.fxml"));

            parametrePane = (Pane) root.lookup("#parametrePane");
            titleLabel = (Label) root.lookup("#Title");
            volumeSlider = (Slider) root.lookup("#volume");
            langue = new SimpleIntegerProperty(0);
            music = (ImageView) root.lookup("#music");
            franceIcon = (ImageView) root.lookup("#buttonFrancais");
            creerBindingsLangue(franceIcon, 0);
            ukIcon = (ImageView) root.lookup("#buttonAnglais");
            creerBindingsLangue(ukIcon, 1);
            languesLabel = (Label) root.lookup("#languesLabel");
            modificationProfilLabel = (Label) root.lookup("#modificationProfilLabel");
            nomModifLabel = (Label) root.lookup("#nomModifLabel");
            soldeModifLabel = (Label) root.lookup("#soldeModifLabel");
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

            configurerMusique(gestionmusique);

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
    public void configurerMusique(GestionMusique gestionmusique) {
        Image playImage = new Image("images/button-music-On.png");
        Image pauseImage = new Image("images/button-music-Off.png");
        music.setImage(playImage);

        configurerEvenementMusique(gestionmusique, playImage, pauseImage);
        configurerVolumeSlider(gestionmusique);
    }

    private void configurerEvenementMusique(GestionMusique gestionmusique, Image playImage, Image pauseImage) {
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
    }

    private void configurerVolumeSlider(GestionMusique gestionmusique) {
        volumeSlider.setMin(0);
        volumeSlider.setMax(10);
        volumeSlider.setValue(2);

        // Liaison entre le Slider et la propriété volume du MediaPlayer
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volumeValue = newValue.doubleValue() / 10.0;
            gestionmusique.setVolume(volumeValue);
        });
    }


    public void reset() {
        volumeSlider.setValue(2);
        Image playImage = new Image("images/button-music-On.png");
        music.setImage(playImage);
        this.show();
    }

    // Cette méthode permet de faire un effet de zoom sur un label
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

    // Cette méthode permet de changer la langue de l'application
    private void creerBindingsLangue(ImageView imageView, int langue) {
        EventHandler<MouseEvent> langueChange = mouseEvent -> {
            this.langue.set(langue);
            if (langue == 0) {
                this.languesLabel.setText("Langues :");
                this.disconnectLabel.setText("Se déconnecter");
                this.modificationProfilLabel.setText("Modification profil :");
                this.nomModifLabel.setText("Nom");
                this.soldeModifLabel.setText("Solde");
                this.titleLabel.setText("Réglages");
            } else {
                this.languesLabel.setText("Languages :");
                this.disconnectLabel.setText("Log Off");
                this.modificationProfilLabel.setText("Update profile :");
                this.nomModifLabel.setText("Name");
                this.soldeModifLabel.setText("Balance");
                this.titleLabel.setText("Settings");
            }
        };

        imageView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, langueChange);
    }

    public IntegerProperty getLangueProperty() {
        return langue;
    }

    public synchronized static VueParametre getInstance(Stage p, GestionMusique gestionmusique) {
        if (instance == null) {
            instance = new VueParametre(p, gestionmusique);
        }
        return instance;
    }

    public IntegerProperty getLangueChoisie() {
        return langue;
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
