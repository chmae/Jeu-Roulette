package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.StatistiquesRoulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.JouerPartie;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Probabilite;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueDuJeu;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VueDroite extends VBox {

    private IJeu jeu;

    private ClignoterThread clignoterThread;

    private StatistiquesRoulette statistiquesRoulette = new StatistiquesRoulette();
    @FXML
    private Label couleurs;
    @FXML
    private Label numero;

    @FXML
    private Circle rouge;
    @FXML
    private Circle noir;
    @FXML
    private Circle vert;
    @FXML
    private Pane pane;
    @FXML
    private Label Rouge;
    @FXML
    private Label Noir;
    @FXML
    private Label Vert;
    public VueDroite(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueDroite.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            noir = (Circle) root.lookup("#noir");
            rouge = (Circle) root.lookup("#rouge");
            vert = (Circle) root.lookup("#vert");
            pane = (Pane) root.lookup("#pane");
            numero = (Label) root.lookup("#numero");
            getChildren().addAll(root);


        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
    }


    public void afficherStats() {
        Platform.runLater(() -> {
            statistiquesRoulette.mettreAJourProbabilites();

            Rouge.textProperty().bind(Bindings.createStringBinding(() ->
                            String.format("%.0f%%", statistiquesRoulette.getProbabiliteRouge() * 100),
                    statistiquesRoulette.probabiliteRougeProperty()));

            Noir.textProperty().bind(Bindings.createStringBinding(() ->
                            String.format("%.0f%%", statistiquesRoulette.getProbabiliteNoir() * 100),
                    statistiquesRoulette.probabiliteNoirProperty()));

            Vert.textProperty().bind(Bindings.createStringBinding(() ->
                            String.format("%.0f%%", statistiquesRoulette.getProbabiliteVert() * 100),
                    statistiquesRoulette.probabiliteVertProperty()));

            int[] numerosPlusSortis = statistiquesRoulette.numerosLesPlusSortis();

            StringBuilder numerosText = new StringBuilder();
            for (int numero : numerosPlusSortis) {
                numerosText.append(numero).append(", ");
            }
            if (numerosText.length() > 0) {
                numerosText.delete(numerosText.length() - 2, numerosText.length());
            }

            if (statistiquesRoulette.getNbToursJoues() >= 3) {
                numero.setText(numerosText.toString());
            } else {
                numero.setText("Numéro");
            }
        });
    }

    public void stopClignotement() {
        if (clignoterThread != null) {
            clignoterThread.interrupt();
        }
    }


    public static class ClignoterThread extends Thread {

        private ImageView couronne;

        public ClignoterThread(ImageView couronne) {
            this.couronne = couronne;
        }

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


    public void setStatistiquesRoulette(StatistiquesRoulette statistiquesRoulette) {
        this.statistiquesRoulette = statistiquesRoulette;
    }
}
