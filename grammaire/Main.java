import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import lp.whileLexer;
import lp.whileParser;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("programm start");
        String inputFile="tests/test.while";

        ANTLRStringStream input=new ANTLRFileStream(inputFile);
        
        whileLexer lexer=new whileLexer(input);
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        whileParser parser=new whileParser(tokens);

        try {
            CommonTree arbre=(CommonTree)(parser.axiome().getTree());
            System.out.println(arbre);
            System.out.println(arbre.getChild(0));
        } catch(Exception e) {
            System.err.println("Problème");
        }
    }
}