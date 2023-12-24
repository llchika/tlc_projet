# Grammaire

Ce répertoire contient la grammaire du langage While et un .jar contenant le runtime d'ANTLR 3.5, nécessaire à la compilation du compilateur.

# Arbre généré

## Fonctions

### Déclaration unique
Dans le cas où une seule fonction est déclarée dans le programme, l'AST posséde la forme suivante:
```
Function
├── ...
└── ...
```

Dans le cas où plusieurs fonctions sont déclarées, l'AST posséde la forme suivante:
```
nil
├── Function
│   ├── ...
│   └── ...
└── Function
    ├── ...
    └── ...
```

