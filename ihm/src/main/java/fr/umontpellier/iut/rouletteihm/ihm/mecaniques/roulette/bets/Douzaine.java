package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;

public class Douzaine extends Bet {
    private String choixJoueur;
    private final ArrayList<Integer> premiereDouzaine = new ArrayList<Integer>(12);
    private final ArrayList<Integer> deuxiemeDouzaine = new ArrayList<Integer>(12);
    private final ArrayList<Integer> troisiemeDouzaine = new ArrayList<Integer>(12);

    public Douzaine(String desc, int mise) {
        super(desc, mise);
        choixJoueur = "";
        for (int i = 1; i <= 12; i++) {
            premiereDouzaine.add(i);
        }
        for (int i = 13; i <= 24; i++) {
            deuxiemeDouzaine.add(i);
        }
        for (int i = 25; i <= 36; i++) {
            troisiemeDouzaine.add(i);
        }
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        int numero = numeroTire.getValeur();

        if ((numero >= 1 && numero <= 12) && choixJoueur.equals("1-12")) {
            return true;
        } else if ((numero >= 13 && numero <= 24) && choixJoueur.equals("13-24")) {
            return true;
        } else return (numero >= 25 && numero <= 36) && choixJoueur.equals("25-36");

    }

    @Override
    public void afficherBet() {
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir une douzaine", "1-12", "13-24", "25-36");
    }


}