package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class VueWin {

    private Stage stage;
    private Stage primaryStage;
    @FXML
    private Label gainJeton;
    private GestionMusique gestionMusique;

    public VueWin(Stage p) {
        primaryStage = p;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vue-win.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            gainJeton = (Label) root.lookup("#gainJeton");
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.setOnShown(event -> {
                stage.setAlwaysOnTop(true);
                stage.setAlwaysOnTop(false);
                stage.setX(primaryStage.getX() + (primaryStage.getWidth() - stage.getWidth()) / 2);
                stage.setY(primaryStage.getY() + (primaryStage.getHeight() - stage.getHeight()) / 2);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }

    public Label getGainJeton() {
        return gainJeton;
    }

    public void afficher() {
        stage.show();

        // --Code de sauvegarde de la musique Win-- //
        gestionMusique = new GestionMusique();
        gestionMusique.setMusique("ihm/src/main/resources/musique/Bruitdepièces.mp3");
        gestionMusique.setVolume(0.4);
        gestionMusique.lireMusique();

        // Événement pour arrêter la musique lorsque la fenêtre se ferme
        stage.setOnHidden(event -> {
            gestionMusique.arreterMusique();
            stage.close();
        });


        Duration pauseDuration = Duration.seconds(10);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> {

        }));
        timeline.play();

    }

    public Stage getStage() {
        return stage;
    }

}
