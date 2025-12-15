# Exercice 03 : Calculatrice

## Objectif

Mettre en pratique les concepts fondamentaux de Java en creant une calculatrice interactive en ligne de commande.

## Structure du projet

```
03_Calculatrice/
├── enonce.md
├── src/
│   └── com/
│       └── demo/
│           └── Calculatrice.java    # Code source
└── out/
    └── production/                  # Fichiers compiles
```

**Concepts a valider :**
- Types primitifs (`int`, `double`, `char`, `boolean`)
- Opérateurs arithmétiques (`+`, `-`, `*`, `/`, `%`)
- Structures conditionnelles (`if-else`)
- Structure de sélection (`switch`)
- Boucles (`while`, `do-while`, `for`)
- Tableaux

## Cahier des charges

Créer un programme `Calculatrice.java` qui :

1. **Affiche un menu** proposant les opérations : addition, soustraction, multiplication, division, modulo
2. **Demande à l'utilisateur** de saisir deux nombres (décimaux acceptés)
3. **Demande le choix de l'opération** via un caractère (`+`, `-`, `*`, `/`, `%`)
4. **Effectue le calcul** et affiche le résultat
5. **Gère les erreurs** : division par zéro
6. **Propose de continuer** ou de quitter après chaque calcul

### Bonus (optionnel)

7. **Stocke l'historique** des 5 derniers résultats dans un tableau
8. **Affiche l'historique** avant de quitter

## Contraintes techniques

Vous **devez obligatoirement** utiliser :

| Concept | Utilisation attendue |
|---------|---------------------|
| `int` | Compteur de calculs |
| `double` | Nombres saisis et résultats |
| `char` | Choix de l'opération (+, -, *, /, %) |
| `boolean` | Variable pour continuer/quitter la boucle |
| `switch` | Sélection de l'opération selon le caractère |
| `if-else` | Vérification de la division par zéro |
| Boucle `do-while` | Menu principal (exécuter au moins une fois) |

**Bonus (optionnel) :**

| Concept | Utilisation attendue |
|---------|---------------------|
| Tableau `double[]` | Stockage des 5 derniers résultats |
| Boucle `for` | Affichage de l'historique |

## Exemple d'exécution

```
=== CALCULATRICE JAVA ===

Entrez le premier nombre : 10.5
Entrez le deuxième nombre : 3

Choisissez l'opération :
  + : Addition
  - : Soustraction
  * : Multiplication
  / : Division
  % : Modulo
Votre choix : +

Résultat : 10.5 + 3.0 = 13.5

Voulez-vous continuer ? (o/n) : o

Entrez le premier nombre : 20
Entrez le deuxième nombre : 0

Choisissez l'opération :
  + : Addition
  - : Soustraction
  * : Multiplication
  / : Division
  % : Modulo
Votre choix : /

Erreur : Division par zéro impossible !

Voulez-vous continuer ? (o/n) : o

Entrez le premier nombre : 17
Entrez le deuxième nombre : 5

Choisissez l'opération :
  + : Addition
  - : Soustraction
  * : Multiplication
  / : Division
  % : Modulo
Votre choix : %

Résultat : 17.0 % 5.0 = 2.0

Voulez-vous continuer ? (o/n) : n

=== HISTORIQUE DES RÉSULTATS ===
Calcul 1 : 13.5
Calcul 2 : 2.0

Merci d'avoir utilisé la calculatrice !
```

## Indices / Rappels de syntaxe

### Scanner (lecture clavier)
```java
import java.util.Scanner;
```
```java
Scanner scanner = new Scanner(System.in);
double nombre = scanner.nextDouble();
char caractere = scanner.next().charAt(0);
```

### Switch
```java
switch (variable) {
    case 'valeur1':
        // instructions
        break;
    case 'valeur2':
        // instructions
        break;
    default:
        // instructions par défaut
}
```

### Boucle do-while
```java
do {
    // instructions exécutées au moins une fois
} while (condition);
```

### Tableau
```java
double[] tableau = new double[5];  // tableau de 5 éléments
tableau[0] = 10.5;                 // affectation
```

## Criteres de validation

Avant de consulter la solution, verifiez que votre programme :

- [ ] Compile sans erreur
- [ ] Utilise les 4 types primitifs demandés (`int`, `double`, `char`, `boolean`)
- [ ] Utilise un `switch` pour le choix de l'opération
- [ ] Utilise un `if-else` pour gérer la division par zéro
- [ ] Utilise une boucle `do-while` pour le menu principal
- [ ] Gère correctement la division par zéro (message d'erreur, pas de crash)
- [ ] Permet de faire plusieurs calculs avant de quitter

**Bonus (optionnel) :**

- [ ] Utilise un tableau pour stocker l'historique
- [ ] Affiche l'historique des résultats à la fin

## Solution

La solution est disponible dans le fichier `src/com/demo/Calculatrice.java`.
