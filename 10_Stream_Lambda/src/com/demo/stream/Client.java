package com.demo.stream;

/**
 * Classe Client - Modele de donnees pour les exercices Stream/Lambda
 * Represente un client avec ses informations de base et ses depenses.
 */
public class Client {
    private String nom;
    private String ville;
    private int age;
    private double depenses;

    /**
     * Constructeur complet
     * @param nom Nom du client
     * @param ville Ville de residence
     * @param age Age du client
     * @param depenses Total des depenses en euros
     */
    public Client(String nom, String ville, int age, double depenses) {
        this.nom = nom;
        this.ville = ville;
        this.age = age;
        this.depenses = depenses;
    }

    public String getNom() { return nom; }
    public String getVille() { return ville; }
    public int getAge() { return age; }
    public double getDepenses() { return depenses; }

    @Override
    public String toString() {
        return nom + " (" + ville + ", " + age + " ans, " + depenses + " EUR)";
    }
}
