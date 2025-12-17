package com.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Exercice 2 : Utilisation de try-catch-finally.
 * Demonstre la lecture d'un fichier avec gestion manuelle de la fermeture.
 */
public class Main2 {
    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            // Ouvrir le fichier notes.txt
            // reader = new BufferedReader(new FileReader("notes.txt"));
            reader = new BufferedReader(new FileReader("05_Exceptions/notes.txt"));

            // Lire et afficher chaque ligne
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }

        } catch (FileNotFoundException e) {
            // Gerer fichier non trouve
            System.out.println("Erreur : Le fichier 'notes.txt' n'existe pas.");
            System.out.println("Assurez-vous que le fichier est dans le repertoire d'execution.");

        } catch (IOException e) {
            // Gerer erreur de lecture
            System.out.println("Erreur lors de la lecture : " + e.getMessage());

        } finally {
            // Fermer le reader (attention au null !)
            // Le finally s'execute TOUJOURS, meme si une exception est levee
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Erreur lors de la fermeture : " + e.getMessage());
                }
            }
            System.out.println("--- Fin de lecture ---");
        }
    }
}
