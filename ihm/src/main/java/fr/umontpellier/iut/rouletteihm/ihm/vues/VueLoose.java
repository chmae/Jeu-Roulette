package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class VueLoose {
    private Stage stage;
    private Stage primaryStage;

    private GestionMusique sonsTrompette;

    public VueLoose(Stage p) {
        primaryStage = p;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vue-loose.fxml"));
            loader.setController(this);
            Parent root = loader.load();

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

    public void afficher() {
        stage.show();

        // --Code de sauvegarde de la musique Win-- //
        sonsTrompette = new GestionMusique();
        sonsTrompette.setMusique("ihm/src/main/resources/musique/SadTrompette.mp3");
        sonsTrompette.setVolume(0.3);
        sonsTrompette.lireMusique();

        stage.setOnHidden(event -> {
            sonsTrompette.arreterMusique();
            stage.close();
        });

        Duration pauseDuration = Duration.seconds(5);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> {
        }));
        timeline.play();

    }

    public Stage getStage() {
        return stage;
    }
}
