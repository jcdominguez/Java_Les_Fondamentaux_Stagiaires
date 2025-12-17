package com.demo;

import java.util.ArrayList;

/**
 * Classe d'execution pour la Partie 4.
 * Demontre le polymorphisme, upcasting, downcasting et equals().
 */
public class RunPartie4 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 4 ===\n");

        // Creation de la flotte (ArrayList de Vehicule)
        ArrayList<Vehicule> flotte = new ArrayList<>();

        // UPCASTING : on ajoute des types specifiques dans une liste de Vehicule
        System.out.println("--- Ajout de vehicules (Upcasting) ---");
        flotte.add(new Voiture("AA-123-BB", "Renault", "Clio", 2022,
                TypeMoteur.ESSENCE, 90, 6.5, 45.0, 5, true));
        flotte.add(new Moto("CC-456-DD", "Yamaha", "MT-07", 2023,
                TypeMoteur.ESSENCE, 75, 4.2, 35.0, 689, "Roadster"));
        flotte.add(new Camion("EE-789-FF", "Mercedes", "Actros", 2021,
                TypeMoteur.DIESEL, 400, 28.0, 120.0, 20.0, true));
        System.out.println("3 vehicules ajoutes a la flotte.\n");

        // POLYMORPHISME : parcours avec appels polymorphiques
        System.out.println("--- Parcours polymorphique ---");
        for (Vehicule v : flotte) {
            System.out.println(v);
            v.demarrer();
            System.out.println();
        }

        // INSTANCEOF et DOWNCASTING
        System.out.println("--- Downcasting avec instanceof ---");
        for (Vehicule v : flotte) {
            System.out.print(v.getDescription() + " -> ");
            if (v instanceof Voiture) {
                Voiture voiture = (Voiture) v;
                System.out.println("C'est une voiture avec " +
                        voiture.getNombrePortes() + " portes");
            } else if (v instanceof Moto) {
                Moto moto = (Moto) v;
                System.out.println("C'est une moto de " +
                        moto.getCylindree() + "cc (" + moto.getTypeMoto() + ")");
            } else if (v instanceof Camion) {
                Camion camion = (Camion) v;
                System.out.println("C'est un camion de " +
                        camion.getCapaciteTonnes() + " tonnes");
            }
        }

        // EQUALS : comparaison par immatriculation
        System.out.println("\n--- Test equals() ---");
        Vehicule v1 = flotte.get(0);
        Vehicule v2 = new Voiture("AA-123-BB", "Autre", "Marque", 2020,
                TypeMoteur.DIESEL, 100, 8.0, 50.0, 3, false);
        Vehicule v3 = new Voiture("ZZ-999-ZZ", "Renault", "Clio", 2022,
                TypeMoteur.ESSENCE, 90, 6.5, 45.0, 5, true);

        System.out.println("v1: " + v1.getImmatriculation());
        System.out.println("v2: " + v2.getImmatriculation());
        System.out.println("v3: " + v3.getImmatriculation());
        System.out.println("v1.equals(v2) [meme immat] : " + v1.equals(v2));
        System.out.println("v1.equals(v3) [immat diff] : " + v1.equals(v3));

        System.out.println("\n=== PARTIE 4 TERMINEE ===");
    }
}
