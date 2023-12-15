package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;
import java.util.Arrays;

public class Colonne extends Bet {
    private String choixJoueur;
    private final ArrayList<Integer> premiereColonne = new ArrayList<Integer>(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34));
    private final ArrayList<Integer> deuxiemeColonne = new ArrayList<Integer>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35));
    private final ArrayList<Integer> troisiemeColonne = new ArrayList<Integer>(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36));

    public Colonne(String desc, int mise) {
        super(desc, mise);
        choixJoueur = "";
    }

    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        if (premiereColonne.contains(numeroTire.getValeur()) && choixJoueur.equals("premiereColonne")) {
            return true;
        } else if (deuxiemeColonne.contains(numeroTire.getValeur()) && choixJoueur.equals("deuxiemeColonne")) {
            return true;
        } else return troisiemeColonne.contains(numeroTire.getValeur()) && choixJoueur.equals("troisiemeColonne");
    }


    @Override
    public void afficherBet() {
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir une Colonne", "premiereColonne", "deuxiemeColonne", "troisiemeColonne");
    }


}



