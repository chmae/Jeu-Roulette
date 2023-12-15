// Probabilite.java
package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.roulette;

import java.util.ArrayList;

public class Probabilite {
    private static final long multiplier = 1664525;
    private static final long increment = 1013904223;
    private static final long modulos = (long) Math.pow(2, 32);

    private long seed;
    private ArrayList<Integer> tabDe5;

    public Probabilite(long seed) {
        this.seed = seed;
        tabDe5 = new ArrayList<>();
    }

    public int nextRandom(int min, int max) {
        seed = (multiplier * seed + increment) % modulos;
        tabDe5.add((int) (seed % (max - min + 1)) + min);
        return (int) (seed % (max - min + 1)) + min;
    }

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
