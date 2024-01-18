package fr.umontpellier.iut.rouletteihm.ihm;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

import java.util.List;

/**
 * Interface représentant un jeu de roulette.
 * Un jeu de roulette est composé d'une liste de joueurs, d'un joueur courant, et d'un état de fin de partie.
 * Il permet de passer au joueur suivant, de tourner la roulette, et de récupérer le résultat du tour actuel.
 * Il permet également de récupérer les propriétés des joueurs.
 * Il permet enfin de récupérer le joueur courant et l'état de fin de partie.
 * Il est possible de s'abonner aux changements de joueur courant et d'état de fin de partie.
 * Il est possible de s'abonner aux changements de propriétés des joueurs.
 */
public interface IJeu {

    ObjectProperty<IJoueur> joueurCourantProperty();

    BooleanProperty finDePartieProperty();


    List<? extends IJoueur> getJoueurs();

    void passerTour();

    void tournerTour();

    Roulette.resultatTour getResultatTourActuel();

}
