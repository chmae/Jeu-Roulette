package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

// Classe représentant un type de pari 'Carre' (Carré) héritant de la classe Bet
public class Carre extends Bet {

    // Attribut représentant les choix du joueur
    private final int[] choixJoueur;

    // Constructeur prenant une description et une mise en paramètres
    public Carre(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = new int[4]; // Initialisation du tableau pour stocker les choix du joueur
    }

    // Méthode pour vérifier si le pari est gagné en comparant avec le résultat du tour
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        for (int i = 0; i < 4; i++) {
            if (choixJoueur[i] == numeroTire.getValeur()) {
                return true; // Si l'un des choix du joueur correspond au numéro tiré, le pari est gagné
            }
        }
        return false; // Si aucun choix du joueur ne correspond au numéro tiré, le pari est perdu
    }

    // Méthode pour afficher le pari en demandant les choix du joueur
    @Override
    public void afficherBet() {
        for (int i = 0; i < 4; i++) {
            // Demande au joueur de saisir un nombre entre 1 et 36 et le stocke dans le tableau des choix
            choixJoueur[i] = Interaction.saisirPlageMinMax("Veuillez choisir un nombre entre 1 et 36", 1, 36);
        }
    }
}