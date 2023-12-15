package fr.umontpellier.iut.rouletteihm.ihm.vues;

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

    private GestionMusique musique = new GestionMusique();


    public VueAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueAccueil.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            connexion = (Label) root.lookup("#connexion");
            inscription = (Label) root.lookup("#inscription");
            pane = (Pane) root.lookup("#pane");
            vueInscription = new VueInscription();
            vueConnexion = new VueConnexion();
            getChildren().add(root);

            // --Code de sauvegarde de la musique d'aceuil-- //
            String cheminAudioGolde = "ihm/src/main/resources/musique/sonsVueAccueil.mp3";
            musique.setMusique(cheminAudioGolde);
            musique.setVolume(0.05);


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

}
