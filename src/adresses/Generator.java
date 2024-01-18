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
import java.io.FileWriter;

// import com.sun.org.apache.bcel.internal.generic.Instruction;
//import com.sun.source.tree.Tree;

import src.adresses.Instruction3Ad;

public class Generator {
    public static List<Instruction3Ad> code = new LinkedList<>();
    private static int nbRegister = 0;

    public static void execute() {
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



    private static void generateFrag(Tree frag, List<Instruction3Ad> functCode)
    {
        // TODO : corps de la fonction
            switch (frag.toString())
            {
                case "nop" :
                break;
                case "Set" :
                CommonTree filsGauche=(CommonTree)(frag.getChild(0)); // À gauche du égal
                CommonTree filsDroit=(CommonTree)(frag.getChild(1)); // À droite du égal
                for(int i = 0 ; i < filsDroit.getChildCount() ; i++)
                {
                    switch (filsDroit.getChild(i).getText())
                    {
                        case "Var": {
                            functCode.add(new Instruction3Ad(filsGauche.getChild(i).getText(), Instruction3Ad.Operator.setTo, "", ""));
                        }
                    }
                }

                break;
                // case "If" :
                // generateIf(instruction, functCode);
                // break;
                // case "While" :
                // generateWhile(instruction, functCode);
                // break;
                // case "For" :
                // generateFor(instruction, functCode);
                // break;
                // case "ForEach" :
                // generateForeach(instruction, functCode);
                // break;
            }
        
    }




    // Generation du code d'une fonction
    //  - while, for, foreach, if
    //  - constructeur d'arbre, de liste
    //  - affectation, copie, comparaison, hd, tl, appel fonction quelconque
    private static void generateFunct(Tree arbre) throws RuntimeException {
        // Arborescence de la fonction :
        // Function
        // |---<name>
        // |---Definition
        //     |---Input
        //     |---Corps
        //     |---Output
        String name = arbre.getChild(0).toString();

        // TODO : stockage dans une autre liste pour effectuer les opti directement à l'échelle de la fonction
        List<Instruction3Ad> functCode = code;


        // Identification et linkage des différentes composantes de la fonction
        Tree input = null;
        Tree instructions = null;
        Tree output = null;
        Tree definition = arbre.getChild(1);
        for (int i = 0 ; i < definition.getChildCount() ; i++)
        {
            Tree t = definition.getChild(i);
            if (t.toString().equals("Input"))
                input = t;
            else if (t.toString().equals("Corps"))
                instructions = t;
            else if (t.toString().equals("Output"))
                output = t;
            else
                System.out.println("error in Generator.generateFunct() : unespected token \"" + t.toString() + "\"");
        }
        // Erreurs pour le debug
        if (instructions == null)
        {
            throw new RuntimeException("No match for the corpse of the function " + name);
        }
        if (output == null)
            throw new RuntimeException("No match for the output of the function " + name);


        // Instruction 3 adr de début de fonction
        functCode.add(new Instruction3Ad("", Instruction3Ad.Operator.begin, name, ""));

        // Instructions pour les paramètres de fonction
        if (input != null)
        {

            for (int i = 0 ; i < input.getChildCount() ; i++) {
                functCode.add(new Instruction3Ad("", Instruction3Ad.Operator.pop, input.getChild(i).toString(), ""));
            }
        }

        // Traitements internes de la fonction
        for (int i = 0 ; i < instructions.getChildCount() ; i++)
        {
            Tree instruction = instructions.getChild(i);
            // TODO : Generation d'instruction
            generateFrag(instruction, functCode);
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

        if (arbre.toString().equals("nil")) {
            for (int i = 0 ; i < arbre.getChildCount() ; i++) {
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



    public static void writeCode() {
        try {
            FileWriter fw=new FileWriter("sortieTroisAdresses.txt"); 
            for (Instruction3Ad ins : code) {
                fw.write(ins.toString()+'\n');
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void afficheASTAvecDecalage(Tree arbre, int decalage) {
        System.out.println(arbre.toString());
        for (int i = 0 ; i < arbre.getChildCount() ; i++) {
            for (int j = 0 ; j < decalage ; j++)
                System.out.print("|   ");
            System.out.print("|---");
            afficheASTAvecDecalage(arbre.getChild(i), decalage + 1);
        }
    }

    public static void afficheAST(Tree arbre) {
        System.out.println("AST :");
        afficheASTAvecDecalage(arbre, 0);
    }
}
