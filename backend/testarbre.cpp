#include "ArbreBinaire.h"
#include <iostream>
/*
int main() {
    std::shared_ptr<ArbreBinaire> arbrefeuille1 = std::make_shared<ArbreBinaire>();  //2 feuilles 
    std::shared_ptr<ArbreBinaire> arbrefeuille2 = std::make_shared<ArbreBinaire>();  //2 feuilles 
    std::shared_ptr<ArbreBinaire> arbre1 = std::make_shared<ArbreBinaire>(nullptr, arbrefeuille2); //ie 4 feuilles 
    std::shared_ptr<ArbreBinaire> arbre2 = std::make_shared<ArbreBinaire>(arbre1); // Ici on le dit pas mais donc fils droit = nullptr ie 5 feuille car 4 dans le fils gauche (arbre), et 1 dans le droit (là de base)

    // Affichage des adresses mémoire pour vérifier les partages
    std::cout << "Adresse arbre1: " << arbre1.get() << std::endl;
    std::cout << "Adresse arbre2: " << arbre2.get() << std::endl;

    int taille = arbre1->hauteurArbre(arbre1);
    int nombreDeFeuilles = arbre1->compterFeuilles();
    std::cout << "Nombre de feuilles dans arbre1 : " << nombreDeFeuilles <<" taille"<<taille<< std::endl;
    int nombreDeFeuilles2 = arbre2->compterFeuilles();
    std::cout << "Nombre de feuilles dans arbre2 : " << nombreDeFeuilles2 << std::endl;
    
    arbrefeuille1->setLeft(arbrefeuille2);
    arbrefeuille1->setRight(arbrefeuille2);
    int nombreDeFeuilles3 = arbre1->compterFeuilles();
    std::cout << "Nombre de feuilles dans arbre1 aprs modif  : " << nombreDeFeuilles3 << std::endl;
    int nombreDeFeuilles4 = arbre2->compterFeuilles();
    std::cout << "Nombre de feuilles dans arbre2  aprs midif: " << nombreDeFeuilles4 << std::endl;
/*
    // Modification de feuille1
    std::shared_ptr<ArbreBinaire> nouvelleFeuille = std::make_shared<ArbreBinaire>();
    feuille1->setLeft(nouvelleFeuille);

    // Affichage des adresses mémoire après modification
    std::cout << "Adresse arbre1 après modification: " << arbre1.get() << std::endl;
    std::cout << "Adresse arbre2 après modification: " << arbre2.get() << std::endl;

    // Affichage des arbres après modification
    std::cout << "Arbre1 après modification : " << std::endl;
    arbre1->afficher();

    std::cout << "Arbre2 après modification : " << std::endl;
    arbre2->afficher();

    return 0;
}
*/