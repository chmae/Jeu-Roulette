package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

// Classe représentant un type de pari 'Transversale' héritant de la classe Bet
public class Transversale extends Bet {

    // Attribut pour stocker les choix du joueur (un tableau de 3 entiers)
    private int[] choixJoueur;

    // Constructeur prenant une description et une mise en paramètres
    public Transversale(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = new int[3]; // Initialisation du tableau de choix du joueur contenant 3 entiers
    }

    // Méthode pour vérifier si le pari est gagné en fonction des valeurs tirées et du choix du joueur
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        // Parcours du tableau de choix du joueur pour vérifier si une des valeurs choisies correspond à la valeur tirée
        for (int i = 0; i < 3; i++) {
            if (choixJoueur[i] == numeroTire.getValeur()) {
                return true; // Si la valeur tirée correspond à l'une des valeurs choisies, le pari est gagné
            }
        }
        return false; // Si aucune des valeurs choisies ne correspond à la valeur tirée, le pari est perdu
    }

    // Méthode pour afficher le pari (non implémentée dans ce cas)
    @Override
    public void afficherBet() {
        return; // Cette méthode est vide, elle ne fait rien pour l'affichage du pari
    }
}
