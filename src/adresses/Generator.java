/////////////////////////////////////////////////////////////////////////////////////////////////
//  FONCTION DE CONSTRUCTION :
//      - arguments :
//          Tree arbre : Endroit où l'on est rendu dans l'AST
//          List<Instruction3Ad> functCode : Liste d'instructions de code 3 adresse
//          int currentRegister : Indice du registre courant
//      - Types de traitement et échelles :
//          - code
//              - fonction
//                  - while
//                  - for
//                  - foreach
//                  - if
//                      - constructeur d'arbre
//                      - constructeur de liste

//                      - affectation val constante
//                      - copie
//                      - comparaison
//                      - head
//                      - tail
//                      - appel fonction quelconque
//      - Retour :
//          indice suivant le dernier registre utilisé


// TODO : changer les nom des instructions du style "BoucleIf", "BoucleFor"
// TODO : nouveau while.g issu de la fusion avec main ne fonctionne pas

package src.adresses;

import java.util.LinkedList;
import java.util.List;
import org.antlr.runtime.tree.*;

import com.sun.org.apache.bcel.internal.generic.Instruction;
//import com.sun.source.tree.Tree;

import src.adresses.Instruction3Ad;

public class Generator {
    public static List<Instruction3Ad> code = new LinkedList<>();
    private static int nbRegister = 0;

    public static void execute() {
        System.out.println("Test");
    }

    // Squelette d'une méthode de génération
    // Les autres méthodes doivent ressembler à ça
    private static void generateThing(Tree arbre, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
    }


    private static void generateConstructor(Tree arbre, List<Instruction3Ad> functCode) {


        int nbArbres = arbre.getChildCount();
        for (int i = nbArbres - 1; i >= 0; i--)
        {
            functCode.add(new Instruction3Ad("r" + nbRegister, Instruction3Ad.Operator.copy, arbre.getChild(i).toString(), ""));
            nbRegister++;
        }
    }

//                      - affectation val constante
//                      - copie
//                      - constructeur d'arbre
//                      - constructeur de liste
//                      - comparaison
//                      - head
//                      - tail
//                      - appel fonction quelconque



    private static void generateSet(Tree arbre, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
        
    }


    private static void generateIf(Tree arbre, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
    }

    private static void generateWhile(Tree arbre, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
    }

    private static void generateFor(Tree arbre, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
    }

    private static void generateForeach(Tree arbre, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
    }

    // Generation du code d'une fonction
    //  - while, for, foreach, if
    //  - constructeur d'arbre, de liste
    //  - affectation, copie, comparaison, hd, tl, appel fonction quelconque
    private static void generateFunct(Tree arbre) {
        System.out.println("fonct : " + arbre.toString());

        // TODO : stockage dans une autre liste pour effectuer les opti directement à l'échelle de la fonction
        List<Instruction3Ad> functCode = code;

        // Identification et linkage des différentes composantes de la fonction
        Tree input = null;
        Tree instructions = null;
        Tree output = null;
        for (int i = 1 ; i < arbre.getChildCount() ; i++)
        {
            Tree t = arbre.getChild(i);
            switch (t.toString())
            {
            case "Input" :
                input = t;
                break;
            case "Instructions" :
                instructions = t;
                break;
            case "Output" :
                output = t;
                break;
            default :
                System.out.println("error in Generator.generateFunct() : unespected token " + t.toString());
            }
        }


        // Instruction de début de fonction
        functCode.add(new Instruction3Ad("", Instruction3Ad.Operator.beginFun, arbre.getChild(0).toString(), ""));

        // Instructions sur les paramètres de fonction
        if (input != null)
        {
            System.out.println("params : " + input.toString());

            for (int i = 0 ; i < input.getChildCount() ; i++) {
                functCode.add(new Instruction3Ad("", Instruction3Ad.Operator.param, input.getChild(i).toString(), ""));
            }
        }

        // Traitements internes de la fonction
        for (int i = 0 ; i < instructions.getChildCount() ; i++)
        {
            Tree instruction = instructions.getChild(i);
            // TODO : Generation d'instruction
            switch (instruction.toString())
            {
                case "nop" :
                break;
                case "Set" :
                generateSet(instruction, functCode);
                break;
                case "BoucleIf" :
                generateIf(instruction, functCode);
                break;
                case "BoucleWhile" :
                generateWhile(instruction, functCode);
                break;
                case "BoucleFor" :
                generateFor(instruction, functCode);
                break;
                case "BoucleForeach" :
                generateForeach(instruction, functCode);
                break;
            }
        }

        // Retours de la fonction
        if (output != null)
        {
            for (int i = 0 ; i < output.getChildCount() ; i++)
            {
                functCode.add(new Instruction3Ad("", Instruction3Ad.Operator.returns,
                                                    output.getChild(i).toString(), ""));
            }
        }

        ///////////////////////////////////////////////////////////////////////////////
        //  TODO
        //  A ajouter :
        //      - Phase d'optimisation de la fonction
        ///////////////////////////////////////////////////////////////////////////////

        // TODO : Ajout de la fonction au programme final
    }

    // Génération du code global
    public static void generateCodeFrom(CommonTree arbre) {

        System.out.println(arbre.toString());
        if (arbre.toString().equals("nil")) {
            for (int i = 0 ; i < arbre.getChildCount() ; i++) {
                System.out.println("fonction : " + arbre.getChild(i).toString());
                generateFunct(arbre.getChild(i));
            }
        }

        else {
            generateFunct(arbre);
        }
    }

    

    public static void printCode() {
        for (Instruction3Ad ins : code) {
            System.out.println(ins);
        }
    }

    public static void afficheAST(Tree arbre) {
        System.out.print(arbre.toString() + " (");
        for (int i = 0 ; i < arbre.getChildCount() ; i++) {
            System.out.print("ind " + i + " : ");
            afficheAST(arbre.getChild(i));
        }
        System.out.print(" )");
    }
}
