package com.demo;

/**
 * Classe d'execution pour la Partie 2.
 * Verifie que l'interface et la classe abstraite compilent correctement.
 */
public class RunPartie2 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 2 ===\n");

        // On ne peut PAS instancier une classe abstraite
        // Vehicule v = new Vehicule(...);  // ERREUR de compilation !

        System.out.println("Interface Louable : OK");
        System.out.println("Classe abstraite Vehicule : OK");
        System.out.println("(Impossible d'instancier Vehicule directement)");

        System.out.println("\n=== PARTIE 2 TERMINEE ===");
        System.out.println("Passez a la Partie 3 pour creer les sous-classes.");
    }
}
