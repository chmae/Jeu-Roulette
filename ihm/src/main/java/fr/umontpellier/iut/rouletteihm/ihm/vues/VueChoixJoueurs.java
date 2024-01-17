package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.application.controller.client.ControllerClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;

public class VueChoixJoueurs extends Pane {

    public TextField getNom1() {
        return nom1;
    }

    public TextField getSolde1() {
        return solde1;
    }

    public TextField getNom2() {
        return nom2;
    }

    public TextField getSolde2() {
        return solde2;
    }

    public TextField getNom3() {
        return nom3;
    }

    public TextField getSolde3() {
        return solde3;
    }

    public TextField getNom4() {
        return nom4;
    }

    public TextField getSolde4() {
        return solde4;
    }

    @FXML
    private TextField nom1;
    @FXML
    private TextField solde1;
    @FXML
    private TextField nom2;
    @FXML
    private TextField solde2;
    @FXML
    private TextField nom3;
    @FXML
    private TextField solde3;
    @FXML
    private TextField nom4;
    @FXML
    private TextField solde4;
    @FXML
    private ImageView valider;

    private BooleanProperty validationChoixJoueurs;

    public VueChoixJoueurs() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/vueChoixJoueur.fxml"));


            nom1 = (TextField) root.lookup("#nom1");
            solde1 = (TextField) root.lookup("#solde1");

            nom2 = (TextField) root.lookup("#nom2");
            solde2 = (TextField) root.lookup("#solde2");

            nom3 = (TextField) root.lookup("#nom3");
            solde3 = (TextField) root.lookup("#solde3");

            nom4 = (TextField) root.lookup("#nom4");
            solde4 = (TextField) root.lookup("#solde4");

            valider = (ImageView) root.lookup("#valider");

            getChildren().add(root);

            valider.setOnMouseEntered(event -> valider.setOpacity(0.7));
            valider.setOnMouseExited(event -> valider.setOpacity(1.0));


        } catch (IOException e) {
            e.printStackTrace();
        }
        validationChoixJoueurs = new SimpleBooleanProperty(false);
        creerBinding();
    }

    public void utilisateurConnecte() {
        nom1.setEditable(false);
        solde1.setEditable(false);
        nom1.setText(ControllerClient.getPrenomClient());
        solde1.setText(String.valueOf(ControllerClient.getSoldeClient()));
    }

    public void multiplayerIsntSelected() {
        nom2.setVisible(false);
        solde2.setVisible(false);
        nom3.setVisible(false);
        solde3.setVisible(false);
        nom4.setVisible(false);
        solde4.setVisible(false);
    }

    public int getNombreJoueurs() {
        if(nom2.getText().isEmpty()) {
            return 1;
        } else if(nom3.getText().isEmpty()) {
            return 2;
        } else if(nom4.getText().isEmpty()) {
            return 3;
        } else {
            return 4;
        }
    }

    public BooleanProperty validationChoixJoueurs() {
        return validationChoixJoueurs;
    }

    public void creerBinding() {
        valider.setOnMouseClicked(event -> {
            validationChoixJoueurs.set(true);
        });
    }



}
