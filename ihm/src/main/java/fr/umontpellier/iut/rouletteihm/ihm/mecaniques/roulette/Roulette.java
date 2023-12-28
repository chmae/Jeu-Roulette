package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu.EtatJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu.InitialisationJoueurs;
import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau.Nombres;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant la roulette
 */
public class Roulette implements IJeu {

    /**
     * Constantes pour les couleurs
     */
    public static final String Rouge = "Rouge";
    public static final String Noir = "Noir";
    public static final String Vert = "Vert";

    private static final String[] Cases = {
            Vert, Rouge, Noir,
            Rouge, Noir, Rouge,
            Noir, Rouge, Noir,
            Rouge, Noir, Noir,
            Rouge, Noir, Rouge,
            Noir, Rouge, Noir,
            Rouge, Rouge, Noir,
            Rouge, Noir, Rouge,
            Noir, Rouge, Noir,
            Rouge, Noir, Noir,
            Rouge, Noir, Rouge,
            Noir, Rouge, Noir, Rouge};

    private static int nbToursRoulette;
    private static int valeurResultat;
    private static int random;
    private Joueur joueur;
    private static Probabilite generateur;

    private final BooleanProperty finDePartie = new SimpleBooleanProperty();

    private EtatJeu etatCourantDuJeu;
    private final ObjectProperty<IJoueur> joueurCourant = new SimpleObjectProperty<>();

    private final List<Joueur> joueurs;


    /**
     * Renvoie le résultat du tour actuel
     * @return
     */
    public resultatTour getResultatTourActuel() {
        return resultatTourActuel;
    }


    private resultatTour resultatTourActuel;

    /**
     * Constructeur de la classe Roulette
     */
    public Roulette() {
        nbToursRoulette = 0;
        valeurResultat = 0;
        long seed = System.currentTimeMillis();
        generateur = new Probabilite(seed);
        random = generateur.nextRandom(0, 36);
        joueurs = new ArrayList<>();
        resultatTourActuel = null;
    }

    // récupère le joueur courant
    public Joueur getJoueurCourant() {
        return (Joueur) joueurCourant.getValue();
    }


    /**
     * Méthode permettant de récupérer le joueur courant
     * @return le joueur courant
     */
    @Override
    public ObjectProperty<IJoueur> joueurCourantProperty() {
        return joueurCourant;
    }

    /**
     * Méthode permettant de récupérer la propriété finDePartie
     * @return la propriété finDePartie
     */
    @Override
    public BooleanProperty finDePartieProperty() {
        return finDePartie;
    }

    /**
     * Méthode permettant de récupérer la liste des joueurs
     * @return la liste des joueurs
     */
    @Override
    public List<? extends IJoueur> getJoueurs() {
        return joueurs;
    }

    @Override
    public void passerTour() {

    }

    /**
     * Méthode permettant de tourner la roulette
     */
    @Override
    public void tournerTour() {
        this.resultatTourActuel = this.resultatTour();
    }

    /**
     * Méthode permettant de récupérer le joueur
     * @return le joueur
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Méthode permettant de récupérer l'état courant du jeu
     * @return l'état courant du jeu
     */
    public EtatJeu getEtatCourantDuJeu() {
        return etatCourantDuJeu;
    }

    /**
     * Méthode permettant de modifier l'état courant du jeu
     * @param etatCourantDuJeu le nouvel état courant du jeu
     */
    public void setEtatCourantDuJeu(EtatJeu etatCourantDuJeu) {
        this.etatCourantDuJeu = etatCourantDuJeu;
    }

    /**
     * Méthode permettant de récupérer le joueur
     * @return le joueur
     */
    public void run(Joueur joueur) {
        joueurCourant.set(joueur);
        joueurs.add(joueur);
        etatCourantDuJeu = new InitialisationJoueurs(this, joueur);
    }

    /**
     * Méthode permettant de récupérer le nombre de tours de la roulette
     * @return le nombre de tours de la roulette
     */
    public int getNbToursRoulette() {
        return nbToursRoulette;
    }

    /**
     * Méthode permettant de récupérer la valeur du résultat
     * @return la valeur du résultat
     */
    private static String getCouleur() {
        return Cases[valeurResultat];
    }

    /**
     * Méthode permettant de récupérer la valeur du résultat
     * @return la valeur du résultat
     */
    private static int getValeur() {
        return valeurResultat;
    }

    /**
     * Méthode permettant de récupérer les cases
     * @return les cases
     */
    public String[] getCases() {
        return Cases;
    }

    /**
     * Méthode permettant de récupérer le résultat du tour
     * @return le résultat du tour
     */
    public resultatTour resultatTour() {
        nbToursRoulette++;
        random = generateur.nextRandom(0, 36);
        valeurResultat = random;
        return new resultatTour(getValeur(), getCouleur());
    }

    /**
     * Méthode permettant de réinitialiser la roulette
     */
    public void reset() {
        nbToursRoulette = 0;
        valeurResultat = 0;
        long seed = System.currentTimeMillis();
        generateur = new Probabilite(seed);
        random = generateur.nextRandom(0, 36);
    }


    //======================================================================================================================
    public static class resultatTour {
        private final String couleur;
        private final int valeur;

        public resultatTour(int valeur, String couleur) {
            this.valeur = valeur;
            this.couleur = couleur;
        }

        public String getCouleur() {
            return couleur;
        }

        public int getValeur() {
            return valeur;
        }

        public Nombres getNombres() {
            return new Nombres(valeur, couleur, 0);
        }
    }
}
