package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Classe Joueur qui implémente l'interface IJoueur
public class Joueur implements IJoueur {

    // Attributs privés de la classe Joueur utilisant les classes de propriété de JavaFX
    private final SimpleStringProperty nom;
    private final SimpleIntegerProperty monArgent;

    private final SimpleIntegerProperty miseActuelle;
    private final SimpleIntegerProperty miseTotale;


    // Constructeur de la classe Joueur prenant en paramètres le nom du joueur et son argent initial
    public Joueur(String nom, int argent) {
        this.nom = new SimpleStringProperty(nom);
        monArgent = new SimpleIntegerProperty(argent);
        miseTotale = new SimpleIntegerProperty(0);
        miseActuelle = new SimpleIntegerProperty(0);
    }

    // Méthode pour obtenir le nom du joueur
    public String getMonNom() {
        return nom.getValue();
    }

    // Méthode pour obtenir l'argent du joueur
    public int getMonArgent() {
        return monArgent.getValue();
    }

    // Méthode pour savoir si le joueur peut miser
    public boolean prelevable() {
        return (monArgent.getValue() >= 1);
    }

    // Méthode pour mettre à jour l'argent du joueur
    public void miseAJourBanque(int amount) {
        monArgent.setValue(monArgent.getValue() + amount);
        System.out.println(monArgent.getValue());
//        miseTotale += amount;
    }

    // Méthode pour mettre à jour la mise actuelle du joueur
    @Override
    public void setMiseActuelle(int mise) {
        miseActuelle.setValue(mise);
    }

    // Méthode pour mettre à jour la mise totale du joueur
    @Override
    public void setMiseTotale(int mise) {
        miseTotale.setValue(mise);
    }

    // Méthode pour obtenir la mise totale du joueur
    @Override
    public int getMiseTotale() {
        return miseTotale.getValue();
    }

    // Méthode pour obtenir la mise actuelle du joueur
    @Override
    public int getMiseActuelle() {
        return miseActuelle.getValue();
    }

    @Override
    public IntegerProperty getMiseActuelleProperty() {
        return miseActuelle;
    }

    // Méthode pour obtenir la propriété de la mise totale du joueur
    @Override
    public IntegerProperty getMiseTotaleProperty() {
        return miseTotale;
    }

    @Override
    public String getNom() {
        return getMonNom();
    }

    // Méthode pour obtenir la propriété de l'argent du joueur
    @Override
    public StringProperty nomProperty() {
        return nom;
    }

    @Override
    public int getSolde() {
        return getMonArgent();
    }

    // Méthode pour obtenir la propriété de l'argent du joueur
    public IntegerProperty soldeProperty() {
        return monArgent;
    }

}
