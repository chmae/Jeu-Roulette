package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.List;

public class VueAutresJoueurs extends Pane {

    private IJeu jeu;
    private IJoueur joueur;
    private Label solde;
    private Label solde1;
    private Label solde2;
    private Label username;
    private Label username1;
    private Label username2;
    private Label labelSolde;
    private Label labelSolde1;
    private Label labelSolde2;
    private ImageView bgPlayer;
    private ImageView bgPlayer1;
    private ImageView bgPlayer2;
    private ImageView couronnePlayer;
    private ImageView couronnePlayer1;
    private ImageView couronnePlayer2;
    private ImageView photoProfil;
    private ImageView photoProfil1;
    private ImageView photoProfil2;
    private Label[] soldeTab;
    private Label[] usernameTab;

    private VueAccueil vueAccueil = new VueAccueil();
    private VueParametre vueParametre;

    private GestionMusique musiqueCasino = new GestionMusique();

    private GestionMusique sonsBoutonParametre = new GestionMusique();

    @FXML
    private ImageView lampe1;
    @FXML
    private ImageView lampe2;

    private List<Joueur> joueurs;

    public VueAutresJoueurs(IJeu jeu, List<Joueur> joueurs) {
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
            labelSolde = (Label) root.lookup("#labelSolde");
            labelSolde1 = (Label) root.lookup("#labelSolde1");
            labelSolde2 = (Label) root.lookup("#labelSolde2");
            bgPlayer = (ImageView) root.lookup("#bgPlayer");
            bgPlayer1 = (ImageView) root.lookup("#bgPlayer1");
            bgPlayer2 = (ImageView) root.lookup("#bgPlayer2");
            couronnePlayer = (ImageView) root.lookup("#couronnePlayer");
            couronnePlayer1 = (ImageView) root.lookup("#couronnePlayer1");
            couronnePlayer2 = (ImageView) root.lookup("#couronnePlayer2");
            photoProfil = (ImageView) root.lookup("#photoProfil");
            photoProfil1 = (ImageView) root.lookup("#photoProfil1");
            photoProfil2 = (ImageView) root.lookup("#photoProfil2");
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
        this.joueurs=joueurs;
        soldeTab = new Label[]{solde, solde1, solde2};
        usernameTab = new Label[]{username, username1, username2};

        solde.setVisible(false);
        solde1.setVisible(false);
        solde2.setVisible(false);
        username.setVisible(false);
        username1.setVisible(false);
        username2.setVisible(false);
        labelSolde.setVisible(false);
        labelSolde1.setVisible(false);
        labelSolde2.setVisible(false);
        bgPlayer.setVisible(false);
        bgPlayer1.setVisible(false);
        bgPlayer2.setVisible(false);
        couronnePlayer.setVisible(false);
        couronnePlayer1.setVisible(false);
        couronnePlayer2.setVisible(false);
        photoProfil.setVisible(false);
        photoProfil1.setVisible(false);
        photoProfil2.setVisible(false);


        if(joueurs.size()>1) {
            solde.setText(String.valueOf(joueurs.get(1).getSolde()));
            solde.setVisible(true);
            username.setText(joueurs.get(1).getNom());
            username.setVisible(true);
            labelSolde.setVisible(true);
            bgPlayer.setVisible(true);
            couronnePlayer.setVisible(true);
            photoProfil.setVisible(true);
            if(joueurs.size()>2) {
                solde1.setText(String.valueOf(joueurs.get(2).getSolde()));
                solde1.setVisible(true);
                username1.setText(joueurs.get(2).getNom());
                username1.setVisible(true);
                labelSolde1.setVisible(true);
                bgPlayer1.setVisible(true);
                couronnePlayer1.setVisible(true);
                photoProfil1.setVisible(true);
                if(joueurs.size()>3) {
                    solde2.setText(String.valueOf(joueurs.get(3).getSolde()));
                    solde2.setVisible(true);
                    username2.setText(joueurs.get(3).getNom());
                    username2.setVisible(true);
                    labelSolde2.setVisible(true);
                    bgPlayer2.setVisible(true);
                    couronnePlayer2.setVisible(true);
                    photoProfil2.setVisible(true);
                }
            }
        }
        creerBindings();

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
        vueParametre = VueParametre.getInstance((Stage) new Scene(new Parent() {
        }).getWindow(), musiqueCasino);
        vueParametre.show();
    }

    public IntegerProperty getLangueChoisie() {
        if (getScene() == null) {
            return VueParametre.getInstance((Stage) new Scene(new Parent() {
            }).getWindow(), musiqueCasino).getLangueChoisie();
        }
        return VueParametre.getInstance((Stage) getScene().getWindow(), musiqueCasino).getLangueChoisie();
    }

    public void creerBindings() {
        VueParametre.getInstance((Stage) new Scene(new Parent() {
        }).getWindow(), musiqueCasino).getLangueChoisie().addListener((observable, oldValue, newValue) -> {
            String texte = "";
            if (newValue.intValue() == 0) {
                texte = "Solde :";
            } else {
                texte = "Balance :";
            }
            labelSolde.setText(texte);
            labelSolde1.setText(texte);
            labelSolde2.setText(texte);
        });

        jeu.joueurCourantProperty().addListener((observable, oldValue, newValue) -> {
            int placeJoueurCourant = 0;
            for (int i=0; i<joueurs.size(); i++) {
                if (joueurs.get(i)==newValue) {
                    placeJoueurCourant = i;
                    break;
                }
            }
            int placerJoueur = placeJoueurCourant+1;
            for (int j=0; j<joueurs.size()-1; j++) {
                if (placerJoueur>joueurs.size()-1) {
                    placerJoueur = 0;
                }
                soldeTab[j].setText(String.valueOf(joueurs.get(placerJoueur).getSolde()));
                usernameTab[j].setText(joueurs.get(placerJoueur).getNom());
                placerJoueur++;
            }
        });
    }

}
