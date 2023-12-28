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

    // Constructeur par défaut
    public Nombres() {
    }

    // Constructeur prenant des paramètres pour initialiser les attributs
    public Nombres(int num, String col, int angle) {
        this.nombre = num;
        this.couleur = col;
        this.pair = pair(num); // Vérification si le nombre est pair
        this.demi = firstEighteen(num); // Vérification si le nombre est dans la première moitié (1-18)
        this.section = section(num); // Détermination de la section du nombre
        this.colonne = column(num); // Détermination de la colonne du nombre
        this.angleRoulette = angle; // Attribution de l'angle sur la roulette
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
        return num % 2 == 0; // Retourne vrai si le nombre est pair
    }

    // Méthode privée pour vérifier si le nombre est dans la première moitié (1-18)
    private boolean firstEighteen(int num) {
        return num >= 1 && num <= 18; // Retourne vrai si le nombre est entre 1 et 18 inclus
    }

    // Méthode privée pour déterminer la section du nombre
    private int section(int num) {
        if (num >= 1 && num <= 12) {
            return 1; // Section 1 : 1 à 12
        } else if (num >= 13 && num <= 24) {
            return 2; // Section 2 : 13 à 24
        } else {
            return 3; // Section 3 : 25 à 36
        }
    }

    // Méthode privée pour déterminer la colonne du nombre
    private int column(int num) {
        if (num % 3 == 1) {
            return 1; // Colonne 1
        } else if (num % 3 == 2) {
            return 2; // Colonne 2
        } else {
            return 3; // Colonne 3
        }
    }
}
