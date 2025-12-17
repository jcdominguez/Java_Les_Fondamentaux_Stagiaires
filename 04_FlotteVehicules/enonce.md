# TP 04 : Gestion d'une Flotte de Vehicules

## Introduction

Ce TP vous guide pas a pas dans la creation d'un systeme de gestion de flotte de vehicules pour une entreprise de location. Vous allez decouvrir progressivement les concepts fondamentaux de la programmation orientee objet en Java.

**Le TP est divise en 5 parties progressives.** Chaque partie introduit 2-3 nouveaux concepts. Completez chaque partie avant de passer a la suivante.

## Architecture finale

A la fin du TP, vous aurez cree cette architecture :

```
                     Louable (interface)
                         ^
                         | implements
                         |
    Moteur <--------- Vehicule (abstraite)
   (composition)         ^
                        /|\
                       / | \
                      /  |  \
               Voiture  Moto  Camion


    Client <-------- Reservation --------> Vehicule
              (association)         (association)
```

## Structure du projet

```
04_FlotteVehicules/
├── src/
│   └── com/
│       └── demo/
│           ├── TypeMoteur.java      (Partie 1)
│           ├── Moteur.java          (Partie 1)
│           ├── RunPartie1.java      (Partie 1)
│           ├── Louable.java         (Partie 2)
│           ├── Vehicule.java        (Partie 2)
│           ├── RunPartie2.java      (Partie 2)
│           ├── Voiture.java         (Partie 3)
│           ├── Moto.java            (Partie 3)
│           ├── Camion.java          (Partie 3)
│           ├── RunPartie3.java      (Partie 3)
│           ├── RunPartie4.java      (Partie 4)
│           ├── Client.java          (Partie 5)
│           ├── Reservation.java     (Partie 5)
│           └── FlotteVehicules.java (Partie 5)
├── out/
│   └── production/
└── enonce.md
```

---

# Partie 1 : Fondations - Enum et Classe simple

## Objectifs

- Comprendre l'encapsulation (attributs prives + getters/setters)
- Creer une enumeration simple
- Utiliser les classes enveloppes (Wrappers) : `Integer`, `Double`
- Redefinir la methode `toString()`

## Concepts introduits

| Concept | Description |
|---------|-------------|
| **Encapsulation** | Attributs `private`, acces via getters/setters |
| **Enum** | Type enumere pour definir un ensemble fini de constantes |
| **Wrappers** | `Integer`, `Double` au lieu de `int`, `double` |
| **toString()** | Redefinition pour affichage personnalise |

## Classes a creer

### 1. `TypeMoteur.java` (enum)

Une enumeration representant les types de motorisation :

**Constantes** : `ESSENCE`, `DIESEL`, `ELECTRIQUE`, `HYBRIDE`

### 2. `Moteur.java` (classe)

Une classe representant un moteur de vehicule.

**Attributs** (tous `private`, avec Wrappers) :
- `type` : TypeMoteur
- `puissance` : Integer (en chevaux)
- `consommation` : Double (en L/100km ou kWh/100km)

**Methodes** :
- Constructeur avec tous les attributs
- Getters pour chaque attribut
- `toString()` : retourne une description formatee

### 3. `RunPartie1.java` (classe de test)

Une classe avec une methode `main` pour tester vos implementations.

## Cahier des charges detaille

### TypeMoteur.java

```java
package com.demo;

public enum TypeMoteur {
    // TODO: Declarer les constantes
}
```

### Moteur.java

```java
package com.demo;

public class Moteur {
    // TODO: Attributs prives avec Wrappers (Integer, Double)

    // TODO: Constructeur

    // TODO: Getters

    // TODO: toString() - format: "DIESEL 150ch - 7.5 L/100km"
}
```

## Indices / Rappels de syntaxe

### Enum simple

```java
public enum Couleur {
    ROUGE,
    BLEU,
    VERT,
    JAUNE
}

// Utilisation
Couleur c = Couleur.ROUGE;
System.out.println(c);         // "ROUGE"
System.out.println(c.name());  // "ROUGE"

// Parcourir toutes les valeurs
for (Couleur couleur : Couleur.values()) {
    System.out.println(couleur);
}
```

### Wrappers

