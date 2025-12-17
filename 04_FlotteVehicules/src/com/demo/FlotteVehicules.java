package com.demo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principale de l'application de gestion de flotte.
 * Demontre l'integration de tous les concepts POO.
 */
public class FlotteVehicules {
    private ArrayList<Vehicule> flotte;
    private ArrayList<Reservation> reservations;
    private Scanner scanner;

    public FlotteVehicules() {
        this.flotte = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initialiserFlotte();
    }

    /**
     * Initialise la flotte avec quelques vehicules de demonstration.
     */
    private void initialiserFlotte() {
        // Upcasting : on ajoute des types specifiques dans ArrayList<Vehicule>
        flotte.add(new Voiture("AA-123-BB", "Renault", "Clio", 2022,
                TypeMoteur.ESSENCE, 90, 6.5, 45.0, 5, true));
        flotte.add(new Voiture("BB-234-CC", "Peugeot", "308", 2023,
                TypeMoteur.DIESEL, 130, 5.2, 55.0, 5, true));
        flotte.add(new Voiture("CC-345-DD", "Tesla", "Model 3", 2024,
                TypeMoteur.ELECTRIQUE, 283, 14.0, 85.0, 5, true));
        flotte.add(new Moto("DD-456-EE", "Yamaha", "MT-07", 2023,
                TypeMoteur.ESSENCE, 75, 4.2, 35.0, 689, "Roadster"));
        flotte.add(new Moto("EE-567-FF", "BMW", "R1250GS", 2022,
                TypeMoteur.ESSENCE, 136, 5.1, 65.0, 1254, "Trail"));
        flotte.add(new Camion("FF-678-GG", "Mercedes", "Actros", 2021,
                TypeMoteur.DIESEL, 400, 28.0, 120.0, 20.0, true));
        flotte.add(new Camion("GG-789-HH", "Iveco", "Daily", 2023,
                TypeMoteur.DIESEL, 180, 12.0, 80.0, 3.5, false));
    }

