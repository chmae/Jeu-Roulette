package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

public class EtatJeu {

    // Déclaration des champs de classe
    protected final Roulette jeu;
    protected final Joueur joueurCourant;

    // Constructeur prenant une instance de Roulette comme paramètre
    public EtatJeu(Roulette roulette) {
        this.jeu = roulette;
        joueurCourant = roulette.getJoueurCourant();
    }

    // Méthode abstraite pour démarrer la partie
    public void demarrerPartie() {
    }

    // Méthode abstraite pour passer au prochain tour
    public void prochainTour() {
    }


}