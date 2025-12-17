package com.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercice 3 : Utilisation de try-with-resources.
 * Demonstre la lecture d'un fichier avec fermeture automatique des ressources.
 * Parse les donnees et gere les erreurs de format et de validation.
 */
public class Main3 {
    public static void main(String[] args) {
        List<Etudiant> etudiants = new ArrayList<>();

        // Utiliser try-with-resources : le reader sera ferme automatiquement

        // ATTENTION au chemin du fichier qui doit etre relatif au repertoire
        // d'execution de la commande java
        // try (BufferedReader reader = new BufferedReader(new
        // FileReader("notes.txt"))){
        try (BufferedReader reader = new BufferedReader(new FileReader("05_Exceptions/notes.txt"))) {

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                // Ignorer les lignes vides
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                try {
                    // 1. Separer nom et note (split ";")
                    String[] parts = ligne.split(";");

                    if (parts.length != 2) {
                        System.out.println("Ligne : " + ligne + " -> Format invalide (attendu: nom;note)");
                        continue;
                    }

                    String nom = parts[0].trim();

                    // 2. Parser la note (peut lever NumberFormatException)
                    int note = Integer.parseInt(parts[1].trim());

                    // 3. Creer un Etudiant (peut lever IllegalArgumentException)
                    Etudiant etudiant = new Etudiant(nom, note);

                    // 4. Ajouter a la liste si OK
                    etudiants.add(etudiant);
                    System.out.println("Ligne : " + ligne + " -> OK");

                } catch (NumberFormatException e) {
                    // Erreur de parsing de la note
                    System.out.println("Ligne : " + ligne + " -> Erreur de format");

                } catch (IllegalArgumentException e) {
                    // Note invalide (hors bornes 0-20) ou nom vide
                    System.out.println("Ligne : " + ligne + " -> Note invalide");
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        // Afficher les etudiants charges
        System.out.println("\n=== Etudiants charges ===");
        etudiants.forEach(System.out::println);

        System.out.println("\nTotal : " + etudiants.size() + " etudiant(s)");
    }
}
