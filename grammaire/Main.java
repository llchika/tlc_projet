import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import lp.whileLexer;
import lp.whileParser;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile="tests/test.while";

        ANTLRStringStream input=new ANTLRFileStream(inputFile);
        
        whileLexer lexer=new whileLexer(input);
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        whileParser parser=new whileParser(tokens);

        // Voir CommonTree;
        CommonTree arbre;

        try {
            System.out.println(parser.axiome().getTree());
        } catch(Exception e) {
            System.err.println("Problème");
        }
    }
}