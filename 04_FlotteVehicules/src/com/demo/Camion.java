package com.demo;

/**
 * Classe representant un camion.
 * Demontre l'heritage et la redefinition de methodes.
 */
public class Camion extends Vehicule {
    private Double capaciteTonnes;
    private Boolean hayon;

    /**
     * Constructeur de Camion.
     */
    public Camion(String immatriculation, String marque, String modele, Integer annee,
                  TypeMoteur typeMoteur, Integer puissance, Double consommation,
                  Double tarifJournalier,
                  Double capaciteTonnes, Boolean hayon) {
        super(immatriculation, marque, modele, annee,
              typeMoteur, puissance, consommation, tarifJournalier);
        this.capaciteTonnes = capaciteTonnes;
        this.hayon = hayon;
    }

    @Override
    public void demarrer() {
        System.out.println("Le camion " + marque + " " + modele + " demarre lourdement.");
    }

    @Override
    public String getTypeVehicule() {
        return "Camion";
    }

    // Getters specifiques
    public Double getCapaciteTonnes() {
        return capaciteTonnes;
    }

    public Boolean getHayon() {
        return hayon;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + capaciteTonnes + "T, Hayon: " +
               (hayon ? "Oui" : "Non");
    }
}
