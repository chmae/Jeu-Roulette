package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau;

// Classe représentant les nombres sur le plateau de la roulette
public class Nombres {

    // Déclaration des attributs pour stocker les informations sur les nombres
    private int nombre; // Numéro du nombre
    private String couleur; // Couleur associée
    private boolean pair; // Booléen indiquant si le nombre est pair
    private boolean demi; // Booléen indiquant si le nombre est dans la première moitié des nombres (1-18)
    private int section; // Numéro de section
    private int colonne; // Numéro de colonne
    private int angleRoulette; // Angle sur la roulette

    // Constructeur prenant des paramètres pour initialiser les attributs
    public Nombres(int num, String col, int angle) {
        this.nombre = num;
        this.couleur = col;
        this.pair = pair(num);
        this.demi = firstEighteen(num);
        this.section = section(num);
        this.colonne = column(num);
        this.angleRoulette = angle;
    }

    // Méthode pour obtenir le numéro du nombre
    public int getNumber() {
        return nombre;
    }

    // Méthode pour obtenir la couleur associée au nombre
    public String getColor() {
        return couleur;
    }

    // Méthode pour vérifier si le nombre est pair
    public boolean getOdd() {
        return pair;
    }

    // Méthode pour vérifier si le nombre est dans la première moitié (1-18)
    public boolean getfirstEighteen() {
        return demi;
    }

    // Méthode pour obtenir le numéro de section du nombre
    public int getSection() {
        return section;
    }

    // Méthode pour obtenir le numéro de colonne du nombre
    public int getColumn() {
        return colonne;
    }

    // Méthode pour obtenir l'angle sur la roulette du nombre
    public int getAngle() {
        return angleRoulette;
    }

    // Méthode privée pour vérifier si le nombre est pair
    private boolean pair(int num) {
        if (num % 2 == 0) {
            return false;
        }
        return true;
    }

    // Méthode privée pour vérifier si le nombre est dans la première moitié (1-18)
    private boolean firstEighteen(int num) {
        for (int i = 1; i < 19; i++) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    // Méthode privée pour déterminer la section du nombre
    private int section(int num) {
        for (int i = 1; i < 13; i++) {
            if (i == num) {
                return 1;
            }
        }
        for (int i = 13; i < 25; i++) {
            if (i == num) {
                return 2;
            }
        }
        return 3;
    }

    // Méthode privée pour déterminer la colonne du nombre
    private int column(int num) {
        for (int i = 1; i < 35; i += 3) {
            if (i == num) {
                return 1;
            }
        }
        for (int i = 2; i < 36; i += 3) {
            if (i == num) {
                return 2;
            }
        }
        return 3;
    }
}
