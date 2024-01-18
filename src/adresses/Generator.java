package src.adresses;
import java.io.File;
import java.io.FileWriter;
import org.antlr.runtime.tree.*;

public class Generator {
    public static void execute(Tree arbre) {
        try {
            FileWriter fichierSortie = new FileWriter("build/sortieTroisAdresses.txt");

            fichierSortie.write("Test");

            fichierSortie.close();

        } catch (Exception e) {
            System.out.println("Erreur création du fichier de sortie");
            e.printStackTrace();
            return;
        }

        System.out.println("Test");
    }
}
