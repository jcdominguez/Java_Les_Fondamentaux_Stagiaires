package com.demo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exercice 1 : Calculer la moyenne des notes d'une classe
 * Utilisation de ArrayList pour stocker un nombre variable de notes
 */
public class MoyenneClasse {
    public static void main(String[] args) {
        ArrayList<Double> notes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Boucle de saisie des notes
        while (true) {
            System.out.print("Entrez une note (0-20) ou -1 pour terminer : ");
            double note = scanner.nextDouble();

            if (note == -1) {
                break;
            }

            if (note < 0 || note > 20) {
                System.out.println("Note invalide, veuillez entrer une note entre 0 et 20");
                continue;
            }

            notes.add(note);
        }

        // Affichage des resultats
        System.out.println();
        System.out.println("Notes saisies : " + notes);

        if (notes.isEmpty()) {
            System.out.println("Aucune note saisie.");
        } else {
            // Calcul de la moyenne
            double somme = 0;
            for (Double n : notes) {
                somme += n;
            }
            double moyenne = somme / notes.size();
            System.out.printf("Moyenne de la classe : %.2f%n", moyenne);
        }

        scanner.close();
    }
}
