package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class VueJoueurCourant extends GridPane {

    private IJoueur joueur;
    private final IJeu jeu;

    @FXML
    private Label SoldePlayer;
    @FXML
    private ImageView miseJoueur; // Change this from HBox to ImageView
    @FXML
    private ImageView CouronneJoueur;
    @FXML
    private ImageView photoProfil;
    @FXML
    private ImageView flags;
    @FXML
    private ImageView fitheight;
    @FXML
    private Label soldePlayer2;
    @FXML
    private Label mise;
    private Label labelInstructions;

    public VueJoueurCourant(IJeu jeu, Label labelInstructions) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueJoueurCourant.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            SoldePlayer = (Label) root.lookup("#soldePlayer");
            mise = (Label) root.lookup("#mise");
            getChildren().addAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
        setId("vueJoueurCourant");
        this.creerBindings();
        SoldePlayer.setText(String.valueOf(jeu.joueurCourantProperty().getValue().soldeProperty().getValue()));
        soldePlayer2.setText(String.valueOf(jeu.joueurCourantProperty().getValue().getMiseActuelle()));
        this.labelInstructions = labelInstructions;
        joueur = jeu.joueurCourantProperty().get();
    }

    public void creerBindings() {
        jeu.joueurCourantProperty().addListener((observableValue, iJoueur, t1) -> {
            joueur = t1;
            SoldePlayer.setText(String.valueOf(joueur.soldeProperty().getValue()));
            soldePlayer2.setText(String.valueOf(joueur.getMiseActuelle()));
            jeu.tournerTour();
            labelInstructions.setText("Quelle valeur voulez-vous miser ?");
        });

        jeu.joueurCourantProperty().get().getMiseTotaleProperty().addListener((observableValue, number, t1) -> mise.setText(String.valueOf(t1)));

        jeu.joueurCourantProperty().get().soldeProperty().addListener((observableValue, number, t1) -> {
            joueur = jeu.joueurCourantProperty().get();
            SoldePlayer.setText(String.valueOf(joueur.soldeProperty().getValue()));
        });

        jeu.joueurCourantProperty().get().getMiseActuelleProperty().addListener((observable, oldValue, newValue) -> {
            soldePlayer2.setText(String.valueOf(joueur.getMiseActuelle()));
            jeu.joueurCourantProperty().get().setMiseTotale(jeu.joueurCourantProperty().get().getMiseTotale() + jeu.joueurCourantProperty().get().getMiseActuelle());
        });
    }
}
