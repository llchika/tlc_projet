# Projet: Théorie des langages et compilation
Projet réalisé dans le cadre du module « Théorie des langages et compilation ».

Création d'un compilateur (pas encore) pour une variante du langage While, en utilisant antlr 3.5 et Java.

## Structure du projet
Le projet est structuré ainsi:
```c
.
├── grammaire // Grammaire du langage
│   ├── antlr.jar
│   └── while.g
├── Makefile
├── programmes // Programmes écrit en While
├── README.md
└── src // Fichiers sources
```

## Compilation
La compilation du projet se fait via un Makefile.<br>
Testé sous Linux (Debian), devrait à priori fonctionner pour Linux/MacOS, mais pas pour Windows (ce qui est logique car Java est un langage **universel**)<br><br>
Pour compiler le compilateur
```
make
```

<br>

Pour nettoyer le projet
```
make clean
```

<br>

Pour lancer le projet
```
make file=fichier.while start
```
Par exemple, pour compiler programmes/test.while
```
make file=programmes/test.while start
```
