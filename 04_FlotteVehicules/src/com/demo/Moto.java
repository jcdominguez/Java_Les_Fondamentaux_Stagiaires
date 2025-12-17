package com.demo;

/**
 * Classe representant une moto.
 * Demontre l'heritage et la redefinition de methodes.
 */
public class Moto extends Vehicule {
    private Integer cylindree;  // en cc
    private String typeMoto;    // Roadster, Sportive, Trail, etc.

    /**
     * Constructeur de Moto.
     */
    public Moto(String immatriculation, String marque, String modele, Integer annee,
                TypeMoteur typeMoteur, Integer puissance, Double consommation,
                Double tarifJournalier,
                Integer cylindree, String typeMoto) {
        super(immatriculation, marque, modele, annee,
              typeMoteur, puissance, consommation, tarifJournalier);
        this.cylindree = cylindree;
        this.typeMoto = typeMoto;
    }

    @Override
    public void demarrer() {
        System.out.println("La moto " + marque + " " + modele + " vrombrit !");
    }

    @Override
    public String getTypeVehicule() {
        return "Moto";
    }

    // Getters specifiques
    public Integer getCylindree() {
        return cylindree;
    }

    public String getTypeMoto() {
        return typeMoto;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + cylindree + "cc " + typeMoto;
    }
}
