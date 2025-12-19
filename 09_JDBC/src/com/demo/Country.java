package com.demo;

/**
 * Classe representant un pays avec sa capitale.
 */
public class Country {
    private Integer id;
    private String name;
    private String capital;

    /**
     * Constructeur par defaut
     */
    public Country() {
    }

    /**
     * Constructeur avec tous les parametres
     */
    public Country(Integer id, String name, String capital) {
        this.id = id;
        this.name = name;
        this.capital = capital;
    }

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return name + " (capitale : " + capital + ")";
    }
}
