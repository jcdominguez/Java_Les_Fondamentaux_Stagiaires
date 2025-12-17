package com.demo;

/**
 * Exercice 1 : Gestion des exceptions non-controlees (RuntimeException).
 * Demonstre l'utilisation de try-catch pour capturer IllegalArgumentException.
 */
public class Main {
    public static void main(String[] args) {
        // Creer ces etudiants et gerer les erreurs avec try-catch
        // Le programme doit continuer meme apres une erreur

        creerEtudiant("Alice", 15);
        creerEtudiant("", 12);           // nom vide
        creerEtudiant("Bob", -5);        // note negative
        creerEtudiant("Charlie", 25);    // note > 20
        creerEtudiant("Diana", 18);

        System.out.println("Programme termine");
    }

    /**
     * Tente de creer un etudiant et affiche le resultat.
     * Les erreurs sont capturees et affichees sans interrompre le programme.
     * @param nom Le nom de l'etudiant
     * @param note La note de l'etudiant
     */
    static void creerEtudiant(String nom, int note) {
        try {
            Etudiant etudiant = new Etudiant(nom, note);
            System.out.println("+ Cree : " + etudiant);
        } catch (IllegalArgumentException e) {
            System.out.println("x Erreur : " + e.getMessage());
        }
    }
}
