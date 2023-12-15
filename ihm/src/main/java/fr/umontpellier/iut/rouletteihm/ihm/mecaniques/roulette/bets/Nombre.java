package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

public class Nombre extends Bet {
    private String choixJoueur;

    public Nombre(String description, int mise) {
        super(description, mise);
        choixJoueur = "";
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        return numeroTire.getValeur() == Integer.parseInt(choixJoueur);
    }

    @Override
    public void afficherBet() {
        choixJoueur = Integer.toString(Interaction.saisirPlageMinMax("Veuillez choisir un nombre entre", 0, 36));
    }

    public String getChoixJoueur() {
        return choixJoueur;
    }
}
