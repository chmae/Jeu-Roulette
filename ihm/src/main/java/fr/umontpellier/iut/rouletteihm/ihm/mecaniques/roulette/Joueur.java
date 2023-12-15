package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Joueur implements IJoueur {
    private final SimpleStringProperty nom;
    private final SimpleIntegerProperty monArgent;

    private final SimpleIntegerProperty miseActuelle;
    private final SimpleIntegerProperty miseTotale;


    public Joueur(String nom, int argent) {
        this.nom = new SimpleStringProperty(nom);
        monArgent = new SimpleIntegerProperty(argent);
        miseTotale = new SimpleIntegerProperty(0);
        miseActuelle = new SimpleIntegerProperty(0);
    }

    public String getMonNom() {
        return nom.getValue();
    }

    public int getMonArgent() {
        return monArgent.getValue();
    }

    public boolean prelevable() {
        return (monArgent.getValue() >= 1);
    }

    public void miseAJourBanque(int amount) {
        monArgent.setValue(monArgent.getValue() + amount);
        System.out.println(monArgent.getValue());
//        miseTotale += amount;
    }

    @Override
    public void setMiseActuelle(int mise) {
        miseActuelle.setValue(mise);
    }

    @Override
    public void setMiseTotale(int mise) {
        miseTotale.setValue(mise);
    }

    @Override
    public int getMiseTotale() {
        return miseTotale.getValue();
    }

    @Override
    public int getMiseActuelle() {
        return miseActuelle.getValue();
    }

    @Override
    public IntegerProperty getMiseActuelleProperty() {
        return miseActuelle;
    }

    @Override
    public IntegerProperty getMiseTotaleProperty() {
        return miseTotale;
    }

    @Override
    public String getNom() {
        return getMonNom();
    }

    @Override
    public StringProperty nomProperty() {
        return nom;
    }


    @Override
    public int getSolde() {
        return getMonArgent();
    }

    public IntegerProperty soldeProperty() {
        return monArgent;
    }

}
