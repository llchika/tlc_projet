package src;

import org.antlr.grammar.v3.ANTLRParser.throwsSpec_return;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import src.lp.whileLexer;
import src.lp.whileParser;

import src.verif.Verificator;
import src.adresses.Generator;

import java.io.File;

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

        // Voir CommonTree;
        CommonTree arbre=(CommonTree)(parser.axiome().getTree());

        boolean valide=Verificator.execute(arbre); // Vérifications sur l'AST
        if (valide) {
            Generator.execute(); // Génération code 3 adresses
        }
    }
}
