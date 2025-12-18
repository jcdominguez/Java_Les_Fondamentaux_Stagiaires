# TP 06 : Validateur de Donnees - Deploiement JAR

## Objectif

Creer une librairie de validation de donnees, puis une application qui l'utilise, et les deployer sous forme de fichiers JAR.

**Concepts :**
- Creation d'une librairie JAR
- Compilation avec un classpath externe
- Fichier **MANIFEST.MF** (Main-Class, Class-Path)
- Creation d'un JAR executable
- Execution

## Structure du projet

```
06_ValidateurDonnees/
├── lib-validation/                         # Projet librairie
│   ├── src/
│   │   └── com/
│   │       └── demo/
│   │           └── ValidationUtils.java    # Classe utilitaire
│   ├── out/
│   │   └── production/
│   │       └── com/
│   │           └── demo/
│   │               └── ValidationUtils.class
│   └── validation-utils.jar                # JAR librairie genere
│
├── app-validateur/                         # Projet application
│   ├── src/
│   │   └── com/
│   │       └── demo/
│   │           └── Validateur.java         # Application principale
│   ├── lib/
│   │   └── validation-utils.jar            # Copie de la librairie
│   ├── out/
│   │   └── production/
│   │       └── com/
│   │           └── demo/
│   │               └── Validateur.class
│   ├── MANIFEST.MF                         # Manifest pour JAR executable
│   └── validateur.jar                      # JAR executable final
│
└── enonce.md
```

## Cahier des charges

### Partie 1 : La librairie `validation-utils.jar`

Creer une classe utilitaire `ValidationUtils` avec des methodes statiques pour valider :

1. **Email** : doit contenir un `@` suivi d'un `.`
2. **Telephone francais** : 10 chiffres, commence par `0`
3. **Code postal francais** : exactement 5 chiffres
4. **IBAN francais** : commence par `FR`, 27 caracteres au total

### Partie 2 : L'application `validateur.jar`

Creer une application console interactive qui :
- Propose un menu pour valider differents types de donnees
- Utilise la librairie `ValidationUtils` pour les validations
- Affiche clairement si la donnee est valide ou non

## Contraintes techniques

| Concept | Utilisation attendue |
|---------|---------------------|
| **Methodes statiques** | Toutes les methodes de `ValidationUtils` sont statiques |
| **Librairie JAR** | Creer `validation-utils.jar` avec `jar cvf` |
| **Classpath** | Compiler l'application avec `-cp lib/validation-utils.jar` |
| **MANIFEST.MF** | Definir `Main-Class` et `Class-Path` |
| **JAR executable** | Creer `validateur.jar` avec `jar cvfm` |
| **Import de package** | L'application importe `com.demo.ValidationUtils` |

## Architecture des classes

```
lib-validation/
    ValidationUtils (methodes statiques)
        +isValidEmail(String) : boolean
        +isValidTelephone(String) : boolean
        +isValidCodePostal(String) : boolean
        +isValidIBAN(String) : boolean
        +formatResultat(String, String, boolean) : String

app-validateur/
    Validateur (classe principale)
        +main(String[])
        -afficherMenu()
        -validerEmail()
        -validerTelephone()
        -validerCodePostal()
        -validerIBAN()
        -validerTout()
```

### Detail de la classe ValidationUtils

```java
package com.demo;

public class ValidationUtils {
    // Valide un email (contient @ suivi d'un .)
    public static boolean isValidEmail(String email)

    // Valide un telephone francais (10 chiffres, commence par 0)
    public static boolean isValidTelephone(String tel)

    // Valide un code postal francais (5 chiffres)
    public static boolean isValidCodePostal(String cp)

    // Valide un IBAN francais (FR + 25 caracteres)
    public static boolean isValidIBAN(String iban)

    // Formate le resultat de validation
    public static String formatResultat(String type, String valeur, boolean valide)
}
```

## Exemple d'execution

