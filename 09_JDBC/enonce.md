# TP 09 : Jeu des Capitales - JDBC et Base de donnees

## Objectif

Apprendre a utiliser JDBC pour interagir avec une base de donnees relationnelle en Java.

**Concepts a valider :**
- Connexion a une base de donnees H2 avec JDBC
- Execution de requetes SQL avec PreparedStatement
- Lecture de resultats avec ResultSet
- Gestion des ressources avec try-with-resources
- Gestion des erreurs avec SQLException

## Structure du projet

```
09_JDBC/
├── enonce.md
├── README.md
├── countries.mv.db              # Base de donnees H2 (a telecharger)
├── lib/
│   └── h2-*.jar                 # Driver JDBC H2 (a telecharger)
├── src/
│   └── com/
│       └── demo/
│           ├── Country.java
│           ├── CountryDAO.java
│           └── JeuCapitales.java
└── out/
    └── production/
```

---

## Base de donnees

Vous disposez d'une base de donnees H2 contenant la liste des pays europeens et leur capitale.

**Fichier a telecharger :** `countries.mv.db` (a placer a la racine du projet)

**Structure de la table `countries` :**

| Colonne | Type | Description |
|---------|------|-------------|
| id | INT | Identifiant unique (AUTO_INCREMENT) |
| name | VARCHAR(255) | Nom du pays |
| capital | VARCHAR(255) | Nom de la capitale |

---

## Configuration du driver H2

### Telecharger H2

1. Aller sur http://www.h2database.com/html/download.html
2. Telecharger : Binary JAR
3. Placer le JAR dans le dossier `lib/` du projet

### Configurer avec IntelliJ

1. File > Project Structure
2. Libraries > + > From JAR
3. Selectionner `lib/h2-*.jar`
4. OK

### Configurer avec Eclipse

1. Clic droit sur le projet > Properties
2. Java Build Path > Libraries > Add External JARs
3. Selectionner `lib/h2-*.jar`
4. OK

---

## Cahier des charges

### Etape 1 : Tester la connexion et la recuperation des donnees

Ecrire un programme qui :
- Se connecte a la base H2
- Recupere tous les pays
- Affiche la liste des pays avec leur capitale

### Etape 2 : Creer la classe Country

### Etape 3 : Creer un ArrayList de pays

Creer une classe DAO (Data Access Object) qui :
- Recupere tous les pays dans une `List<Country>`
- Permet de recuperer N pays aleatoires sans doublons

### Etape 4 : Implementer le jeu

Le programme doit :
1. Selectionner aleatoirement 4 pays
2. Choisir un de ces 4 pays pour la question
3. Afficher les 4 capitales comme propositions
4. Lire la reponse du joueur au clavier
5. Indiquer si la reponse est correcte ou non

### Etape 5 : Gestion du score

- Le joueur saisi son nom en debut de partie
- +1 point pour une bonne reponse
- -1 point pour une mauvaise reponse
- Le joueur decide quand arreter la partie
- Le score est sauvegarde en base (cumule avec l'ancien score)

**Table des scores :**

```sql
CREATE TABLE IF NOT EXISTS scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL UNIQUE,
    score INT DEFAULT 0
);
```



---

## Exemple d'execution

```
=== Jeu des Capitales ===
1. Jouer une partie
2. Voir le classement
3. Quitter
Votre choix : 1

Entrez votre nom : Alice
Bienvenue Alice !

========================================
Quelle est la capitale du pays : Espagne ?

Saisir le numero correspondant a la proposition choisie:
1. Paris
2. Londres
3. Madrid
4. Lisbonne

Votre reponse : 3
Bonne reponse ! La capitale de Espagne est bien Madrid.
Score de la partie : 1

Voulez-vous continuer ? (o/n) : o

========================================
Quelle est la capitale du pays : Allemagne ?

Saisir le numero correspondant a la proposition choisie:
1. Berlin
2. Vienne
3. Berne
4. Amsterdam

Votre reponse : 2
Mauvaise reponse ! La capitale de Allemagne est Berlin.
Score de la partie : 0

Voulez-vous continuer ? (o/n) : n

=== Fin de la partie ===
Score de cette partie : 0
Score total de Alice : 0 points

=== Jeu des Capitales ===
1. Jouer une partie
2. Voir le classement
3. Quitter
Votre choix : 3
Au revoir !
```

---

## Indices / Rappels de syntaxe

### Connexion a une base H2

```java
import java.sql.*;

String url = "jdbc:h2:./countries";  // Fichier countries.mv.db a la racine
String user = "sa";
String password = "";

try (Connection conn = DriverManager.getConnection(url, user, password)) {
    System.out.println("Connexion reussie !");
} catch (SQLException e) {
    System.out.println("Erreur : " + e.getMessage());
}
```

### Executer une requete SELECT

```java
String sql = "SELECT id, name, capital FROM countries";

try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql);
     ResultSet rs = pstmt.executeQuery()) {

    while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String capital = rs.getString("capital");
        System.out.println(name + " -> " + capital);
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

### Requete avec parametre

```java
String sql = "SELECT score FROM scores WHERE player_name = ?";

try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {

    pstmt.setString(1, "Alice");  // Remplace le ?
    ResultSet rs = pstmt.executeQuery();

    if (rs.next()) {
        System.out.println("Score : " + rs.getInt("score"));
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

### INSERT et UPDATE

```java
// INSERT
String sqlInsert = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
    pstmt.setString(1, "Alice");
    pstmt.setInt(2, 10);
    pstmt.executeUpdate();
}

// UPDATE
String sqlUpdate = "UPDATE scores SET score = score + ? WHERE player_name = ?";
try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
    pstmt.setInt(1, 5);
    pstmt.setString(2, "Alice");
    pstmt.executeUpdate();
}
```

### Lire le clavier avec Scanner

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

// Lire une chaine
System.out.print("Votre nom : ");
String nom = scanner.nextLine();

// Lire un nombre
System.out.print("Votre choix : ");
int choix = Integer.parseInt(scanner.nextLine());
```

### Nombre aleatoire

```java
import java.util.Random;
import java.util.Collections;

Random random = new Random();
int index = random.nextInt(liste.size());  // Entre 0 et size-1

// Melanger une liste
Collections.shuffle(liste, random);
```

---

## Solution

La solution se trouve dans le dossier `src/com/demo/`.
