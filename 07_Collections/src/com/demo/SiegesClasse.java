package com.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercice 3 : Gerer l'attribution des sieges aux eleves
 * Utilisation de HashMap pour associer numero de siege -> nom de l'eleve
 */
public class SiegesClasse {
    public static void main(String[] args) {
        HashMap<Integer, String> sieges = new HashMap<>();

        // Attribution des sieges
        System.out.println("Attribution des sieges...");

        sieges.put(1, "Jean Dupont");
        System.out.println("Siege 1 -> Jean Dupont");

        sieges.put(2, "Marie Martin");
        System.out.println("Siege 2 -> Marie Martin");

        sieges.put(5, "Paul Durand");
        System.out.println("Siege 5 -> Paul Durand");

        sieges.put(3, "Sophie Bernard");
        System.out.println("Siege 3 -> Sophie Bernard");

        // Afficher tous les sieges attribues
        System.out.println();
        System.out.println("Liste des sieges attribues :");
        for (Map.Entry<Integer, String> entry : sieges.entrySet()) {
            System.out.println("Siege " + entry.getKey() + " : " + entry.getValue());
        }

        // Rechercher qui est au siege 3
        System.out.println();
        String eleve3 = sieges.get(3);
        System.out.println("Qui est au siege 3 ? " + eleve3);

        // Verifier si le siege 4 est attribue
        if (sieges.containsKey(4)) {
            System.out.println("Le siege 4 est occupe par " + sieges.get(4));
        } else {
            System.out.println("Le siege 4 est libre.");
        }
    }
}
