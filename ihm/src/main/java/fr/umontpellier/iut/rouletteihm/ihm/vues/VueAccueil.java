package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.RouletteIHM;
import fr.umontpellier.iut.rouletteihm.application.controller.client.ControllerClient;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe VueAccueil qui permet d'afficher la vue d'accueil
 * Cette classe est un Pane qui contient un Pane, un Label, deux ImageView et un Button qui permettent de naviguer entre les différentes vues
 * Cette classe permet aussi de lancer la musique d'accueil
 */
public class VueAccueil extends Pane {
    private IJoueur joueur;
    @FXML
    private ImageView jouer;
    @FXML
    private ImageView quitter;
    @FXML
    private Label connexion;
    @FXML
    private Label inscription;
    private VueInscription vueInscription;

    private VueConnexion vueConnexion;
    @FXML
    private Pane pane;
    @FXML
    private ImageView info;

    private GestionMusique musique = new GestionMusique();
    private VueRules vueRules;


    /**
     * Constructeur de la classe VueAccueil
     * Ce constructeur permet de charger le fichier FXML de la vue d'accueil
     * Ce constructeur permet aussi de configurer les boutons de la vue d'accueil
     * Ce constructeur permet aussi de lancer la musique d'accueil
     */
    public VueAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueAccueil.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            connexion = (Label) root.lookup("#connexion");
            inscription = (Label) root.lookup("#inscription");
            pane = (Pane) root.lookup("#pane");
            info = (ImageView) root.lookup("#info");
            vueInscription = new VueInscription();
            vueConnexion = new VueConnexion();
            vueRules = new VueRules();
            getChildren().add(root);

            // --Code de sauvegarde de la musique d'aceuil-- //
            configurerMusiqueAccueil();

            info.setOnMouseEntered(event -> info.setOpacity(0.7));
            info.setOnMouseExited(event -> info.setOpacity(1.0));

            jouer.setOnMouseEntered(event -> jouer.setOpacity(0.7));
            jouer.setOnMouseExited(event -> jouer.setOpacity(1.0));

            connexion.setOnMouseEntered(event -> connexion.setOpacity(0.7));
            connexion.setOnMouseExited(event -> connexion.setOpacity(1.0));

            quitter.setOnMouseEntered(event -> quitter.setOpacity(0.7));
            quitter.setOnMouseExited(event -> quitter.setOpacity(1.0));

            inscription.setOnMouseEntered(event -> inscription.setOpacity(0.7));
            inscription.setOnMouseExited(event -> inscription.setOpacity(1.0));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void configurerMusiqueAccueil() {
        String cheminAudioGolde = "ihm/src/main/resources/musique/sonsVueAccueil.mp3";
        musique.setMusique(cheminAudioGolde);
        musique.setVolume(0.05);
    }
    public Label getConnexion() {
        return connexion;
    }

    public Label getInscription() {
        return inscription;
    }

    public void afficherConnexionPopup() {
        if (vueConnexion == null) {
            vueConnexion = new VueConnexion();
        }
        Stage popupStage = new Stage();
        popupStage.setResizable(false);
        popupStage.initOwner(getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(vueConnexion, getScene().getWidth(), getScene().getHeight());

        popupStage.setScene(scene);
        popupStage.setTitle("Connexion");
        popupStage.showAndWait();

        if (VueAutresJoueurs.isBoutonQuitterClicked()) {
            VueAutresJoueurs.getInstance().fermerVueAccueil();
        }
    }


    public void afficherRulesPopUp() {
        vueRules = new VueRules();

        Stage popupStage = new Stage();
        popupStage.setResizable(false);
        popupStage.initOwner(getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(vueRules, getScene().getWidth(), getScene().getHeight());
        popupStage.setScene(scene);
        popupStage.setWidth(250);
        popupStage.setHeight(465);
        popupStage.setTitle("Règles du jeu");
        popupStage.show();
    }


    public void afficherInscriptionPopup() {
        if (vueInscription == null) {
            vueInscription = new VueInscription();
        }

        Stage popupStage = new Stage();
        popupStage.setResizable(false);
        popupStage.initOwner(getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(vueInscription, getScene().getWidth(), getScene().getHeight());

        popupStage.setScene(scene);
        popupStage.setTitle("Inscription");
        popupStage.show();
    }


    // --Getters-- //
    public GestionMusique getMusique() {
        return musique;
    }

    public ImageView getBoutonJouer() {
        return jouer;
    }

    public ImageView getBoutonQuitter() {
        return quitter;
    }

    public void fermerPlateforme() {
        Platform.exit();
    }

    public void fermerFenetre() {
        if (pane.getScene() != null) {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        } else {
            System.err.println("Erreur : la scène est nulle, impossible de fermer la fenêtre.");
        }
    }

    public ImageView getInfo() {
        return info;
    }
}
