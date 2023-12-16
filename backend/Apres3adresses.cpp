#include "ArbreBinaire.h"
#include "iostream"
//ya des =nullptr un peu partout car je suis pas certain

ArbreBinaire* cons(ArbreBinaire* left, ArbreBinaire* right=nullptr) {
    ArbreBinaire* arbre=new ArbreBinaire(left,right);
    return arbre;
}

// Fonction pour construire une liste à partir de 2 arbres (ou moins..) 
ArbreBinaire* list(ArbreBinaire* left,ArbreBinaire* right=nullptr) {
    return cons(left,right);
}
//nop
void nop(){
    std::cout<<"Fait rien"<<std::endl;
}



//Mawa je te laisse faire ca, c'est presque list je crois 
//Hd

//tl




