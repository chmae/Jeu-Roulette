package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

public class RougeNoir extends Bet {
    private String choixJoueur;

    public RougeNoir(String description, int mise) {
        super(description, mise);
        choixJoueur = "";
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        return numeroTire.getCouleur().equals(choixJoueur);
    }

    @Override
    public void afficherBet() {
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir une couleur", "Rouge", "Noir");
    }
}
