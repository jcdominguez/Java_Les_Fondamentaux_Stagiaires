# TP 08 : Catalogue de morceaux de musique - IO et Serialisation

## Objectif

Apprendre a manipuler les entrees/sorties en Java.

**Concepts a valider :**
- Parcourir un dossier du systeme de fichiers (java.nio)
- Lire un fichier texte avec un flux (FileReader + BufferedReader)
- Serialiser et deserialiser des objets Java

## Structure du projet

```
08_IO/
├── enonce.md
├── data/                        # Fichiers texte decrivant les morceaux
│   ├── comfortably_numb.txt
│   ├── bohemian_rhapsody.txt
│   └── hotel_california.txt
├── output/                      # Fichiers serialises (.ser)
├── src/
│   └── com/
│       └── demo/
│           ├── Song.java
│           └── CatalogueMorceaux.java
└── out/
    └── production/
```

---

## Format des fichiers texte (*.txt)

Chaque fichier du dossier `data/` contient un morceau de musique avec ce format :

```
Title = Comfortably Numb
Artist = Pink Floyd
Album = The Wall
Duration = 384
Genre = Rock
```

---

## Classe Java a implementer

```java
package com.demo;

import java.io.Serializable;

public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String artist;
    private String album;
    private Integer duration;    // en secondes
    private String genre;

    // TODO : Constructeur par defaut
    // TODO : Constructeur avec tous les parametres
    // TODO : Getters et Setters

    @Override
    public String toString() {
        return title + " (" + artist + ") - " + album +
               " [" + duration + " sec, " + genre + "]";
    }
}
```

---

## Cahier des charges

### Fonctionnalite 1 : Parcourir un dossier pour choisir un fichier texte

Le programme demande le dossier a parcourir, puis utilise `java.nio` (`Files.list` ou `Files.newDirectoryStream`) pour :
- Afficher tous les fichiers `.txt` du dossier
- Permettre a l'utilisateur d'en choisir un par son numero

### Fonctionnalite 2 : Ouvrir un flux de lecture et analyser le fichier

Apres selection du fichier :
- Ouvrir un `FileReader` enveloppe dans un `BufferedReader`
- Lire chaque ligne
- Extraire le champ et la valeur (`split` sur `=`)
- Remplir un objet `Song`

Exemple :
```
Title = Comfortably Numb
=> song.setTitle("Comfortably Numb")
```

### Fonctionnalite 3 : Serialiser l'objet Song

L'objet `Song` instancie doit etre sauvegarde dans un fichier :
```
output/ComfortablyNumb.ser
```

En utilisant :
- `FileOutputStream`
- `ObjectOutputStream`

Un message confirme la serialisation.

### Fonctionnalite 4 : Recharger un objet serialise

Le programme doit aussi permettre :
- De parcourir le dossier `output/` contenant les `.ser`
- D'en choisir un
- De le lire via `ObjectInputStream`
- D'afficher son contenu avec `song.toString()`

---

## Exemple d'execution

```
=== Catalogue de Morceaux ===
1. Importer un morceau depuis un fichier texte
2. Afficher un morceau a partir d'un fichier serialise
3. Quitter
Votre choix : 1

Fichiers disponibles :
  1. bohemian_rhapsody.txt
  2. comfortably_numb.txt
  3. hotel_california.txt

Entrez le numero du fichier : 2

Morceau importe : Comfortably Numb (Pink Floyd) - The Wall [384 sec, Rock]
Objet Song serialise dans output/ComfortablyNumb.ser

=== Catalogue de Morceaux ===
1. Importer un morceau depuis un fichier texte
2. Afficher un morceau a partir d'un fichier serialise
3. Quitter
Votre choix : 2

Fichiers serialises disponibles :
  1. ComfortablyNumb.ser

Entrez le numero du fichier : 1

Comfortably Numb (Pink Floyd) - The Wall [384 sec, Rock]

=== Catalogue de Morceaux ===
1. Importer un morceau depuis un fichier texte
2. Afficher un morceau a partir d'un fichier serialise
3. Quitter
Votre choix : 3
Au revoir !
```

---

## Indices / Rappels de syntaxe

### Parcourir un dossier avec java.nio

```java
import java.nio.file.*;
import java.util.stream.*;

Path dossier = Paths.get("data");

// Methode 1 : Files.list avec Stream
try (Stream<Path> stream = Files.list(dossier)) {
    stream.filter(Files::isRegularFile)
          .filter(p -> p.toString().endsWith(".txt"))
          .forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}

// Methode 2 : Files.newDirectoryStream
try (DirectoryStream<Path> stream = Files.newDirectoryStream(dossier, "*.txt")) {
    for (Path fichier : stream) {
        System.out.println(fichier.getFileName());
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### Lire un fichier texte ligne par ligne

```java
import java.io.*;

try (BufferedReader reader = new BufferedReader(new FileReader("data/song.txt"))) {
    String ligne;
    while ((ligne = reader.readLine()) != null) {
        String[] parts = ligne.split("=", 2);
        String cle = parts[0].trim();
        String valeur = parts[1].trim();
        System.out.println(cle + " -> " + valeur);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### Serialiser un objet

```java
import java.io.*;

Song song = new Song("Titre", "Artiste", "Album", 300, "Rock");

try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("output/song.ser"))) {
    oos.writeObject(song);
    System.out.println("Objet serialise !");
} catch (IOException e) {
    e.printStackTrace();
}
```

### Deserialiser un objet

```java
import java.io.*;

try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("output/song.ser"))) {
    Song song = (Song) ois.readObject();
    System.out.println(song);
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```


---

## Solution

La solution se trouve dans le dossier `src/com/demo/`.
