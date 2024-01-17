package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import fr.umontpellier.iut.rouletteihm.ihm.IJoueur;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Joueur implements IJoueur {
    private final SimpleStringProperty nom;
    private final SimpleIntegerProperty monArgent;

    private final SimpleIntegerProperty miseActuelle;
    private final SimpleIntegerProperty miseTotale;

    public ArrayList<Integer> getListeParis() {
        return listeParis;
    }

    private ArrayList<Integer> listeParis;

    public ArrayList<Integer> getListeMultiplicateursParis() {
        return listeMultiplicateursParis;
    }

    public ArrayList<Integer> getListeMontantsParis() {
        return listeMontantsParis;
    }
    private ArrayList<String> listeNomsParis;

    private ArrayList<Integer> listeMultiplicateursParis;
    private ArrayList<Integer> listeMontantsParis;


    public Joueur(String nom, int argent) {
        this.nom = new SimpleStringProperty(nom);
        monArgent = new SimpleIntegerProperty(argent);
        miseTotale = new SimpleIntegerProperty(0);
        miseActuelle = new SimpleIntegerProperty(0);
        listeParis = new ArrayList<>();
        listeNomsParis =  new ArrayList<>();
        listeMultiplicateursParis = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            listeMultiplicateursParis.add(0);
        }
        listeMontantsParis = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            listeMontantsParis.add(0);
        }
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

    public void ajouterParis(ArrayList<Integer> nombreParies, int valeurMontant, String nomParis) {
        boolean multiplicateur = true;
        if (!listeNomsParis.isEmpty()) {
            for (String nomP : listeNomsParis) {
                if (nomP.equals(nomParis)) {
                    multiplicateur = false;
                    break;
                }
            }
        }

        if (multiplicateur) {
            listeNomsParis.add(nomParis);
            if (nombreParies.size() == 1) {
                ajouterMultiplicateurParis(36, nombreParies);
            } else if (nombreParies.size() == 2){
                ajouterMultiplicateurParis(18, nombreParies);
            } else if (nombreParies.size() == 3){
                ajouterMultiplicateurParis(12, nombreParies);
            } else if (nombreParies.size() == 4){
                ajouterMultiplicateurParis(9, nombreParies);
            } else if (nombreParies.size() == 6){
                ajouterMultiplicateurParis(6, nombreParies);
            } else if (nombreParies.size() == 12) {
                ajouterMultiplicateurParis(3, nombreParies);
            } else {
                ajouterMultiplicateurParis(2, nombreParies);
            }
        }

        for (Integer nombre : nombreParies) {
            if(!listeParis.contains(nombre)) {
                listeParis.add(nombre);
            }
        }

        ajouterValeurMontant(valeurMontant, nombreParies);
        miseAJourBanque(-valeurMontant);
    }
    private void ajouterValeurMontant(int valeur, ArrayList<Integer> nombreParies) {
        for (Integer nombre : nombreParies) {
            listeMontantsParis.set(nombre, listeMontantsParis.get(nombre) + valeur);
        }
    }
    private void ajouterMultiplicateurParis(int multiplcateur, ArrayList<Integer> nombreParies) {
        for (Integer nombre : nombreParies) {
            listeMultiplicateursParis.set(nombre, listeMultiplicateursParis.get(nombre) + multiplcateur);
        }
    }

    public void viderMontantsParis() {
        for (int i = 0; i < 37; i++) {
            listeMontantsParis.set(i, 0);
        }
    }

    public void viderMultiplicateursParis() {
        for (int i = 0; i < 37; i++) {
            listeMultiplicateursParis.set(i, 0);
        }
    }

    public void viderListeParis() {
        listeParis.clear();
    }

    public ObservableList<Integer> getListeParisObserver() {
        return FXCollections.observableList(listeParis);
    }

}
