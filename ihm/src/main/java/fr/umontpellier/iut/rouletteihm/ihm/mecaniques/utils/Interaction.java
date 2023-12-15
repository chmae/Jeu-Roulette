package fr.umontpellier.iut.rouletteihm.ihm.mecaniques.utils;

import java.io.InputStreamReader;
import java.util.*;

public class Interaction {

    //Initialisation des variables
    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    //Actions & méthodes

    public static String saisirChaine(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public static int saisirEntier(String prompt) {
        int resultat = Integer.MIN_VALUE;
        do {
            try {
                resultat = Integer.parseInt(saisirChaine(prompt));
            } catch (NumberFormatException e) {
                // attendre que l'utilisateur entre un nombre
            }
        }
        while (resultat == Integer.MIN_VALUE);
        return resultat;
    }

    //Méthode pour demander à l'utilisateur de choisir un nombre entre 0 et 36
    public static int saisirPlageMinMax(String prompt, int min, int max) {
        String message = String.format("\n%s %d et %d\n", prompt, min, max);
        int resultat;
        do {
            resultat = saisirEntier(message);
        }
        while (resultat < min || resultat > max);
        return resultat;
    }

    //Méthode pour demander à l'utilisateur de choisir un type de pari
    public static String saisirParmiChoix(String prompt, String... betTypes) {
        String message = String.format("\n%s\nChoissiez entre : %s\n", prompt, Arrays.toString(betTypes));
        Set<String> betTypesSet = new TreeSet<>(Arrays.asList(betTypes));
        String resultat;
        do {
            resultat = saisirChaine(message);
        } while (!betTypesSet.contains(resultat));
        return resultat;
    }
}

