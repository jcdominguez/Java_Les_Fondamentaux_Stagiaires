package com.demo;

/**
 * Classe representant une voiture.
 * Demontre l'heritage et la redefinition de methodes.
 */
public class Voiture extends Vehicule {
    private Integer nombrePortes;
    private Boolean climatisation;

    /**
     * Constructeur de Voiture.
     */
    public Voiture(String immatriculation, String marque, String modele, Integer annee,
                   TypeMoteur typeMoteur, Integer puissance, Double consommation,
                   Double tarifJournalier,
                   Integer nombrePortes, Boolean climatisation) {
        super(immatriculation, marque, modele, annee,
              typeMoteur, puissance, consommation, tarifJournalier);
        this.nombrePortes = nombrePortes;
        this.climatisation = climatisation;
    }

    @Override
    public void demarrer() {
        System.out.println("La voiture " + marque + " " + modele + " demarre en douceur.");
    }

    @Override
    public String getTypeVehicule() {
        return "Voiture";
    }

    // Getters specifiques
    public Integer getNombrePortes() {
        return nombrePortes;
    }

    public Boolean getClimatisation() {
        return climatisation;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + nombrePortes + " portes, Clim: " +
               (climatisation ? "Oui" : "Non");
    }
}
