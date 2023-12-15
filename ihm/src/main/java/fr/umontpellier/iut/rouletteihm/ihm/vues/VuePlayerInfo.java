package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class VuePlayerInfo extends HBox {

    private IJeu jeu;

    public VuePlayerInfo(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VuePlayerInfo.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            getChildren().addAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
    }

}
