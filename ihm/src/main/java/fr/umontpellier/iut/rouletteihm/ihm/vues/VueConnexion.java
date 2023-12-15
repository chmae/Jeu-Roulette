package fr.umontpellier.iut.rouletteihm.ihm.vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VueConnexion extends Pane {

    @FXML
    private ImageView seconnecter;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    public VueConnexion() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/VueConnexion.fxml"));
            getChildren().add(root);
            seconnecter = (ImageView) root.lookup("#seconnecter");
            email = (TextField) root.lookup("#email");
            password = (PasswordField) root.lookup("#password");

            seconnecter.setOnMouseEntered(event -> seconnecter.setOpacity(0.7));
            seconnecter.setOnMouseExited(event -> seconnecter.setOpacity(1.0));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
