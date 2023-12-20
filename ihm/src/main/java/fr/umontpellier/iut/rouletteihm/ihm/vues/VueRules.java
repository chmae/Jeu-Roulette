package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.GestionMusique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VueRules extends Pane{
    @FXML
    private ImageView quit;

  @FXML
  private Pane pane;


    public VueRules() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/VueRules.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            quit = (ImageView) root.lookup("#quit");
            pane = (Pane) root.lookup("#pane");
            getChildren().add(root);
            quit.setOnMouseClicked(event -> {
                // sons bouton quit //
                GestionMusique sonsBoutonQuit = new GestionMusique();
                String cheminAudioBouton = "ihm/src/main/resources/musique/sonsBouton.mp3";
                sonsBoutonQuit.setMusique(cheminAudioBouton);
                sonsBoutonQuit.setVolume(0.2);
                sonsBoutonQuit.lireMusique();
                sonsBoutonQuit.remettreMusiqueAuDebut();
                fermerFenetre();

            });

            quit.setOnMouseEntered(event -> quit.setOpacity(0.7));
            quit.setOnMouseExited(event -> quit.setOpacity(1.0));
        } catch (Exception e) {
            e.printStackTrace();
        }



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
