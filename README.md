# Projet: Théorie des langages et compilation
Projet réalisé dans le cadre du module « Théorie des langages et compilation ».<br>
Il contient un compilateur pour une variante du langage While.

## Structure du projet
Le projet est structuré ainsi:
```c
.
├── backend                 // Bibliothèque native (C++)
│   ├── ArbreBinaire.h      //Classe definissant la structure d'arbre
│   ├── ArbreBinaire.cpp
│   ├── BackendExplication.md  //Explication backend
│   ├── CodeTranslator.h    //Classe traduction de code 3 adresses vers le code C++
│   ├── CodeTranslator.cpp
│   ├── RunTime.cpp        //Contient les fonctions nécessaires à l'execution du code C++
│   ├── testBackend.cpp    //Couverture de test

├── grammaire               
│   ├── antlr.jar
│   └── while.g             // Grammaire de While (ANTLR)
│   ├── description_ast.md  //Description de l'ast, de la grammaire

├── programmes              // Exemples de programmes While
├── src                     // Travail sur l'AST (Java)
│   ├── adresses            // Traduction vers code 3 adresses
│   ├── verif               // Analyse sémantique
│   ├── utils
│   └── Compilator.java     // Main
└── swl                     // Bibliothèque standard While
├── README.md
├── Makefile                //Chaîne de compilation
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
Pour tester le backend
```
make testArbre
```
