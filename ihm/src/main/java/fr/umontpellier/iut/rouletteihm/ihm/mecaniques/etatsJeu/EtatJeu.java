package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

public class EtatJeu {
    protected final Roulette jeu;
    protected final Joueur joueurCourant;

    public EtatJeu(Roulette roulette) {
        this.jeu = roulette;
        joueurCourant = roulette.getJoueurCourant();
    }

    public void demarrerPartie() {
    }

    public void prochainTour() {
    }


}