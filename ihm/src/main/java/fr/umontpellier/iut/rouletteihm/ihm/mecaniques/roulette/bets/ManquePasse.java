package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;

// Classe représentant un type de pari 'ManquePasse' héritant de la classe Bet
public class ManquePasse extends Bet {

    // Attributs privés pour stocker les choix du joueur et les listes de numéros
    private String choixJoueur;
    private final ArrayList<Integer> listeUnDixHuit = new ArrayList<Integer>(18);
    private final ArrayList<Integer> listeDixNeufTrenteSix = new ArrayList<Integer>(18);

    // Constructeur prenant une description et une mise en paramètres
    public ManquePasse(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = ""; // Initialisation du choix du joueur à une chaîne vide

        // Initialisation des listes représentant les numéros de '1-18' et '19-36'
        for (int i = 1; i <= 18; i++) {
            listeUnDixHuit.add(i); // Liste des numéros '1-18'
        }
        for (int i = 19; i <= 36; i++) {
            listeDixNeufTrenteSix.add(i); // Liste des numéros '19-36'
        }
    }

    // Méthode pour vérifier si le pari est gagné en comparant avec le résultat du tour
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        int numero = numeroTire.getValeur(); // Récupération du numéro tiré

        // Vérification si le numéro tiré appartient au groupe choisi par le joueur
        if ((numero >= 1 && numero <= 18) && choixJoueur.equals("1-18")) {
            return true;
        } else return (numero >= 19 && numero <= 36) && choixJoueur.equals("19-36");
    }

    // Méthode pour afficher le pari en demandant au joueur de choisir un groupe
    @Override
    public void afficherBet() {
        // Appel d'une méthode (supposée) saisirParmiChoix() pour obtenir le groupe choisi par le joueur
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir un ManquePasse", "1-18", "19-36");
    }
}