    /**
     * Affiche le menu principal.
     */
    private void afficherMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Afficher l'inventaire complet");
        System.out.println("2. Rechercher par type de vehicule");
        System.out.println("3. Afficher les vehicules disponibles");
        System.out.println("4. Creer une reservation");
        System.out.println("5. Afficher les reservations");
        System.out.println("6. Comparer deux vehicules");
        System.out.println("7. Statistiques de la flotte");
        System.out.println("8. Quitter");
        System.out.print("Votre choix : ");
    }

    /**
     * Affiche l'inventaire complet (polymorphisme sur toString).
     */
    private void afficherInventaire() {
        System.out.println("\n=== INVENTAIRE COMPLET ===");
        int i = 1;
        for (Vehicule v : flotte) {
            String statut = v.getDisponible() ? "Disponible" : "Loue";
            System.out.println(i + ". " + v.getDescription() + " - " + statut +
                    " - " + String.format("%.2f", v.getTarifJournalier()) + " EUR/jour");
            i++;
        }
    }

    /**
     * Recherche par type de vehicule (downcasting avec instanceof).
     */
    private void rechercherParType() {
        System.out.println("\n=== RECHERCHER PAR TYPE ===");
        System.out.println("1. Voitures");
        System.out.println("2. Motos");
        System.out.println("3. Camions");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        System.out.println();
        for (Vehicule v : flotte) {
            if (choix == 1 && v instanceof Voiture) {
                Voiture voit = (Voiture) v;  // Downcasting
                System.out.println("- " + v.getDescription() + " | " +
                        voit.getNombrePortes() + " portes | " +
                        (v.getDisponible() ? "Disponible" : "Loue"));
            } else if (choix == 2 && v instanceof Moto) {
                Moto moto = (Moto) v;
                System.out.println("- " + v.getDescription() + " | " +
                        moto.getCylindree() + "cc " + moto.getTypeMoto() + " | " +
                        (v.getDisponible() ? "Disponible" : "Loue"));
            } else if (choix == 3 && v instanceof Camion) {
                Camion cam = (Camion) v;
                System.out.println("- " + v.getDescription() + " | " +
                        cam.getCapaciteTonnes() + "T | " +
                        (v.getDisponible() ? "Disponible" : "Loue"));
            }
        }
    }

    /**
     * Affiche les vehicules disponibles.
     */
    private void afficherDisponibles() {
        System.out.println("\n=== VEHICULES DISPONIBLES ===");
        int i = 1;
        for (Vehicule v : flotte) {
            if (v.getDisponible()) {
                System.out.println(i + ". " + v.getDescription() + " | " +
                        v.getTypeVehicule() + " | " +
                        String.format("%.2f", v.getTarifJournalier()) + " EUR/jour");
            }
            i++;
        }
    }

    /**
     * Cree une reservation (association + polymorphisme).
     */
    private void creerReservation() {
        System.out.println("\n=== CREER UNE RESERVATION ===");

        // Afficher vehicules disponibles
        afficherDisponibles();

        System.out.print("\nNumero du vehicule : ");
        int numVehicule = scanner.nextInt();
        scanner.nextLine();

        if (numVehicule < 1 || numVehicule > flotte.size()) {
            System.out.println("Numero invalide.");
            return;
        }

        Vehicule vehicule = flotte.get(numVehicule - 1);
        if (!vehicule.getDisponible()) {
            System.out.println("Ce vehicule n'est pas disponible.");
            return;
        }

        System.out.print("Nom du client : ");
        String nom = scanner.nextLine();
        System.out.print("Telephone : ");
        String tel = scanner.nextLine();
        System.out.print("Nombre de jours : ");
        int nbJours = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Date de debut (JJ/MM/AAAA) : ");
        String date = scanner.nextLine();

        // Creation des objets (association)
        Client client = new Client(nom, tel);
        Reservation reservation = new Reservation(vehicule, client, nbJours, date);
        reservations.add(reservation);

        System.out.println("\n--- Reservation creee ---");
        System.out.println(reservation);
        vehicule.demarrer();  // Polymorphisme
    }

    /**
     * Affiche les reservations.
     */
    private void afficherReservations() {
        System.out.println("\n=== RESERVATIONS ===");
        if (reservations.isEmpty()) {
            System.out.println("Aucune reservation.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    /**
     * Compare deux vehicules (utilise equals).
     */
    private void comparerVehicules() {
        System.out.println("\n=== COMPARER DEUX VEHICULES ===");
        afficherInventaire();

        System.out.print("\nNumero du premier vehicule : ");
        int num1 = scanner.nextInt();
        System.out.print("Numero du second vehicule : ");
        int num2 = scanner.nextInt();
        scanner.nextLine();

        if (num1 < 1 || num1 > flotte.size() || num2 < 1 || num2 > flotte.size()) {
            System.out.println("Numero(s) invalide(s).");
            return;
        }

        Vehicule v1 = flotte.get(num1 - 1);
        Vehicule v2 = flotte.get(num2 - 1);

        System.out.println("\n--- Comparaison ---");
        System.out.println("Vehicule 1 : " + v1.getDescription());
        System.out.println("Vehicule 2 : " + v2.getDescription());
        System.out.println("Meme vehicule (equals) ? " + v1.equals(v2));

        // Comparaison des tarifs
        System.out.println("\nComparaison des tarifs pour 7 jours :");
        System.out.println("- " + v1.getDescription() + " : " +
                String.format("%.2f", v1.calculerTarif(7)) + " EUR");
        System.out.println("- " + v2.getDescription() + " : " +
                String.format("%.2f", v2.calculerTarif(7)) + " EUR");
    }

    /**
     * Affiche les statistiques (instanceof et comptage).
     */
    private void afficherStatistiques() {
        System.out.println("\n=== STATISTIQUES DE LA FLOTTE ===");

        int nbVoitures = 0, nbMotos = 0, nbCamions = 0;
        int nbDisponibles = 0;
        Double totalTarifs = 0.0;

        for (Vehicule v : flotte) {
            if (v instanceof Voiture) nbVoitures++;
            else if (v instanceof Moto) nbMotos++;
            else if (v instanceof Camion) nbCamions++;

            if (v.getDisponible()) nbDisponibles++;
            totalTarifs += v.getTarifJournalier();
        }

        System.out.println("Nombre total de vehicules : " + flotte.size());
        System.out.println("- Voitures : " + nbVoitures);
        System.out.println("- Motos : " + nbMotos);
        System.out.println("- Camions : " + nbCamions);
        System.out.println("\nDisponibles : " + nbDisponibles + "/" + flotte.size());
        System.out.println("Tarif journalier moyen : " +
                String.format("%.2f", totalTarifs / flotte.size()) + " EUR");
        System.out.println("Nombre de reservations : " + reservations.size());
    }

    /**
     * Lance le menu interactif.
     */
    public void lancerMenu() {
        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: afficherInventaire(); break;
                case 2: rechercherParType(); break;
                case 3: afficherDisponibles(); break;
                case 4: creerReservation(); break;
                case 5: afficherReservations(); break;
                case 6: comparerVehicules(); break;
                case 7: afficherStatistiques(); break;
                case 8: System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 8);
    }

    /**
     * Point d'entree de l'application.
     */
    public static void main(String[] args) {
        FlotteVehicules app = new FlotteVehicules();
        app.lancerMenu();
    }
}
