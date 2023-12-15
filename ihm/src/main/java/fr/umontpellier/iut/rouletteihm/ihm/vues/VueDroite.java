package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.JouerPartie;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class VueDroite extends VBox {

    private IJeu jeu;

    @FXML
    private ImageView passer;
    @FXML
    private ImageView couronne;

    private JouerPartie jouerPartie = new JouerPartie();

    private ClignoterThread clignoterThread;

    public VueDroite(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueDroite.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getChildren().addAll(root);
            passer.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("passer");
                    jouerPartie.passerTour();
                    System.out.println("OK");
                }
            });

            clignoterThread = new ClignoterThread(couronne);
            clignoterThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
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
}
