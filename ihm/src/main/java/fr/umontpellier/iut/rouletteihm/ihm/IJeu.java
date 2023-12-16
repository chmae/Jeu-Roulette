package fr.umontpellier.iut.rouletteihm.ihm;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

import java.util.List;

public interface IJeu {

    ObjectProperty<IJoueur> joueurCourantProperty();

    BooleanProperty finDePartieProperty();


    List<? extends IJoueur> getJoueurs();

    void passerTour();

    void tournerTour();

    Roulette.resultatTour getResultatTourActuel();

}
