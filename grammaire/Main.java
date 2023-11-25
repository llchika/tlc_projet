import org.antlr.runtime.*;

import lp.whileLexer;
import lp.whileParser;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile="tests/test.while";

        ANTLRStringStream input=new ANTLRFileStream(inputFile);
        
        whileLexer lexer=new whileLexer(input);
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        whileParser parser=new whileParser(tokens);

        try {
            parser.axiome();
        } catch(Exception e) {
            System.err.println("Problème");
        }
    }
}