```
=== VALIDATEUR DE DONNEES ===
Application utilisant la librairie validation-utils.jar

=== MENU ===
1. Valider un email
2. Valider un telephone
3. Valider un code postal
4. Valider un IBAN
5. Valider un contact complet
6. Quitter

Votre choix : 1

Entrez l'email a valider : jean.dupont@email.fr
Email 'jean.dupont@email.fr' : VALIDE

=== MENU ===
1. Valider un email
2. Valider un telephone
3. Valider un code postal
4. Valider un IBAN
5. Valider un contact complet
6. Quitter

Votre choix : 1

Entrez l'email a valider : emailsansat
Email 'emailsansat' : INVALIDE

=== MENU ===
...

Votre choix : 2

Entrez le telephone a valider : 06 12 34 56 78
Telephone '06 12 34 56 78' : VALIDE

=== MENU ===
...

Votre choix : 5

=== VALIDATION CONTACT COMPLET ===
Email : marie.martin@test.com
Telephone : 0612345678
Code postal : 75001
IBAN : FR76 3000 6000 0112 3456 7890 189

--- RESULTATS ---
Email 'marie.martin@test.com' : VALIDE
Telephone '0612345678' : VALIDE
Code postal '75001' : VALIDE
IBAN 'FR76 3000 6000 0112 3456 7890 189' : VALIDE

Bilan : 4/4 champs valides
Contact VALIDE !

=== MENU ===
...

Votre choix : 6

Merci d'avoir utilise le Validateur de donnees !
```

## Indices / Rappels de syntaxe

### Creer une librairie JAR

```bash
# 1. Compiler les sources
cd lib-validation
javac -d out/production src/com/demo/*.java

# 2. Creer le fichier JAR
jar cvf validation-utils.jar -C out/production .
```

Le flag `-C out/production .` permet de changer de repertoire avant d'ajouter les fichiers.

### MEMO : Options de la commande `jar`

| Option | Signification |
|--------|---------------|
| c | Creer un nouveau JAR |
| f | Specifier le nom du fichier JAR en sortie |
| m | Indiquer un manifest personnalise |
| v | Mode "verbose" : affiche les fichiers ajoutes |
| x | Extraire le contenu d'un JAR |
| t | Lister le contenu d'un JAR (table of contents) |

### Compiler avec une librairie externe

```bash
# Copier la librairie dans le dossier lib/
cd app-validateur
cp ../lib-validation/validation-utils.jar lib/

# Compiler en specifiant le classpath
javac -cp lib/validation-utils.jar -d out/production src/com/demo/*.java
```

### Fichier MANIFEST.MF

Le fichier `MANIFEST.MF` doit se terminer par une ligne vide :

```
Manifest-Version: 1.0
Main-Class: com.demo.Validateur
Class-Path: lib/validation-utils.jar

```

**Important** : Il doit y avoir une ligne vide a la fin du fichier !

### Creer un JAR executable

```bash
cd app-validateur

# Creer le JAR avec le manifest
jar cvfm validateur.jar MANIFEST.MF -C out/production . lib/

# Executer le JAR
java -jar validateur.jar
```

### Verifier le contenu d'un JAR

```bash
# Lister le contenu
jar tf validation-utils.jar

# Extraire et visualiser le manifest
jar xf validateur.jar META-INF/MANIFEST.MF
cat META-INF/MANIFEST.MF
```

### Methodes utiles pour la validation

```java
// Verifier si une chaine contient un caractere
String email = "test@example.com";
int atIndex = email.indexOf('@');  // retourne -1 si absent

// Supprimer espaces et tirets
String cleaned = telephone.replaceAll("[\\s\\-]", "");

// Verifier si un caractere est un chiffre
char c = '5';
boolean estChiffre = Character.isDigit(c);

// Convertir en majuscules
String upper = iban.toUpperCase();
```

## Compilation et execution pas a pas

### Etape 1 : Compiler et packager la librairie

```bash
cd 06_ValidateurDonnees/lib-validation

# Compiler
javac -d out/production src/com/demo/*.java

# Verifier la compilation
ls out/production/com/demo/

# Creer le JAR
jar cvf validation-utils.jar -C out/production .

# Verifier le JAR
jar tf validation-utils.jar
```

### Etape 2 : Compiler l'application avec la librairie

