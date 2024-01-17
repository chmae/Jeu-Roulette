package fr.umontpellier.iut.rouletteihm.ihm;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
