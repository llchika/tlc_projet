package src.adresses;

import java.util.LinkedList;
import java.util.List;
import org.antlr.runtime.tree.*;
import src.adresses.Instruction3Ad;

public class Generator {
    public static List<Instruction3Ad> code = new LinkedList<>();


    public static void execute() {
        System.out.println("Test");
    }

    // Generation du code d'une instruction
    private static int generateFrag(Tree arbre, List<Instruction3Ad> l, int currentRegister)
    {
        switch(arbre.toString())//Conversion de l'arbre en string
        {
        case "terminal": // Plutot dans le default du coup
            l.add(new Instruction3Ad("r"+currentRegister, "=", arbre.toString(), null));
            return currentRegister+1;
            
        case "Set":
            l.add(new Instruction3Ad(arbre.getChild(0).toString(), "setTo", arbre.getChild(1).toString(), ""));
            return currentRegister+1;
        }
        System.out.println("problème dans generateFrag()");
        return 0;
    }

    // Generation du code d'une fonction
    private static int generateFunct(Tree arbre, int firstRegister)
    {
        List<Instruction3Ad> codeFonction = code;

        Tree params = arbre.getChild(1);
        int i = 0;
        while(params.getChild(i) != null)
        {
            codeFonction.add(new Instruction3Ad("", "param", params.getChild(i).toString(), ""));
            i++;
        }
        int numRegister = generateFrag(arbre.getChild(2), codeFonction, firstRegister);

        codeFonction.add(new Instruction3Ad("return", "", arbre.getChild(3).getChild(0).toString(), ""));
        return numRegister;
    }

    // Génération du code global
    public static void generateCodeFrom(CommonTree arbre)
    {
        int i = 0;
        int currentRegister = 0;
        while(arbre.getChild(i) != null)
        {
            currentRegister = generateFunct(arbre.getChild(i), currentRegister);
            i++;
        }
    }


    public static void printCode()
    {
        for (Instruction3Ad ins : code)
        {
            System.out.println(ins);
        }
    }
}
