#include "ArbreBinaire.h"
#include "iostream"
//Chaque fonction correspond à un élèment de
//la partie 'Les expressions' de la spécification while
// pour chaque fonction je marque ici à quoi ca se rapporte
//car flemme de faire le .H ^pour le moment^^^^^^^^



//Symb : un symbole i.e. un arbre feuille (lexeme Symbol) 
std::shared_ptr<ArbreBinaire> symb() {
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(nullptr,nullptr);//ou mettre cons..
    return arbre;
}

//(cons) = nil construit un arbre vide 
std::shared_ptr<ArbreBinaire> cons() {
    return nullptr;
}

// (cons A B) construit un arbre binaire ayant A pour fils gauche et B pour fils droit 
std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right) {
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(left,right);
    return arbre;
}

// (cons T) = T retourne l’arbre T 
std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &arbre) {
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(arbre);
    return arbre;
}

//(cons T1 T2 … Tn) = (cons T1 (cons T2 … (cons Tn-1 Tn) …)
//Pas besoin ? enfin jamais on va avoir + de deux args 
//Sinon... on mets une liste, et recursivement on crée la liste..

//(list) = nil construit une list vide
std::shared_ptr<ArbreBinaire> list() {
    return cons();
}

//(list T) = (cons T nil) construit une liste a un élément
std::shared_ptr<ArbreBinaire> list(const std::shared_ptr<ArbreBinaire> &element) {
    return cons(element,nullptr);
}


//(list T1 T2 … Tn) = (cons T1 (cons T2 … (cons Tn nil) …) construit une liste à n éléments
//idem que pour cons. ca serait cool si c est automatisé qu'on utilise que 2 trucs ! 


/* (hd T) 
o si T = (cons A B) alors retourne A
o si T = Symb alors retourne nil 
o si T = nil alors retourne nil
Gestion erreur prise en compte
*/
std::shared_ptr<ArbreBinaire> hd(const std::shared_ptr<ArbreBinaire> &something) {
    if(something==nullptr){
        return nullptr;
    }
    if(something->getLeft()==nullptr && something->getRight()==nullptr){
        return nullptr;
    }
    return something->getLeft();
}

/* (tl T) 
o si T = (const A B) alors retourne B
o si T = Symb alors retourne nil
o si T = nil alors retourne nil
Gestion erreur prise en compte
*/
std::shared_ptr<ArbreBinaire> tl(const std::shared_ptr<ArbreBinaire> &something) {
    if(something==nullptr){
        return nullptr;
    }
    if(something->getLeft()==nullptr && something->getRight()==nullptr){
        return nullptr;
    }
    return something->getRight();
}


void nop(){
    std::cout<<"jé né complend pas poulkoi sa malche"<<std::endl;
}



//Fonctions d'évaluation
int evalueAsInt(const std::shared_ptr<ArbreBinaire> arbre)  {
    if (arbre->getRight() == nullptr) {
        return 1;
    } else {
        int hauteurDroit = evalueAsInt(arbre->getRight());
        return 1 + hauteurDroit;
    } 
}

bool evalueAsBool(const std::shared_ptr<ArbreBinaire> arbre){
    if(arbre==nullptr){
        return false;// NULL que si arbre null ?
    }
    else{
        return true; 
    }

}
//je sais pas trop quoi faire ici ! ca affiche un peu tout et n'importe quoi mdr
std::string evalueAsString( const std::shared_ptr<ArbreBinaire> arbre, int hauteur=0){
    std::string string= "";//la chaine de caracteres
    if(arbre==nullptr){
        return string;
    }
    if(arbre->getLeft()==nullptr && arbre->getRight()==nullptr){
        char ascii=hauteur;
        string+=ascii;
        return string;
    }
    else{
    string+=evalueAsString(arbre->getLeft(),hauteur+1);
    string+=evalueAsString(arbre->getRight(),hauteur+1);
    }

    return string;
}
