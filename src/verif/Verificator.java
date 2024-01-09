package verif;

import java.util.ArrayList;

import org.antlr.runtime.tree.CommonTree;

public class Verificator {
    private static ArrayList<String> variables=new ArrayList<String>();

    public static boolean execute(CommonTree noeud) {
        try {
            parcourir(noeud);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void parcourir(CommonTree noeud) {
        if (noeud.getText().equals("Set")) {
            verifSet(noeud);
        } else if (noeud.getText().equals("Input")) {
            verifInput(noeud);
        }else {
            for (int i=0; i<noeud.getChildCount(); i++) {
                parcourir((CommonTree)(noeud.getChild(i)));
            }
        }
    }


    private static void verifInput(CommonTree noeud) throws RuntimeException {
        CommonTree tmp=noeud;
        while (tmp.getChildCount()!=0) {
            if (!variables.contains(tmp.getChild(0).getText())) {
                variables.add(tmp.getChild(0).getText());
            }
            tmp=(CommonTree)(tmp.getChild(0));
        }

    }

    private static void verifSet(CommonTree noeud) throws RuntimeException {
        int gauche=0, droite=1;
        CommonTree filsGauche=noeud;
        CommonTree filsDroit=(CommonTree)(noeud.getChild(1));

        
        while (filsGauche.getChildCount()!=0) {
            filsGauche=(CommonTree)(filsGauche.getChild(0));
            if (!variables.contains(filsGauche.getText())) {
                variables.add(filsGauche.getText());
            }
            gauche++;
        }

        while (filsDroit.getChildCount()!=0) {
            filsDroit=(CommonTree)(filsDroit.getChild(0));
            if (!variables.contains(filsDroit.getText())) {
                throw new RuntimeException("Variable "+ filsDroit.getText() +" non définie");
            }
            droite++;
        }

        if (gauche==droite || droite==1) {
        } else {
            throw new RuntimeException("Variable "+ noeud.getChild(0).getText() +" mal formée");
        }
    }
}
