# Grammaire

Ce répertoire contient la grammaire du langage While et un .jar contenant le runtime d'ANTLR 3.5, nécessaire à la compilation du compilateur.

# Arbre généré

## Fonctions

### Déclaration unique
Dans le cas où il n'y a qu'une fonction déclarée dans, l'AST aura la forme suivante:
```
Function
├── ...
└── ...
```

Dans le cas où il y a plusieurs fonctions déclarées, l'AST aura la forme suivante:
```
nil
├── Function
│   ├── ...
│   └── ...
└── Function
    ├── ...
    └── ...
```

