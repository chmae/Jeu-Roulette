package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

public class PairImpair extends Bet {
    private String choixJoueur;

    public PairImpair(String desc, int mise) {
        super(desc, mise);
        choixJoueur = "";
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        if (numeroTire.getValeur() % 2 == 0 && choixJoueur.equals("Pair")) {
            return true;
        } else return numeroTire.getValeur() % 2 == 1 && choixJoueur.equals("Impair");
    }

    @Override
    public void afficherBet() {
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir entre Pair ou Impair", "Pair", "Impair");
    }
}