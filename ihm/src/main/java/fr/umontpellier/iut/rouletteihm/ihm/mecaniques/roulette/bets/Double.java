package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

// Classe représentant un type de pari 'Double' héritant de la classe Bet
public class Double extends Bet {

    // Attribut représentant les choix du joueur
    public int[] choixJoueur;

    // Constructeur prenant une description et une mise en paramètres
    public Double(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = new int[2]; // Initialisation du tableau pour stocker les choix du joueur
    }

    // Méthode pour vérifier si le pari est gagné en comparant avec le résultat du tour
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        for (int i = 0; i < 2; i++) {
            if (choixJoueur[i] == numeroTire.getValeur()) {
                return true; // Si l'un des choix du joueur correspond au numéro tiré, le pari est gagné
            }
        }
        return false; // Si aucun choix du joueur ne correspond au numéro tiré, le pari est perdu
    }

    // Méthode pour afficher le pari
    @Override
    public void afficherBet() {
        // Cette méthode est vide car elle semble ne pas être définie pour ce type de pari
        // Si aucune action n'est à réaliser pour afficher ce pari, la méthode reste vide
        // Elle pourrait être implémentée si nécessaire pour une fonctionnalité spécifique
        return; // Cette instruction retourne directement, indiquant qu'aucune action n'est requise ici
    }
}

