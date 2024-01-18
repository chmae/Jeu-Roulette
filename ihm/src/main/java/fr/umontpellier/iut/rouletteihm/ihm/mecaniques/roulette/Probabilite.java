// Probabilite.java
package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import java.util.ArrayList;

/**
 * Classe permettant de calculer la probabilité d'un nombre
 * en fonction de la seed
 */
public class Probabilite {

    // Constantes pour le calcul de la seed (voir la méthode nextRandom)
    private static final long multiplier = 1664525;
    private static final long increment = 1013904223;
    private static final long modulos = (long) Math.pow(2, 32);

    private long seed;
    private ArrayList<Integer> tabDe5;


    /**
     * Constructeur de la classe Probabilite
     *
     * @param seed la seed à utiliser pour le calcul
     */
    public Probabilite(long seed) {
        this.seed = seed;
        tabDe5 = new ArrayList<>();
    }


    /**
     * Méthode permettant de calculer le prochain nombre aléatoire
     *
     * @param min le minimum du nombre aléatoire
     * @param max le maximum du nombre aléatoire
     * @return le nombre aléatoire
     */
    public int nextRandom(int min, int max) {
        seed = (multiplier * seed + increment) % modulos;
        tabDe5.add((int) (seed % (max - min + 1)) + min);
        return (int) (seed % (max - min + 1)) + min;
    }


    /**
     * Méthode permettant de calculer la probabilité d'un nombre
     *
     * @param min   le minimum du nombre aléatoire
     * @param max   le maximum du nombre aléatoire
     * @param value le nombre dont on veut calculer la probabilité
     * @return la probabilité du nombre
     */
    public double getProbability(int min, int max, int value) {
        int count = 0;
        int iterations = 1;

        for (int i = 0; i < iterations; i++) {
            int randomValue = nextRandom(min, max);
            if (randomValue == value) {
                count++;
            }
        }

        return (double) count / iterations;
    }
}
