import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import lp.whileLexer;
import lp.whileParser;

import verif.Verificator;
import adresses.Generator;

public class Compilator {
    

    public static void main(String[] args) throws Exception {
        String inputFile="tests/test.while";

        ANTLRStringStream input=new ANTLRFileStream(inputFile);
        
        whileLexer lexer=new whileLexer(input);
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        whileParser parser=new whileParser(tokens);

        // Voir CommonTree;
        CommonTree arbre=(CommonTree)(parser.axiome().getTree());

        boolean valide=Verificator.execute(arbre); // Vérifications sur l'AST
        Generator.test();
    }
}