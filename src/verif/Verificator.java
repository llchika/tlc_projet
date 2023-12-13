package src.verif;

import java.util.ArrayList;
import java.util.Arrays;

import src.utils.Triplet;

import org.antlr.runtime.tree.CommonTree;

// Classe qui permet de vérifier la sémantique
public class Verificator {
    // Stockage des variables définies dans le programme
    private static ArrayList<String> variables=new ArrayList<String>();
    private static ArrayList<Triplet<String, Integer>> functions=new ArrayList<>(Arrays.asList(
        new Triplet<>("cons", 1, -1),
        new Triplet<>("list", 1, -1),
        new Triplet<>("hd", 1, 1),
        new Triplet<>("tl", 1, 1)
    ));

    // Execution de la procédure de vérification à partir du noeud noeud
    public static boolean execute(CommonTree noeud) {
        try {
            if (noeud.getText()==null) { // S'il y a plusieurs fonctions déclarées, la racine de l'arbre vaut null
                int mainFound=0;
                for (int i=0; i<noeud.getChildCount(); i++) { // On parcourt les fonctions
                    if (noeud.getChild(i).getText()=="Function") {
                        if (noeud.getChild(i).getChild(0).getText().equals("main")) {
                            mainFound++;
                        }
                    } else {
                        throw new RuntimeException("Instruction out of function");
                    }
                }
                if (mainFound==1) { // Début du parcours de l'arbre, on sait qu'on a que des fonctions ici
                    for (int i=0; i<noeud.getChildCount(); i++) {
                        variables=new ArrayList<String>(); // Remise à 0 du contexte
                        parcourir((CommonTree)(noeud.getChild(i))); // Le programme s'arrête là s'il y a une erreur
                        
                        String funName=noeud.getChild(i).getChild(0).getText();

                        if (funName.equals("main")) { // Tout le code en dessous du main ne sera jamais executé, inutile de le vérifier
                            break;
                        } else { // Sinon on ajoute aux fonctions connues
                            int argsN=noeud.getChild(i).getChild(1).getChild(0).getChildCount(); // Nombre d'arguments de la fonction
                            int retN=noeud.getChild(i).getChild(1).getChild(2).getChildCount(); // Nombre de valeur de retour

                            putFun(funName, retN, argsN);
                        }
                    }
                } else if (mainFound>1) {
                    throw new RuntimeException("main function redefinition");
                } else {
                    throw new RuntimeException("main not found");
                }
            } else if (noeud.getText().equals("Function")) {
                if (!noeud.getChild(0).getText().equals("main")) {
                    throw new RuntimeException("main not found");
                }
                parcourir(noeud); // Parcours de l'arbre
            } else {
                throw new RuntimeException("unknown problem");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Parcours de noeuf
    private static void parcourir(CommonTree noeud) {
        if (noeud.getText()==null) {

        }
        // Cas ou le noeud est un Set
        else if (noeud.getText().equals("Set")) {
            verifSet(noeud);
        }
        // Cas ou le noeud est un Input
        else if (noeud.getText().equals("Input")) {
            verifInput(noeud);
        }
        // Cas ou le noeud est un output
        else if (noeud.getText().equals("Output")) {
            verifOutput(noeud);
        }
        // Et puis sinon on parcour les enfants du noeud
        else {
            for (int i = 0; i < noeud.getChildCount(); i++) {
                parcourir((CommonTree) (noeud.getChild(i)));
            }
        }
    }

    // Vérification de l'appartenance de variable à variables
    private static boolean verifVar(String varName) {
        return variables.contains(varName);
    }

    // Ajout du nom de la variable aux variables existantes
    private static boolean putVar(String varName) {
        //System.out.println(varName);
        if (!verifVar(varName)) { // Si la variable n'est pas déjà dedans
            variables.add(varName);
            return true;
        }
        return false;
    }

    private static boolean verifFun(String funName) {
        for (int i=0; i<functions.size(); i++) {
            if (functions.get(i).getKey().equals(funName)) {
                return true;
            }
        }
        return false;
    }

    private static int getFunRetN(String funName) {
        for (int i=0; i<functions.size(); i++) {
            if (functions.get(i).getKey().equals(funName)) {
                return functions.get(i).getValue1();
            }
        }
        return -1;
    }

    private static int getFunArgsN(String funName) {
        for (int i=0; i<functions.size(); i++) {
            if (functions.get(i).getKey().equals(funName)) {
                return functions.get(i).getValue2();
            }
        }
        return -1;
    }

    private static boolean putFun(String funName, int nRet, int nArgs) {
        if (!verifFun(funName)) {
            functions.add(new Triplet<>(funName, nRet, nArgs));
            return true;
        }
        return false;
    }

    // Vérification d'un noeud Input
    private static void verifInput(CommonTree noeud) {
        for (int i = 0; i<noeud.getChildCount(); i++) {
            putVar(noeud.getChild(i).getText());
        }
    }

    // Vérification d'un noeud Output
    private static void verifOutput(CommonTree noeud) throws RuntimeException {
        for (int i = 0; i < noeud.getChildCount(); i++) {
            if (!verifVar(noeud.getChild(i).getText())) {
                throw new RuntimeException("Variable " + noeud.getChild(i).getText() + " non définie");
            }
        }
    }

    private static void verifFunCall(CommonTree noeud) throws RuntimeException {
        String funName=noeud.getChild(0).getText(); // nom de la fonction
        if (!verifFun(funName)) {
            throw new RuntimeException("Undefined function "+funName);
        }
        // Arguments
        CommonTree args=(CommonTree)(noeud.getChild(1));// ie noeud args hein
        int tmp=getFunArgsN(funName);
        if (tmp!=-1) { // -1 <=> Plusieurs paramètres ok
            if (args.getChildCount()!=tmp) {
                throw new RuntimeException("Wrong number of argument in "+funName+" call. "+args.getChildCount()+"/"+tmp);
            }
        }
        for (int i=0; i<args.getChildCount(); i++) {
            switch (args.getChild(i).getText()) {
                    case "Var": {
                        if (!verifVar(args.getChild(i).getChild(0).getText())) {
                            throw new RuntimeException("Undefined variable "+args.getChild(i).getChild(0).getText());
                        } 
                        break;
                    }
                    case "FunCall": {
                        String funNamet=args.getChild(i).getChild(0).getText();
                        if (!verifFun(funNamet)) {
                            throw new RuntimeException("Undefined function "+funNamet);
                        }
                        verifFunCall((CommonTree)(args.getChild(i)));
                        break;
                    }
                    default:
                        break;
                }
        }
    }

    private static void verifSet(CommonTree noeud) throws RuntimeException {
        // Variables pour vérifier si la sémantique est bonne
        int gauche=0, droite=0;
        CommonTree filsGauche=(CommonTree)(noeud.getChild(0)); // À gauche du égal
        CommonTree filsDroit=(CommonTree)(noeud.getChild(1)); // À droite du égal

        // Création des variables
        while (filsGauche!=null) {
            if (!putVar(filsGauche.getChild(0).getText())) {
                throw new RuntimeException("Multiple assignment of  " + filsGauche.getChild(0).getText());
            }
            //System.out.println(variables);
            filsGauche=(CommonTree)(filsGauche.getChild(1));
            gauche++;
        }

        // Vérification des valeurs
        while (filsDroit!=null) {
            if (filsDroit.getText().equals("Exprs")) {
                switch (filsDroit.getChild(0).getText()) {
                    case "Var": {
                        if (!verifVar(filsDroit.getChild(0).getChild(0).getText())) {
                            throw new RuntimeException("Undefined variable "+filsDroit.getChild(0).getChild(0).getText());
                        } 
                        break;
                    }
                    case "FunCall": {
                        String funName=filsDroit.getChild(0).getChild(0).getText();
                        if (!verifFun(funName)) {
                            throw new RuntimeException("Undefined function "+funName);
                        } else {
                            verifFunCall((CommonTree)(filsDroit.getChild(0)));
                            droite+=getFunRetN(funName)-1;
                        }
                        break;
                    }
                    default:
                        break;
                }

                filsDroit=(CommonTree)(filsDroit.getChild(1));
                droite++;
            } else {
                filsDroit=null;
            }
        }

        if (gauche == droite || droite==1) {

        } else {
            throw new RuntimeException("Uneven variable assignment: "+gauche+" := "+droite);
        }
    }
}