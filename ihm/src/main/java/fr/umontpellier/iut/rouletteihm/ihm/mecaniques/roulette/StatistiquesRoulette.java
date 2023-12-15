package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Arrays;

public class StatistiquesRoulette {
    private int nbToursJoues;
    private int[] nbSortiesParNumero;
    private int[] nbSortiesParCouleur;
    private DoubleProperty probabiliteRouge = new SimpleDoubleProperty();
    private DoubleProperty probabiliteNoir = new SimpleDoubleProperty();
    private DoubleProperty probabiliteVert = new SimpleDoubleProperty();

    public StatistiquesRoulette() {
        nbToursJoues = 0;
        nbSortiesParNumero = new int[37]; // 37 numéros possibles (0-36)
        nbSortiesParCouleur = new int[3]; // 3 couleurs possibles (vert, rouge, noir)
    }

    public void mettreAJourProbabilites() {
        int nbSortiesRouge = nbSortiesParCouleur[1];
        int nbSortiesNoir = nbSortiesParCouleur[2];
        int nbSortiesVert = nbSortiesParCouleur[0];

        double totalSorties = nbToursJoues;

        probabiliteRouge.set(totalSorties != 0 ? nbSortiesRouge / totalSorties : 0);
        probabiliteNoir.set(totalSorties != 0 ? nbSortiesNoir / totalSorties : 0);
        probabiliteVert.set(totalSorties != 0 ? nbSortiesVert / totalSorties : 0);
    }

    public double getProbabiliteRouge() {
        return probabiliteRouge.get();
    }

    public DoubleProperty probabiliteRougeProperty() {
        return probabiliteRouge;
    }

    public double getProbabiliteNoir() {
        return probabiliteNoir.get();
    }

    public DoubleProperty probabiliteNoirProperty() {
        return probabiliteNoir;
    }

    public double getProbabiliteVert() {
        return probabiliteVert.get();
    }

    public DoubleProperty probabiliteVertProperty() {
        return probabiliteVert;
    }

    public void enregistrerResultat(Roulette.resultatTour resultat) {
        nbToursJoues++;

        // Enregistrement du nombre de sorties par numéro
        int numero = resultat.getValeur();
        nbSortiesParNumero[numero]++;

        // Enregistrement du nombre de sorties par couleur
        String couleur = resultat.getCouleur();
        if (couleur.equals(Roulette.Rouge)) {
            nbSortiesParCouleur[1]++;
        } else if (couleur.equals(Roulette.Noir)) {
            nbSortiesParCouleur[2]++;
        } else {
            nbSortiesParCouleur[0]++;
        }
    }

    public double getProbabiliteNumero(int numero) {
        int nbSorties = nbSortiesParNumero[numero];
        return (nbToursJoues != 0) ? (double) nbSorties / nbToursJoues : 0.0;
    }


    public double getProbabiliteCouleur(String couleur) {
        int indexCouleur;
        if (couleur.equals(Roulette.Rouge)) {
            indexCouleur = 1;
        } else if (couleur.equals(Roulette.Noir)) {
            indexCouleur = 2;
        } else {
            indexCouleur = 0;
        }
        int nbSorties = nbSortiesParCouleur[indexCouleur];

        return (nbToursJoues != 0) ? (double) nbSorties / nbToursJoues : 0;
    }


    public void afficherStatistique() {
        System.out.println("Nombre de tours joués : " + nbToursJoues);
        System.out.println("Nombre de sorties par numéro :");
        for (int i = 0; i < 37; i++) {
            System.out.println("Numéro " + i + " : " + nbSortiesParNumero[i]);
            System.out.println("Proba " + i + " : " + getProbabiliteNumero(i));
        }
        System.out.println("Nombre de sorties par couleur :");
        System.out.println("Vert : " + nbSortiesParCouleur[0]);
        System.out.println("Proba Vert : " + getProbabiliteCouleur(Roulette.Vert));
        System.out.println("Rouge : " + nbSortiesParCouleur[1]);
        System.out.println("Proba Rouge : " + getProbabiliteCouleur(Roulette.Rouge));
        System.out.println("Noir : " + nbSortiesParCouleur[2]);
        System.out.println("Proba Noir : " + getProbabiliteCouleur(Roulette.Noir));
    }

    public int[] numerosLesPlusSortis() {
        int[] numerosPlusSortis = new int[3];
        int[] copieNbSortiesParNumero = Arrays.copyOf(nbSortiesParNumero, nbSortiesParNumero.length);

        // Recherche des 3 numéros les plus sortis
        for (int i = 0; i < 3; i++) {
            int maxSorties = -1;
            int indexMax = -1;

            // Parcours du tableau copieNbSortiesParNumero pour trouver le numéro avec le plus de sorties
            for (int j = 0; j < copieNbSortiesParNumero.length; j++) {
                if (copieNbSortiesParNumero[j] > maxSorties) {
                    maxSorties = copieNbSortiesParNumero[j];
                    indexMax = j;
                }
            }

            // Ajout du numéro trouvé dans le tableau numerosPlusSortis
            numerosPlusSortis[i] = indexMax;

            // Mise à zéro du nombre de sorties pour ce numéro dans le tableau copieNbSortiesParNumero
            copieNbSortiesParNumero[indexMax] = 0;
        }

        return numerosPlusSortis;
    }

    public void afficherNumerosLesPlusSortis() {
        int[] numerosPlusSortis = numerosLesPlusSortis();
        System.out.println("Les 3 numéros les plus sortis sont :");
        for (int i = 0; i < 3; i++) {
            System.out.println(numerosPlusSortis[i]);
        }
    }

    public int getNbToursJoues() {
        return nbToursJoues;
    }
}
