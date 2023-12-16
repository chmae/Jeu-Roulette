package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

public class Carre extends Bet {
    private final int[] choixJoueur;

    public Carre(String desc, int mise) {
        super(desc, mise);
        choixJoueur = new int[4];
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        for (int i = 0; i < 4; i++) {
            if (choixJoueur[i] == numeroTire.getValeur()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void afficherBet() {
        for (int i = 0; i < 4; i++) {
            choixJoueur[i] = Interaction.saisirPlageMinMax("Veuillez choisir un nombre entre 1 et 36", 1, 36);
        }
    }
}

