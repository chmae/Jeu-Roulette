package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

public class Sixain extends Bet {
    public int[] choixJoueur;

    public Sixain(String desc, int mise) {
        super(desc, mise);
        choixJoueur = new int[6];
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        for (int i = 0; i < 6; i++) {
            if (choixJoueur[i] == numeroTire.getValeur()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void afficherBet() {
        return;
    }
}