```java
// Primitif vs Wrapper
int primitif = 42;
Integer wrapper = 42;  // Auto-boxing

// Pourquoi les Wrappers ?
// - Peuvent etre null (utile pour valeurs optionnelles)
// - Necessaires pour les collections (ArrayList<Integer>)
// - Methodes utilitaires (Integer.parseInt(), etc.)
```

### Redefinition de toString()

```java
@Override
public String toString() {
    return "Description: " + attribut1 + ", " + attribut2;
}
```

## Test attendu

### RunPartie1.java

```java
package com.demo;

public class RunPartie1 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 1 ===\n");

        // TODO: Tester TypeMoteur
        // - Creer des variables TypeMoteur (ESSENCE, ELECTRIQUE, etc.)
        // - Afficher ces valeurs
        // - Parcourir toutes les valeurs avec TypeMoteur.values()

        // TODO: Tester Moteur
        // - Creer 2 instances de Moteur avec differents types
        // - Afficher avec toString()
        // - Tester les getters (getType, getPuissance, getConsommation)

        System.out.println("\n=== PARTIE 1 TERMINEE ===");
    }
}
```

### Sortie console attendue

```
=== TEST PARTIE 1 ===

--- Test TypeMoteur ---
Type 1 : ESSENCE
Type 2 : ELECTRIQUE

Tous les types de moteur :
- ESSENCE
- DIESEL
- ELECTRIQUE
- HYBRIDE

--- Test Moteur ---
DIESEL 150ch - 7.5 L/100km
ELECTRIQUE 204ch - 18.0 L/100km

--- Test Getters ---
Type de m1 : DIESEL
Puissance de m1 : 150 ch
Consommation de m1 : 7.5 L/100km

=== PARTIE 1 TERMINEE ===
```


---

# Partie 2 : Interface et Classe abstraite

## Objectifs

- Creer une interface definissant un contrat
- Creer une classe abstraite implementant une interface
- Comprendre la composition (un objet contient un autre objet)
- Utiliser les methodes abstraites

## Concepts introduits

| Concept | Description |
|---------|-------------|
| **Interface** | Contrat definissant des methodes a implementer |
| **Classe abstraite** | Classe non instanciable avec methodes abstraites |
| **Composition** | Un objet `Vehicule` contient un objet `Moteur` |
| **Methode abstraite** | Methode sans corps, a implementer par les sous-classes |

## Classes a creer

### 1. `Louable.java` (interface)

Une interface definissant le contrat pour tout objet louable.

**Methodes a declarer** :
- `Double calculerTarif(int nbJours)` : calcule le tarif pour N jours
- `String getDescription()` : retourne une description de l'objet

### 2. `Vehicule.java` (classe abstraite)

La classe parent de tous les vehicules.

**Attributs** (tous `private` ou `protected`, avec Wrappers) :
- `immatriculation` : String
- `marque` : String
- `modele` : String
- `annee` : Integer
- `disponible` : Boolean (initialise a `true`)
- `tarifJournalier` : Double
- `moteur` : Moteur (composition)

**Constructeur** :
- Recoit les infos du vehicule ET les infos du moteur
- Cree l'objet `Moteur` en interne (composition)

**Methodes abstraites** :
- `void demarrer()` : chaque type de vehicule demarre differemment
- `String getTypeVehicule()` : retourne "Voiture", "Moto" ou "Camion"

**Methodes concretes** :
- Implementation de `calculerTarif(int nbJours)` : `tarifJournalier * nbJours`
- Implementation de `getDescription()` : `marque + " " + modele + " (" + annee + ")"`
- Getters/setters
- `toString()`

## Cahier des charges detaille

### Louable.java

```java
package com.demo;

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
```

### Vehicule.java (structure)

```java
package com.demo;

public abstract class Vehicule implements Louable {
    // TODO: Attributs (protected pour acces par sous-classes)

    // TODO: Constructeur - cree le Moteur en interne (composition)
    // public Vehicule(String immat, String marque, String modele, Integer annee,
    //                 TypeMoteur typeMoteur, Integer puissance, Double conso,
    //                 Double tarifJournalier)

    // TODO: Methodes abstraites
    // public abstract void demarrer();
    // public abstract String getTypeVehicule();

    // TODO: Implementation de Louable
    // calculerTarif() et getDescription()

    // TODO: Getters/setters

    // TODO: toString()
}
```

