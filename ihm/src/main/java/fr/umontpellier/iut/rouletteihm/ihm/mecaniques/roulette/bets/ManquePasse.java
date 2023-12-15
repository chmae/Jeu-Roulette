package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;

public class ManquePasse extends Bet {
    private String choixJoueur;
    private final ArrayList<Integer> listeUnDixHuit = new ArrayList<Integer>(18);
    private final ArrayList<Integer> listeDixNeufTrenteSix = new ArrayList<Integer>(18);

    public ManquePasse(String desc, int mise) {
        super(desc, mise);
        choixJoueur = "";
        for (int i = 1; i <= 18; i++) {
            listeUnDixHuit.add(i);
        }
        for (int i = 19; i <= 36; i++) {
            listeDixNeufTrenteSix.add(i);
        }

    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        int numero = numeroTire.getValeur();
        if ((numero >= 1 && numero <= 18) && choixJoueur.equals("1-18")) {
            return true;
        } else return (numero >= 19 && numero <= 36) && choixJoueur.equals("19-36");

    }

    @Override
    public void afficherBet() {
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir un ManquePasse", "1-18", "19-36");
    }


}
