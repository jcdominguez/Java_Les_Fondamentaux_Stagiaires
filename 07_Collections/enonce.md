# TP 07 : Collections - List, Set, Map

## Objectif

Apprendre a utiliser les principales collections Java pour stocker et manipuler des donnees.

---

## Exercices

### Exercice 1 : La moyenne de la classe

**Probleme** : Calculer la moyenne des notes d'une classe

- Ecrire un programme qui permet de saisir au clavier des notes comprises entre 0 et 20
- Nous ne savons pas a l'avance combien de notes il y aura a saisir
- Taper -1 quand il n'y a plus de notes a saisir
- Afficher alors la liste des notes et la moyenne

**Classe a creer : `MoyenneClasse.java`**

**Resultat attendu :**

```
Entrez une note (0-20) ou -1 pour terminer : 15
Entrez une note (0-20) ou -1 pour terminer : 12
Entrez une note (0-20) ou -1 pour terminer : 18
Entrez une note (0-20) ou -1 pour terminer : 25
Note invalide, veuillez entrer une note entre 0 et 20
Entrez une note (0-20) ou -1 pour terminer : 14
Entrez une note (0-20) ou -1 pour terminer : -1

Notes saisies : [15.0, 12.0, 18.0, 14.0]
Moyenne de la classe : 14.75
```

---

### Exercice 2 : Jeu avec nombres aleatoires

**Probleme** : Generer 5 nombres aleatoires differents entre 0 et 10

- Pour les besoins d'un jeu, nous devons generer aleatoirement 5 nombres compris entre 0 et 10
- Ces 5 nombres doivent etre **differents** !
- Afficher chaque tentative et indiquer si le nombre est ajoute ou deja present

**Classe a creer : `JeuNombres.java`**

**Resultat attendu (exemple, les nombres varient) :**

```
Generation de 5 nombres uniques entre 0 et 10...
Tentative 1 : 3 (ajoute)
Tentative 2 : 2 (ajoute)
Tentative 3 : 6 (ajoute)
Tentative 4 : 10 (ajoute)
Tentative 5 : 2 (deja present)
Tentative 6 : 6 (deja present)
Tentative 7 : 3 (deja present)
Tentative 8 : 9 (ajoute)

Les 5 nombres generes : [2, 3, 6, 9, 10]
```

---

### Exercice 3 : Les sieges d'une classe

**Probleme** : Gerer l'attribution des sieges aux eleves

- Dans une classe, chaque siege est numerote et attribue a un eleve pour toute l'annee
- Un eleve est represente par un String (prenom + nom)

**Classe a creer : `SiegesClasse.java`**

**Resultat attendu :**

```
Attribution des sieges...
Siege 1 -> Jean Dupont
Siege 2 -> Marie Martin
Siege 5 -> Paul Durand
Siege 3 -> Sophie Bernard

Liste des sieges attribues :
Siege 1 : Jean Dupont
Siege 2 : Marie Martin
Siege 3 : Sophie Bernard
Siege 5 : Paul Durand

Qui est au siege 3 ? Sophie Bernard
Le siege 4 est libre.
```

---

### Exercice 4 : Enregistrer les notes de chaque eleve

**Probleme** : Gerer les notes de plusieurs eleves

- Chaque eleve recoit differentes notes dans differentes matieres
- On ne sait pas a l'avance le nombre d'eleves ni le nombre de notes par eleve
- Un eleve est represente par un String (prenom + nom)

**Classe a creer : `NotesEleves.java`**

**Resultat attendu :**

```
Enregistrement des notes...
Alice : ajout note 15.0
Alice : ajout note 12.0
Bob : ajout note 18.0
Alice : ajout note 14.0
Bob : ajout note 16.0
Charlie : ajout note 10.0
Bob : ajout note 13.0

=== Moyennes des eleves ===
Alice : notes [15.0, 12.0, 14.0] -> moyenne = 13.67
Bob : notes [18.0, 16.0, 13.0] -> moyenne = 15.67
Charlie : notes [10.0] -> moyenne = 10.00
```

---

## Rappels de syntaxe

### Random

```java
import java.util.Random;

Random random = new Random();
int nombre = random.nextInt(11);  // Nombre entre 0 et 10 (inclus)
```

### Scanner

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
double note = scanner.nextDouble();
scanner.close();
```





---

## Solution

La solution se trouve dans le dossier `src/com/demo/`.
