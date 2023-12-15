package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu.EtatJeu;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu.InitialisationJoueurs;
import fr.umontpellier.iut.rouletteihm.ihm.IJeu;
import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.Nombres;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class Roulette implements IJeu {
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

    public resultatTour getResultatTourActuel() {
        return resultatTourActuel;
    }

    private resultatTour resultatTourActuel;

    public Roulette() {
        nbToursRoulette = 0;
        valeurResultat = 0;
        long seed = System.currentTimeMillis();
        generateur = new Probabilite(seed);
        random = generateur.nextRandom(0, 36);
        joueurs = new ArrayList<>();
        resultatTourActuel = null;
    }

    public Joueur getJoueurCourant() {
        return (Joueur) joueurCourant.getValue();
    }

    @Override
    public ObjectProperty<IJoueur> joueurCourantProperty() {
        return joueurCourant;
    }

    @Override
    public BooleanProperty finDePartieProperty() {
        return finDePartie;
    }

    @Override
    public List<? extends IJoueur> getJoueurs() {
        return joueurs;
    }

    @Override
    public void passerTour() {

    }

    @Override
    public void tournerTour() {
        this.resultatTourActuel = this.resultatTour();
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public EtatJeu getEtatCourantDuJeu() {
        return etatCourantDuJeu;
    }

    public void setEtatCourantDuJeu(EtatJeu etatCourantDuJeu) {
        this.etatCourantDuJeu = etatCourantDuJeu;
    }

    public void run(Joueur joueur) {
        joueurCourant.set(joueur);
        joueurs.add(joueur);
        etatCourantDuJeu = new InitialisationJoueurs(this, joueur);
    }

    public int getNbToursRoulette() {
        return nbToursRoulette;
    }

    private static String getCouleur() {
        return Cases[valeurResultat];
    }

    private static int getValeur() {
        return valeurResultat;
    }

    public String[] getCases() {
        return Cases;
    }

    public resultatTour resultatTour() {
        nbToursRoulette++;
        random = generateur.nextRandom(0, 36);
        valeurResultat = random;
        return new resultatTour(getValeur(), getCouleur());
    }

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
