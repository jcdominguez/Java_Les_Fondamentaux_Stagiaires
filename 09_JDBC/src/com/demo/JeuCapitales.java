package com.demo;

import java.util.*;

/**
 * Jeu des capitales europeennes.
 * Le joueur doit deviner les capitales des pays.
 */
public class JeuCapitales {

    private static final int NB_PROPOSITIONS = 4;
    private static CountryDAO dao = new CountryDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialiser la table des scores
        dao.initScoresTable();

        boolean continuer = true;

        while (continuer) {
            afficherMenuPrincipal();
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    jouerPartie();
                    break;
                case "2":
                    dao.afficherClassement();
                    break;
                case "3":
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Affiche le menu principal
     */
    private static void afficherMenuPrincipal() {
        System.out.println("=== Jeu des Capitales ===");
        System.out.println("1. Jouer une partie");
        System.out.println("2. Voir le classement");
        System.out.println("3. Quitter");
        System.out.print("Votre choix : ");
    }

    /**
     * Lance une partie de jeu
     */
    private static void jouerPartie() {
        // Demander le nom du joueur
        System.out.print("\nEntrez votre nom : ");
        String nomJoueur = scanner.nextLine().trim();

        if (nomJoueur.isEmpty()) {
            System.out.println("Nom invalide.");
            return;
        }

        // Afficher le score actuel du joueur
        Integer scoreActuel = dao.getPlayerScore(nomJoueur);
        if (scoreActuel != null) {
            System.out.println("Bon retour " + nomJoueur + " ! Votre score actuel : " + scoreActuel + " points");
        } else {
            System.out.println("Bienvenue " + nomJoueur + " !");
        }

        int scorePartie = 0;
        boolean continuerPartie = true;

        while (continuerPartie) {
            // Jouer une question
            int resultat = jouerQuestion();
            scorePartie += resultat;

            System.out.println("Score de la partie : " + scorePartie);

            // Demander si le joueur veut continuer
            System.out.print("\nVoulez-vous continuer ? (o/n) : ");
            String reponse = scanner.nextLine().trim().toLowerCase();

            if (!reponse.equals("o") && !reponse.equals("oui")) {
                continuerPartie = false;
            }
        }

        // Sauvegarder le score
        dao.savePlayerScore(nomJoueur, scorePartie);

        Integer nouveauScore = dao.getPlayerScore(nomJoueur);
        System.out.println("\n=== Fin de la partie ===");
        System.out.println("Score de cette partie : " + scorePartie);
        System.out.println("Score total de " + nomJoueur + " : " + nouveauScore + " points");
    }

    /**
     * Joue une question et retourne le score (+1 ou -1)
     */
    private static int jouerQuestion() {
        // Recuperer 4 pays aleatoires
        List<Country> pays = dao.getRandomCountries(NB_PROPOSITIONS);

        if (pays.size() < NB_PROPOSITIONS) {
            System.out.println("Erreur : pas assez de pays dans la base.");
            return 0;
        }

        // Choisir aleatoirement le pays pour la question
        Random random = new Random();
        int indexQuestion = random.nextInt(NB_PROPOSITIONS);
        Country paysQuestion = pays.get(indexQuestion);

        // Melanger les propositions pour l'affichage
        List<Country> propositions = new ArrayList<>(pays);
        Collections.shuffle(propositions, random);

        // Trouver l'index de la bonne reponse apres le melange
        int bonneReponse = -1;
        for (int i = 0; i < propositions.size(); i++) {
            if (propositions.get(i).getId().equals(paysQuestion.getId())) {
                bonneReponse = i + 1;  // +1 car les choix commencent a 1
                break;
            }
        }

        // Afficher la question
        System.out.println("\n========================================");
        System.out.println("Quelle est la capitale du pays : " + paysQuestion.getName() + " ?");
        System.out.println("\nSaisir le numero correspondant a la proposition choisie:");

        for (int i = 0; i < propositions.size(); i++) {
            System.out.println((i + 1) + ". " + propositions.get(i).getCapital());
        }

        // Lire la reponse
        System.out.print("\nVotre reponse : ");
        String input = scanner.nextLine().trim();

        try {
            int choix = Integer.parseInt(input);

            if (choix < 1 || choix > NB_PROPOSITIONS) {
                System.out.println("Choix invalide !");
                return -1;
            }

            if (choix == bonneReponse) {
                System.out.println("Bonne reponse ! La capitale de " + paysQuestion.getName() +
                                   " est bien " + paysQuestion.getCapital() + ".");
                return 1;
            } else {
                System.out.println("Mauvaise reponse ! La capitale de " + paysQuestion.getName() +
                                   " est " + paysQuestion.getCapital() + ".");
                return -1;
            }

        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un numero valide.");
            return -1;
        }
    }
}
