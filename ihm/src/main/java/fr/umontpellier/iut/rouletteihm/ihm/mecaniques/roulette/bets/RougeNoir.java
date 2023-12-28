package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

// Classe représentant un type de pari 'RougeNoir' héritant de la classe Bet
public class RougeNoir extends Bet {

    // Attribut privé pour stocker le choix du joueur
    private String choixJoueur;

    // Constructeur prenant une description et une mise en paramètres
    public RougeNoir(String description, int mise) {
        super(description, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = ""; // Initialisation du choix du joueur à une chaîne vide
    }

    // Méthode pour vérifier si le pari est gagné en fonction de la couleur tirée et du choix du joueur
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        // Vérification si la couleur tirée correspond au choix du joueur
        return numeroTire.getCouleur().equals(choixJoueur);
    }

    // Méthode pour afficher le pari en demandant au joueur de choisir entre 'Rouge' ou 'Noir'
    @Override
    public void afficherBet() {
        // Appel d'une méthode saisirParmiChoix() pour obtenir le choix de couleur 'Rouge' ou 'Noir' du joueur
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir une couleur", "Rouge", "Noir");
    }
}