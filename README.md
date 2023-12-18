# Projet: Théorie des langages et compilation
Projet réalisé dans le cadre du module « Théorie des langages et compilation ».<br>
Il contient un compilateur pour une variante du langage While.

## Structure du projet
Le projet est structuré ainsi:
```c
.
├── backend                 // Bibliothèque native (C++)
├── grammaire               
│   ├── antlr.jar
│   └── while.g             // Grammaire de While (ANTLR)
├── Makefile
├── programmes              // Exemples de programmes While
├── README.md
├── src                     // Travail sur l'AST (Java)
│   ├── adresses            // Traduction vers code 3 adresses
│   ├── verif               // Analyse sémantique
│   ├── utils
│   └── Compilator.java     // Main
└── swl                     // Bibliothèque standard While
```

## Compilation
La compilation du projet se fait via un Makefile.<br>
Testé sous Linux (Debian & Ubuntu), devrait à priori fonctionner pour Linux/MacOS, mais pas pour Windows (ce qui est logique car Java est un langage **universel**)<br><br>
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
