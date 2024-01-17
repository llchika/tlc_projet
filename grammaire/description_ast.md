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

### Type de Déclaration
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

