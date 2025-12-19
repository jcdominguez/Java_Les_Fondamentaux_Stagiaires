# TP 10 : Stream & Lambda - Analyse de clients

## Objectif

Apprendre a utiliser les expressions lambda et l'API Stream pour manipuler des collections de donnees de maniere fonctionnelle.

**Concepts a valider :**
- Comprendre et utiliser une expression lambda
- Manipuler les interfaces fonctionnelles : `Predicate`, `Function`, `Consumer`, `Supplier`, `BinaryOperator`
- Construire des pipelines Stream : `filter`, `map`, `forEach`, `reduce`, `findFirst`
- Utiliser les Collectors pour agreger des donnees

## Structure du projet

```
10_Stream_Lambda/
├── enonce.md
├── src/
│   └── com/
│       └── demo/
│           └── stream/
│               ├── Client.java            # Classe modele (fournie)
│               ├── TraitementClients.java # Exercices 1-4
│               └── ClientAnalytics.java   # Mini-projet final
└── out/
    └── production/
```

---

## Preparation : Classe Client (fournie)

La classe `Client` est fournie et represente un client avec ses informations :

```java
package com.demo.stream;

public class Client {
    private String nom;
    private String ville;
    private int age;
    private double depenses;

    public Client(String nom, String ville, int age, double depenses) {
        this.nom = nom;
        this.ville = ville;
        this.age = age;
        this.depenses = depenses;
    }

    public String getNom() { return nom; }
    public String getVille() { return ville; }
    public int getAge() { return age; }
    public double getDepenses() { return depenses; }

    @Override
    public String toString() {
        return nom + " (" + ville + ", " + age + " ans, " + depenses + " EUR)";
    }
}
```

**Donnees de test** a utiliser dans `TraitementClients.java` :

```java
List<Client> clients = List.of(
    // Paris (4)
    new Client("Alice", "Paris", 32, 1500.0),
    new Client("Charlie", "Paris", 45, 2500.0),
    new Client("Isabelle", "Paris", 29, 1800.0),
    new Client("Marc", "Paris", 52, 3200.0),
    // Lyon (3)
    new Client("Bob", "Lyon", 25, 800.0),
    new Client("Eve", "Lyon", 35, 950.0),
    new Client("Nicolas", "Lyon", 41, 1600.0),
    // Marseille (2)
    new Client("Diana", "Marseille", 28, 1200.0),
    new Client("Olivier", "Marseille", 38, 2100.0),
    // Bordeaux (2)
    new Client("Francois", "Bordeaux", 33, 1350.0),
    new Client("Sophie", "Bordeaux", 27, 720.0),
    // Toulouse (2)
    new Client("Guillaume", "Toulouse", 44, 1900.0),
    new Client("Julie", "Toulouse", 31, 1100.0),
    // Nantes (2)
    new Client("Hugo", "Nantes", 26, 650.0),
    new Client("Lea", "Nantes", 39, 1450.0),
    // Nice (2)
    new Client("Kevin", "Nice", 48, 2800.0),
    new Client("Marie", "Nice", 23, 550.0),
    // Lille (1)
    new Client("Paul", "Lille", 36, 1750.0)
);
```

---

## Exercice 1 : Premieres lambdas

**Objectif** : Manipuler `Predicate`, `Function`, `Consumer`

### A faire :

1. Creer un `Predicate<Client>` pour verifier si un client est **parisien**
2. Creer un `Predicate<Client>` pour verifier si un client est **premium** (depenses > 1000)
3. Creer une `Function<Client, String>` qui extrait le **nom** du client
4. Creer un `Consumer<String>` qui affiche `"Client VIP : <nom>"`

### Question :

Combiner les `Predicate` avec `.and()` pour filtrer les clients parisiens ET premium, puis afficher leurs noms.

**Squelette de code :**

```java
package com.demo.stream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class TraitementClients {
    public static void main(String[] args) {
        List<Client> clients = List.of(
            // Paris (4)
            new Client("Alice", "Paris", 32, 1500.0),
            new Client("Charlie", "Paris", 45, 2500.0),
            new Client("Isabelle", "Paris", 29, 1800.0),
            new Client("Marc", "Paris", 52, 3200.0),
            // Lyon (3)
            new Client("Bob", "Lyon", 25, 800.0),
            new Client("Eve", "Lyon", 35, 950.0),
            new Client("Nicolas", "Lyon", 41, 1600.0),
            // ... autres clients (voir donnees completes)
        );

        // TODO : Creer les lambdas
        Predicate<Client> estParisien = /* ... */;
        Predicate<Client> estPremium = /* ... */;
        Function<Client, String> versNom = /* ... */;
        Consumer<String> afficherVIP = /* ... */;

        // TODO : Combiner et tester
    }
}
```

