package fr.umontpellier.iut.rouletteihm.ihm.vues;


import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Vue associée au joueur courant.
 */
public class VueJoueurCourant extends GridPane {

    private IJoueur joueur;
    private final IJeu jeu;

    @FXML
    private Label SoldePlayer;
    @FXML
    private ImageView miseJoueur; // Change this from HBox to ImageView
    @FXML
    private ImageView CouronneJoueur;
    @FXML
    private ImageView photoProfil;
    @FXML
    private ImageView flags;
    @FXML
    private ImageView fitheight;
    @FXML
    private Label soldePlayer2;
    @FXML
    private Label mise;
    private Label labelInstructions;
    private IntegerProperty langueChoisie;
    @FXML
    private Label labelMise;
    @FXML
    private Label labelMiseTotale;

    private ClignoterThread clignoterThread;
    @FXML
    private Label nomJoueur;
    @FXML
    private ImageView couronne;
    @FXML
    private ImageView passer;
    @FXML
    private ImageView passer1;

    private static VueJoueurCourant instance;
    public VueJoueurCourant(IJeu jeu, Label labelInstructions, IntegerProperty langueChoisie) {
        instance = this;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueJoueurCourant.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            SoldePlayer = (Label) root.lookup("#soldePlayer");
            mise = (Label) root.lookup("#mise");
            passer = (ImageView) root.lookup("#passer");
            passer1 = (ImageView) root.lookup("#passer1");
            nomJoueur = (Label) root.lookup("#nomJoueur");
            getChildren().addAll(root);
            hoverImagePasser(passer);
            hoverImagePasser(passer1);
            labelMise = (Label) root.lookup("#labelMise");
            labelMiseTotale = (Label) root.lookup("#labelMiseTotale");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
        this.langueChoisie = langueChoisie;
        setId("vueJoueurCourant");
        this.creerBindings();
        SoldePlayer.setText(String.valueOf(jeu.joueurCourantProperty().getValue().soldeProperty().getValue()));
        soldePlayer2.setText(String.valueOf(jeu.joueurCourantProperty().getValue().getMiseActuelle()));
        this.labelInstructions = labelInstructions;
        creerBindingsMisesInfo();
        nomJoueur.setText(jeu.joueurCourantProperty().getValue().getNom());
        joueur = jeu.joueurCourantProperty().get();
        clignoterThread = new ClignoterThread(couronne);
        clignoterThread.start();
    }

    /**
     * Crée les bindings entre les propriétés du joueur courant et les éléments graphiques JavaFX.
     */
    public void creerBindings() {
        jeu.joueurCourantProperty().addListener((observableValue, iJoueur, t1) -> {
            joueur = t1;
            SoldePlayer.setText(String.valueOf(joueur.soldeProperty().getValue()));
            soldePlayer2.setText(String.valueOf(joueur.getMiseActuelle()));
            nomJoueur.setText(joueur.getNom());
            joueur.setMiseTotale(0);
            joueur.setMiseActuelle(0);
            if (langueChoisie.intValue() == 0) {
                labelInstructions.setText(jeu.joueurCourantProperty().get().getNom()+", à toi de jouer !");
            } else {
                labelInstructions.setText(jeu.joueurCourantProperty().get().getNom()+", it's your turn !");
            }
        });

        jeu.joueurCourantProperty().get().getMiseTotaleProperty().addListener((observableValue, number, t1) -> mise.setText(String.valueOf(t1)));

        jeu.joueurCourantProperty().get().soldeProperty().addListener((observableValue, number, t1) -> {
            joueur = jeu.joueurCourantProperty().get();
            SoldePlayer.setText(String.valueOf(joueur.soldeProperty().getValue()));
        });

        jeu.joueurCourantProperty().get().getMiseActuelleProperty().addListener((observable, oldValue, newValue) -> {
            soldePlayer2.setText(String.valueOf(joueur.getMiseActuelle()));
        });
    }

    private void hoverImagePasser(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GOLD);
        dropShadow.setRadius(11);
        dropShadow.setSpread(0.6);

        // Création des transitions
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        FillTransition fillTransition = new FillTransition(Duration.millis(200));

        imageView.setOnMouseEntered(event -> {
            imageView.setEffect(dropShadow);
            scaleTransition.setFromX(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToY(1.2);
            scaleTransition.playFromStart();
            fillTransition.setFromValue(Color.TRANSPARENT);
            fillTransition.setToValue(Color.GOLD);
            fillTransition.playFromStart();
        });

        imageView.setOnMouseExited(event -> {
            imageView.setEffect(null);

            scaleTransition.setFromX(1.2);
            scaleTransition.setToX(1.0);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToY(1.0);
            scaleTransition.playFromStart();

            fillTransition.setFromValue(Color.GOLD);
            fillTransition.setToValue(Color.TRANSPARENT);
            fillTransition.playFromStart();
        });
    }


    public void stopClignotement() {
        if (clignoterThread != null) {
            clignoterThread.interrupt();
        }
    }

    public ImageView getPasser() {
        return passer;
    }

    public ImageView getPasser1() {
        return passer1;
    }

    public static class ClignoterThread extends Thread {

        private ImageView couronne;

        public ClignoterThread(ImageView couronne) {
            this.couronne = couronne;
        }

        // Méthode exécutée dans le thread
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Platform.runLater(() -> {
                        DropShadow dropShadow = new DropShadow(10, Color.web("#CC00FF"));
                        couronne.setEffect(dropShadow);
                    });
                    Thread.sleep(1000);
                    Platform.runLater(() -> {
                        DropShadow dropShadow = new DropShadow(10, Color.web("#FF00FF"));
                        couronne.setEffect(dropShadow);
                    });
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // Arrêt du thread
            }
        }
    }

    public void creerBindingsMisesInfo() {
        langueChoisie.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) {
                labelMise.setText("Mise:");
                labelMiseTotale.setText("MiseT:");
            } else {
                labelMise.setText("Bet:");
                labelMiseTotale.setText("TBet:");
            }
        });
    }

    public static VueJoueurCourant getInstance() {
        return instance;
    }

    public Label getNomJoueur() {
        return nomJoueur;
    }

}

