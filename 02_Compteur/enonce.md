# Exercice 02 : Compteur - Mode Debugging

## Objectif

Decouvrir le **mode Debugging** dans IntelliJ IDEA en utilisant un programme simple qui manipule une variable.

**Concepts a valider :**
- Manipulation de variables
- Point d'arret (breakpoint)
- Execution en mode debug
- Inspecteur de variables
- Mode pas a pas

## Structure du projet

```
02_Compteur/
├── enonce.md
├── src/
│   └── com/
│       └── demo/
│           └── Compteur.java
└── out/
    └── production/
```

## Code source a utiliser

Voici le code a saisir dans la classe `Compteur.java` :

```java
package com.demo;

public class Compteur {
    public static void main(String[] args) {
        System.out.println("Bonjour, je suis un compteur:");
        int a = 0;
        a++;
        a++;
        System.out.println(a);
    }
}
```

## Instructions

### Partie 1 : Creation du projet

#### Etape 1 : Creer un nouveau projet Java

1. Lancer IntelliJ IDEA
2. File > New > Project (ou depuis l'ecran d'accueil : New Project)
3. Dans la fenetre "New Project" :
   - **Name** : `02_Compteur`
   - **Location** : choisir le dossier où créer le projet
   - **Language** : Java
   - **Build system** : IntelliJ
   - **JDK** : selectionner un JDK
   - **Decocher** "Add sample code"
4. Cliquer sur **Create**

#### Etape 2 : Creer le package com.demo

1. Clic droit sur le dossier `src` > New > Package
2. Nom du package : `com.demo`
3. Appuyer sur Entree

#### Etape 3 : Creer la classe Compteur

1. Clic droit sur le package `com.demo` > New > Java Class
2. Nom de la classe : `Compteur`
3. Appuyer sur Entree

#### Etape 4 : Ecrire le code (methode main)

1. Copier le code source fourni ci-dessus dans la classe créée
2. Le fichier doit contenir le package, la classe et la methode `main()`

#### Etape 5 : Executer le programme (Run)

1. Clic droit sur `Compteur.java` > Run 'Compteur.main()'
2. Ou cliquer sur le bouton **Play**
3. Observer le resultat dans la console en bas de l'ecran

#### Etape 6 : Observer la Configuration d'execution

1. Menu Run > Edit Configurations
2. Observer les parametres de la configuration creee automatiquement :
   - Classe principale : `com.demo.Compteur`
   - JDK utilise
   - Working directory

---

### Partie 2 : Utilisation du Debugger

#### Etape 7 : Ajouter un point d'arret (Breakpoint)

1. Ouvrir le fichier `Compteur.java`
2. Cliquer dans la marge gauche (a cote du numero de ligne) sur la ligne `int a = 0;`
3. Un point rouge apparait : c'est le **breakpoint**


#### Etape 8 : Lancer en mode Debug

1. Cliquer sur l'icone **insecte** (bug) dans la barre d'outils, ou
2. Clic droit sur le fichier > **Debug 'Compteur.main()'**

Le programme demarre et s'arrete au breakpoint.

#### Etape 9 : Observer l'inspecteur de variables

Une fois le programme arrete au breakpoint :

1. Le panneau **Debug** s'ouvre en bas de l'ecran
2. L'onglet **Variables** affiche les variables locales
3. Vous verrez `a` apparaitre avec sa valeur actuelle

#### Etape 10 : Avancer en mode pas a pas

Utilisez les boutons de la barre d'outils Debug ou les raccourcis :

| Action | Bouton | Raccourci | Description |
|--------|--------|-----------|-------------|
| **Step Over** | Fleche vers le bas | `F8` | Execute la ligne courante et passe a la suivante |
| **Step Into** | Fleche vers le bas avec point | `F7` | Entre dans la methode appelee |
| **Step Out** | Fleche vers le haut | `Shift+F8` | Sort de la methode courante |
| **Resume** | Fleche verte (play) | `F9` | Continue jusqu'au prochain breakpoint |

#### Etape 11 : Observer l'evolution de la variable

1. Appuyez sur `F8` (Step Over) pour executer `int a = 0;`
   - Dans l'inspecteur, `a = 0`
2. Appuyez sur `F8` pour executer `a++;`
   - Dans l'inspecteur, `a = 1`
3. Appuyez sur `F8` pour executer le second `a++;`
   - Dans l'inspecteur, `a = 2`
4. Appuyez sur `F8` pour executer `System.out.println(a);`
   - La console affiche `2`

