package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Bet;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;

// Classe représentant un type de pari 'Douzaine' héritant de la classe Bet
public class Douzaine extends Bet {

    // Attributs privés pour stocker les choix du joueur et les numéros des douzaines
    private String choixJoueur;
    private final ArrayList<Integer> premiereDouzaine = new ArrayList<Integer>(12);
    private final ArrayList<Integer> deuxiemeDouzaine = new ArrayList<Integer>(12);
    private final ArrayList<Integer> troisiemeDouzaine = new ArrayList<Integer>(12);

    // Constructeur prenant une description et une mise en paramètres
    public Douzaine(String desc, int mise) {
        super(desc, mise); // Appel du constructeur de la classe parente Bet
        choixJoueur = ""; // Initialisation du choix du joueur à une chaîne vide

        // Initialisation des listes représentant les numéros des différentes douzaines
        for (int i = 1; i <= 12; i++) {
            premiereDouzaine.add(i); // Première douzaine de numéros (1-12)
        }
        for (int i = 13; i <= 24; i++) {
            deuxiemeDouzaine.add(i); // Deuxième douzaine de numéros (13-24)
        }
        for (int i = 25; i <= 36; i++) {
            troisiemeDouzaine.add(i); // Troisième douzaine de numéros (25-36)
        }
    }

    // Méthode pour vérifier si le pari est gagné en comparant avec le résultat du tour
    @Override
    public boolean gagne(Roulette.resultatTour numeroTire) {
        int numero = numeroTire.getValeur(); // Récupération du numéro tiré

        // Vérification si le numéro tiré appartient à la douzaine choisie par le joueur
        if ((numero >= 1 && numero <= 12) && choixJoueur.equals("1-12")) {
            return true;
        } else if ((numero >= 13 && numero <= 24) && choixJoueur.equals("13-24")) {
            return true;
        } else return (numero >= 25 && numero <= 36) && choixJoueur.equals("25-36");
    }

    // Méthode pour afficher le pari en demandant au joueur de choisir une douzaine
    @Override
    public void afficherBet() {
        // Appel d'une méthode (supposée) saisirParmiChoix() pour obtenir la douzaine choisie par le joueur
        choixJoueur = Interaction.saisirParmiChoix("Veuillez choisir une douzaine", "1-12", "13-24", "25-36");
    }
}