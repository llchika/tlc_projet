#include "ArbreBinaire.h"
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