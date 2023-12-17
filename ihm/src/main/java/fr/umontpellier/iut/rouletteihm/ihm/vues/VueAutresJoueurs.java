package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VueAutresJoueurs extends Pane {

    private IJeu jeu;
    private IJoueur joueur;
    private Label solde;
    private Label solde1;
    private Label solde2;
    private Label username;
    private Label username1;
    private Label username2;
    private VueAccueil vueAccueil = new VueAccueil();
    private VueParametre vueParametre;

    private GestionMusique musiqueCasino = new GestionMusique();

    private GestionMusique sonsBoutonParametre = new GestionMusique();

    @FXML
    private ImageView lampe1;
    @FXML
    private ImageView lampe2;

    public VueAutresJoueurs(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueAutresJoueurs.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            ImageView topBackground = (ImageView) root.lookup("#topBackground");
            ImageView CornerBackground = (ImageView) root.lookup("#CornerBackground");
            ImageView CornerQuit = (ImageView) root.lookup("#CornerQuit");
            ImageView buttonQuit = (ImageView) root.lookup("#buttonQuit");
            ImageView parametre = (ImageView) root.lookup("#parametre");
            HBox autresJoueur = (HBox) root.lookup("#autresJoueur");
            ImageView lampe1 = (ImageView) root.lookup("#lampe1");
            ImageView lampe2 = (ImageView) root.lookup("#lampe2");
            solde2 = (Label) root.lookup("#solde2");
            solde1 = (Label) root.lookup("#solde1");
            solde = (Label) root.lookup("#solde");
            username = (Label) root.lookup("#username");
            username1 = (Label) root.lookup("#username1");
            username2 = (Label) root.lookup("#username2");
            getChildren().addAll(topBackground, CornerBackground, CornerQuit, buttonQuit, parametre, autresJoueur, lampe1, lampe2);


            HoverImage(buttonQuit);
            HoverImage(parametre);
            Stage primaryStage = new Stage();
            vueParametre = VueParametre.getInstance(primaryStage, musiqueCasino);

            //-- sons bouton-- //
            String cheminAudioBouton = "ihm/src/main/resources/musique/sonsBouton.mp3";
            sonsBoutonParametre.setMusique(cheminAudioBouton);
            sonsBoutonParametre.setVolume(0.2);

            //-- Musique Casino -- //
            String cheminAudioCasino = "ihm/src/main/resources/musique/casino.mp3";
            musiqueCasino.setMusique(cheminAudioCasino);
            musiqueCasino.setVolume(0.2);
            musiqueCasino.mettreLaMusiqueEnBoucle(true);
            musiqueCasino.lireMusiqueProgressivement(musiqueCasino.getVolume());

            buttonQuit.setOnMouseClicked(event -> {
                sonsBoutonParametre.lireMusique();
                sonsBoutonParametre.remettreMusiqueAuDebut();
                musiqueCasino.arreterMusique();
                musiqueCasino.remettreMusiqueAuDebut();

                Stage stage = (Stage) buttonQuit.getScene().getWindow();
                stage.close();
                if (vueAccueil.getScene() == null) {
                    primaryStage.setScene(new Scene(vueAccueil, 599, 390));
                }
                primaryStage.setTitle("Accueil");
                primaryStage.show();
            });


            vueAccueil.getBoutonQuitter().setOnMouseClicked(mouseEvent -> {
                vueAccueil.fermerPlateforme();
                primaryStage.close();
            });
            vueAccueil.getBoutonJouer().setOnMouseClicked(mouseEvent -> {
                Stage stage = (Stage) buttonQuit.getScene().getWindow();
                stage.show();
            });

            parametre.setOnMouseClicked(event -> {
                afficherVueParametre();
                sonsBoutonParametre.lireMusique();
                sonsBoutonParametre.remettreMusiqueAuDebut();
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> {
                        lampe1.setEffect(null);
                        lampe1.setStyle("-fx-effect: dropshadow(three-pass-box, black, 30, 0, 0, 0);");
                        lampe2.setEffect(null);
                        lampe2.setStyle("-fx-effect: dropshadow(three-pass-box, black, 30, 0, 0, 0);");
                    });
                    Thread.sleep(1000);
                    Platform.runLater(() -> {
                        lampe1.setEffect(null);
                        lampe1.setStyle("-fx-effect: dropshadow(three-pass-box, CC00FF, 30, 0, 0, 0);");
                        lampe2.setEffect(null);
                        lampe2.setStyle("-fx-effect: dropshadow(three-pass-box, CC00FF, 30, 0, 0, 0);");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void HoverImage(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setFromX(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToY(1.1);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            scaleTransition.play();
            imageView.setOpacity(0.7);
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            scaleTransition.setRate(-1);
            imageView.setOpacity(1.0);
            scaleTransition.play();
        });
    }

    private void afficherVueParametre() {
        vueParametre = VueParametre.getInstance((Stage) new Scene(new Parent() {}).getWindow(), musiqueCasino);
        vueParametre.show();
    }

    public IntegerProperty getLangueChoisie() {
        if (getScene() == null) {
            return VueParametre.getInstance((Stage) new Scene(new Parent() {}).getWindow(), musiqueCasino).getLangueChoisie();
        }
        return VueParametre.getInstance((Stage) getScene().getWindow(), musiqueCasino).getLangueChoisie();
    }

    public void creerBindings() {
        VueParametre.getInstance((Stage) new Scene(new Parent() {}).getWindow(), musiqueCasino).getLangueChoisie().addListener((observable, oldValue, newValue) -> {
            String texte = "";
            if(newValue.intValue()==0) {
                texte = "Solde :";
            } else {
                texte = "Balance :";
            }
            username.setText(texte);
            username1.setText(texte);
            username2.setText(texte);
        });
    }

}
