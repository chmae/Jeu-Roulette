package fr.umontpellier.iut.rouletteihm.ihm;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Interface représentant un joueur de roulette.
 *    Un joueur de roulette est composé d'un nom, d'un solde, d'une mise actuelle et d'une mise totale.
 *    Il permet de mettre à jour le solde, et de récupérer les propriétés du joueur.
 *    Il permet également de récupérer la mise actuelle et la mise totale.
 *    Il est possible de s'abonner aux changements de propriétés du joueur.
 */
public interface IJoueur {
    int getSolde();

    int getMiseTotale();

    IntegerProperty getMiseTotaleProperty();

    String getNom();

    IntegerProperty soldeProperty();

    void miseAJourBanque(int amount);

    StringProperty nomProperty();

    IntegerProperty getMiseActuelleProperty();

    int getMiseActuelle();

    void setMiseActuelle(int mise);

    void setMiseTotale(int mise);

    ArrayList<Integer> getListeParis();

    ArrayList<Integer> getListeMontantsParis();

    ArrayList<Integer> getListeMultiplicateursParis();

    void ajouterParis(ArrayList<Integer> nombreParies, int valeurMontant, String nomParis);

    ObservableList<Integer> getListeParisObserver();
}
