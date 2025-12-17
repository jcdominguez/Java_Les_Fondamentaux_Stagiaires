package com.demo;

/**
 * Classe d'execution pour la Partie 1.
 * Teste TypeMoteur et Moteur.
 */
public class RunPartie1 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 1 ===\n");

        // Test TypeMoteur
        System.out.println("--- Test TypeMoteur ---");
        TypeMoteur essence = TypeMoteur.ESSENCE;
        TypeMoteur electrique = TypeMoteur.ELECTRIQUE;

        System.out.println("Type 1 : " + essence);
        System.out.println("Type 2 : " + electrique);

        // Parcours de toutes les valeurs
        System.out.println("\nTous les types de moteur :");
        for (TypeMoteur type : TypeMoteur.values()) {
            System.out.println("- " + type);
        }

        // Test Moteur
        System.out.println("\n--- Test Moteur ---");
        Moteur m1 = new Moteur(TypeMoteur.DIESEL, 150, 7.5);
        Moteur m2 = new Moteur(TypeMoteur.ELECTRIQUE, 204, 18.0);

        System.out.println(m1);
        System.out.println(m2);

        // Test des getters
        System.out.println("\n--- Test Getters ---");
        System.out.println("Type de m1 : " + m1.getType());
        System.out.println("Puissance de m1 : " + m1.getPuissance() + " ch");
        System.out.println("Consommation de m1 : " + m1.getConsommation() + " L/100km");

        System.out.println("\n=== PARTIE 1 TERMINEE ===");
    }
}
