package src;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import src.lp.whileLexer;
import src.lp.whileParser;

import src.verif.Verificator;
import src.adresses.Generator;


import java.io.File;

/**
 * Main, charge les fichiers sources, le parser et le lexer,
 * lance la vérification de l'AST et la génération du code 3 adresses
 */
public class Compilator {
    public static void main(String[] args) throws Exception {
        if (args.length!=0) {
            File file=new File(args[0]);
            if(!file.exists()) {
                throw new Exception("Le fichier n'existe pas.");
            }
        } else {
            throw new Exception("Aucun fichier saisi");
        }

        ANTLRStringStream input=new ANTLRFileStream(args[0]);
        
        whileLexer lexer=new whileLexer(input);
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        whileParser parser=new whileParser(tokens);

        CommonTree arbre=(CommonTree)(parser.axiome().getTree()); // Génération de l'AST

        boolean valide=Verificator.execute(arbre); // Vérifications sur l'AST
        if (valide) {
            //Generator.afficheAST(arbre);
            Generator.generateCodeFrom(arbre); // Génération code 3 adresses

            //Generator.printCode();
            Generator.writeCode();
        } else {
            System.out.println("Echec du build");
        }
    }
}
