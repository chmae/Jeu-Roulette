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
    // Déclaration des champs et variables de classe
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

    // Méthode pour créer un nouveau client
    @FXML
    public void creerClient() throws ServiceException {
        // Récupération des données du formulaire de création de client depuis l'IHM
        String nom = this.nom.getText();
        prenoom = this.prenom.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String passwordConfirm = this.paswwordConfirm.getText();
        // Vérification si des champs obligatoires sont vides
        if (nom.isEmpty() || prenoom.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            // Affichage d'une alerte en cas de champs manquants
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs manquants");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        // Création d'une fenêtre de dialogue pour choisir le solde initial du nouveau client
        ChoixSoldeDialog dialog = new ChoixSoldeDialog();
        dialog.initOwner(pane.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);

        Optional<Integer> result = dialog.showAndWait();

        if (result.isPresent()) {
            // Récupération du solde choisi depuis la fenêtre de dialogue
            int soldeChoisi = result.get();

            // Appel du service client pour créer un nouveau client avec les informations fournies
            ClientService clientService = ClientService.getInstance();
            int idClientCree = clientService.createClient(nom, prenoom, email, password, passwordConfirm, soldeChoisi);

            // Mise à jour de l'ID du client connecté dans le ControllerClient
            ControllerClient controllerClient = ControllerClient.getInstance();
            controllerClient.setIdClientConnecte(idClientCree);

            // Actions après la création du client
            VueAccueil vueAccueil = rouletteIHM.getVueAccueil();
            if (vueAccueil != null) {
                // Affichage de la fenêtre de connexion et démarrage du jeu avec le nouveau client
                vueAccueil.afficherConnexionPopup();
                rouletteIHM.demarrerPartie(prenoom, soldeChoisi);
                vueAccueil.fermerFenetre();
            } else {
                System.err.println("Erreur : VueAccueil est null");
            }

            // Fermeture de la fenêtre actuelle
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Annulation de la création du compte.");
        }
    }

    // Méthode déclenchée lors de l'appui sur la touche Entrée dans le formulaire de création de client
    @FXML
    public void validerFormulaire(KeyEvent keyEvent) throws ServiceException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.creerClient();
        }
    }
}
