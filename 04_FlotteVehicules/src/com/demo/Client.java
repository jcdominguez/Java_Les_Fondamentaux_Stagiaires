package com.demo;

/**
 * Classe representant un client.
 * Demontre l'utilisation de variables static pour l'auto-increment.
 */
public class Client {
    private static int compteurId = 0;  // Variable de classe

    private Integer id;
    private String nom;
    private String telephone;

    /**
     * Constructeur de Client.
     * L'ID est genere automatiquement.
     */
    public Client(String nom, String telephone) {
        this.id = ++compteurId;  // Auto-increment
        this.nom = nom;
        this.telephone = telephone;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Client #" + id + " : " + nom + " (Tel: " + telephone + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Client)) return false;
        Client other = (Client) obj;
        return this.id.equals(other.id);
    }
}
