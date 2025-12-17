package com.demo;

/**
 * Classe representant un etudiant avec son nom et sa note.
 * Leve des exceptions si les donnees sont invalides.
 */
public class Etudiant {
    private String nom;
    private int note;

    /**
     * Constructeur de la classe Etudiant.
     * @param nom Le nom de l'etudiant (ne peut pas etre null ou vide)
     * @param note La note de l'etudiant (doit etre entre 0 et 20)
     * @throws IllegalArgumentException si le nom est null/vide ou la note hors bornes
     */
    public Etudiant(String nom, int note) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas etre vide");
        }
        if (note < 0 || note > 20) {
            throw new IllegalArgumentException("La note doit etre entre 0 et 20 : " + note);
        }

        this.nom = nom;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public int getNote() {
        return note;
    }

    @Override
    public String toString() {
        return nom + " : " + note + "/20";
    }
}
