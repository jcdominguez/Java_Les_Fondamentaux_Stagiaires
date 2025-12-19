package com.demo.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mini-projet : Classe utilitaire pour l'analyse de donnees clients.
 * Toutes les methodes utilisent l'API Stream.
 */
public class ClientAnalytics {

    /**
     * Retourne la liste des clients premium (depenses > 1000 EUR)
     * @param clients Liste de clients a analyser
     * @return Liste des clients premium
     */
    public static List<Client> getPremiumClients(List<Client> clients) {
        return clients.stream()
            .filter(c -> c.getDepenses() > 1000)
            .collect(Collectors.toList());
        // Alternative Java 16+ : .toList()
    }

    /**
     * Calcule le total des depenses de tous les clients
     * @param clients Liste de clients
     * @return Total des depenses en EUR
     */
    public static double getTotalDepenses(List<Client> clients) {
        return clients.stream()
            .mapToDouble(Client::getDepenses)
            .sum();
    }

    /**
     * Trouve le client ayant le plus depense
     * @param clients Liste de clients
     * @return Optional contenant le client ou vide si liste vide
     */
    public static Optional<Client> getClientLePlusDepensier(List<Client> clients) {
        return clients.stream()
            .max(Comparator.comparingDouble(Client::getDepenses));
    }

    /**
     * Compte le nombre de clients par ville
     * @param clients Liste de clients
     * @return Map associant chaque ville au nombre de clients
     */
    public static Map<String, Long> compterClientsParVille(List<Client> clients) {
        return clients.stream()
            .collect(Collectors.groupingBy(
                Client::getVille,
                Collectors.counting()
            ));
    }

    // --- Methodes bonus (Exercice 5) ---

    /**
     * Trie les clients par age croissant
     * @param clients Liste de clients
     * @return Liste triee par age
     */
    public static List<Client> trierParAge(List<Client> clients) {
        return clients.stream()
            .sorted(Comparator.comparingInt(Client::getAge))
            .collect(Collectors.toList());
    }

    /**
     * Calcule la moyenne des depenses
     * @param clients Liste de clients
     * @return Moyenne des depenses ou 0 si liste vide
     */
    public static double getMoyenneDepenses(List<Client> clients) {
        return clients.stream()
            .collect(Collectors.averagingDouble(Client::getDepenses));
    }

    /**
     * Extrait les noms des clients tries alphabetiquement
     * @param clients Liste de clients
     * @return Liste des noms tries
     */
    public static List<String> getNomsTriesAlphabetiquement(List<Client> clients) {
        return clients.stream()
            .map(Client::getNom)
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Groupe les clients par ville
     * @param clients Liste de clients
     * @return Map associant chaque ville a sa liste de clients
     */
    public static Map<String, List<Client>> grouperParVille(List<Client> clients) {
        return clients.stream()
            .collect(Collectors.groupingBy(Client::getVille));
    }

    // --- Main pour tester ---

    public static void main(String[] args) {
        // 18 clients dans 8 grandes villes francaises
        List<Client> clients = List.of(
            // Paris (4)
            new Client("Alice", "Paris", 32, 1500.0),
            new Client("Charlie", "Paris", 45, 2500.0),
            new Client("Isabelle", "Paris", 29, 1800.0),
            new Client("Marc", "Paris", 52, 3200.0),
            // Lyon (3)
            new Client("Bob", "Lyon", 25, 800.0),
            new Client("Eve", "Lyon", 35, 950.0),
            new Client("Nicolas", "Lyon", 41, 1600.0),
            // Marseille (2)
            new Client("Diana", "Marseille", 28, 1200.0),
            new Client("Olivier", "Marseille", 38, 2100.0),
            // Bordeaux (2)
            new Client("Francois", "Bordeaux", 33, 1350.0),
            new Client("Sophie", "Bordeaux", 27, 720.0),
            // Toulouse (2)
            new Client("Guillaume", "Toulouse", 44, 1900.0),
            new Client("Julie", "Toulouse", 31, 1100.0),
            // Nantes (2)
            new Client("Hugo", "Nantes", 26, 650.0),
            new Client("Lea", "Nantes", 39, 1450.0),
            // Nice (2)
            new Client("Kevin", "Nice", 48, 2800.0),
            new Client("Marie", "Nice", 23, 550.0),
            // Lille (1)
            new Client("Paul", "Lille", 36, 1750.0)
        );

        System.out.println("=== ClientAnalytics - Demonstration ===\n");

        // Clients premium
        System.out.println("Clients premium (depenses > 1000 EUR) :");
        List<Client> premiums = getPremiumClients(clients);
        premiums.forEach(c -> System.out.println("  - " + c));

        // Total des depenses
        System.out.println("\nTotal des depenses : "
            + getTotalDepenses(clients) + " EUR");

        // Client le plus depensier
        System.out.println("\nClient le plus depensier : "
            + getClientLePlusDepensier(clients)
                .map(Client::toString)
                .orElse("Aucun client"));

        // Comptage par ville
        System.out.println("\nNombre de clients par ville :");
        Map<String, Long> parVille = compterClientsParVille(clients);
        parVille.forEach((ville, count) ->
            System.out.println("  - " + ville + " : " + count + " client(s)"));

        // Bonus : statistiques avancees
        System.out.println("\n=== Statistiques avancees (Exercice 5) ===");

        System.out.println("\nClients tries par age :");
        trierParAge(clients).forEach(c ->
            System.out.println("  - " + c.getNom() + " (" + c.getAge() + " ans)"));

        System.out.println("\nMoyenne des depenses : "
            + String.format("%.2f", getMoyenneDepenses(clients)) + " EUR");

        System.out.println("\nNoms tries alphabetiquement : "
            + getNomsTriesAlphabetiquement(clients));

        System.out.println("\nClients groupes par ville :");
        grouperParVille(clients).forEach((ville, listeClients) -> {
            System.out.println("  " + ville + " :");
            listeClients.forEach(c -> System.out.println("    - " + c.getNom()));
        });
    }
}
