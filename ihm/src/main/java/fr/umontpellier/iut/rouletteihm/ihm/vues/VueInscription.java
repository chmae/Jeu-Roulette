package fr.umontpellier.iut.rouletteihm.ihm.vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VueInscription extends Pane {

    @FXML
    private ImageView inscription;
    @FXML
    private TextField prenom;


    public VueInscription() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/VueInscription.fxml"));
            prenom = (TextField) root.lookup("#prenom");
            inscription = (ImageView) root.lookup("#inscription");
            getChildren().add(root);

            inscription.setOnMouseEntered(event -> inscription.setOpacity(0.7));
            inscription.setOnMouseExited(event -> inscription.setOpacity(1.0));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
