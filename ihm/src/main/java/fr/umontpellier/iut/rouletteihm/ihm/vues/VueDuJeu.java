package fr.umontpellier.iut.rouletteihm.ihm.vues;

import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.RouletteIHM;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.Boule;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.CreationTable;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.vues.VueInscription;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.beans.property.IntegerProperty;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.StatistiquesRoulette;

/**
 * Classe VueDuJeu qui permet de créer la vue du jeu avec toutes les autres vues (VuePlateau, VueBet, VueJoueurCourant, VueAutresJoueurs, VueDroite, VueGauche, VuePlayerInfo)
 * Elle permet aussi de créer les bindings entre les différentes vues
 * Elle permet aussi de créer les actions des boutons
 */
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
//    private ArrayList<ArrayList<Integer>> listesParis = new ArrayList<>(new ArrayList<>());
    private static VueDuJeu instance;
    private VueRoue vueRoue = new VueRoue();
    private StatistiquesRoulette statistiquesRoulette = new StatistiquesRoulette();
    private StringProperty valeurGagneeProperty = new SimpleStringProperty("0");
    private IntegerProperty langue;
    private VueInscription vueInscription = new VueInscription();
    private List<Joueur> joueurs;
    private int numeroJoueurJouant = 0;
    private Stage primaryStage = RouletteIHM.getPrimaryStage();

    public VueDuJeu(IJeu jeu, List<Joueur> joueurs) {
        this.jeu = jeu;
        instance = this;
        this.joueurs = joueurs;

        autresJoueurs = new VueAutresJoueurs(jeu, joueurs);

        vueBet = VueBet.getInstance(jeu, autresJoueurs.getLangueChoisie());
        labelInstructions = vueBet.getLabelInstruction();
        table = new CreationTable(jeu, labelInstructions, vueBet, autresJoueurs.getLangueChoisie());
//        listesParis = new ArrayList<>();
//        listesParis.add(table.getListeParis(0));
//        listesParis.add(table.getListeParis(1));
//        listesParis.add(table.getListeParis(2));
//        listesParis.add(table.getListeParis(3));
        plateau = new VuePlateau(jeu);
        vueDroite = new VueDroite(jeu);
        vueGauche = new VueGauche(jeu);
        vuePlayerInfo = new VuePlayerInfo(jeu);
        joueurCourantvue = new VueJoueurCourant(jeu, labelInstructions, autresJoueurs.getLangueChoisie());
        elementsGauche = new HBox(10, vueGauche, autresJoueurs);

        add(vueRoue.getRoue(), 1, 1);
        add(plateau, 1, 1);
        add(elementsGauche, 0, 0);
        add(vueDroite, 0, 0);
        add(vueBet, 0, 1);
        add(joueurCourantvue, 1, 2);
        add(autresJoueurs, 0, 0);
//        listesParis = table.getListeParis();

        vueDroite.setTranslateX(1020);
        setHalignment(joueurCourantvue, HPos.RIGHT);
        setValignment(joueurCourantvue, VPos.BOTTOM);
        joueurCourantvue.translateXProperty().bind(plateau.translateXProperty().add(plateau.getWidth() / 2).add(875));
        joueurCourantvue.translateYProperty().bind(plateau.translateYProperty().add(plateau.getHeight() / 2).add(-45));
        vueRoue.getRoue().translateXProperty().bind(plateau.translateXProperty().add(plateau.getWidth() / 2).add(60));
        vueRoue.getRoue().translateYProperty().bind(plateau.translateYProperty().add(plateau.getHeight() / 2).add(0));
        vueBet.setTranslateY(-15);

        plateau.setTranslateY(-500);
        plateau.setTranslateX(-200);

        joueurCourantvue.toFront();
        plateau.toBack();

//        multiplicateursGain = table.getMultiplicateursParis();
//        montantsParis = table.getMontantParis();

        joueurCourantvue.getPasser().setOnMouseClicked(mouseEvent -> {
            labelInstructions.setText("Vous avez décidé de passer !");
            numeroJoueurJouant++;
            if (numeroJoueurJouant>joueurs.size()-1) {
                vueRoue.animation(jeu.getResultatTourActuel().getNombres());
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

                pauseTransition.setOnFinished(event -> {
                    vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
                    vueDroite.afficherStats();
                    vueDroite.setStatistiquesRoulette(statistiquesRoulette);

                    for (Joueur j : joueurs) {
                        if (!j.getListeParis().isEmpty() && j.getListeParis().contains(jeu.getResultatTourActuel().getValeur())) {
                            int n = j.getListeMontantsParis().get(jeu.getResultatTourActuel().getValeur()) * j.getListeMultiplicateursParis().get(jeu.getResultatTourActuel().getValeur());
                            j.miseAJourBanque(n);
                            if (joueurs.get(0)==j) {
                                whenWin();
                            }
                        } else if (!j.getListeParis().isEmpty()){
                            j.miseAJourBanque(-jeu.joueurCourantProperty().get().getMiseTotale());
                            if (joueurs.get(0)==j) {
                                whenLose();
                            }
                        }
                    }

                    for (Joueur j : joueurs) {
                        if (!j.getListeParis().isEmpty()) {
                            j.viderListeParis();
                            j.viderMontantsParis();
                            j.viderMultiplicateursParis();
                            j.setMiseTotale(0);
                        }
                    }
                    for (Node node : table.getTable().getChildren()) {
                        if (node instanceof ImageView) {
                            ImageView imageView = (ImageView) node;
                            imageView.setVisible(false);
                        }
                    }
                    jeu.tournerTour();
                });
                pauseTransition.play();
                statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
                statistiquesRoulette.afficherStatistique();
                numeroJoueurJouant = 0;
            }
            jeu.joueurCourantProperty().set(joueurs.get(numeroJoueurJouant));
            gererAnimation();
        });

        joueurCourantvue.getPasser1().setOnMouseClicked(mouseEvent -> {
            labelInstructions.setText("Vous avez décidé de passer !");
            numeroJoueurJouant++;
            if (numeroJoueurJouant>joueurs.size()-1) {
                vueRoue.animation(jeu.getResultatTourActuel().getNombres());
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

                pauseTransition.setOnFinished(event -> {
                    vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
                    vueDroite.afficherStats();
                    vueDroite.setStatistiquesRoulette(statistiquesRoulette);

                    for (Joueur j : joueurs) {
                        if (!j.getListeParis().isEmpty() && j.getListeParis().contains(jeu.getResultatTourActuel().getValeur())) {
                            int n = j.getListeMontantsParis().get(jeu.getResultatTourActuel().getValeur()) * j.getListeMultiplicateursParis().get(jeu.getResultatTourActuel().getValeur());
                            j.miseAJourBanque(n);
                            if (joueurs.get(0)==j) {
                                whenWin();
                            }
                        } else if (!j.getListeParis().isEmpty()){
                            j.miseAJourBanque(-jeu.joueurCourantProperty().get().getMiseTotale());
                            if (joueurs.get(0)==j) {
                                whenLose();
                            }
                        }
                    }

                    for (Joueur j : joueurs) {
                        if (!j.getListeParis().isEmpty()) {
                            j.viderListeParis();
                            j.viderMontantsParis();
                            j.viderMultiplicateursParis();
                            j.setMiseTotale(0);
                        }
                    }
                    for (Node node : table.getTable().getChildren()) {
                        if (node instanceof ImageView) {
                            ImageView imageView = (ImageView) node;
                            imageView.setVisible(false);
                        }
                    }
                    jeu.tournerTour();
                });
                pauseTransition.play();
                statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
                statistiquesRoulette.afficherStatistique();
                numeroJoueurJouant = 0;
                vueBet.validationProperty().set(false);

            }
            jeu.joueurCourantProperty().set(joueurs.get(numeroJoueurJouant));
            gererAnimation();
        });
    }
    private void gererAnimation() {
        vueRoue.animation(jeu.getResultatTourActuel().getNombres());

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

        statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
        statistiquesRoulette.afficherStatistique();

        pauseTransition.setOnFinished(event -> {
            vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
            vueDroite.afficherStats();
            vueDroite.setStatistiquesRoulette(statistiquesRoulette);
            jeu.tournerTour();
        });
        pauseTransition.play();
    }

    // Méthode qui permet de retourner la vue du plateau
    public CreationTable getTable() {
        return table;
    }

    // Méthode qui permet de créer les bindings entre les différentes vues
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



        vueBet.validationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && numeroJoueurJouant==joueurs.size()-1) {
                System.out.println(jeu.joueurCourantProperty().get().getListeParis().toString());
                vueRoue.animation(jeu.getResultatTourActuel().getNombres());
                statistiquesRoulette.enregistrerResultat(jeu.getResultatTourActuel());
                statistiquesRoulette.afficherStatistique();
                numeroJoueurJouant=0;
                jeu.joueurCourantProperty().set(joueurs.get(numeroJoueurJouant));

                // Ajouter un délai de 5 secondes avant d'afficher whenWin / whenLose
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

                pauseTransition.setOnFinished(event -> {
                    vueGauche.afficherDerniersResultats(jeu.getResultatTourActuel());
                    vueDroite.afficherStats();
                    vueDroite.setStatistiquesRoulette(statistiquesRoulette);
                    if (jeu.joueurCourantProperty().get().getListeParis().contains(jeu.getResultatTourActuel().getValeur())) {
                        whenWin();
                    } else {
                        whenLose();
                    }
                    for (Node node : table.getTable().getChildren()) {
                        if (node instanceof ImageView) {
                            ImageView imageView = (ImageView) node;
                            imageView.setVisible(false);
                        }
                    }
                    if (autresJoueurs.getLangueChoisie().getValue() == 0) {
                        labelInstructions.setText("Le " + jeu.getResultatTourActuel().getValeur() + " " + jeu.getResultatTourActuel().getCouleur() + " est tombé ! Misez pour rejouer");
                    } else {
                        if (jeu.getResultatTourActuel().getCouleur().equals("Noir")) {
                            labelInstructions.setText("The Black " + jeu.getResultatTourActuel().getValeur() + " was selected ! Bet to play again");
                        } else if (jeu.getResultatTourActuel().getCouleur().equals("Rouge")) {
                            labelInstructions.setText("The Red " + jeu.getResultatTourActuel().getValeur() + " was selected ! Bet to play again");
                        } else {
                            labelInstructions.setText("The Green " + jeu.getResultatTourActuel().getValeur() + " was selected !");
                        }
                    }
                    for (Joueur j : joueurs) {
                        if (!j.getListeParis().isEmpty() && j.getListeParis().contains(jeu.getResultatTourActuel().getValeur())) {
                            int n = j.getListeMontantsParis().get(jeu.getResultatTourActuel().getValeur()) * j.getListeMultiplicateursParis().get(jeu.getResultatTourActuel().getValeur());
                            j.miseAJourBanque(n);
                        } else if (!j.getListeParis().isEmpty()){
                            j.miseAJourBanque(-jeu.joueurCourantProperty().get().getMiseTotale());
                        }
                    }
                    jeu.tournerTour();
//                    table.viderListeParis();
//                    table.viderMontantsParis();
//                    table.viderMultiplicateursParis();
                    for (Joueur j : joueurs) {
                        if (!j.getListeParis().isEmpty()) {
                            j.viderListeParis();
                            j.viderMontantsParis();
                            j.viderMultiplicateursParis();
                            j.setMiseTotale(0);
                        }
                    }
                    jeu.joueurCourantProperty().set(joueurs.get(numeroJoueurJouant));
                    numeroJoueurJouant=0;
                    vueBet.validationProperty().set(false);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                pauseTransition.play();
            } else if(newValue) {
                numeroJoueurJouant++;
                vueBet.validationProperty().set(false);
                jeu.joueurCourantProperty().set(joueurs.get(numeroJoueurJouant));
            }
        });

        jeu.joueurCourantProperty().get().getListeParisObserver().addListener((ListChangeListener<Integer>) c -> {
            vueBet.setOk(true);
        });

        autresJoueurs.getLangueChoisie().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) {
                labelInstructions.setText("La langue a bien été changée !");
            } else {
                labelInstructions.setText("Language has been change successfully !");
            }
        });
    }



    //Méthode qui permet d'afficher la popup de victoire
    public void whenWin() {
        System.out.println(jeu.joueurCourantProperty().get().getListeMontantsParis().toString());
        System.out.println(jeu.joueurCourantProperty().get().getListeMultiplicateursParis().toString());
        int n = jeu.joueurCourantProperty().get().getListeMontantsParis().get(jeu.getResultatTourActuel().getValeur()) * jeu.joueurCourantProperty().get().getListeMultiplicateursParis().get(jeu.getResultatTourActuel().getValeur());
        System.out.println("Valeur gagnée : " + n);
        valeurGagneeProperty.set(String.valueOf(n));
//        jeu.joueurCourantProperty().get().miseAJourBanque(n);
//        labelInstructions.setText("Gagné !");

        VueWin vueWin = new VueWin(primaryStage);
        vueWin.getGainJeton().textProperty().bind(valeurGagneeProperty);
        vueWin.afficher();


        Duration pauseDuration = Duration.seconds(5);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> vueWin.getStage().close()));
        timeline.play();
    }

   /* private int calculPerteWin() {
        return jeu.joueurCourantProperty().get().getMiseTotale()-montantsParis.get(jeu.getResultatTourActuel().getValeur());
    }*/


    //Méthode qui permet d'afficher la popup de défaite
    private void whenLose() {
//        jeu.joueurCourantProperty().get().miseAJourBanque(-jeu.joueurCourantProperty().get().getMiseTotale());
//        labelInstructions.setText("Perdu !");

        VueLoose vueLoose = new VueLoose(primaryStage);
        vueLoose.afficher();

        Duration pauseDuration = Duration.seconds(3);
        Timeline timeline = new Timeline(new KeyFrame(pauseDuration, event -> vueLoose.getStage().close()));
        timeline.play();
    }

    // Méthode qui permet de retourner l'instance de la classe
    public static VueDuJeu getInstance() {
        return instance;
    }

    public void resetPartie() {
        jeu.tournerTour();
    }

    public IJeu getJeu() {
        return jeu;
    }

//    public ArrayList<Integer> getListeParis() {
//        return listesParis;
//    }

    public Label getLabelInstructions() {
        return labelInstructions;
    }

//    public VueBet getVueBet() {
//        return vueBet;
//    }


}