## Indices / Rappels de syntaxe

### Interface

```java
public interface MonInterface {
    // Toutes les methodes sont publiques et abstraites par defaut
    void methode1();
    String methode2(int param);
}
```

### Classe abstraite implementant une interface

```java
public abstract class MaClasse implements MonInterface {
    // Peut implementer certaines methodes de l'interface
    @Override
    public void methode1() {
        System.out.println("Implementation concrete");
    }

    // Peut laisser d'autres methodes abstraites
    // methode2() sera implementee par les sous-classes

    // Peut avoir ses propres methodes abstraites
    public abstract void nouvelleMethode();
}
```

### Composition

```java
public class Voiture {
    private Moteur moteur;  // Composition: Voiture CONTIENT un Moteur

    public Voiture(..., TypeMoteur type, Integer puissance, Double conso) {
        // Le Moteur est cree ICI, dans le constructeur
        this.moteur = new Moteur(type, puissance, conso);
    }
}
```

### Difference Composition vs Association

```
COMPOSITION (losange plein) :
- L'objet contenu est cree PAR le conteneur
- Si Vehicule est detruit, son Moteur aussi
- Exemple: Vehicule cree son Moteur

ASSOCIATION (fleche simple) :
- L'objet reference existe independamment
- Exemple: Reservation reference un Vehicule existant
```

## Test attendu

### RunPartie2.java

```java
package com.demo;

public class RunPartie2 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 2 ===\n");

        // NOTE: On ne peut PAS instancier une classe abstraite
        // Vehicule v = new Vehicule(...);  // ERREUR de compilation !

        // TODO: Verifier que le code compile correctement
        // - L'interface Louable existe avec ses 2 methodes
        // - La classe abstraite Vehicule implemente Louable
        // - Les tests fonctionnels seront possibles en Partie 3

        System.out.println("Interface Louable : OK");
        System.out.println("Classe abstraite Vehicule : OK");
        System.out.println("\n=== PARTIE 2 TERMINEE ===");
    }
}
```

### Sortie console attendue

```
=== TEST PARTIE 2 ===

Interface Louable : OK
Classe abstraite Vehicule : OK
(Impossible d'instancier Vehicule directement)

=== PARTIE 2 TERMINEE ===
Passez a la Partie 3 pour creer les sous-classes.
```


---

# Partie 3 : Heritage et Redefinition

## Objectifs

- Creer des sous-classes avec `extends`
- Appeler le constructeur parent avec `super()`
- Redefinir des methodes avec `@Override`
- Implementer les methodes abstraites

## Concepts introduits

| Concept | Description |
|---------|-------------|
| **Heritage** | `class Voiture extends Vehicule` |
| **super()** | Appel du constructeur de la classe parent |
| **@Override** | Annotation indiquant une redefinition |
| **Methodes concretes** | Implementation des methodes abstraites |

## Classes a creer

### 1. `Voiture.java`

**Attributs specifiques** :
- `nombrePortes` : Integer
- `climatisation` : Boolean

**Methodes** :
- Constructeur appelant `super(...)` puis initialisant les attributs specifiques
- `demarrer()` : affiche "La voiture [marque] [modele] demarre en douceur."
- `getTypeVehicule()` : retourne "Voiture"
- Getters pour les attributs specifiques
- `toString()` : appelle `super.toString()` et ajoute les infos specifiques

### 2. `Moto.java`

**Attributs specifiques** :
- `cylindree` : Integer (en cc)
- `typeMoto` : String ("Roadster", "Sportive", "Trail", etc.)

**Methodes** :
- Constructeur, `demarrer()` : "La moto [marque] [modele] vrombrit !"
- `getTypeVehicule()` : retourne "Moto"
- Getters, `toString()`

### 3. `Camion.java`

**Attributs specifiques** :
- `capaciteTonnes` : Double
- `hayon` : Boolean

**Methodes** :
- Constructeur, `demarrer()` : "Le camion [marque] [modele] demarre lourdement."
- `getTypeVehicule()` : retourne "Camion"
- Getters, `toString()`

