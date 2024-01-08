#include "ArbreBinaire.h"
#include "RunTime.cpp"
#include <iostream>
#include <cassert>


int testCopie() {
    std::shared_ptr<ArbreBinaire> feuille = std::make_shared<ArbreBinaire>();  //arbre feuille
    std::shared_ptr<ArbreBinaire> feuille2 = std::make_shared<ArbreBinaire>();  //arbre feuille
    
    // Test de la copie 
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(nullptr, feuille); //ie 3 feuilles 
    std::shared_ptr<ArbreBinaire> arbreTest = std::make_shared<ArbreBinaire>(arbre); // copie d'arbre  ie 3 feuilles
    //Affichage des adresses
    std::cout << "Adresse arbre: " << arbre.get() << std::endl; 
    std::cout << "Adresse arbre Test: " << arbreTest.get() << std::endl;

    //Test d'evaluation du int
    int intvalue1 = evalueAsInt(arbre);
    int intvalue2 = evalueAsInt(arbreTest);
    if(intvalue1==intvalue2){
        std::cout <<"Copie réussie. Valeur="<<intvalue1<<std::endl;
    }else{
        std::cout <<"Echec test copie"<<std::endl;
    }
    


    
    
    return 0;
}
int main(){
    testCopie();
    return 1;
}

/* test ASCII
#include "ArbreBinaire.h"
#include "fonctionsArbres.cpp"
#include <iostream>

int main() {
    // Création d'un arbre binaire simple pour le test
    std::shared_ptr<ArbreBinaire> feuille1 = std::make_shared<ArbreBinaire>();
    std::shared_ptr<ArbreBinaire> feuille2 = std::make_shared<ArbreBinaire>();
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>();

    // Ajout de 97 niveaux de fils pour obtenir une hauteur de 97 qui correspond à 'a'
    for (int i = 0; i < 97; ++i) {
        arbre = std::make_shared<ArbreBinaire>(arbre, nullptr);
    }

    // Appel de la fonction evalueAsString
    int nbfeuille=arbre->compterFeuilles();
    std::string representation="nombre de feuilles:"+std::to_string(nbfeuille);
    representation += " ,string:"+evalueAsString(arbre);
    representation+="toto";
    // Affichage de la représentation ASCII de l'arbre
    std::cout << representation<<std::endl;;

    return 0;
}


*/