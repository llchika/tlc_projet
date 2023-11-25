# Grammaire
Commandes du Makefile:
- **compile_grammar**: Permet de créer avec antlr les classes Java du Lexer et du Parser.
- **compile_lp**: Compile le main, le lexer et le parser.
- **start**: Éxecute le main.
- **clean**: Supprime tout ce qui a été généré par commande.

make éxecute dans l'ordre: compile_grammar > compile_lp > start

Testé sous Linux (Debian), devrait à priori fonctionner pour Linux/Mac, mais pas pour Windows (ce qui est logique car Java est un langage **universel**)