## Cahier des charges detaille

### Voiture.java (structure)

```java
package com.demo;

public class Voiture extends Vehicule {
    // TODO: Attributs specifiques

    // TODO: Constructeur
    // public Voiture(String immat, String marque, String modele, Integer annee,
    //                TypeMoteur typeMoteur, Integer puissance, Double conso,
    //                Double tarifJournalier,
    //                Integer nombrePortes, Boolean climatisation)

    // TODO: Implementation des methodes abstraites
    @Override
    public void demarrer() {
        // "La voiture [marque] [modele] demarre en douceur."
    }

    @Override
    public String getTypeVehicule() {
        return "Voiture";
    }

    // TODO: Getters

    // TODO: toString() - appeler super.toString() et completer
}
```

## Indices / Rappels de syntaxe

### Heritage et super()

```java
public class Enfant extends Parent {
    private String attributEnfant;

    public Enfant(String attrParent, String attrEnfant) {
        super(attrParent);  // DOIT etre la premiere instruction
        this.attributEnfant = attrEnfant;
    }
}
```

### Redefinition avec @Override

```java
@Override
public void methode() {
    // Nouvelle implementation
}

@Override
public String toString() {
    return super.toString() + " - Info supplementaire";
}
```

### Acces aux attributs protected du parent

```java
public class Enfant extends Parent {
    @Override
    public void demarrer() {
        // Acces direct aux attributs protected du parent
        System.out.println("La " + marque + " " + modele + " demarre");
    }
}
```

## Test attendu

### RunPartie3.java

```java
package com.demo;

public class RunPartie3 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 3 ===\n");

        // TODO: Creer et tester une Voiture
        // - Instancier avec tous les parametres
        // - Afficher avec toString()
        // - Appeler demarrer()
        // - Tester getTypeVehicule() et calculerTarif()

        // TODO: Creer et tester une Moto
        // - Memes tests que pour Voiture

        // TODO: Creer et tester un Camion
        // - Memes tests que pour Voiture

        System.out.println("\n=== PARTIE 3 TERMINEE ===");
    }
}
```

### Sortie console attendue

```
=== TEST PARTIE 3 ===

--- Test Voiture ---
Voiture AA-123-BB : Renault Clio (2022) - Essence 90ch - 5 portes, Clim: Oui
La voiture Renault Clio demarre en douceur.
Type: Voiture
Tarif 5 jours: 225.0 EUR

--- Test Moto ---
Moto CC-456-DD : Yamaha MT-07 (2023) - Essence 75ch - 689cc Roadster
La moto Yamaha MT-07 vrombrit !
Type: Moto

--- Test Camion ---
Camion EE-789-FF : Mercedes Actros (2021) - Diesel 400ch - 20.0T, Hayon: Oui
Le camion Mercedes Actros demarre lourdement.
Type: Camion

=== PARTIE 3 TERMINEE ===
```


---

# Partie 4 : Polymorphisme et Collections

## Objectifs

- Stocker differents types dans une collection de type parent (upcasting)
- Comprendre les appels polymorphiques
- Utiliser `instanceof` pour verifier le type
- Effectuer un downcasting securise
- Redefinir `equals()` pour comparer des objets

## Concepts introduits

| Concept | Description |
|---------|-------------|
| **ArrayList<Vehicule>** | Collection pouvant contenir Voiture, Moto, Camion |
| **Upcasting** | `Vehicule v = new Voiture(...)` |
| **Polymorphisme** | `v.demarrer()` appelle la bonne implementation |
| **instanceof** | `if (v instanceof Voiture)` |
| **Downcasting** | `Voiture voit = (Voiture) v` |
| **equals()** | Comparaison logique d'objets |

## Modifications a effectuer

### 1. Ajouter `equals()` dans `Vehicule.java`

