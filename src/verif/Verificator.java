package src.verif;

import java.util.ArrayList;
import java.util.Arrays;

import src.utils.Triplet;

import org.antlr.runtime.tree.CommonTree;

/*
 * Classe effectuant la vérification sémantique à partir d'un AST
 */
public class Verificator {
    // Liste des variables disponibles
    private static ArrayList<String> variables=new ArrayList<String>();

    // Liste des fonctions disponibles
    // Un Triplet correspond à : (Nom de fonction, nombre de valeurs en sortie, nombre de valeurs en entrée)
    // Les fonctions enregistrées correspond aux fonctions de la bibliothèque native
    private static ArrayList<Triplet<String, Integer>> functions=new ArrayList<>(Arrays.asList(
        new Triplet<>("cons", 1, -1),
        new Triplet<>("list", 1, -1),
        new Triplet<>("hd", 1, 1),
        new Triplet<>("tl", 1, 1),
        new Triplet<>("not", 1, 1)
    ));

    /**
     * Lance l'analyse sémantique
     * @param noeud: Arbre de syntaxe abstraite à analyser
     * @return vrai (true) si le code est correct, faux (false) sinon
     */
    public static boolean execute(CommonTree noeud) {
        try {
            if (noeud.getText()==null) { // S'il y a plusieurs fonctions déclarées, la racine de l'arbre vaut null
                int mainFound=0;
                for (int i=0; i<noeud.getChildCount(); i++) { // On parcourt les différentes fonctions
                    CommonTree noeudFonction = (CommonTree) noeud.getChild(i);
                    if (noeudFonction.getText()=="Function") {
                         CommonTree FonctionName= (CommonTree) noeudFonction.getChild(0);//noeud Nom de la fonction
                        if (FonctionName.getText().equals("main")) { // On s'ssure qu'il n'y a qu'un main dans le programme
                            mainFound++;
                        }
                    } else {
                        throw new RuntimeException("Instruction out of function");
                    }
                }
                if (mainFound==1) { // Début du parcours de l'arbre, on sait qu'on a que des fonctions ici
                    for (int i=0; i<noeud.getChildCount(); i++) {
                        variables=new ArrayList<String>(); // Remise à 0 du contexte pour chaque fonction
                        CommonTree noeudFunction=(CommonTree)(noeud.getChild(i));//Noeud fonction
                        parcourir(noeudFunction); // On parcourt l'arbre d'une fonction
                        // S'il y a une erreur, l'analyse se sera arrêtée avant d'arriver ici
                        
                        String funName=noeudFunction.getChild(0).getText();//Nom de la fonction

                        if (funName.equals("main")) { // Tout le code en dessous du main ne sera jamais executé, inutile de le vérifier
                            break;
                        } else { // Sinon on ajoute aux fonctions connues
                            int argsN, retN;
                            if (noeud.getChild(i).getChild(1).getChildCount()==3) { // S'il y a des variables en entrée
                                argsN=noeudFunction.getChild(1).getChild(0).getChildCount();
                                retN=noeudFunction.getChild(1).getChild(2).getChildCount();
                            } else {
                                argsN=0;
                                retN=noeudFunction.getChild(1).getChild(1).getChildCount();
                            }
                            putFun(funName, retN, argsN);//Ajout de la fonction 
                        }
                    }
                } else if (mainFound>1) {//Si plusieurs main, c'est un probleme
                    throw new RuntimeException("main function redefinition");
                } else {//Si aucun, c'est un probleme
                    throw new RuntimeException("main not found");
                }
            } else if (noeud.getText().equals("Function")) { // S'il n'y a qu'une fonction dans le programme, vaut Function
                if (!noeud.getChild(0).getText().equals("main")) { // Il faut alors que cette fonction soit un Main
                    throw new RuntimeException("main not found");
                }
                parcourir(noeud); // Parcours de l'arbre
            } else {
                throw new RuntimeException("unknown problem"); // ???
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Parcours récursif d'un arbre
     * @param noeud: Noeud à analyser...
     */
    private static void parcourir(CommonTree noeud) {
        /*
         * /!\ Ne pas remplacer par un switch case, pas convaincu que Java va savoir utiliser le bon == /!\ en fait ca marche juste pas je crois :(
         */
        if (noeud.getText()==null) {

        }
        else if (noeud.getText().equals("Set")) {
            verifSet(noeud);
        }
        else if (noeud.getText().equals("Input")) {
            verifInput(noeud);
        }
        else if (noeud.getText().equals("Output")) {
            verifOutput(noeud);
        } 
        else if (noeud.getText().equals("If")) {
            verifIf(noeud);
        }
        else if (noeud.getText().equals("For")) {
            verifForWhile(noeud);
        }
        else if (noeud.getText().equals("While")) {
            verifForWhile(noeud);
        }
        else if (noeud.getText().equals("ForEach")) {
            verifForEach(noeud);
        }
        // Et puis sinon on parcour les enfants du noeud
        else {
            for (int i = 0; i < noeud.getChildCount(); i++) {
                parcourir((CommonTree) (noeud.getChild(i)));
            }
        }
    }


    /**
     * Vérifie l'existence d'une variable
     * @param varName: Variable à vérifier
     * @return vrai (true) si la variable existe, faux (false) sinon
     */
    private static boolean verifVar(String varName) {
        return variables.contains(varName);
    }


    // Ajout du nom de la variable aux variables existantes si elle n'existe pas encore
    /**
     * Ajoute une variable à la liste des variables existantes
     * @param varName: Variable à ajouter
     * @return vrai (true) si la variable a été ajoutée, faux (false) sinon
     */
    private static boolean putVar(String varName) {
        if (!verifVar(varName)) { // Si la variable n'est pas déjà dedans
            variables.add(varName);
            return true;
        }
        return false;
    }


    /**
     * Vérifie qu'une fonction existe
     * @param funName: Nom de la fonction à vérifier
     * @return vrai (true) si la fonction existe, faux (false) sinon
     */
    private static boolean verifFun(String funName) {
        for (int i=0; i<functions.size(); i++) {
            if (functions.get(i).getKey().equals(funName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Donne le nombre de valeurs retournées par une fonction
     * @param funName: Nom de la fonction à chercher
     * @return Nombre de valeurs retournées par la fonction, si la fonction n'existe pas renvoie -1
     */
    private static int getFunRetN(String funName) {
        for (int i=0; i<functions.size(); i++) {
            if (functions.get(i).getKey().equals(funName)) {
                return functions.get(i).getValue1();
            }
        }
        return -1;//Si la fonction n'existe pas
    }


    /**
     * Donne paramètres d'une fonction
     * @param funName: Nom de la fonction à chercher
     * @return Nombre paramètres de la fonction, si la fonction n'existe pas renvoie -1
     */
    private static int getFunArgsN(String funName) {
        for (int i=0; i<functions.size(); i++) {
            if (functions.get(i).getKey().equals(funName)) {
                return functions.get(i).getValue2();
            }
        }
        return -1;
    }

    /**
     * Ajoute une fonction de la liste des fonctions connues
     * @param funName: Nom de la fonction
     * @param nRet: Nombre de valeurs retournées
     * @param nArgs: Nombre de paramètres
     * @return vrai (true) si la fonction a été ajoutée, faux (false) sinon
     */
    private static boolean putFun(String funName, int nRet, int nArgs) {
        if (!verifFun(funName)) {
            functions.add(new Triplet<>(funName, nRet, nArgs));
            return true;
        } else {
            throw new RuntimeException(funName+" function redefinition");
        }
    }


    /**
     * Vérification d'un arbre correspondand à un Input
     * Créé par l'instruction Read d'une fonction
     * @param noeud: Arbre à vérifier
     */
    private static void verifInput(CommonTree noeud) {
        for (int i = 0; i<noeud.getChildCount(); i++) {
            putVar(noeud.getChild(i).getText());
        }
    }


    /**
     * Vérification d'un arbre correspondand à un Output
     * Créé par l'instruction Write d'une fonction
     * @param noeud: Arbre à vérifier
     */
    private static void verifOutput(CommonTree noeud) throws RuntimeException {
        for (int i = 0; i < noeud.getChildCount(); i++) {
            if (!verifVar(noeud.getChild(i).getText())) {
                throw new RuntimeException("Variable " + noeud.getChild(i).getText() + " non définie");
            }
        }
    }

    /**
     * Vérification d'un arbre correspondand à un FunCall
     * Créé par les appels de fonction, vérifie 
     * que les expressions utilisée en paramètres sont
     * valides et que la fonction existe
     * @param noeud: Arbre à vérifier
     */
    private static void verifFunCall(CommonTree noeud) throws RuntimeException {
        String funName=noeud.getChild(0).getText(); // Nom de la fonction
        //Erreur si la fonction n'existe pas
        if (!verifFun(funName)) {
            throw new RuntimeException("Undefined function "+funName);
        }

        // Arguments
        //On verifie si on a le bon nombre d'arguments
        CommonTree args=(CommonTree)(noeud.getChild(1));// Noeud Args
        int tmp=getFunArgsN(funName); // Nombre de paramètres de la fonction
        if (tmp!=-1) { // -1 <=> Plusieurs paramètres ok
            if (args.getChildCount()!=tmp) { // Vérifie qu'on a le bon nombre de paramètres
                throw new RuntimeException("Wrong number of argument in "+funName+" call. "+args.getChildCount()+"/"+tmp+ " (g/e)");
            }
        }
        //On verifie si ils existent 
        for (int i=0; i<args.getChildCount(); i++) { // Vérifie que les paramètres sont valides
            CommonTree noeudType=(CommonTree)args.getChild(i);
            switch (noeudType.getText()) {
                    case "Var": {
                        if (!verifVar(noeudType.getChild(0).getText())) {
                            throw new RuntimeException("Undefined variable "+noeudType.getChild(0).getText());
                        } 
                        break;
                    }
                    case "FunCall": {
                        String funNamet=noeudType.getChild(0).getText();
                        if (!verifFun(funNamet)) {
                            throw new RuntimeException("Undefined function "+funNamet);
                        }
                        verifFunCall((CommonTree)(noeudType));
                        break;
                    }
                    default:
                        break;
                }
        }
    }

    /**
     * Vérification d'un arbre correspondand à un Set
     * Créé par les affectations de variable
     * Vérifie qu'il y a le bon nombre de variables
     * affecté/à affecter et que les valeurs à affecter
     * existent bien.
     * @param noeud: Arbre à vérifier
     */
    private static void verifSet(CommonTree noeud) throws RuntimeException {
        // Variables pour vérifier si la sémantique est bonne
        int gauche=0, droite=0;
        CommonTree filsGauche=(CommonTree)(noeud.getChild(0)); // À gauche du égal
        CommonTree filsDroit=(CommonTree)(noeud.getChild(1)); // À droite du égal

        ArrayList<String> variablesVues=new ArrayList<>();

        // Vérification des affectées
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

        // Création des variables
        while (filsGauche!=null) {
            if (!variablesVues.contains(filsGauche.getChild(0).getText())) {
                variablesVues.add(filsGauche.getChild(0).getText());
                putVar(filsGauche.getChild(0).getText());
            } else {
                throw new RuntimeException("Multiple assignment of  " + filsGauche.getChild(0).getText());
            }
            filsGauche=(CommonTree)(filsGauche.getChild(1));
            gauche++;
        }

        if (gauche == droite || droite==1) {

        } else {
            throw new RuntimeException("Uneven variable assignment: "+gauche+" := "+droite);
        }
    }

    /**
     * Vérification d'un arbre correspondand à un If
     * Créé par l'instruction if de while
     * @param noeud: Arbre à vérifier
     */
    private static void verifIf(CommonTree noeud) throws RuntimeException {
        CommonTree condition=(CommonTree)(noeud.getChild(0).getChild(0)); // Noeud Var ou fonction
        //Cas Var: on verifie que ca soit bien defini.
        if(condition.getText().equals("Var")) {
            //System.out.println(condition.getChild(0).getText());
            if (!verifVar(condition.getChild(0).getText())) {
                throw new RuntimeException("Undefined variable "+condition.getChild(0).getText());
            }
        }
        // Cas Funcall on verifie aussi.
        else if(condition.getText().equals("FunCall")) {
            verifFunCall(condition);
        }
        // Condition ok

        for (int i=1; i<noeud.getChildCount(); i++) { // Au moins un Then, peut être un Else
            variables.add(null); // Créaction du contexte

            parcourir((CommonTree)(noeud.getChild(i)));

            String tmp;
            do { // On supprime le contexte
                tmp=variables.get(variables.size()-1);
                variables.remove(variables.size()-1);
            } while (tmp!=null);
        }
    }


    /**
     * Vérification d'un arbre correspondand à un For ou un While
     * Créé par les instructions For et While de while.
     * @param noeud: Arbre à vérifier
     */
    private static void verifForWhile(CommonTree noeud) throws RuntimeException {
        CommonTree forWhile=(CommonTree)(noeud.getChild(0));   // Noeud For ou While
        if(forWhile.getText().equals("Var")) {
            if (!verifVar(forWhile.getChild(0).getText())) {
                throw new RuntimeException("Undefined variable "+forWhile.getChild(0).getText());
             }

        }
        else if(forWhile.getText().equals("FunCall")) {
            verifFunCall(forWhile);
        }
        // forWhile ok

        variables.add(null); // Créaction du contexte
        parcourir((CommonTree)(noeud.getChild(1)));

        String tmp;
        do { // On supprime le contexte
            tmp=variables.get(variables.size()-1);
            variables.remove(variables.size()-1);
        } while (tmp!=null);
    }

    private static void verifForEach(CommonTree noeud) throws RuntimeException {
        CommonTree forEach=(CommonTree)(noeud.getChild(1));   // Noeud ForEach
        if(forEach.getText().equals("Var")) {
            if (!verifVar(forEach.getChild(0).getText())) {
                throw new RuntimeException("Undefined variable "+forEach.getChild(0).getText());
             }

        }
        else if(forEach.getText().equals("FunCall")) {
            verifFunCall(forEach);
        }
        // forWhile ok
        
        variables.add(null); // Créaction du contexte

        putVar(noeud.getChild(0).getText());
        parcourir((CommonTree)(noeud.getChild(2)));

        String tmp;
        do { // On supprime le contexte
            tmp=variables.get(variables.size()-1);
            variables.remove(variables.size()-1);
        } while (tmp!=null);
    }
}