package com.demo;

/**
 * Interface definissant le contrat pour tout objet louable.
 * Demontre l'utilisation des interfaces en Java.
 */
public interface Louable {

    /**
     * Calcule le tarif de location pour un nombre de jours donne.
     * @param nbJours nombre de jours de location
     * @return le montant total de la location
     */
    Double calculerTarif(int nbJours);

    /**
     * Retourne une description de l'objet louable.
     * @return description formatee
     */
    String getDescription();
}
