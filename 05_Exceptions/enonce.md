# Exercice 05 : Exceptions - Gestion des erreurs

## Objectif

Apprendre a gerer les exceptions en Java pour ecrire du code robuste et maintenable.

**Concepts a valider :**
- Distinguer exceptions controlees et non-controlees
- Maitriser try-catch-finally
- Utiliser try-with-resources
- Creer et lever des exceptions personnalisees

## Structure du projet

```
05_Exceptions/
├── enonce.md
├── notes.txt                    # Fichier de donnees de test
├── src/
│   └── com/
│       └── demo/
│           ├── Etudiant.java
│           ├── Main.java
│           ├── Main2.java
│           └── Main3.java
└── out/
    └── production/
```

## Contexte

Vous gerez un systeme de notes d'etudiants. Le fichier `notes.txt` contient :

```
Alice;15
Bob;12
Charlie;abc
Diana;22
Eve;8
Frank;18
Grace;0
Henri;20
Isabelle;-3
Jules;17
Kim;
Lea;14
Marc;treize
Nina;19
Oscar;21
Paul;10
Quentin;11
Rose;16
Sophie;7
Thomas;13
```

**Cas d'erreurs presents :**
- Notes hors bornes : Diana (22), Isabelle (-3), Oscar (21)
- Format invalide (texte) : Charlie (abc), Marc (treize)
- Valeur manquante : Kim (pas de note)

Le fichier `notes.txt` est fourni dans le dossier de l'exercice.

---

## Cahier des charges

### Exercice 1 : Exceptions non-controlees

**Creer la classe `Etudiant`**

```java
package com.demo;

public class Etudiant {
    private String nom;
    private int note;

    public Etudiant(String nom, int note) {
        // TODO : Lever IllegalArgumentException si :
        // - nom est null ou vide
        // - note < 0 ou note > 20

        this.nom = nom;
        this.note = note;
    }

    public String getNom() { return nom; }
    public int getNote() { return note; }

    @Override
    public String toString() {
        return nom + " : " + note + "/20";
    }
}
```

**Tester dans `Main.java`**

```java
package com.demo;

public class Main {
    public static void main(String[] args) {
        // TODO : Creer ces etudiants et gerer les erreurs avec try-catch
        // Le programme doit continuer meme apres une erreur

        creerEtudiant("Alice", 15);
        creerEtudiant("", 12);           // nom vide
        creerEtudiant("Bob", -5);        // note negative
        creerEtudiant("Charlie", 25);    // note > 20
        creerEtudiant("Diana", 18);

        System.out.println("Programme termine");
    }

    static void creerEtudiant(String nom, int note) {
        // TODO : try-catch ici
    }
}
```

**Resultat attendu :**

```
+ Cree : Alice : 15/20
x Erreur : Le nom ne peut pas etre vide
x Erreur : La note doit etre entre 0 et 20 : -5
x Erreur : La note doit etre entre 0 et 20 : 25
+ Cree : Diana : 18/20
Programme termine
```

---

### Exercice 2 : try-catch-finally

**Probleme** : lire le fichier `notes.txt` et afficher son contenu

```java
package com.demo;

import java.io.*;

public class Main2 {
    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            // TODO : Ouvrir et lire le fichier ligne par ligne
            // Afficher chaque ligne

        } catch (FileNotFoundException e) {
            // TODO : Gerer fichier non trouve

        } catch (IOException e) {
            // TODO : Gerer erreur de lecture

        } finally {
            // TODO : Fermer le reader (attention au null !)
            System.out.println("--- Fin de lecture ---");
        }
    }
}
```

**Questions a se poser :**

1. Que se passe-t-il si le fichier n'existe pas ?
2. Le message "Fin de lecture" s'affiche-t-il quand meme ?
3. Pourquoi faut-il verifier `reader != null` dans le finally ?

---

### Exercice 3 : try-with-resources

**Refactorer l'exercice 2 avec try-with-resources**

```java
package com.demo;

import java.io.*;
import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        List<Etudiant> etudiants = new ArrayList<>();

        // TODO : Utiliser try-with-resources
        // try (BufferedReader reader = new BufferedReader(new FileReader("notes.txt"))) {
        //     ...
        // }

        // Pour chaque ligne :
        // 1. Separer nom et note (split ";")
        // 2. Creer un Etudiant (attention aux erreurs de parsing !)
        // 3. L'ajouter a la liste si OK

        System.out.println("\n=== Etudiants charges ===");
        etudiants.forEach(System.out::println);

        System.out.println("\nTotal : " + etudiants.size() + " etudiant(s)");
    }
}
```

**Resultat attendu :**

```
Ligne : Alice;15 -> OK
Ligne : Bob;12 -> OK
Ligne : Charlie;abc -> Erreur de format
Ligne : Diana;22 -> Note invalide
Ligne : Eve;8 -> OK
Ligne : Frank;18 -> OK
Ligne : Grace;0 -> OK
Ligne : Henri;20 -> OK
Ligne : Isabelle;-3 -> Note invalide
Ligne : Jules;17 -> OK
Ligne : Kim; -> Format invalide (attendu: nom;note)
Ligne : Lea;14 -> OK
Ligne : Marc;treize -> Erreur de format
Ligne : Nina;19 -> OK
Ligne : Oscar;21 -> Note invalide
Ligne : Paul;10 -> OK
Ligne : Quentin;11 -> OK
Ligne : Rose;16 -> OK
Ligne : Sophie;7 -> OK
Ligne : Thomas;13 -> OK

=== Etudiants charges ===
Alice : 15/20
Bob : 12/20
Eve : 8/20
Frank : 18/20
Grace : 0/20
Henri : 20/20
Jules : 17/20
Lea : 14/20
Nina : 19/20
Paul : 10/20
Quentin : 11/20
Rose : 16/20
Sophie : 7/20
Thomas : 13/20

Total : 14 etudiant(s)
```


---

## Indices / Rappels de syntaxe

### Lever une exception

```java
if (condition) {
    throw new IllegalArgumentException("Message d'erreur");
}
```

### try-catch-finally

```java
try {
    // Code risque
} catch (ExceptionType e) {
    // Gestion de l'erreur
    System.out.println(e.getMessage());
} finally {
    // Toujours execute (nettoyage)
}
```

### try-with-resources

```java
try (BufferedReader reader = new BufferedReader(new FileReader("fichier.txt"))) {
    String ligne;
    while ((ligne = reader.readLine()) != null) {
        System.out.println(ligne);
    }
} catch (IOException e) {
    System.out.println("Erreur : " + e.getMessage());
}
// Le reader est ferme automatiquement
```

### Parser une ligne

```java
String[] parts = ligne.split(";");
String nom = parts[0];
int note = Integer.parseInt(parts[1]);  // peut lever NumberFormatException
```


---

## Solution

La solution se trouve dans le dossier `src/com/demo/`.
