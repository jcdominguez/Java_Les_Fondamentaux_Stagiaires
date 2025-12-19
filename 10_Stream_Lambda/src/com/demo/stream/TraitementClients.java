package com.demo.stream;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Classe principale pour les exercices 1 a 4 sur les Stream et Lambda.
 * Demontre l'utilisation des interfaces fonctionnelles et des pipelines Stream.
 */
public class TraitementClients {

    public static void main(String[] args) {
        // Donnees de depart - 18 clients dans 8 grandes villes francaises
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

        System.out.println("=== EXERCICE 1 : Premieres lambdas ===\n");
        exercice1(clients);

        System.out.println("\n=== EXERCICE 2 : Pipeline Stream complet ===\n");
        exercice2(clients);

        System.out.println("\n=== EXERCICE 3 : Supplier et findFirst ===\n");
        exercice3(clients);

        System.out.println("\n=== EXERCICE 4 : reduce et BinaryOperator ===\n");
        exercice4(clients);
    }

    /**
     * Exercice 1 : Manipulation des interfaces fonctionnelles
     * - Predicate : teste une condition
     * - Function : transforme une valeur
     * - Consumer : consomme une valeur (effet de bord)
     */
    private static void exercice1(List<Client> clients) {
        // Predicate<Client> : verifie si un client est parisien
        Predicate<Client> estParisien = client -> client.getVille().equals("Paris");

        // Predicate<Client> : verifie si un client est premium (depenses > 1000)
        Predicate<Client> estPremium = client -> client.getDepenses() > 1000;

        // Function<Client, String> : extrait le nom du client
        Function<Client, String> versNom = client -> client.getNom();
        // Equivalent avec reference de methode : Client::getNom

        // Consumer<String> : affiche "Client VIP : <nom>"
        Consumer<String> afficherVIP = nom -> System.out.println("Client VIP : " + nom);

        System.out.println("Test des Predicates :");
        for (Client c : clients) {
            System.out.println(c.getNom() + " - Parisien: " + estParisien.test(c)
                             + ", Premium: " + estPremium.test(c));
        }

        System.out.println("\nClients parisiens ET premium :");
        // Combinaison de Predicates avec and()
        Predicate<Client> parisienEtPremium = estParisien.and(estPremium);

        for (Client c : clients) {
            if (parisienEtPremium.test(c)) {
                String nom = versNom.apply(c);  // Function.apply()
                afficherVIP.accept(nom);         // Consumer.accept()
            }
        }
    }

    /**
     * Exercice 2 : Pipeline Stream complet
     * Utilise filter(), map(), forEach() pour traiter les donnees
     */
    private static void exercice2(List<Client> clients) {
        // Recreer les lambdas (ou les passer en parametre)
        Predicate<Client> estParisien = client -> client.getVille().equals("Paris");
        Predicate<Client> estPremium = client -> client.getDepenses() > 1000;
        Function<Client, String> versNom = Client::getNom;  // Reference de methode
        Consumer<String> afficher = nom -> System.out.println("Client VIP : " + nom);

        System.out.println("Pipeline Stream - Clients parisiens premium :");

        // Pipeline complet
        clients.stream()
               .filter(estParisien.and(estPremium))  // Filtrage combine
               .map(versNom)                          // Transformation Client -> String
               .forEach(afficher);                    // Affichage

        // Version plus concise (tout inline)
        System.out.println("\nVersion inline :");
        clients.stream()
               .filter(c -> c.getVille().equals("Paris") && c.getDepenses() > 1000)
               .map(Client::getNom)
               .forEach(nom -> System.out.println("-> " + nom));
    }

    /**
     * Exercice 3 : Supplier et findFirst
     * Gestion des cas ou aucun resultat n'est trouve
     */
    private static void exercice3(List<Client> clients) {
        Predicate<Client> estPremium = client -> client.getDepenses() > 1000;

        // Supplier<Client> : fournit un client par defaut
        Supplier<Client> clientParDefaut = () -> new Client("Inconnu", "N/A", 0, 0.0);

        // Recherche du premier client premium
        Client premierPremium = clients.stream()
            .filter(estPremium)
            .findFirst()
            .orElseGet(clientParDefaut);  // Utilise le Supplier si Optional est vide

        System.out.println("Premier client premium trouve : " + premierPremium);

        // Test avec un Predicate qui ne trouve rien
        Predicate<Client> depensesPlusde5000 = c -> c.getDepenses() > 5000;

        Client clientRiche = clients.stream()
            .filter(depensesPlusde5000)
            .findFirst()
            .orElseGet(clientParDefaut);

        System.out.println("Client avec depenses > 5000 : " + clientRiche);

        // Alternative avec orElse (valeur directe, pas Supplier)
        System.out.println("\nAvec orElse (valeur directe) :");
        Client autre = clients.stream()
            .filter(c -> c.getAge() > 50)
            .findFirst()
            .orElse(new Client("Aucun senior", "N/A", 0, 0.0));
        System.out.println("Client > 50 ans : " + autre);
    }

    /**
     * Exercice 4 : reduce et BinaryOperator
     * Agregation de donnees numeriques et recherche de max
     */
    private static void exercice4(List<Client> clients) {
        // Calcul du total des depenses avec reduce
        double totalDepenses = clients.stream()
            .map(Client::getDepenses)
            .reduce(0.0, Double::sum);  // BinaryOperator : (a, b) -> a + b

        System.out.println("Total des depenses : " + totalDepenses + " EUR");

        // Alternative avec mapToDouble (plus efficace pour les primitifs)
        double total2 = clients.stream()
            .mapToDouble(Client::getDepenses)
            .sum();
        System.out.println("Total (mapToDouble) : " + total2 + " EUR");

        // Trouver le client avec le plus de depenses - methode 1 : BinaryOperator
        BinaryOperator<Client> plusDepensier = (c1, c2) ->
            c1.getDepenses() > c2.getDepenses() ? c1 : c2;

        Client topClient = clients.stream()
            .reduce(plusDepensier)
            .orElse(null);

        System.out.println("\nClient le plus depensier (reduce) : " + topClient);

        // Methode 2 : max avec Comparator
        Client topClient2 = clients.stream()
            .max(Comparator.comparingDouble(Client::getDepenses))
            .orElse(null);

        System.out.println("Client le plus depensier (max) : " + topClient2);

        // Calcul de la moyenne
        double moyenne = clients.stream()
            .mapToDouble(Client::getDepenses)
            .average()
            .orElse(0.0);

        System.out.println("\nMoyenne des depenses : " + String.format("%.2f", moyenne) + " EUR");
    }
}
