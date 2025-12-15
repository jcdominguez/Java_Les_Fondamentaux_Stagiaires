# Exercice 01 : HelloWorld - Compilation et Execution en Terminal

## Objectif
Apprendre a compiler et executer un programme Java en ligne de commande, sans utiliser d'IDE.

## Prerequis
- JDK (Java Development Kit) installe sur votre machine
- La variable d'environnement `PATH` configuree pour acceder aux commandes `javac` et `java`

Pour verifier votre installation, ouvrez un terminal et tapez :
```bash
java -version
javac -version
```

## Structure du projet

```
01_HelloWorld/
├── enonce.md
└── HelloWorld.java    # Code source
```

## Le code source

Le fichier `HelloWorld.java` vous est fourni. Voici son contenu :

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## Instructions

### Etape 1 : Ouvrir un terminal
- **Windows** : Ouvrez l'invite de commandes (cmd) ou PowerShell
- **macOS** : Ouvrez l'application Terminal
- **Linux** : Ouvrez un terminal

### Etape 2 : Se positionner dans le dossier de l'exercice
Utilisez la commande `cd` pour naviguer jusqu'au dossier de l'exercice :

```bash
cd chemin/vers/01_HelloWorld
```

### Etape 3 : Compiler le programme
Utilisez le compilateur Java (`javac`) pour compiler le fichier source :

```bash
javac HelloWorld.java
```

Si la compilation reussit, aucun message n'apparait et le fichier `HelloWorld.class` est cree dans le meme dossier.

### Etape 4 : Executer le programme
Utilisez l'interpreteur Java (`java`) pour executer le programme compile :

```bash
java HelloWorld
```

**Attention** : Utilisez le nom exact de la classe (`HelloWorld`).

## Resultat attendu

```
Hello, World!
```

## Points cles a retenir

| Fichier | Description |
|---------|-------------|
| `HelloWorld.java` | Code source (lisible par l'humain) |
| `HelloWorld.class` | Bytecode compile (executable par la JVM) |

| Commande | Role |
|----------|------|
| `javac HelloWorld.java` | Compile le fichier source |
| `java HelloWorld` | Execute la classe |

## En cas d'erreur

- **"javac n'est pas reconnu"** : Le JDK n'est pas installe ou le PATH n'est pas configure
- **"Error: Could not find or load main class"** : Verifiez le classpath et le nom de la classe

