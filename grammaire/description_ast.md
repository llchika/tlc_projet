# Grammaire

    Ce répertoire contient la grammaire du langage While et un .jar contenant le runtime d'ANTLR 3.5, nécessaire 
à la compilation du compilateur.
    La grammaire est l'élément qui permet de reconnaître un langage. Pour le langage 'while', nous avons 
prédéfini différents tokens qui nous sont utiles pour la réécriture de l'arbre de syntaxe abstrait.

## Tokens:
Var;Function;Commands;Output;Input;Function;Definition;Corps;Set;FunCall;Nil;Args;Exprs;If;Else;Then;Condition;While;For;ForEach;Nop;Symbol;Equals;
## Regles principales :
    - 'function' permet la reconnaissance d'une fonction : un SYMBOL suivi d'une définition.
    - 'definition' reconnaît la définition de la fonction : les inputs, les outputs, ainsi que le corps de la 
fonction.
    - 'commands' permet de reconnaître des commandes. Une 'command' permet de reconnaître des structures typiques 
du langage 'while', comme la structure if, for, etc.
    - 'expression' permet de reconnaître une expression telle que A, B, ou encore cons, nil, etc.


# Arbre généré

L'AST (Abstract Syntax Tree) est une représentation arborescente des programmes 'WHILE' créée lors de l'analyse syntaxique. Il est construit à partir de différentes règles de la grammaire. Chacune de ces règles contribue à la construction d'un élément spécifique de l'AST.

## Fonctions

<<<<<<< HEAD
### Type de Déclaration
Dans le cas où une seule fonction est déclarée dans le programme, l'AST posséde la forme suivante:
```
=======
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
>>>>>>> main
Function
├── ...
└── ...
```

<<<<<<< HEAD
Dans le cas où plusieurs fonctions sont déclarées, l'AST posséde la forme suivante:
```
=======

### Déclaration multiple
Dans le cas où il y a plusieurs fonctions déclarées, l'AST aura la forme suivante:
```c
>>>>>>> main
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