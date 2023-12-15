package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

public class InitialisationJoueurs extends EtatJeu {

    public InitialisationJoueurs(Roulette roulette, Joueur joueur) {
        super(roulette);
        initialiserJoueur(roulette, joueur);
    }

    private void initialiserJoueur(Roulette roulette, Joueur joueur) {
        roulette.setJoueur(joueur);
    }
}

