package com.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Exercice 4 : Gerer les notes de plusieurs eleves
 * Utilisation de HashMap avec ArrayList pour associer eleve -> liste de notes
 */
public class NotesEleves {
    public static void main(String[] args) {
        HashMap<String, ArrayList<Double>> notesEleves = new HashMap<>();

        System.out.println("Enregistrement des notes...");

        // Ajout des notes pour differents eleves
        ajouterNote(notesEleves, "Alice", 15.0);
        ajouterNote(notesEleves, "Alice", 12.0);
        ajouterNote(notesEleves, "Bob", 18.0);
        ajouterNote(notesEleves, "Alice", 14.0);
        ajouterNote(notesEleves, "Bob", 16.0);
        ajouterNote(notesEleves, "Charlie", 10.0);
        ajouterNote(notesEleves, "Bob", 13.0);

        // Afficher les moyennes
        System.out.println();
        afficherMoyennes(notesEleves);
    }

    /**
     * Ajoute une note a un eleve.
     * Si l'eleve n'existe pas, cree sa liste de notes.
     */
    static void ajouterNote(HashMap<String, ArrayList<Double>> notes,
                            String eleve, Double note) {
        // Verifier si l'eleve existe deja
        if (!notes.containsKey(eleve)) {
            // Creer une nouvelle liste pour cet eleve
            notes.put(eleve, new ArrayList<>());
        }

        // Ajouter la note a la liste de l'eleve
        notes.get(eleve).add(note);

        System.out.println(eleve + " : ajout note " + note);
    }

    /**
     * Affiche la moyenne de chaque eleve.
     */
    static void afficherMoyennes(HashMap<String, ArrayList<Double>> notes) {
        System.out.println("=== Moyennes des eleves ===");

        for (Map.Entry<String, ArrayList<Double>> entry : notes.entrySet()) {
            String eleve = entry.getKey();
            ArrayList<Double> listeNotes = entry.getValue();

            // Calculer la moyenne
            double somme = 0;
            for (Double n : listeNotes) {
                somme += n;
            }
            double moyenne = somme / listeNotes.size();

            System.out.printf("%s : notes %s -> moyenne = %.2f%n",
                    eleve, listeNotes, moyenne);
        }
    }
}
