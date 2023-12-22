#include "ArbreBinaire.h"
#include "iostream"
//Chaque fonction correspond à un élèment de
//la partie 'Les expressions' de la spécification while
// pour chaque fonction je marque ici à quoi ca se rapporte
//car flemme de faire le .H ^pour le moment^^^^^^^^


//(cons) = nil construit un arbre vide 
std::shared_ptr<ArbreBinaire> cons() {
    return nullptr;
}

// (cons A B) construit un arbre binaire ayant A pour fils gauche et B pour fils droit 
std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right) {
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(left,right);
    return arbre;
}

//(cons T1 T2 … Tn) = (cons T1 (cons T2 … (cons Tn-1 Tn) …)
//Pas besoin ? enfin jamais on va avoir + de deux args 

//(list) = nil construit une list vide
std::shared_ptr<ArbreBinaire> list() {
    return cons();
}





//     hs à partir d'ici 
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




