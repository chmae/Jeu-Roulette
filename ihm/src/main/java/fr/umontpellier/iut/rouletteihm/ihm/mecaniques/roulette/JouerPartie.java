package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets.*;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette.bets.Double;
import fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils.Interaction;

import java.util.ArrayList;

//CLasse qui permet de jouer une partie de roulette
public class JouerPartie {

    // Attributs privés de la classe JouerPartie
    private static final String nomJeu = "roulette";

    // Tableau de paris possibles
    private final Bet[] bets = {
            new RougeNoir("Rouge ou Noir", 2),
            new PairImpair("Pair ou impair", 2),
            new Nombre("Choisir un nombre", 36),
            new Douzaine("Douzaine", 3),
            new Colonne("Colonne", 3),
            new ManquePasse("Manque ou Passe", 2),
            new Carre("Carre", 9),
            new Double("Double", 18),
//            new Transversale("Transversale", 12),
//            new Sixain("Sixain", 6),
    };

    // Attributs privés de la classe JouerPartie
    private final Roulette roulette;
    private Roulette.resultatTour resultatTour;


    // Constructeur de la classe JouerPartie
    public JouerPartie() {
        roulette = new Roulette();
    }

    // Méthode pour obtenir le nom du jeu
    public void setResultatTour(Roulette.resultatTour resultatTour) {
        this.resultatTour = resultatTour;
    }

    // Méthode pour obtenir le résultat du tour de roulette
    public void jouerOuPasser(Joueur joueur) {
        String jouerOuAttendre = Interaction.saisirParmiChoix("Voulez-vous jouer ou passer ? ", "jouer", "passer");
        if (jouerOuAttendre.equals("passer")) {
            passerTour();
        } else {
            jouer(joueur);
        }
    }

    // Méthode pour passer le tour
    public void passerTour() {
        System.out.println("\nVous avez choisi de passer votre tour.");
        System.out.println("La roulette commence à tourner !");

        //sleep durant 5s
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("La roulette s'est arrêtée !");
        Roulette.resultatTour resultatTour = roulette.resultatTour();
        System.out.println("Le numéro tiré est : " + resultatTour.getValeur() + " " + resultatTour.getCouleur());
    }

    // Méthode pour miser un certain nombre de jetons
    public int miser(Joueur joueur) {
        int mise = Interaction.saisirPlageMinMax("Veuillez saisir votre mise :", 1, joueur.getMonArgent());
        joueur.miseAJourBanque(-mise);
        return mise;
    }

    // Méthode pour choisir un pari
    public Bet choisirBet() {
        System.out.println("\nVeuillez choisir un pari parmis les suivants :");
        for (int i = 0; i < bets.length; i++) {
            System.out.println((i + 1) + " - " + bets[i].getBetType());
        }
        int choix = Interaction.saisirPlageMinMax("Votre choix :", 1, bets.length);
        return bets[choix - 1];
    }

    // Méthode pour calculer le gain par pari
    public int[] gainParBet(ArrayList<Bet> bets, ArrayList<Integer> mise) {
        int[] gainTotaux = new int[bets.size()];
        for (int i = 0; i < bets.size(); i++) {
            if (bets.get(i).gagne(resultatTour)) {
                gainTotaux[i] = bets.get(i).paiement(mise.get(i));
            }
        }
        return gainTotaux;
    }

    // Méthode pour jouer une partie de roulette
    public void jouer(Joueur joueur) {
        System.out.println("\nVous avez choisi de jouer !");
        ArrayList<Bet> bets = new ArrayList<>();
        ArrayList<Integer> mises = new ArrayList<>();
        String continuerDeMiserOuLancer = "";
        do {
            int mise = miser(joueur);
            mises.add(mise);
            Bet bet = choisirBet();
            bet.afficherBet();
            bets.add(bet);
            continuerDeMiserOuLancer = Interaction.saisirParmiChoix("Êtes-vous prêt ou voulez-vous miser encore ?", "prêt", "miser");
            if (continuerDeMiserOuLancer.equals("miser") && joueur.getMonArgent() == 0) {
                System.out.println("Vous n'avez plus d'argent !");
                continuerDeMiserOuLancer = "prêt";
            }
        } while (continuerDeMiserOuLancer.equals("miser"));

        //affiche les mises et les bets
        System.out.println("\nVous avez misé :");
        for (int i = 0; i < bets.size(); i++) {
            System.out.println(mises.get(i) + " jetons sur " + bets.get(i).getBetType());
        }

        System.out.println("\nLa roulette commence à tourner !\n");
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(2000);
                System.out.println("...");
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nLa roulette s'est arrêtée !");
        setResultatTour(roulette.resultatTour());
        System.out.println("Le numéro tiré est : " + resultatTour.getValeur() + " " + resultatTour.getCouleur());
        int[] gainTotaux = gainParBet(bets, mises);
        for (int i = 0; i < gainTotaux.length; i++) {
            if (gainTotaux[i] == 0) {
                System.out.println("Vous avez perdu !\n");
                joueur.miseAJourBanque(gainTotaux[i]);
            } else {
                System.out.println("Vous avez gagné " + gainTotaux[i] + " jetons pour le pari " + bets.get(i).getBetType() + " !\n");
                joueur.miseAJourBanque(gainTotaux[i]);
            }
        }
    }


}