package com.demo;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Application de gestion d'un catalogue de morceaux de musique.
 * Permet d'importer des morceaux depuis des fichiers texte,
 * de les serialiser et de les recharger.
 */
public class CatalogueMorceaux {

    // pour depuis la console
    // private static final String DOSSIER_DATA = "data";
    // private static final String DOSSIER_OUTPUT = "output";
    // pour l'execution dans l'IDE : la racine est à l'extérieur du dossier exercice
    private static final String DOSSIER_DATA = "08_IO/data";
    private static final String DOSSIER_OUTPUT = "08_IO/output";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            afficherMenu();
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    importerMorceau(scanner);
                    break;
                case "2":
                    afficherMorceauSerialise(scanner);
                    break;
                case "3":
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Affiche le menu principal
     */
    private static void afficherMenu() {
        System.out.println("=== Catalogue de Morceaux ===");
        System.out.println("1. Importer un morceau depuis un fichier texte");
        System.out.println("2. Afficher un morceau a partir d'un fichier serialise");
        System.out.println("3. Quitter");
    }

    /**
     * Fonctionnalite 1 : Importer un morceau depuis un fichier texte
     */
    private static void importerMorceau(Scanner scanner) {
        Path dossierData = Paths.get(DOSSIER_DATA);

        // Verifier que le dossier existe
        if (!Files.exists(dossierData) || !Files.isDirectory(dossierData)) {
            System.out.println("Erreur : le dossier '" + DOSSIER_DATA + "' n'existe pas.");
            return;
        }

        // Lister les fichiers .txt
        List<Path> fichiersTxt = listerFichiers(dossierData, ".txt");

        if (fichiersTxt.isEmpty()) {
            System.out.println("Aucun fichier .txt trouve dans le dossier '" + DOSSIER_DATA + "'.");
            return;
        }

        // Afficher les fichiers disponibles
        System.out.println("\nFichiers disponibles :");
        for (int i = 0; i < fichiersTxt.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + fichiersTxt.get(i).getFileName());
        }

        // Choisir un fichier
        Path fichierChoisi = choisirFichier(fichiersTxt, scanner);
        if (fichierChoisi == null) {
            return;
        }

        // Lire le fichier et creer l'objet Song
        Song song = lireFichierTexte(fichierChoisi);
        if (song == null) {
            return;
        }

        System.out.println("\nMorceau importe : " + song);

        // Serialiser l'objet
        serialiserSong(song, Paths.get(DOSSIER_OUTPUT));
    }

    /**
     * Fonctionnalite 2 : Afficher un morceau depuis un fichier serialise
     */
    private static void afficherMorceauSerialise(Scanner scanner) {
        Path dossierOutput = Paths.get(DOSSIER_OUTPUT);

        // Verifier que le dossier existe
        if (!Files.exists(dossierOutput) || !Files.isDirectory(dossierOutput)) {
            System.out.println("Erreur : le dossier '" + DOSSIER_OUTPUT + "' n'existe pas.");
            return;
        }

        // Lister les fichiers .ser
        List<Path> fichiersSer = listerFichiers(dossierOutput, ".ser");

        if (fichiersSer.isEmpty()) {
            System.out.println("Aucun fichier .ser trouve dans le dossier '" + DOSSIER_OUTPUT + "'.");
            return;
        }

        // Afficher les fichiers disponibles
        System.out.println("\nFichiers serialises disponibles :");
        for (int i = 0; i < fichiersSer.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + fichiersSer.get(i).getFileName());
        }

        // Choisir un fichier
        Path fichierChoisi = choisirFichier(fichiersSer, scanner);
        if (fichierChoisi == null) {
            return;
        }

        // Deserialiser et afficher
        Song song = deserialiserSong(fichierChoisi);
        if (song != null) {
            System.out.println("\n" + song);
        }
    }

    /**
     * Liste tous les fichiers avec une extension donnee dans un dossier
     * Utilise java.nio (Files.list)
     */
    private static List<Path> listerFichiers(Path dossier, String extension) {
        List<Path> fichiers = new ArrayList<>();

        try (Stream<Path> stream = Files.list(dossier)) {
            fichiers = stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(extension.toLowerCase()))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du dossier : " + e.getMessage());
        }

        return fichiers;
    }

    /**
     * Permet a l'utilisateur de choisir un fichier dans une liste
     */
    private static Path choisirFichier(List<Path> fichiers, Scanner scanner) {
        System.out.print("\nEntrez le numero du fichier : ");
        String input = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < fichiers.size()) {
                return fichiers.get(index);
            } else {
                System.out.println("Numero invalide.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un numero valide.");
            return null;
        }
    }

    /**
     * Lit un fichier texte et cree un objet Song
     * Format attendu : "Cle = Valeur" sur chaque ligne
     */
    private static Song lireFichierTexte(Path fichier) {
        Song song = new Song();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier.toFile()))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                // Ignorer les lignes vides
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                // Separer cle et valeur
                String[] parts = ligne.split("=", 2);
                if (parts.length != 2) {
                    System.out.println("Ligne ignoree (format invalide) : " + ligne);
                    continue;
                }

                String cle = parts[0].trim();
                String valeur = parts[1].trim();

                // Affecter la valeur au bon champ
                switch (cle.toLowerCase()) {
                    case "title":
                        song.setTitle(valeur);
                        break;
                    case "artist":
                        song.setArtist(valeur);
                        break;
                    case "album":
                        song.setAlbum(valeur);
                        break;
                    case "duration":
                        try {
                            song.setDuration(Integer.parseInt(valeur));
                        } catch (NumberFormatException e) {
                            System.out.println("Duree invalide : " + valeur);
                        }
                        break;
                    case "genre":
                        song.setGenre(valeur);
                        break;
                    default:
                        System.out.println("Champ inconnu ignore : " + cle);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouve : " + fichier);
            return null;
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            return null;
        }

        return song;
    }

    /**
     * Serialise un objet Song dans un fichier .ser
     */
    private static void serialiserSong(Song song, Path dossierOutput) {
        // Creer le nom du fichier a partir du titre (sans espaces)
        String nomFichier = song.getTitle().replaceAll("\\s+", "") + ".ser";
        Path cheminFichier = dossierOutput.resolve(nomFichier);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(cheminFichier.toFile()))) {
            oos.writeObject(song);
            System.out.println("Objet Song serialise dans " + cheminFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la serialisation : " + e.getMessage());
        }
    }

    /**
     * Deserialise un objet Song depuis un fichier .ser
     */
    private static Song deserialiserSong(Path fichier) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fichier.toFile()))) {
            Song song = (Song) ois.readObject();
            return song;
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouve : " + fichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Song introuvable : " + e.getMessage());
        }
        return null;
    }
}
