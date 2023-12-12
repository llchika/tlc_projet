package verif;

import java.util.ArrayList;

import org.antlr.runtime.tree.CommonTree;

// Classe qui permet de vérifier la sémantique
public class Verificator {
    //Stockage des variables définies dans le programme
    private static ArrayList<String> variables=new ArrayList<String>();
    //Execution de la procédure de vérification à partir du noeud noeud
    public static boolean execute(CommonTree noeud) {
        try {
            parcourir(noeud);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Parcours de noeuf
    private static void parcourir(CommonTree noeud) {
        //Cas ou le noeud est un Set
        if (noeud.getText().equals("Set")) {
            verifSet(noeud);
        }
        //Cas ou le noeud est un Input
        else if (noeud.getText().equals("Input")) {
            verifInput(noeud);
        }
        //Et puis sinon on parcour les enfants du noeud  
        else {
            for (int i=0; i<noeud.getChildCount(); i++) {
                parcourir((CommonTree)(noeud.getChild(i)));
            }
        }
    }

    //Vérification de l'appartenance de variable à variables
    private static boolean verifVar(String varName) {
        if (varName.contains("?")) {
            varName=varName.replace("?", "");
        }
        return variables.contains(varName);
    }

    //Ajout du nom de la variable aux variables existantes
    private static void putVar(String varName) {
        variables.add(varName);
    }

    //Vérification d'un noeud Input
    private static void verifInput(CommonTree noeud) throws RuntimeException {
        for (int i=0; i<noeud.getChildCount(); i++) {
            putVar(noeud.getChild(i).getText());
        }
    }

    //Vérificaiton d'un noeud Input
    private static void verifSet(CommonTree noeud) throws RuntimeException {
        // Variables pour vérifier si la sémantique est bonne
        int gauche=0, droite=1;
        //Fils du noeud
        CommonTree filsGauche=noeud;
        CommonTree filsDroit=(CommonTree)(noeud.getChild(1));

        //Parcours du fils gauche: on les comptes et on ajoute les nouvelles variables
        while (filsGauche.getChildCount()!=0) {
            filsGauche=(CommonTree)(filsGauche.getChild(0));
            if (!verifVar(filsGauche.getText())) {
                variables.add(filsGauche.getText());
            }
            gauche++;
        }

        // Existance du fils droit ? 
        if (!verifVar(filsDroit.getText())) {
            throw new RuntimeException("Variable "+ filsDroit.getText() +" non définie");
        }
        //Parcours du fils droit: on compte le nombre de fils
        while (filsDroit.getChildCount()!=0) {
            filsDroit=(CommonTree)(filsDroit.getChild(0));
            if (!verifVar(filsDroit.getText())) {
                throw new RuntimeException("Variable "+ filsDroit.getText() +" non définie");
            }
            droite++;
        }
        //La sémantique est bonne si on a soit autant de variables à gauche et
        // à droite, soit si on a une seule variable à droite
        if (gauche==droite || droite==1) {
        } else {
            throw new RuntimeException("Variable "+ noeud.getChild(0).getText() +" mal formée");
        }
    }
}