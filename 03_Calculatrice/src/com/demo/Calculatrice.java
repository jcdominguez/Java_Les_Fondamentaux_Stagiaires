package com.demo;

import java.util.Scanner;

public class Calculatrice {
    public static void main(String[] args) {
        // === DECLARATION DES VARIABLES ===

        int compteur = 0;                    // compte le nombre de calculs
        int indexHistorique = 0;             // index pour le tableau
        double nombre1, nombre2;             // nombres saisis par l'utilisateur
        double resultat = 0;                 // resultat du calcul
        char operation;                      // operation choisie (+, -, *, /, %)
        char continuer;                      // choix de continuer (o/n)
        boolean enCours = true;              // ontrole la boucle principale
        boolean calculValide;                // indique si le calcul a reussi

        double[] historique = new double[5]; // Tableau historique

        // Scanner pour la lecture clavier
        Scanner scanner = new Scanner(System.in);

        // === PROGRAMME PRINCIPAL ===

        System.out.println("=== CALCULATRICE JAVA ===");

        // Boucle do-while : menu principal (s'execute au moins une fois)
        do {
            System.out.println();
            calculValide = true;

            // Saisie du premier nombre
            System.out.print("Entrez le premier nombre : ");
            nombre1 = scanner.nextDouble();

            // Saisie du deuxieme nombre
            System.out.print("Entrez le deuxieme nombre : ");
            nombre2 = scanner.nextDouble();

            // Affichage du menu des operations
            System.out.println();
            System.out.println("Choisissez l'operation :");
            System.out.println("  + : Addition");
            System.out.println("  - : Soustraction");
            System.out.println("  * : Multiplication");
            System.out.println("  / : Division");
            System.out.println("  % : Modulo");
            System.out.print("Votre choix : ");
            operation = scanner.next().charAt(0);

            System.out.println();

            // Switch : selection de l'operation selon le caractere
            switch (operation) {
                case '+':
                    resultat = nombre1 + nombre2;
                    break;
                case '-':
                    resultat = nombre1 - nombre2;
                    break;
                case '*':
                    resultat = nombre1 * nombre2;
                    break;
                case '/':
                    // verification de la division par zero
                    if (nombre2 == 0) {
                        System.out.println("Erreur : Division par zero impossible !");
                        calculValide = false;
                    } else {
                        resultat = nombre1 / nombre2;
                    }
                    break;
                case '%':
                    // verification du modulo par zero
                    if (nombre2 == 0) {
                        System.out.println("Erreur : Modulo par zero impossible !");
                        calculValide = false;
                    } else {
                        resultat = nombre1 % nombre2;
                    }
                    break;
                default:
                    System.out.println("Erreur : Operation non reconnue !");
                    calculValide = false;
            }

            // Affichage du resultat si le calcul est valide
            if (calculValide) {
                System.out.println("Resultat : " + nombre1 + " " + operation + " " + nombre2 + " = " + resultat);

                // Stockage dans l'historique (tableau circulaire de 5 elements)
                historique[indexHistorique % 5] = resultat;
                indexHistorique++;
                compteur++;
            }

            // Demande si l'utilisateur veut continuer
            System.out.println();
            System.out.print("Voulez-vous continuer ? (o/n) : ");
            continuer = scanner.next().charAt(0);

            // mise a jour du booleen de controle
            if (continuer == 'n' || continuer == 'N') {
                enCours = false;
            }

        } while (enCours);

        // === AFFICHAGE DE L'HISTORIQUE ===

        System.out.println();
        System.out.println("=== HISTORIQUE DES RESULTATS ===");

        // verifier s'il y a des resultats a afficher
        if (compteur == 0) {
            System.out.println("Aucun calcul effectue.");
        } else {
            // parcours du tableau d'historique
            int nombreResultats = Math.min(compteur, 5);
            for (int i = 0; i < nombreResultats; i++) {
                System.out.println("Calcul " + (i + 1) + " : " + historique[i]);
            }
        }

        System.out.println();
        System.out.println("Merci d'avoir utilise la calculatrice !");

        // Fermeture du Scanner
        scanner.close();
    }
}
