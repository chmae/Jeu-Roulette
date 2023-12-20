package fr.umontpellier.iut.rouletteihm.application.controller.client;

import fr.umontpellier.iut.rouletteihm.RouletteIHM;
import fr.umontpellier.iut.rouletteihm.application.service.ClientService;
import fr.umontpellier.iut.rouletteihm.application.service.exception.ServiceException;
import fr.umontpellier.iut.rouletteihm.ihm.vues.ChoixSoldeDialog;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueAccueil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class NouvelClientController {
    @FXML
    public ImageView inscription;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField paswwordConfirm;
    @FXML
    private Pane pane;
    private RouletteIHM rouletteIHM = RouletteIHM.getInstance();
    private String prenoom;

    @FXML
    public void creerClient() throws ServiceException {
        String nom = this.nom.getText();
        prenoom = this.prenom.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String passwordConfirm = this.paswwordConfirm.getText();
        if (nom.isEmpty() || prenoom.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs manquants");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        ChoixSoldeDialog dialog = new ChoixSoldeDialog();
        dialog.initOwner(pane.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);

        Optional<Integer> result = dialog.showAndWait();

        if (result.isPresent()) {
            int soldeChoisi = result.get();

            ClientService clientService = ClientService.getInstance();
            int idClientCree = clientService.createClient(nom, prenoom, email, password, passwordConfirm, soldeChoisi);

            ControllerClient controllerClient = ControllerClient.getInstance();
            controllerClient.setIdClientConnecte(idClientCree);

            VueAccueil vueAccueil = rouletteIHM.getVueAccueil();
            if (vueAccueil != null) {
                vueAccueil.afficherConnexionPopup();
                rouletteIHM.ajouterJoueur(prenoom, soldeChoisi);
                //--------------------------------------------------------------------------------------------------------------------------------------
                if (vueAccueil.getMultiplayer().isSelected()) {
                    vueAccueil.getVueChoixJoueurs().utilisateurConnecte();
                    vueAccueil.afficherChoixJoueur();
                } else {
                    rouletteIHM.demarrerPartie();
                    vueAccueil.fermerFenetre();
                }
            } else {
                System.err.println("Erreur : VueAccueil est null");
            }

            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Annulation de la création du compte.");
        }
    }

    @FXML
    public void validerFormulaire(KeyEvent keyEvent) throws ServiceException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.creerClient();
        }
    }
}