**Resultat attendu :**

```
Client VIP : Alice
Client VIP : Charlie
```

---

## Exercice 2 : Pipeline Stream complet

**Objectif** : Construire un pipeline avec `filter()`, `map()`, `forEach()`

### A faire :

Reproduire le meme resultat que l'exercice 1 mais avec un pipeline Stream :

```java
// TODO : Construire le pipeline Stream
// Utiliser filter(), map() et forEach()
// avec les lambdas creees a l'exercice 1
clients.stream()
       // ...
```

### Variante inline :

Reecrivez le pipeline en une seule expression sans variables intermediaires :

```java
clients.stream()
       .filter(c -> /* condition parisien ET premium */)
       .map(Client::getNom)  // Reference de methode
       .forEach(nom -> System.out.println("-> " + nom));
```

**Resultat attendu :**

```
Pipeline Stream - Clients parisiens premium :
Client VIP : Alice
Client VIP : Charlie

Version inline :
-> Alice
-> Charlie
```

---

## Exercice 3 : Supplier et findFirst

**Objectif** : Fournir une valeur par defaut avec `Supplier` et utiliser `Optional`

### A faire :

1. Creer un `Supplier<Client>` qui retourne un client par defaut :
   ```java
   Supplier<Client> clientParDefaut = () -> new Client("Inconnu", "N/A", 0, 0.0);
   ```

2. Rechercher le **premier client premium** avec `findFirst()` :
   ```java
   Client premierPremium = clients.stream()
       .filter(estPremium)
       .findFirst()
       .orElseGet(clientParDefaut);
   ```

### Question :

Que se passe-t-il si on cherche un client avec `depenses > 5000` ? Testez et observez.

**Resultat attendu :**

```
Premier client premium trouve : Alice (Paris, 32 ans, 1500.0 EUR)
Client avec depenses > 5000 : Inconnu (N/A, 0 ans, 0.0 EUR)
```

---

## Exercice 4 : reduce et BinaryOperator

**Objectif** : Agreger des donnees numeriques et trouver des extremums

### Partie A : Calcul du total

Calculer le **total des depenses** de tous les clients :

```java
double totalDepenses = clients.stream()
    .map(Client::getDepenses)
    .reduce(0.0, Double::sum);
```

Ou avec `mapToDouble` (plus efficace) :

```java
double total = clients.stream()
    .mapToDouble(Client::getDepenses)
    .sum();
```

### Partie B : Client le plus depensier

**Methode 1** - Avec `BinaryOperator` :

```java
BinaryOperator<Client> plusDepensier = (c1, c2) ->
    c1.getDepenses() > c2.getDepenses() ? c1 : c2;

Client topClient = clients.stream()
    .reduce(plusDepensier)
    .orElse(null);
```

**Methode 2** - Avec `max()` et `Comparator` :

```java
Client topClient = clients.stream()
    .max(Comparator.comparingDouble(Client::getDepenses))
    .orElse(null);
```

**Resultat attendu :**

```
Total des depenses : 6000.0 EUR
Client le plus depensier : Charlie (Paris, 45 ans, 2500.0 EUR)
Moyenne des depenses : 1500.00 EUR
```

---

## Exercice 5 (optionnel) : Statistiques avancees

**Objectif** : Utiliser les `Collectors` pour des agregations complexes

### A faire :

1. **Trier les clients par age** :
   ```java
   clients.stream()
       .sorted(Comparator.comparingInt(Client::getAge))
       .forEach(System.out::println);
   ```

2. **Grouper les clients par ville** :
   ```java
   Map<String, List<Client>> parVille = clients.stream()
       .collect(Collectors.groupingBy(Client::getVille));
   ```

3. **Compter les clients par ville** :
   ```java
   Map<String, Long> comptage = clients.stream()
       .collect(Collectors.groupingBy(
           Client::getVille,
           Collectors.counting()
       ));
   ```

4. **Calculer la moyenne des depenses** :
   ```java
   double moyenne = clients.stream()
       .collect(Collectors.averagingDouble(Client::getDepenses));
   ```

5. **Extraire les noms tries** :
   ```java
   List<String> nomsTries = clients.stream()
       .map(Client::getNom)
       .sorted()
       .toList();  // Java 16+ ou .collect(Collectors.toList())
   ```

---

## Mini-projet : ClientAnalytics

**Objectif** : Creer une classe utilitaire complete utilisant les Streams

### Classe a creer : `ClientAnalytics.java`

