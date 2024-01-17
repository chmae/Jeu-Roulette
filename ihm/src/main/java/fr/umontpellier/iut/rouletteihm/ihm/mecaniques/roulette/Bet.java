package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

// Classe abstraite représentant un type de pari
public abstract class Bet {

    // Attributs privés de la classe Bet
    private final String betType; // Type de pari
    private int gain; // Gain potentiel du pari
    private final String choixJoueur; // Choix du joueur pour le pari

    // Constructeur de la classe Bet prenant le type de pari et le gain en paramètres
    public Bet(String betType, int gain) {
        this.betType = betType; // Initialisation du type de pari
        this.gain = gain; // Initialisation du gain potentiel
        this.choixJoueur = ""; // Initialisation du choix du joueur
    }

    // Méthode pour obtenir le type de pari
    public String getBetType() {
        return betType;
    }

    // Méthode pour obtenir le gain potentiel du pari
    public double getGain() {
        return gain;
    }

    // Méthode pour définir le gain potentiel du pari
    public void setGain(int gain) {
        this.gain = gain;
    }

    // Méthode pour calculer la récompense en fonction de la mise
    public double recompense(int mise) {
        return gain * mise;
    }

    // Méthode pour calculer le paiement en fonction de la mise
    public int paiement(int mise) {
        return (gain * mise);
    }

    // Méthode abstraite pour déterminer si le pari est gagné en fonction du résultat du tour de roulette
    public abstract boolean gagne(Roulette.resultatTour numeroTire);

    // Méthode abstraite pour afficher le pari (à implémenter dans les sous-classes)
    public abstract void afficherBet();
}