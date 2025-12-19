package com.demo;

import java.sql.*;
import java.util.*;

/**
 * Data Access Object pour acceder aux donnees des pays et des scores.
 */
public class CountryDAO {

    private static final String DB_URL = "jdbc:h2:./countries";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    /**
     * Etablit une connexion a la base de donnees
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Initialise la table des scores si elle n'existe pas
     */
    public void initScoresTable() {
        String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "player_name VARCHAR(255) NOT NULL UNIQUE, " +
                     "score INT DEFAULT 0)";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'initialisation de la table scores : " + e.getMessage());
        }
    }

    /**
     * Recupere tous les pays de la base de donnees
     */
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT id, name, capital FROM countries";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Country country = new Country(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("capital")
                );
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation des pays : " + e.getMessage());
        }

        return countries;
    }

    /**
     * Recupere N pays aleatoires sans doublons
     */
    public List<Country> getRandomCountries(int count) {
        List<Country> allCountries = getAllCountries();
        List<Country> randomCountries = new ArrayList<>();

        if (allCountries.size() < count) {
            System.out.println("Pas assez de pays dans la base de donnees.");
            return randomCountries;
        }

        // Melanger la liste et prendre les N premiers
        Random random = new Random();
        Collections.shuffle(allCountries, random);

        for (int i = 0; i < count; i++) {
            randomCountries.add(allCountries.get(i));
        }

        return randomCountries;
    }

    /**
     * Recupere le score d'un joueur
     * @return le score du joueur, ou null s'il n'existe pas
     */
    public Integer getPlayerScore(String playerName) {
        String sql = "SELECT score FROM scores WHERE player_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("score");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation du score : " + e.getMessage());
        }

        return null;
    }

    /**
     * Sauvegarde ou met a jour le score d'un joueur (cumul avec l'ancien score)
     */
    public void savePlayerScore(String playerName, int scoreToAdd) {
        Integer currentScore = getPlayerScore(playerName);

        if (currentScore == null) {
            // Nouveau joueur : insertion
            String sql = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, playerName);
                pstmt.setInt(2, scoreToAdd);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erreur lors de l'insertion du score : " + e.getMessage());
            }
        } else {
            // Joueur existant : mise a jour avec cumul
            String sql = "UPDATE scores SET score = score + ? WHERE player_name = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, scoreToAdd);
                pstmt.setString(2, playerName);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erreur lors de la mise a jour du score : " + e.getMessage());
            }
        }
    }

    /**
     * Affiche le classement des joueurs
     */
    public void afficherClassement() {
        String sql = "SELECT player_name, score FROM scores ORDER BY score DESC LIMIT 10";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n=== Classement ===");
            int rang = 1;
            while (rs.next()) {
                System.out.println(rang + ". " + rs.getString("player_name") + " : " + rs.getInt("score") + " points");
                rang++;
            }
            if (rang == 1) {
                System.out.println("Aucun score enregistre.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage du classement : " + e.getMessage());
        }
    }
}