Deux vehicules sont egaux s'ils ont la meme immatriculation.

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || !(obj instanceof Vehicule)) return false;
    Vehicule other = (Vehicule) obj;
    return this.immatriculation.equals(other.immatriculation);
}
```

### 2. Creer `RunPartie4.java`

Un test demonstrant tous les concepts de polymorphisme.

## Cahier des charges detaille

### RunPartie4.java

Ce fichier doit demontrer :

1. **Creation d'une ArrayList<Vehicule>** contenant differents types
2. **Upcasting** : ajout de Voiture/Moto/Camion dans la liste
3. **Parcours polymorphique** : appel de `toString()` et `demarrer()`
4. **Verification de type** avec `instanceof`
5. **Downcasting** pour acceder aux methodes specifiques
6. **Test de equals()** : comparaison par immatriculation

## Indices / Rappels de syntaxe

### ArrayList generique

```java
import java.util.ArrayList;

ArrayList<Vehicule> liste = new ArrayList<>();
liste.add(new Voiture(...));  // Upcasting implicite
liste.add(new Moto(...));
```

### Polymorphisme

```java
for (Vehicule v : liste) {
    System.out.println(v);  // toString() de Voiture, Moto ou Camion
    v.demarrer();           // demarrer() de Voiture, Moto ou Camion
}
```

### instanceof et downcasting

```java
for (Vehicule v : liste) {
    if (v instanceof Voiture) {
        Voiture voiture = (Voiture) v;  // Downcasting
        System.out.println("Portes: " + voiture.getNombrePortes());
    } else if (v instanceof Moto) {
        Moto moto = (Moto) v;
        System.out.println("Cylindree: " + moto.getCylindree());
    }
}
```

### equals()

```java
@Override
public boolean equals(Object obj) {
    // 1. Meme reference ?
    if (this == obj) return true;

    // 2. Null ou type different ?
    if (obj == null || !(obj instanceof Vehicule)) return false;

    // 3. Comparer les attributs
    Vehicule other = (Vehicule) obj;
    return this.immatriculation.equals(other.immatriculation);
}
```

## Test attendu

### RunPartie4.java

```java
package com.demo;

import java.util.ArrayList;

public class RunPartie4 {
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIE 4 ===\n");

        // TODO: Creer une ArrayList<Vehicule>

        // TODO: UPCASTING - Ajouter des Voiture, Moto, Camion dans la liste

        // TODO: POLYMORPHISME - Parcourir la liste et appeler toString() et demarrer()
        // Observer que chaque type a son propre comportement

        // TODO: INSTANCEOF et DOWNCASTING
        // - Verifier le type avec instanceof
        // - Faire un cast pour acceder aux methodes specifiques
        //   (getNombrePortes pour Voiture, getCylindree pour Moto, etc.)

        // TODO: TEST EQUALS
        // - Creer 2 vehicules avec la meme immatriculation
        // - Creer 1 vehicule avec une immatriculation differente
        // - Verifier que equals() fonctionne correctement

        System.out.println("\n=== PARTIE 4 TERMINEE ===");
    }
}
```

### Sortie console attendue

```
=== TEST PARTIE 4 ===

--- Ajout de vehicules (Upcasting) ---
3 vehicules ajoutes a la flotte.

--- Parcours polymorphique ---
Voiture AA-123-BB : Renault Clio (2022) - Essence 90ch - 5 portes, Clim: Oui
La voiture Renault Clio demarre en douceur.

Moto CC-456-DD : Yamaha MT-07 (2023) - Essence 75ch - 689cc Roadster
La moto Yamaha MT-07 vrombrit !

Camion EE-789-FF : Mercedes Actros (2021) - Diesel 400ch - 20.0T, Hayon: Oui
Le camion Mercedes Actros demarre lourdement.

--- Downcasting avec instanceof ---
Renault Clio (2022) -> C'est une voiture avec 5 portes
Yamaha MT-07 (2023) -> C'est une moto de 689cc (Roadster)
Mercedes Actros (2021) -> C'est un camion de 20.0 tonnes

--- Test equals() ---
v1: AA-123-BB
v2: AA-123-BB
v3: ZZ-999-ZZ
v1.equals(v2) [meme immat] : true
v1.equals(v3) [immat diff] : false

