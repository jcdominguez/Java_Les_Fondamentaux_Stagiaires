package com.demo;

/**
 * Classe d'execution pour la Partie 3.
 * Teste les sous-classes Voiture, Moto et Camion.
 */
public class RunPartie3 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 3 ===\n");

        // Creation d'une Voiture
        System.out.println("--- Test Voiture ---");
        Voiture voiture = new Voiture(
            "AA-123-BB", "Renault", "Clio", 2022,
            TypeMoteur.ESSENCE, 90, 6.5, 45.0,
            5, true
        );
        System.out.println(voiture);
        voiture.demarrer();
        System.out.println("Type: " + voiture.getTypeVehicule());
        System.out.println("Tarif 5 jours: " + voiture.calculerTarif(5) + " EUR");

        // Creation d'une Moto
        System.out.println("\n--- Test Moto ---");
        Moto moto = new Moto(
            "CC-456-DD", "Yamaha", "MT-07", 2023,
            TypeMoteur.ESSENCE, 75, 4.2, 35.0,
            689, "Roadster"
        );
        System.out.println(moto);
        moto.demarrer();
        System.out.println("Type: " + moto.getTypeVehicule());

        // Creation d'un Camion
        System.out.println("\n--- Test Camion ---");
        Camion camion = new Camion(
            "EE-789-FF", "Mercedes", "Actros", 2021,
            TypeMoteur.DIESEL, 400, 28.0, 120.0,
            20.0, true
        );
        System.out.println(camion);
        camion.demarrer();
        System.out.println("Type: " + camion.getTypeVehicule());

        System.out.println("\n=== PARTIE 3 TERMINEE ===");
    }
}
