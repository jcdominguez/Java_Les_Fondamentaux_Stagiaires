package com.demo;

/**
 * Classe representant un moteur de vehicule.
 * Demontre l'encapsulation et l'utilisation des Wrappers.
 */
public class Moteur {
    private TypeMoteur type;
    private Integer puissance;      // en chevaux
    private Double consommation;    // en L/100km ou kWh/100km

    /**
     * Constructeur du moteur.
     * @param type le type de motorisation
     * @param puissance la puissance en chevaux
     * @param consommation la consommation
     */
    public Moteur(TypeMoteur type, Integer puissance, Double consommation) {
        this.type = type;
        this.puissance = puissance;
        this.consommation = consommation;
    }

    // Getters
    public TypeMoteur getType() {
        return type;
    }

    public Integer getPuissance() {
        return puissance;
    }

    public Double getConsommation() {
        return consommation;
    }

    /**
     * Retourne une description formatee du moteur.
     * Format: "DIESEL 150ch - 7.5 L/100km"
     */
    @Override
    public String toString() {
        return type.name() + " " + puissance + "ch - " + consommation + " L/100km";
    }
}