=== PARTIE 4 TERMINEE ===
```

---

# Partie 5 : Association et Application complete

## Objectifs

- Comprendre la difference entre composition et association
- Utiliser des variables `static` pour les compteurs
- Creer une application complete avec menu interactif

## Concepts introduits

| Concept | Description |
|---------|-------------|
| **Association** | `Reservation` reference des objets existants |
| **Static** | Variable de classe partagee (compteur) |
| **Auto-increment** | ID genere automatiquement |
| **Integration** | Application complete combinant tous les concepts |

## Classes a creer

### 1. `Client.java`

**Attributs** :
- `id` : Integer (genere automatiquement)
- `nom` : String
- `telephone` : String

**Variable static** :
- `compteurId` : int (pour generer les IDs)

**Methodes** :
- Constructeur (genere l'ID automatiquement)
- Getters
- `toString()`
- `equals()` : compare par `id`

### 2. `Reservation.java`

**Attributs** :
- `numero` : int (genere automatiquement)
- `vehicule` : Vehicule (association)
- `client` : Client (association)
- `nbJours` : int
- `dateDebut` : String

**Variable static** :
- `compteur` : int (pour numerotation)

**Methodes** :
- Constructeur : recoit un Vehicule et un Client existants, marque le vehicule indisponible
- `getMontantTotal()` : appelle `vehicule.calculerTarif(nbJours)` (polymorphisme)
- `annuler()` : marque le vehicule disponible
- Getters
- `toString()`

### 3. `FlotteVehicules.java`

La classe principale avec le menu interactif.

**Attributs** :
- `flotte` : ArrayList<Vehicule>
- `reservations` : ArrayList<Reservation>
- `scanner` : Scanner

**Menu** :
1. Afficher l'inventaire complet
2. Rechercher par type de vehicule
3. Afficher les vehicules disponibles
4. Creer une reservation
5. Afficher les reservations
6. Comparer deux vehicules
7. Statistiques de la flotte
8. Quitter

## Cahier des charges detaille

### Client.java

```java
package com.demo;

public class Client {
    private static int compteurId = 0;  // Variable de classe

    // TODO: Attributs prives (id, nom, telephone)

    // TODO: Constructeur
    // - Utiliser ++compteurId pour generer l'ID automatiquement

    // TODO: Getters, toString(), equals()
}
```

### Reservation.java

```java
package com.demo;

public class Reservation {
    private static int compteur = 0;

    // TODO: Attributs
    // - numero (int)
    // - vehicule (Vehicule) - ASSOCIATION
    // - client (Client) - ASSOCIATION
    // - nbJours (int)
    // - dateDebut (String)

    // TODO: Constructeur
    // - Generer le numero avec ++compteur
    // - Stocker les REFERENCES aux objets existants (pas de new)
    // - Marquer le vehicule comme indisponible

    // TODO: getMontantTotal() - utilise calculerTarif() du vehicule

    // TODO: annuler() - remet le vehicule disponible

    // TODO: Getters, toString()
}
```

### Difference Composition vs Association

```
PARTIE 2 - COMPOSITION (Vehicule -> Moteur) :
- Le Moteur est CREE par le Vehicule
- Le Moteur n'existe pas avant le Vehicule
- Code: this.moteur = new Moteur(...)

PARTIE 5 - ASSOCIATION (Reservation -> Vehicule, Client) :
- Le Vehicule et le Client existent DEJA
- Reservation les REFERENCE simplement
- Code: this.vehicule = vehicule  (pas de new)
```

## Indices / Rappels de syntaxe

### Variable static

```java
public class Client {
    // Variable de CLASSE (partagee par toutes les instances)
    private static int compteur = 0;

    // Variable d'INSTANCE (propre a chaque objet)
    private Integer id;

    public Client(...) {
        this.id = ++compteur;  // Incremente le compteur partage
    }
}
```

### Association

```java
public class Reservation {
    private Vehicule vehicule;  // Juste une reference

