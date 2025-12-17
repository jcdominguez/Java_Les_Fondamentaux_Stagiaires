package com.demo;

/**
 * Classe abstraite representant un vehicule.
 * Demontre: classe abstraite, implementation d'interface, composition.
 */
public abstract class Vehicule implements Louable {
    protected String immatriculation;
    protected String marque;
    protected String modele;
    protected Integer annee;
    protected Boolean disponible;
    protected Double tarifJournalier;
    protected Moteur moteur;  // COMPOSITION

    /**
     * Constructeur de Vehicule.
     * Cree le Moteur en interne (composition).
     */
    public Vehicule(String immatriculation, String marque, String modele, Integer annee,
                    TypeMoteur typeMoteur, Integer puissance, Double consommation,
                    Double tarifJournalier) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.disponible = true;
        this.tarifJournalier = tarifJournalier;
        // COMPOSITION : le Moteur est cree ICI
        this.moteur = new Moteur(typeMoteur, puissance, consommation);
    }

    // Methodes abstraites (a implementer par les sous-classes)
    public abstract void demarrer();
    public abstract String getTypeVehicule();

    // Implementation de l'interface Louable
    @Override
    public Double calculerTarif(int nbJours) {
        return tarifJournalier * nbJours;
    }

    @Override
    public String getDescription() {
        return marque + " " + modele + " (" + annee + ")";
    }

    // Getters
    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public Integer getAnnee() {
        return annee;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Double getTarifJournalier() {
        return tarifJournalier;
    }

    public Moteur getMoteur() {
        return moteur;
    }

    // Setter pour disponibilite
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return getTypeVehicule() + " " + immatriculation + " : " +
               marque + " " + modele + " (" + annee + ") - " + moteur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Vehicule)) return false;
        Vehicule other = (Vehicule) obj;
        return this.immatriculation.equals(other.immatriculation);
    }
}