```bash
cd ../app-validateur

# Copier la librairie
cp ../lib-validation/validation-utils.jar lib/

# Compiler avec le classpath
javac -cp lib/validation-utils.jar -d out/production src/com/demo/*.java

# Tester l'execution (sans JAR)
java -cp "out/production:lib/validation-utils.jar" com.demo.Validateur
```

### Etape 3 : Creer et executer le JAR executable

```bash
# Creer le JAR executable
jar cvfm validateur.jar MANIFEST.MF -C out/production . lib/

# Executer
java -jar validateur.jar
```

## Criteres de validation

Avant de consulter la solution, verifiez que votre programme :

- [ ] La librairie `validation-utils.jar` est creee correctement
- [ ] La librairie contient la classe `com.demo.ValidationUtils`
- [ ] L'application compile avec le classpath vers la librairie
- [ ] Le fichier `MANIFEST.MF` contient `Main-Class` et `Class-Path`
- [ ] Le JAR executable `validateur.jar` fonctionne avec `java -jar`
- [ ] La validation d'email detecte l'absence de `@` ou de `.`
- [ ] La validation de telephone accepte les espaces et tirets
- [ ] La validation de code postal refuse les codes non numeriques
- [ ] La validation d'IBAN verifie le prefixe `FR` et la longueur
- [ ] Le menu interactif fonctionne correctement
- [ ] L'option "contact complet" valide les 4 types de donnees

## Configuration de l'IDE (Problème de Classpath)

Lorsque vous ouvrez ce projet dans un IDE comme VSCode ou IntelliJ, il est possible que l'IDE ne trouve pas la classe `ValidationUtils` et affiche une erreur, même si la compilation en ligne de commande fonctionne.

Ceci est dû au fait que le projet n'utilise pas un outil de build standard (comme Maven ou Gradle) et que l'IDE ne sait donc pas automatiquement que `app-validateur` dépend de la librairie `validation-utils.jar`.

Voici comment résoudre ce problème pour les IDEs les plus courants.

### Pour Visual Studio Code (VSCode)

Il faut indiquer au plugin Java où se trouvent les librairies `.jar` du projet.

1.  **Créer/Modifier le fichier de configuration** : Créez ou ouvrez le fichier `.vscode/settings.json` à la racine de votre projet.
2.  **Ajouter la configuration des librairies** : Ajoutez le contenu suivant. Ce code utilise un pattern "glob" pour inclure automatiquement tous les fichiers `.jar` situés dans n'importe quel sous-dossier `lib/`.

    ```json
    {
      "java.project.referencedLibraries": [
        "**/lib/**/*.jar"
      ]
    }
    ```

3.  **Recharger le projet** : Si l'erreur persiste, ouvrez la palette de commandes (`Ctrl+Shift+P` ou `Cmd+Shift+P`) et exécutez la commande `Java: Clean Java Language Server Workspace`.

### Pour IntelliJ IDEA

Il faut ajouter manuellement le fichier `.jar` comme une dépendance du module.

1.  **Ouvrir la Structure du Projet** : Allez dans `File` -> `Project Structure...` (ou `Cmd+;` sur macOS).
2.  **Aller aux Modules** : Sélectionnez `Modules` dans le menu de gauche.
3.  **Sélectionner le bon module** : Choisissez le module `app-validateur` dans la liste centrale.
4.  **Ajouter la dépendance** :
    *   Allez dans l'onglet `Dependencies`.
    *   Cliquez sur le `+` et choisissez `JARs or Directories...`.
    *   Naviguez jusqu'au fichier `06_ValidateurDonnees/app-validateur/lib/validation-utils.jar` et sélectionnez-le.
5.  **Appliquer les changements** : Cliquez sur `Apply` puis `OK`. IntelliJ va alors ré-indexer le projet et l'erreur devrait disparaître.

## Solution

La solution est disponible dans les dossiers :

**Librairie :**
- `lib-validation/src/com/demo/ValidationUtils.java`

**Application :**
- `app-validateur/src/com/demo/Validateur.java`
- `app-validateur/MANIFEST.MF`
