package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.etatsJeu;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Joueur;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.Roulette;

// Classe représentant l'état d'initialisation des joueurs dans le jeu
public class InitialisationJoueurs extends EtatJeu {

    // Constructeur prenant une instance de Roulette et un joueur en paramètres
    public InitialisationJoueurs(Roulette roulette, Joueur joueur) {
        super(roulette); // Appel du constructeur de la classe parente EtatJeu
        initialiserJoueur(roulette, joueur); // Appel de la méthode initialiserJoueur
    }

    // Méthode privée pour initialiser le joueur dans la roulette
    private void initialiserJoueur(Roulette roulette, Joueur joueur) {
        roulette.setJoueur(joueur); // Définition du joueur dans la roulette
    }
}


