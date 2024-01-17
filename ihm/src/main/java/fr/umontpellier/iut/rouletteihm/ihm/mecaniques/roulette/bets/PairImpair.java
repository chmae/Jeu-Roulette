package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

// Classe représentant un type de pari 'PairImpair' héritant de la classe Bet
public class PairImpair extends Bet {

    // Attribut privé pour stocker le choix du joueur
    private String choixJoueur;

    // Constructeur prenant une description et une mise en paramètres
    public PairImpair(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = ""; // Initialisation du choix du joueur à une chaîne vide
    }

    // Méthode pour vérifier si le pari est gagné en fonction du numéro tiré et du choix du joueur
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        // Vérification si le numéro tiré est pair et que le choix du joueur est 'Pair'
        if (numeroTire.getValeur() % 2 == 0 && choixJoueur.equals("Pair")) {
            return true;
        } else {
            // Vérification si le numéro tiré est impair et que le choix du joueur est 'Impair'
            return numeroTire.getValeur() % 2 == 1 && choixJoueur.equals("Impair");
        }
    }

    // Méthode pour afficher le pari en demandant au joueur de choisir entre 'Pair' ou 'Impair'
    @Override
    public void afficherBet() {
        // Appel d'une méthode saisirParmiChoix() pour obtenir le choix entre 'Pair' ou 'Impair' du joueur
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir entre Pair ou Impair", "Pair", "Impair");
    }
}