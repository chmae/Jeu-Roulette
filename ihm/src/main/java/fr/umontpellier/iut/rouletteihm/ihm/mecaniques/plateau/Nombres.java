package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.plateau;

public class Nombres {

    private int nombre;
    private String couleur;
    private boolean pair;
    private boolean demi;
    private int section;
    private int colonne;
    private int angleRoulette;

    public Nombres(int num, String col, int angle) {
        this.nombre = num;
        this.couleur = col;
        this.pair = pair(num);
        this.demi = firstEighteen(num);
        this.section = section(num);
        this.colonne = column(num);
        this.angleRoulette = angle;
    }

    public int getNumber() {
        return nombre;
    }

    public String getColor() {
        return couleur;
    }

    public boolean getOdd() {
        return pair;
    }

    public boolean getfirstEighteen() {
        return demi;
    }

    public int getSection() {
        return section;
    }

    public int getColumn() {
        return colonne;
    }

    public int getAngle() {
        return angleRoulette;
    }

    private boolean pair(int num) {
        if (num % 2 == 0) {
            return false;
        }
        return true;
    }

    private boolean firstEighteen(int num) {
        for (int i = 1; i < 19; i++) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

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