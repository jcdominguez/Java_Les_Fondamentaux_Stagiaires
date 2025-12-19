package com.demo;

import java.io.Serializable;

/**
 * Classe representant un morceau de musique.
 * Implemente Serializable pour permettre la sauvegarde/chargement binaire.
 */
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String artist;
    private String album;
    private Integer duration;  // en secondes
    private String genre;

    /**
     * Constructeur par defaut
     */
    public Song() {
    }

    /**
     * Constructeur avec tous les parametres
     */
    public Song(String title, String artist, String album, Integer duration, String genre) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.genre = genre;
    }

    // Getters et Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return title + " (" + artist + ") - " + album +
               " [" + duration + " sec, " + genre + "]";
    }
}
