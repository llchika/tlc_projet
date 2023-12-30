#include "ArbreBinaire.h"
#include <iostream>
/*
int main() {
    std::shared_ptr<ArbreBinaire> feuille = std::make_shared<ArbreBinaire>();  //2 feuilles 
    std::shared_ptr<ArbreBinaire> feuille2 = std::make_shared<ArbreBinaire>();  //2 feuilles pour tester modification
    
    // Test de la copie 
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(nullptr, feuille); //ie 3 feuilles 
    std::shared_ptr<ArbreBinaire> arbreTest = std::make_shared<ArbreBinaire>(arbre); // Ici on le dit pas mais donc fils droit = nullptr ie 5 feuille car 4 dans le fils gauche (arbre), et 1 dans le droit (là de base)
    std::cout << "Adresse arbre: " << arbre.get() << std::endl;
    std::cout << "Adresse arbre Test: " << arbreTest.get() << std::endl;

    //Test d'evaluation d'un arbre vu comme un int
    std::shared_ptr<ArbreBinaire> arbredeux = std::make_shared<ArbreBinaire>(nullptr, feuille); //arbre qui représente (cons nil (cons nil nil)) 

    int intvalue = arbredeux->evalueInt();
    int nombreDeFeuilles = arbredeux->compterFeuilles();
    std::cout << "\nL'arbre possede " << nombreDeFeuilles <<" feuilles et reprèsente l'int "<<intvalue<< std::endl;


    
    
    return 0;
}
*/


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