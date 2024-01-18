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
├── programmes              // Exemples de programmes While
├── src                     // Travail sur l'AST (Java)
│   ├── adresses            // Traduction vers code 3 adresses
│   ├── verif               // Analyse sémantique
│   ├── utils
│   └── Compilator.java     // Main
├── manifest.txt            // Manifest nécessaire à la création du .jar
├── README.md
├── Makefile                // Chaîne de compilation
└── whilec.sh               // Script d'utilisation du compilateur
```

## Compilation
La compilation du projet se fait via un Makefile.

Ce dernier a été testé sous Linux (Debian & Ubuntu).

Pour compiler le compilateur
```
make jar
```

Une fois le jar créé, le compilateur s'utilise via un script
```
./whilec.sh file
```

Par exemple, pour compiler programmes/test.while (fourni avec le code)
```
./whilec.sh programmes/test.while
```

## Autre commandes
Pour tester le backend
```
make testArbre
```

Pour nettoyer le projet
```
make clean
```
