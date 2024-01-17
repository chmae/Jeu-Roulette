// VueGauche.java
package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Probabilite;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Vue associée au panneau de gauche de l'application.
 * <p>
 * Cette vue est définie dans le fichier VueGauche.fxml.
 */
public class VueGauche extends VBox {

    private IJeu jeu;
    private VueDuJeu vueDuJeu;
    @FXML
    private Text titreProba;
    @FXML
    private VBox boxGauche;
    @FXML
    private VBox lastNumber;

    private Probabilite probabilite;

    // Constructeur
    public VueGauche(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueGauche.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            getChildren().addAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
        setId("boxGauche");

        probabilite = new Probabilite(System.currentTimeMillis());
        boxGauche = new VBox();
        getChildren().add(boxGauche);
    }

    public void creerElementResultat(Roulette.resultatTour resultat) {
        Circle cercle = new Circle(15);
        cercle.setStroke(Color.WHITE);
        cercle.setStrokeWidth(1);
        DropShadow ombre = new DropShadow();
        ombre.setColor(Color.DARKGRAY);
        cercle.setEffect(ombre);

        if (resultat.getCouleur().equals("Noir")) {
            cercle.setFill(Color.BLACK);
        } else if (resultat.getCouleur().equals("Rouge")) {
            cercle.setFill(Color.RED);
        } else {
            cercle.setFill(Color.GREEN);
        }

        Text texte = new Text(String.valueOf(resultat.getValeur()));
        texte.setFill(Color.WHITE);

        StackPane stackPaneResultat = new StackPane();
        stackPaneResultat.getChildren().addAll(cercle, texte);

        stackPaneResultat.setAccessibleText(String.valueOf(resultat.getValeur()));

        lastNumber.getChildren().add(stackPaneResultat);
        lastNumber.setSpacing(15);
    }


    // Méthode appelée par le controleur lorsqu'un nouveau résultat est disponible
    public void afficherDerniersResultats(Roulette.resultatTour nouveauResultat) {
        ArrayList<Roulette.resultatTour> listeResultats = new ArrayList<>();
        if (listeResultats.size() > 5) {
            listeResultats.remove(0);
            listeResultats.add(nouveauResultat);
        } else {
            listeResultats.add(nouveauResultat);
        }

        if (lastNumber.getChildren().size() > 4) {
            lastNumber.getChildren().remove(0);
        }
        for (Roulette.resultatTour resultat : listeResultats) {
            creerElementResultat(resultat);
        }
    }

}