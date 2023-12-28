package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

// Classe représentant un type de pari 'Nombre' héritant de la classe Bet
public class Nombre extends Bet {

    // Attribut privé pour stocker le choix du joueur
    private String choixJoueur;

    // Constructeur prenant une description et une mise en paramètres
    public Nombre(String description, int mise) {
        super(description, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = ""; // Initialisation du choix du joueur à une chaîne vide
    }

    // Méthode pour vérifier si le pari est gagné en comparant avec le numéro tiré
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        // Vérification si le numéro tiré est égal au choix du joueur converti en entier
        return numeroTire.getValeur() == Integer.parseInt(choixJoueur);
    }

    // Méthode pour afficher le pari en demandant au joueur de choisir un nombre entre 0 et 36
    @Override
    public void afficherBet() {
        // Appel d'une méthode saisirPlageMinMax() pour obtenir le nombre choisi par le joueur
        choixJoueur = Integer.toString(Interaction.saisirPlageMinMax("Veuillez choisir un nombre entre", 0, 36));
    }

    // Méthode getter pour récupérer le choix du joueur
    public String getChoixJoueur() {
        return choixJoueur;
    }
}
