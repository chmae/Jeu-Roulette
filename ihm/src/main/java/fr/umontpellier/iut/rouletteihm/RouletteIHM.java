package fr.umontpellier.iut.rouletteihm;

import fr.umontpellier.iut.rouletteihm.application.controller.client.ControllerClient;
import fr.umontpellier.iut.rouletteihm.application.controller.client.NouvelClientController;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.CreationTable;
import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;
import fr.umontpellier.iut.rouletteihm.ihm.vues.*;
import fr.umontpellier.iut.rouletteihm.metier.entite.Client;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale de l'application.
 * <p>
 * Cette classe gère les différentes vues de l'application.
 * <p>
 * Elle gère également la communication entre les vues et le modèle.
 */

public class RouletteIHM extends Application {

    private static Stage primaryStage;
    private Stage stage;
    private Roulette roulette;
    private IJeu jeu;
    private VueAccueil vueAccueil;
    private CreationTable table;
    private VueInscription vueInscription;
    private final boolean avecAccueil = true;
    private Label labelInstruction;
    private static RouletteIHM instance;

    private NouvelClientController nouvelClientController = new NouvelClientController();
    private Client client = new Client();
    private List<Joueur> joueurs;
    private VueChoixJoueurs vueChoixJoueurs;
    private BooleanProperty validationChoixJoueurs;


    /**
     * Méthode appelée au démarrage de l'application.
     * <p>
     * Cette méthode initialise la fenêtre principale de l'application.
     *
     * @param primaryStage la fenêtre principale de l'application
     */
    @Override
    public void start(Stage primaryStage) {
        instance = this;
        RouletteIHM.primaryStage = primaryStage;
        RouletteIHM.primaryStage.setWidth(1128);
        RouletteIHM.primaryStage.setHeight(615);
        RouletteIHM.primaryStage.setResizable(false);
        fonctionnaliteAccueil();


    }

    //methode pour lancer l'accueil avec les boutons
    public void fonctionnaliteAccueil() {
        if (avecAccueil) {
            vueAccueil = new VueAccueil();
            vueInscription = new VueInscription();
            vueChoixJoueurs = vueAccueil.getVueChoixJoueurs();
            joueurs = new ArrayList<>();
            stage = new Stage();
            stage.setResizable(false);

            vueAccueil.getMusique().lireMusiqueProgressivement(0.2);

            vueAccueil.getBoutonJouer().setOnMouseClicked(mouseEvent -> {
                vueAccueil.getMusique().arreterMusique();
                vueAccueil.afficherChoixJoueur();
            });

            vueAccueil.getBoutonQuitter().setOnMouseClicked(mouseEvent -> {
                stage.close();
            });
            vueAccueil.getConnexion().setOnMouseClicked(mouseEvent -> {
                vueAccueil.afficherConnexionPopup();
            });
            vueAccueil.getInscription().setOnMouseClicked(mouseEvent -> {
                vueAccueil.afficherInscriptionPopup();
            });
            vueAccueil.getInfo().setOnMouseClicked(mouseEvent -> {
                vueAccueil.afficherRulesPopUp();
            });
            stage.setTitle("Accueil");
            Scene scene = new Scene(vueAccueil, 599, 390);
            scene.getRoot().getProperties().put("RouletteIHM", this);
            stage.setScene(scene);
            stage.show();

            creerBindings();
        }

    }

    public void ajouterJoueur(String nomJoueur, int soldeJoueur) {
        joueurs.add(new Joueur(nomJoueur, soldeJoueur));
    }

    public void creerBindings() {
        vueChoixJoueurs.validationChoixJoueurs().addListener((observable, oldValue, newValue) -> {
            if(!ControllerClient.isUtilisateurConnecte()) {
                ajouterJoueur(vueChoixJoueurs.getNom1().getText(), Integer.parseInt(vueChoixJoueurs.getSolde1().getText()));
            }
            int nbJoueurs = vueChoixJoueurs.getNombreJoueurs();
            if(nbJoueurs>1) {
                ajouterJoueur(vueChoixJoueurs.getNom2().getText(), Integer.parseInt(vueChoixJoueurs.getSolde2().getText()));
                if(nbJoueurs>2) {
                    ajouterJoueur(vueChoixJoueurs.getNom3().getText(), Integer.parseInt(vueChoixJoueurs.getSolde3().getText()));
                    if(nbJoueurs>3) {
                        ajouterJoueur(vueChoixJoueurs.getNom4().getText(), Integer.parseInt(vueChoixJoueurs.getSolde4().getText()));
                    }
                }
            }
            demarrerPartie();
            vueAccueil.fermerFenetre();
        });
    }

    /**
     * Méthode appelée pour démarrer une partie.
     * <p>
     * Cette méthode initialise la roulette et le joueur courant.
     * Elle crée la vue du jeu et l'affiche dans la fenêtre principale.
     *
     * @param nomJoueur le nom du joueur
     * @param solde     le solde du joueur
     */
    public void demarrerPartie() {

        roulette = new Roulette();
        jeu = roulette;

        jeu.joueurCourantProperty().setValue(joueurs.get(0));
        jeu.tournerTour();

        VueDuJeu vueDuJeu = new VueDuJeu(jeu, joueurs);
        labelInstruction = vueDuJeu.getLabelInstructions();
        Scene scene = new Scene(vueDuJeu);

        table = vueDuJeu.getTable();

        table.getTable().setTranslateX(300);
        table.getTable().setTranslateY(15);

        vueDuJeu.add(table.getTable(), 1, 1);

        table.getTable().setTranslateY(-500);
        table.getTable().setTranslateX(100);

        vueDuJeu.creerBindings();

        primaryStage.setScene(scene);
        primaryStage.setTitle("BigRoulette");
        primaryStage.show();

        roulette.finDePartieProperty().addListener(finDuJeu);
    }


    ChangeListener<Boolean> finDuJeu = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            if (t1) {
                closeStage();
                if (conditionDeFinDeJeu()) {
                    openStage();
                    System.out.println("Fin du jeu. Résultat final :");

                    // Peut-être on pourra ajouter une fin de jeu genre en mode la gérer

                    primaryStage.close();//si on veut fermer la fenêtre principale
                } else {
                    String choix = Interaction.saisirParmiChoix("Voulez-vous continuer ou quitter ?", "continuer", "quitter");
                    if (choix.equals("quitter")) {
                        primaryStage.close();
                    }
                }
            }
        }
    };

    private boolean conditionDeFinDeJeu() {
        return false;
    }

    public void closeStage() {
        primaryStage.close();
    }

    public void openStage() {
        primaryStage.show();
    }


    //-------------------- GETTERS --------------------
    public Roulette getRoulette() {
        return roulette;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public VueAccueil getVueAccueil() {
        return vueAccueil;
    }

    public VueInscription getVueInscription() {
        return vueInscription;
    }

    public static RouletteIHM getInstance() {
        return instance;
    }

    public IJeu getJeu() {
        return jeu;
    }

    public NouvelClientController getNouvelClientController() {
        return nouvelClientController;
    }

    public void reinitialiserUtilisateur() {
        this.client = null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