    public Reservation(Vehicule vehiculeExistant, ...) {
        // On ne cree PAS le vehicule, on le reference
        this.vehicule = vehiculeExistant;
    }
}
```

## Test attendu - Application complete

### FlotteVehicules.java (structure simplifiee)

```java
package com.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class FlotteVehicules {
    private ArrayList<Vehicule> flotte;
    private ArrayList<Reservation> reservations;
    private Scanner scanner;

    public FlotteVehicules() {
        this.flotte = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initialiserFlotte();
    }

    private void initialiserFlotte() {
        // Ajouter quelques vehicules de demonstration
        flotte.add(new Voiture("AA-123-BB", "Renault", "Clio", 2022,
                TypeMoteur.ESSENCE, 90, 6.5, 45.0, 5, true));
        flotte.add(new Voiture("BB-234-CC", "Peugeot", "308", 2023,
                TypeMoteur.DIESEL, 130, 5.2, 55.0, 5, true));
        flotte.add(new Moto("CC-456-DD", "Yamaha", "MT-07", 2023,
                TypeMoteur.ESSENCE, 75, 4.2, 35.0, 689, "Roadster"));
        flotte.add(new Camion("EE-789-FF", "Mercedes", "Actros", 2021,
                TypeMoteur.DIESEL, 400, 28.0, 120.0, 20.0, true));
    }

    // TODO: Implementer les methodes du menu

    public void lancerMenu() {
        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();  // Consommer le retour a la ligne

            switch (choix) {
                case 1: afficherInventaire(); break;
                case 2: rechercherParType(); break;
                case 3: afficherDisponibles(); break;
                case 4: creerReservation(); break;
                case 5: afficherReservations(); break;
                case 6: comparerVehicules(); break;
                case 7: afficherStatistiques(); break;
                case 8: System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 8);
    }

    private void afficherMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Afficher l'inventaire complet");
        System.out.println("2. Rechercher par type de vehicule");
        System.out.println("3. Afficher les vehicules disponibles");
        System.out.println("4. Creer une reservation");
        System.out.println("5. Afficher les reservations");
        System.out.println("6. Comparer deux vehicules");
        System.out.println("7. Statistiques de la flotte");
        System.out.println("8. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void main(String[] args) {
        FlotteVehicules app = new FlotteVehicules();
        app.lancerMenu();
    }
}
```

### Sortie console attendue (exemple)

```
=== MENU PRINCIPAL ===
1. Afficher l'inventaire complet
2. Rechercher par type de vehicule
3. Afficher les vehicules disponibles
4. Creer une reservation
5. Afficher les reservations
6. Comparer deux vehicules
7. Statistiques de la flotte
8. Quitter
Votre choix : 1

=== INVENTAIRE COMPLET ===
1. Voiture AA-123-BB : Renault Clio (2022) - Disponible
2. Voiture BB-234-CC : Peugeot 308 (2023) - Disponible
3. Moto CC-456-DD : Yamaha MT-07 (2023) - Disponible
4. Camion EE-789-FF : Mercedes Actros (2021) - Disponible
```

---

# Recapitulatif des concepts

| Partie | Concepts | Classes |
|--------|----------|---------|
| 1 | Encapsulation, Enum, Wrappers, toString() | TypeMoteur, Moteur |
| 2 | Interface, Classe abstraite, Composition | Louable, Vehicule |
| 3 | Heritage, super(), @Override | Voiture, Moto, Camion |
| 4 | Polymorphisme, ArrayList, instanceof, equals() | (tests) |
| 5 | Association, Static, Application complete | Client, Reservation, FlotteVehicules |

---

# Solution

Les fichiers solution se trouvent dans le dossier `src/com/demo/`.

Pour compiler et executer la solution complete :

```bash
cd 04_FlotteVehicules
javac -d out/production src/com/demo/*.java
java -cp out/production com.demo.FlotteVehicules
```

---

# Pour aller plus loin (optionnel)

## Ameliorer l'enum TypeMoteur

Dans la Partie 1, vous avez cree un enum simple. Une amelioration possible est d'enrichir l'enum avec des attributs et des methodes.

### Objectif

Modifier `TypeMoteur` pour :
- Ajouter un attribut `libelle` (nom en francais)
- Ajouter une methode `getLibelle()`
- Ajouter une methode `getUniteConsommation()` qui retourne "kWh/100km" pour ELECTRIQUE, "L/100km" sinon

### Avantages

| Aspect | Interet |
|--------|---------|
| **Encapsulation** | Montre que meme un enum peut avoir des attributs prives |
| **Constructeur** | Demontre qu'un enum peut avoir un constructeur (prive) |
| **Methodes** | On peut ajouter des comportements specifiques |
| **Donnees associees** | Chaque constante porte des informations supplementaires |

### Solution

Voir le fichier `enonce-suite.md` pour la solution complete.
