package fr.umontpellier.iut.rouletteihm;

import fr.umontpellier.iut.rouletteihm.application.controller.client.NouvelClientController;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.CreationTable;
import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;
import fr.umontpellier.iut.rouletteihm.ihm.vues.*;
import fr.umontpellier.iut.rouletteihm.metier.entite.Client;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


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


    @Override
    public void start(Stage primaryStage) {
        instance = this;
        RouletteIHM.primaryStage = primaryStage;
        RouletteIHM.primaryStage.setWidth(1128);
        RouletteIHM.primaryStage.setHeight(615);
        RouletteIHM.primaryStage.setResizable(false);
        fonctionnaliteAccueil();


    }

    public void fonctionnaliteAccueil() {
        if (avecAccueil) {
            vueAccueil = new VueAccueil();
            vueInscription = new VueInscription();
            stage = new Stage();
            stage.setResizable(false);
            vueAccueil.getBoutonJouer().setOnMouseClicked(mouseEvent -> {
                demarrerPartie("Chollet", 9999);
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
            stage.setTitle("Accueil");
            Scene scene = new Scene(vueAccueil, 599, 390);
            scene.getRoot().getProperties().put("RouletteIHM", this);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void demarrerPartie(String nomJoueur, int solde) {
        roulette = new Roulette();
        jeu = roulette;

        Joueur joueur = new Joueur(nomJoueur, solde);
        jeu.joueurCourantProperty().set(joueur);
        jeu.joueurCourantProperty().setValue(joueur);
        jeu.tournerTour();

        VueDuJeu vueDuJeu = new VueDuJeu(jeu);
        labelInstruction = vueDuJeu.getLabelInstructions();
        Scene scene = new Scene(vueDuJeu);

        table = vueDuJeu.getTable();

        table.getTable().setTranslateX(300);
        table.getTable().setTranslateY(15);

        vueDuJeu.add(table.getTable(), 1, 1);

        table.getTable().setTranslateY(-500);
        table.getTable().setTranslateX(100);

        vueDuJeu.creerBindings();
        roulette.run(joueur);

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

    public Roulette getRoulette() {
        return roulette;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public VueAccueil getVueAccueil() {
        return vueAccueil;
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
