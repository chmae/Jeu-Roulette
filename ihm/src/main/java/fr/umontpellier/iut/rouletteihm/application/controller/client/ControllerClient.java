package fr.umontpellier.iut.rouletteihm.application.controller.client;

import com.gasquet.hrepositories.utils.DBUtils;
import fr.umontpellier.iut.rouletteihm.RouletteIHM;
import fr.umontpellier.iut.rouletteihm.application.service.ClientService;
import fr.umontpellier.iut.rouletteihm.application.service.exception.ServiceException;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueAccueil;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueAutresJoueurs;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueJoueurCourant;
import fr.umontpellier.iut.rouletteihm.metier.entite.Client;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class ControllerClient {

    private static int soldeClient;
    @FXML
    public ImageView seconnecter;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Pane pane;
    @FXML
    private Pane parametrePane;

    private RouletteIHM rouletteIHM = RouletteIHM.getInstance();
    private static boolean utilisateurConnecte = false;

    private static int idClientConnecte;

    private static String prenomClient;
    private static ControllerClient instance;
    @FXML
    private TextField nom;
    @FXML
    private TextField solde;
    private final StringProperty prenomProperty = new SimpleStringProperty();
    private final IntegerProperty soldeProperty = new SimpleIntegerProperty();

    public static int getIdClientConnecte() {
        return idClientConnecte;
    }

    public static void setPrenomClient(String prenomClient) {
        ControllerClient.prenomClient = prenomClient;
    }

    public void setIdClientConnecte(int idClientConnecte) {
        ControllerClient.idClientConnecte = idClientConnecte;
    }

    public static void setSoldeClient(int soldeClient) {
        ControllerClient.soldeClient = soldeClient;
    }

    public static boolean isUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public static ControllerClient getInstance() {
        if (instance == null) {
            instance = new ControllerClient();
        }
        return instance;
    }

    /**
     * Méthode appelée lors de la tentative de connexion.
     * Vérifie les informations d'identification et connecte l'utilisateur le cas échéant.
     *
     * @throws ServiceException Si une erreur survient lors de la connexion.
     */
    @FXML
    public void actionConnexion() throws ServiceException {
        try {
            String email = this.email.getText();
            String password = this.password.getText();

            Session session = DBUtils.getSession();
            Transaction transaction = session.beginTransaction();

            List<Client> clients = session.createQuery("FROM Client WHERE email = :email")
                    .setParameter("email", email)
                    .list();

            boolean connexionReussie = false;

            for (Client client : clients) {
                System.out.println("Client trouvé : " + client);

                if (checkPassword(password, client.getPassword())) {
                    System.out.println("Connexion réussie pour le client : " + client);
                    connexionReussie = true;
                    utilisateurConnecte = true;

                    prenomClient = client.getPrenom();
                    System.out.println("Prénom récupéré : " + prenomClient);
                    setPrenomClient(prenomClient);

                    soldeClient = client.getSolde();
                    System.out.println("Solde récupéré" + soldeClient);
                    setSoldeClient(soldeClient);


                    idClientConnecte = client.getIdClient();
                    System.out.println("ID du client connecté lors de la connexion : " + idClientConnecte);


                    VueAccueil vueAccueil = rouletteIHM.getVueAccueil();

                    if (vueAccueil != null) {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        if (stage.isShowing()) {
                            NouvelClientController nouvelClientController = rouletteIHM.getNouvelClientController();

                            if (nouvelClientController != null) {
                                rouletteIHM.demarrerPartie(prenomClient, soldeClient);
                                vueAccueil.fermerFenetre();
                                VueAutresJoueurs.getInstance().fermerVueAccueil();

                            } else {
                                System.err.println("Erreur : nouvelClientController est null");
                            }
                        } else {
                            System.err.println("Erreur : la fenêtre n'est pas affichée");
                        }
                    } else {
                        System.err.println("Erreur : VueAccueil est null");
                    }

                    break;
                }
            }

            if (clients.isEmpty() || !connexionReussie) {
                afficherAlerteErreur();
                return;
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            utilisateurConnecte = false;
            throw new ServiceException("Erreur lors de la connexion à la base de données.");
        }
    }

    /**
     * Affiche une alerte en cas d'échec de connexion.
     */
    private void afficherAlerteErreur() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Échec de la connexion : Informations d'identification incorrectes.");
            alert.showAndWait();
        });
    }

    /**
     * Vérifie si le mot de passe fourni correspond au mot de passe hashé du client.
     *
     * @param plainPassword  Le mot de passe en clair.
     * @param hashedPassword Le mot de passe hashé.
     * @return true si les mots de passe correspondent, sinon false.
     */
    private boolean checkPassword(String plainPassword, String hashedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedInput = md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getEncoder().encodeToString(hashedInput);

            return hash.equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Méthode appelée lors de la validation du formulaire avec la touche ENTER.
     *
     * @param keyEvent L'événement de la touche clavier.
     * @throws ServiceException Si une erreur survient lors de la connexion.
     */
    @FXML
    public void validerFormulaire(KeyEvent keyEvent) throws ServiceException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.actionConnexion();
        }
    }

    /**
     * Méthode appelée lors de la déconnexion de l'utilisateur.
     * Effectue la déconnexion, met à jour le solde et affiche la nouvelle fenêtre d'accueil.
     */
    @FXML
    public void deconnexion() {
        System.out.println("Tentative de déconnexion...");

        if (utilisateurConnecte) {
            try {
                ClientService c = ClientService.getInstance();

                int idClientConnecte = getIdClientConnecte();
                System.out.println("ID du client connecté avant déconnexion : " + idClientConnecte);

                System.out.println("ID du client connecté : " + idClientConnecte);

                if (idClientConnecte <= 0) {
                    System.err.println("Erreur : ID du client connecté incorrect. Aucune déconnexion nécessaire.");
                    return;
                }

                int soldeAMettreAJour;
                try {
                    soldeAMettreAJour = Integer.parseInt(solde.getText());
                } catch (NumberFormatException e) {
                    soldeAMettreAJour = rouletteIHM.getJeu().joueurCourantProperty().get().getSolde();
                }

                c.mettreAJourSolde(idClientConnecte, soldeAMettreAJour);
                rouletteIHM.reinitialiserUtilisateur();

                VueAccueil vueAccueil = rouletteIHM.getVueAccueil();
                if (vueAccueil != null) {
                    Stage stageP = (Stage) parametrePane.getScene().getWindow();
                    Stage stage = RouletteIHM.getPrimaryStage();
                    VueAutresJoueurs.getInstance().getMusiqueCasino().arreterMusique();
                    if (stage.isShowing() && stageP.isShowing()) {
                        System.out.println("Fermeture des fenêtres en cours...");
                        utilisateurConnecte = false;
                        stage.close();
                        stageP.close();
                        System.out.println("Ouverture de la nouvelle fenêtre...");

                        RouletteIHM.getInstance().fonctionnaliteAccueil();
                    } else {
                        System.err.println("Erreur : la fenêtre n'est pas affichée");
                    }
                } else {
                    System.err.println("Erreur : VueAccueil est null");
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("L'utilisateur n'est pas connecté. Aucune déconnexion nécessaire.");
        }
    }


    /**
     * Méthode appelée lors de la mise à jour du prénom du client.
     * Met à jour le prénom dans la base de données et rafraîchit l'interface graphique.
     */
    @FXML
    public void updatePrenom() {
        try (Session session = DBUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                ClientService clientService = ClientService.getInstance();
                int idClientConnecte = getIdClientConnecte();

                String nouveauPrenom = nom.getText();
                clientService.updatePrenom(idClientConnecte, nouveauPrenom);
                Platform.runLater(() -> {
                    setPrenomClient(nouveauPrenom);
                    prenomProperty.set(nouveauPrenom);

                    VueJoueurCourant.getInstance().getNomJoueur().textProperty().bind(prenomProperty);

                    System.out.println("Nom joueur mis à jour avec succès : " + nouveauPrenom);
                    nom.setText("");
                });


                transaction.commit();
            } catch (ServiceException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lors de la mise à jour du solde du client.
     * Met à jour le solde dans la base de données et rafraîchit l'interface graphique.
     */
    @FXML
    public void updateSolde() {
        try {
            ClientService clientService = ClientService.getInstance();

            int idClientConnecte = getIdClientConnecte();
            int soldeActuel = clientService.getSolde(idClientConnecte);
            int nouveauSolde = Integer.parseInt(solde.getText());

            if (nouveauSolde != soldeActuel) {
                clientService.mettreAJourSolde(idClientConnecte, nouveauSolde);

                Platform.runLater(() -> {
                    setSoldeClient(nouveauSolde);
                    soldeProperty.set(nouveauSolde);
                    solde.textProperty().bindBidirectional(soldeProperty, new NumberStringConverter());

                    System.out.println("Solde mis à jour avec succès : " + nouveauSolde);
                    solde.setText("");
                });
                rouletteIHM.demarrerPartie(prenomClient, nouveauSolde);
            } else {
                System.out.println("Aucune mise à jour du solde n'est nécessaire.");
            }
        } catch (ServiceException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
