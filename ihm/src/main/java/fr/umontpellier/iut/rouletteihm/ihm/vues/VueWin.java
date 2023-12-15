package fr.umontpellier.iut.rouletteihm.ihm.vues;

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
    private Button rejouer = new Button();
    private boolean rejouerClicked = false;
    @FXML
    private Label gainJeton;

    public VueWin(Stage p) {
        primaryStage = p;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vue-win.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            rejouer = (Button) root.lookup("#rejouer");
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
        Duration pauseDuration = Duration.seconds(10);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> {
            if (!isRejouerClicked()) {
                stage.close();
            }
        }));
        timeline.play();
        rejouer.setOnAction(event -> {
            timeline.stop();
            setRejouerClicked(true);
            stage.close();
            VueDuJeu.getInstance().resetPartie();
            if (isRejouerClicked()) {
                primaryStage.show();
            }
        });
    }


    public Button getRejouerButton() {
        return rejouer;
    }

    public boolean isRejouerClicked() {
        return rejouerClicked;
    }

    public void setRejouerClicked(boolean rejouerClicked) {
        this.rejouerClicked = rejouerClicked;
    }

    public Stage getStage() {
        return stage;
    }

}
