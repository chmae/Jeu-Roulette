package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

public abstract class Bet {
    private final String betType;
    private int gain;
    private final String choixJoueur;

    public Bet(String betType, int gain) {
        this.betType = betType;
        this.gain = gain;
        this.choixJoueur = "";
    }

    public String getBetType() {
        return betType;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public double recompense(int mise) {
        return gain * mise;
    }

    public int paiement(int mise) {
        return (gain * mise);
    }

    public abstract boolean gagne(Roulette.resultatTour numeroTire);

    public abstract void afficherBet();
}