```java
package com.demo.stream;

import java.util.*;
import java.util.stream.Collectors;

public class ClientAnalytics {

    /**
     * Retourne les clients premium (depenses > 1000)
     */
    public static List<Client> getPremiumClients(List<Client> clients) {
        // TODO : Implementer avec Stream
        return null;
    }

    /**
     * Calcule le total des depenses
     */
    public static double getTotalDepenses(List<Client> clients) {
        // TODO : Implementer avec Stream
        return 0.0;
    }

    /**
     * Trouve le client ayant le plus depense
     */
    public static Optional<Client> getClientLePlusDepensier(List<Client> clients) {
        // TODO : Implementer avec Stream
        return Optional.empty();
    }

    /**
     * Compte le nombre de clients par ville
     */
    public static Map<String, Long> compterClientsParVille(List<Client> clients) {
        // TODO : Implementer avec Stream et Collectors.groupingBy
        return null;
    }

    public static void main(String[] args) {
        // 18 clients - voir donnees completes dans la section "Preparation"
        List<Client> clients = List.of(
            new Client("Alice", "Paris", 32, 1500.0),
            new Client("Charlie", "Paris", 45, 2500.0),
            // ... (18 clients au total)
        );

        // TODO : Tester toutes les methodes
    }
}
```

**Resultat attendu :**

```
=== ClientAnalytics - Demonstration ===

Clients premium (depenses > 1000 EUR) :
  - Alice (Paris, 32 ans, 1500.0 EUR)
  - Charlie (Paris, 45 ans, 2500.0 EUR)
  - Diana (Marseille, 28 ans, 1200.0 EUR)

Total des depenses : 7150.0 EUR

Client le plus depensier : Charlie (Paris, 45 ans, 2500.0 EUR)

Nombre de clients par ville :
  - Paris : 2 client(s)
  - Lyon : 2 client(s)
  - Marseille : 1 client(s)
```



---

## Indices / Rappels de syntaxe

### Interfaces fonctionnelles

```java
import java.util.function.*;

// Predicate<T> : T -> boolean
Predicate<String> estVide = s -> s.isEmpty();
boolean resultat = estVide.test("");  // true
// Combinaison : .and(), .or(), .negate()

// Function<T,R> : T -> R
Function<String, Integer> longueur = s -> s.length();
int len = longueur.apply("Hello");  // 5

// Consumer<T> : T -> void
Consumer<String> afficher = s -> System.out.println(s);
afficher.accept("Bonjour");

// Supplier<T> : () -> T
Supplier<Double> aleatoire = () -> Math.random();
double val = aleatoire.get();

// BinaryOperator<T> : (T, T) -> T
BinaryOperator<Integer> somme = (a, b) -> a + b;
int total = somme.apply(3, 5);  // 8
```

### Pipeline Stream

```java
import java.util.stream.*;

List<String> resultats = liste.stream()
    .filter(x -> /* condition */)     // Filtre les elements
    .map(x -> /* transformation */)   // Transforme chaque element
    .sorted()                         // Trie
    .distinct()                       // Supprime les doublons
    .limit(10)                        // Limite a 10 elements
    .collect(Collectors.toList());    // Collecte en List

// Operations terminales
.forEach(x -> /* action */);    // Execute une action
.count();                       // Compte les elements
.findFirst();                   // Premier element (Optional)
.reduce(identity, accumulator); // Agregation
.collect(Collectors.xxx());     // Collection
```

### Optional

```java
Optional<String> opt = Optional.of("valeur");
Optional<String> vide = Optional.empty();

String val = opt.orElse("defaut");           // Valeur ou defaut
String val2 = opt.orElseGet(() -> "defaut"); // Valeur ou Supplier
opt.ifPresent(v -> System.out.println(v));   // Si present, execute
```

### Collectors utiles

```java
import java.util.stream.Collectors;

// Collecter en List
.collect(Collectors.toList())

// Collecter en Set
.collect(Collectors.toSet())

// Grouper par critere
.collect(Collectors.groupingBy(Client::getVille))

// Grouper et compter
.collect(Collectors.groupingBy(Client::getVille, Collectors.counting()))

// Moyenne
.collect(Collectors.averagingDouble(Client::getDepenses))

// Joindre des Strings
.collect(Collectors.joining(", "))
```

### References de methode

```java
// Instance::methode
Client::getNom        // equivalent a : c -> c.getNom()
String::length        // equivalent a : s -> s.length()

// Classe::methodeStatique
Integer::parseInt     // equivalent a : s -> Integer.parseInt(s)
Math::abs             // equivalent a : n -> Math.abs(n)

// System.out::println
System.out::println   // equivalent a : x -> System.out.println(x)
```


---

## Solution

La solution complete se trouve dans le dossier `src/com/demo/stream/`.
