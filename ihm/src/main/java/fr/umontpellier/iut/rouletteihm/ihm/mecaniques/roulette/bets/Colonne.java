package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;
import java.util.Arrays;

// Classe représentant un type de pari sur les colonnes héritant de la classe Bet
public class Colonne extends Bet {

    // Attributs représentant les choix du joueur et les listes des colonnes possibles
    private String choixJoueur;
    private final ArrayList<Integer> premiereColonne = new ArrayList<Integer>(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34));
    private final ArrayList<Integer> deuxiemeColonne = new ArrayList<Integer>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35));
    private final ArrayList<Integer> troisiemeColonne = new ArrayList<Integer>(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36));

    // Constructeur prenant une description et une mise en paramètres
    public Colonne(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = ""; // Initialisation du choix du joueur
    }

    // Méthode pour vérifier si le pari est gagné en fonction de la colonne choisie par le joueur
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        if (premiereColonne.contains(numeroTire.getValeur()) && choixJoueur.equals("premiereColonne")) {
            return true; // Si le numéro tiré est dans la première colonne et correspond au choix du joueur, le pari est gagné
        } else if (deuxiemeColonne.contains(numeroTire.getValeur()) && choixJoueur.equals("deuxiemeColonne")) {
            return true; // Si le numéro tiré est dans la deuxième colonne et correspond au choix du joueur, le pari est gagné
        } else return troisiemeColonne.contains(numeroTire.getValeur()) && choixJoueur.equals("troisiemeColonne");
        // Si le numéro tiré est dans la troisième colonne et correspond au choix du joueur, le pari est gagné
    }

    // Méthode pour afficher le pari en demandant au joueur de choisir une colonne
    @Override
    public void afficherBet() {
        // Demande au joueur de choisir une colonne parmi les options disponibles
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir une Colonne", "premiereColonne", "deuxiemeColonne", "troisiemeColonne");
    }
}



