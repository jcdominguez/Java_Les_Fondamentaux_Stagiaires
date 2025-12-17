package com.demo;

/**
 * Classe representant une reservation.
 * Demontre l'association (references a des objets existants).
 */
public class Reservation {
    private static int compteur = 0;

    private int numero;
    private Vehicule vehicule;  // ASSOCIATION (pas composition)
    private Client client;      // ASSOCIATION
    private int nbJours;
    private String dateDebut;

    /**
     * Constructeur de Reservation.
     * Recoit des objets existants (association) et marque le vehicule indisponible.
     */
    public Reservation(Vehicule vehicule, Client client, int nbJours, String dateDebut) {
        this.numero = ++compteur;
        this.vehicule = vehicule;  // Reference a un objet EXISTANT
        this.client = client;
        this.nbJours = nbJours;
        this.dateDebut = dateDebut;

        // Marquer le vehicule comme indisponible
        vehicule.setDisponible(false);
    }

    /**
     * Calcule le montant total de la reservation.
     * Utilise le polymorphisme : appelle calculerTarif() du vehicule concret.
     */
    public Double getMontantTotal() {
        return vehicule.calculerTarif(nbJours);
    }

    /**
     * Annule la reservation et libere le vehicule.
     */
    public void annuler() {
        vehicule.setDisponible(true);
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public Client getClient() {
        return client;
    }

    public int getNbJours() {
        return nbJours;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    @Override
    public String toString() {
        return "Reservation #" + numero + " : " + client.getNom() +
               " - " + vehicule.getDescription() +
               " - " + nbJours + " jours (debut: " + dateDebut + ")" +
               " - Total: " + String.format("%.2f", getMontantTotal()) + " EUR";
    }
}
