package src.verif;

import java.util.ArrayList;
import src.utils.Pair;

import org.antlr.runtime.tree.CommonTree;

// Classe qui permet de vérifier la sémantique
public class Verificator {
    // Stockage des variables définies dans le programme
    private static ArrayList<String> variables = new ArrayList<String>();
    private static ArrayList<Pair<String, Integer>> functions = new ArrayList<Pair<String, Integer>>();

    // Execution de la procédure de vérification à partir du noeud noeud
    public static boolean execute(CommonTree noeud) {
        try {
            if (noeud.getText()==null) { // S'il y a plusieurs fonctions déclarées, la racine de l'arbre vaut null
                boolean mainFound=false;
                for (int i=0; i<noeud.getChildCount(); i++) { // On parcourt les fonctions
                    if (noeud.getChild(i).getText()=="Function") {
                        if (noeud.getChild(i).getChild(0).getText().equals("main")) {
                            mainFound=true;
                        }
                    } else {
                        throw new RuntimeException("Instruction out of function");
                    }
                }
                if (!mainFound) {
                    throw new RuntimeException("main not found");
                }
            } else if (noeud.getText().equals("Function")) {
                if (!noeud.getChild(0).getText().equals("main")) {
                    throw new RuntimeException("main not found");
                }
            } else {
                throw new RuntimeException("unknown problem");
            }
            parcourir(noeud);
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
        System.out.println(varName);
        if (!verifVar(varName)) { // Si la variable n'est pas déjà dedans
            variables.add(varName);
            return true;
        }
        return false;
    }

    // Vérification d'un noeud Input
    private static void verifInput(CommonTree noeud) {
        for (int i = 0; i < noeud.getChildCount(); i++) {
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
    // Vérification d'un noeud If
    /*private static void verifIf(CommonTree noeud) throws RuntimeException {
                                        
                                        //TODO A REFAIRE QUAND ON AURA "NOP A" en condition
                                        //Vérification de la condition
                                        CommonTree condition = (CommonTree)noeud.getChild(0);   // noeud Condition
                                        condition=condition.getChild(0);    //noeud Var
                                        //On verifie si la fonction et la variable sont bien definies
                                        for (int i = 0; i < condition.getChildCount(); i++) { //On peut avoir not A   par exemple avec not une fct
                                        if (!verifVar(condition.getChild(i).getText())) {
                                                throw new RuntimeException("Variable " + condition.getChild(i).getText() + " non définie");
                                            }
                                        }

        //Vérification Then
        CommonTree action = (CommonTree)noeud.getChild(1);// Then
        putVar("////")//Genre c'est le nil qu'on ajoute dans son cours pour séparer les sous blocs ! on enleve ce machin à la fin de la verification de then..
        parcourir(action); //La on parcours dans then comme si on avait un bloc normal je pense
       
        //Suppression d'une sous-couche jusqu'à ////
        while(true){
            String check=variables.getLast();
            variables.remove(variables.size()-1);
            if(check.equals("////")){
                break;
            }
        }

    }*/

    /*// Vérification d'un noeud For
    private static void verifFor(CommonTree noeud) throws RuntimeException {
                                        
        //Vérification de la boucle Je crois qu'on a que une operande dans le For A (voir fin specification) ou aussi une fonction ? 
        CommonTree boucle = (CommonTree)noeud.getChild(0);   // noeud Var
        boucle=boucle.getChild(0);    //noeud operande
        //On verifie si la variable sont bien definies
        if (!verifVar(boucle.getText())) {
            throw new RuntimeException("Variable " + boucle.getText() + " non définie");
        }
        
        //Vérification Then. Je crois que c'est comme pour le if ?
        CommonTree action = (CommonTree)noeud.getChild(1);// Then
        putVar("////")//Genre c'est le nil qu'on ajoute dans son cours pour séparer les sous blocs ! on enleve ce machin à la fin de la verification de then..
        parcourir(action); //La on parcours dans then comme si on avait un bloc normal je pense
       
        //Suppression d'une sous-couche jusqu'à ////
        while(true){
            String check=variables.getLast();
            variables.remove(variables.size()-1);
            if(check.equals("////")){
                break;
            }
        }

    }  */ 

    private static void verifSet(CommonTree noeud) throws RuntimeException {
        // Variables pour vérifier si la sémantique est bonne
        int gauche=0, droite=0;
        CommonTree filsGauche=(CommonTree)(noeud.getChild(0)); // À gauche du égal
        CommonTree filsDroit=(CommonTree)(noeud.getChild(1)); // À droite du égal

        // Parcours du fils gauche: on les comptes et on ajoute les nouvelles variables
        do {
            if (!putVar(filsGauche.getChild(0).getText())) {
                throw new RuntimeException("Multiple assignment of  " + filsGauche.getChild(0).getText());
            }
            filsGauche=(CommonTree) (filsGauche.getChild(1));
            gauche++;
        } while (filsGauche.getChildCount()!=1);

        /*do {
            if (!verifVar(filsDroit.getChild(0).getText())) {
                throw new RuntimeException("Undefined " + filsGauche.getChild(0).getText());
            }
            filsGauche=(CommonTree) (filsGauche.getChild(1));
            gauche++;
        } while (filsGauche.getChildCount()!=1)*/

        // Existance du fils droit ?
        /*if (!verifVar(filsDroit.getText())) {
            throw new RuntimeException("Variable " + filsDroit.getText() + " non définie");
        }
        // Parcours du fils droit: on compte le nombre de fils
        while (filsDroit.getChildCount() != 0) {
            filsDroit = (CommonTree) (filsDroit.getChild(0));
            if (!verifVar(filsDroit.getText())) {
                throw new RuntimeException("Variable " + filsDroit.getText() + " non définie");
            }
            droite++;
        }
        // La sémantique est bonne si on a soit autant de variables à gauche et
        // à droite, soit si on a une seule variable à droite
        if (gauche == droite || droite == 1) {
        } else {
            throw new RuntimeException("Variable " + noeud.getChild(0).getText() + " mal formée");
        }*/
    }
}