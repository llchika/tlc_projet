# Grammaire

Ce répertoire contient la grammaire du langage While et un .jar contenant le runtime d'ANTLR 3.5, nécessaire à la compilation du compilateur.

# Arbre généré

## Fonctions

### Déclaration de fonction
Une déclaration de fonction a généralement la forme suivante:
```c
.
└── Function
    ├── nom de fonction
    └── Definition
        └── Definition
            ├── Input
            ├── Corps
            └── Output

```

Dans le cas où la fonction n'a pas de paramètres, l'arbre généré est:
```c
.
└── Function
    ├── nom de fonction
    └── Definition
        └── Definition
            ├── Corps
            └── Output

```

Un noeud Input a la forme suivante:
```c
.
└── Input
    ├── Var1
    ├── Var2
    ...
    └── VarN

```

De même un noeud Output a la forme:
```c
.
└── Output
    ├── Var1
    ├── Var2
    ...
    └── VarN

```

Le noeud Corps a la forme suivante:

```c
.
└── Corps
    ├── Commande1
    ├── Commande2
    ...
    └── CommandeN

```

### Déclaration unique
Dans le cas où il n'y a qu'une fonction déclarée dans, l'AST aura la forme suivante:
```c
Function
├── ...
└── ...
```


### Déclaration multiple
Dans le cas où il y a plusieurs fonctions déclarées, l'AST aura la forme suivante:
```c
nil
├── Function
│   ├── ...
│   └── ...
└── Function
    ├── ...
    └── ...
```

## Structures de controle
Les structures de controle sont des commandes.

### For
```c
.
└── For
    ├── Var
    │   └── nom de variable
    └── Then
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```
Ou
```c
.
└── For
    ├── FunCall
    └── Then
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```

### While
```c
.
└── While
    ├── Var
    │   └── nom de variable
    └── Then
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```
Ou
```c
.
└── While
    ├── FunCall
    └── Then
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```


### ForEach
```c
.
└── ForEach
    ├── variable qui est écrite à chaque itération
    ├── Var
    │   └── nom de variable
    └── Then
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```
Ou
```c
.
└── ForEach
    ├── variable qui est écrite à chaque itération
    ├── FunCall
    └── Then
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```


### If
```c
.
└── If
    ├── Condition
    │   └ À détailler
    ├── Then
    │   ├── Commande1
    │   ├── Commande2
    │   ...
    │   └── CommandeN
    └── Else
        ├── Commande1
        ├── Commande2
        ...
        └── CommandeN
```