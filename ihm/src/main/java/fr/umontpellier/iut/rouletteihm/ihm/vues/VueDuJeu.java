package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.RouletteIHM;
import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.Boule;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.CreationTable;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.StatistiquesRoulette;
import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class VueDuJeu extends GridPane {
    private IJeu jeu;
    private VuePlateau plateau;
    private VueJoueurCourant joueurCourantvue;
    private VueAutresJoueurs autresJoueurs;
    private VueBet vueBet;
    private VueDroite vueDroite;
    private VueGauche vueGauche;
    private VuePlayerInfo vuePlayerInfo;
    private HBox elementsGauche;
    private CreationTable table;
    private Label labelInstructions;
    private ArrayList<Integer> listeParis;
    private static VueDuJeu instance;
    private ArrayList<Integer> multiplicateursGain;
    private ArrayList<Integer> montantsParis;
    private Boule boule = new Boule();
    private VueRoue vueRoue = new VueRoue();
    private StatistiquesRoulette statistiquesRoulette = new StatistiquesRoulette();

    private RouletteIHM rouletteIHM;
    private StringProperty valeurGagneeProperty = new SimpleStringProperty("0");

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        instance = this;


        vueBet = new VueBet(jeu);
        labelInstructions = vueBet.getLabelInstruction();
        table = new CreationTable(jeu, labelInstructions, vueBet);
        listeParis = table.getListeParis();

        plateau = new VuePlateau(jeu);
        autresJoueurs = new VueAutresJoueurs(jeu);
        vueDroite = new VueDroite(jeu);
        vueGauche = new VueGauche(jeu);
        vuePlayerInfo = new VuePlayerInfo(jeu);
        joueurCourantvue = new VueJoueurCourant(jeu, labelInstructions);
        elementsGauche = new HBox(10, vueGauche, autresJoueurs);

        add(vueRoue.getRoue(), 1, 1);
        add(plateau, 1, 1);
        add(elementsGauche, 0, 0);
        add(vueDroite, 0, 0);
        add(vueBet, 0, 1);
        add(joueurCourantvue, 1, 2);
        add(autresJoueurs, 0, 0);
        listeParis = table.getListeParis();

        vueDroite.setTranslateX(1020);
        setHalignment(joueurCourantvue, HPos.RIGHT);
        setValignment(joueurCourantvue, VPos.BOTTOM);
        joueurCourantvue.translateXProperty().bind(plateau.translateXProperty().add(plateau.getWidth() / 2).add(875));
        joueurCourantvue.translateYProperty().bind(plateau.translateYProperty().add(plateau.getHeight() / 2).add(-45));
        vueRoue.getRoue().translateXProperty().bind(plateau.translateXProperty().add(plateau.getWidth() / 2).add(30));
        vueRoue.getRoue().translateYProperty().bind(plateau.translateYProperty().add(plateau.getHeight() / 2).add(0));
        vueBet.setTranslateY(-15);

        plateau.setTranslateY(-500);
        plateau.setTranslateX(-200);

        joueurCourantvue.toFront();
        plateau.toBack();

        multiplicateursGain = table.getMultiplicateursParis();
        montantsParis = table.getMontantParis();

        joueurCourantvue.getPasser().setOnMouseClicked(mouseEvent -> {
            labelInstructions.setText("Vous avez décidé de passer !");
            vueRoue.animation(jeu.getResultatTourActuel().getNombres());
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

            pauseTransition.setOnFinished(event -> {
                vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
                vueDroite.afficherStats();
                vueDroite.setStatistiquesRoulette(statistiquesRoulette);
            });
            pauseTransition.play();
            jeu.tournerTour();
            statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
            statistiquesRoulette.afficherStatistique();

        });

        joueurCourantvue.getPasser1().setOnMouseClicked(mouseEvent -> {
            labelInstructions.setText("Vous avez décidé de passer !");
            vueRoue.animation(jeu.getResultatTourActuel().getNombres());
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

            pauseTransition.setOnFinished(event -> {
                vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
                vueDroite.afficherStats();
                vueDroite.setStatistiquesRoulette(statistiquesRoulette);
            });
            pauseTransition.play();
            jeu.tournerTour();
            statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
            statistiquesRoulette.afficherStatistique();

        });
    }

    public CreationTable getTable() {
        return table;
    }

    public void creerBindings() {
        plateau.prefWidthProperty().bind(widthProperty());
        plateau.prefHeightProperty().bind(heightProperty());
        vueBet.prefWidthProperty().bind(widthProperty());
        vueBet.prefHeightProperty().bind(heightProperty());
        joueurCourantvue.prefWidthProperty().bind(widthProperty());
        joueurCourantvue.prefHeightProperty().bind(heightProperty());
        elementsGauche.prefWidthProperty().bind(widthProperty());
        elementsGauche.prefHeightProperty().bind(heightProperty());
        vueDroite.prefHeightProperty().bind(heightProperty());

        vueBet.creerBinding();
        vueBet.validationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println(listeParis.toString());
                jeu.tournerTour();
                vueRoue.animation(jeu.getResultatTourActuel().getNombres());
                statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
                statistiquesRoulette.afficherStatistique();
                // Ajouter un délai de 5 secondes avant d'afficher whenWin / whenLose
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

                pauseTransition.setOnFinished(event -> {
                    vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
                    vueDroite.afficherStats();
                    vueDroite.setStatistiquesRoulette(statistiquesRoulette);
                    if (listeParis.contains(jeu.getResultatTourActuel().getValeur())) {
                        whenWin();
                    } else {
                        whenLose();
                    }
                    // Uniquement pour le sprint 2 (1 joueur)
                    vueBet.validationProperty().set(false);
                    labelInstructions.setText("Le " + jeu.getResultatTourActuel().getValeur() + " " + jeu.getResultatTourActuel().getCouleur() + " est tombé ! Misez pour rejouer");
                    jeu.tournerTour();
                    table.viderListeParis();
                    table.viderMontantsParis();
                    table.viderMultiplicateursParis();
                    jeu.joueurCourantProperty().get().setMiseTotale(0);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                pauseTransition.play();
            }
        });

        table.getListeParisObserver().addListener((ListChangeListener<Integer>) c -> {
            vueBet.setOk(true);
        });
    }


    Stage primaryStage = RouletteIHM.getPrimaryStage();

    public void whenWin() {
        System.out.println(montantsParis.toString());
        System.out.println(multiplicateursGain.toString());
        int n = montantsParis.get(jeu.getResultatTourActuel().getValeur()) * multiplicateursGain.get(jeu.getResultatTourActuel().getValeur());
        System.out.println("Valeur gagnée : " + n);
        valeurGagneeProperty.set(String.valueOf(n));
        jeu.joueurCourantProperty().get().miseAJourBanque(n);
        labelInstructions.setText("Gagné !");

        VueWin vueWin = new VueWin(primaryStage);
        vueWin.getGainJeton().textProperty().bind(valeurGagneeProperty);
        vueWin.afficher();


        Duration pauseDuration = Duration.seconds(10);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> vueWin.getStage().close()));
        timeline.play();

    }

   /* private int calculPerteWin() {
        return jeu.joueurCourantProperty().get().getMiseTotale()-montantsParis.get(jeu.getResultatTourActuel().getValeur());
    }*/


    private void whenLose() {
        jeu.joueurCourantProperty().get().miseAJourBanque(-jeu.joueurCourantProperty().get().getMiseTotale());
        labelInstructions.setText("Perdu !");

        VueLoose vueLoose = new VueLoose(primaryStage);
        vueLoose.afficher();

        Duration pauseDuration = Duration.seconds(10);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> vueLoose.getStage().close()));
        timeline.play();
    }

    public static VueDuJeu getInstance() {
        return instance;
    }

    public void resetPartie() {
        jeu.tournerTour();
    }


    public IJeu getJeu() {
        return jeu;
    }

    public ArrayList<Integer> getListeParis() {
        return listeParis;
    }

    public Label getLabelInstructions() {
        return labelInstructions;
    }

    public VueBet getVueBet() {
        return vueBet;
    }


}

