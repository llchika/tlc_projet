#include "ArbreBinaire.h"
#include "iostream"

/**
 * @return shared_ptr pointant sur un arbre feuille
*/
std::shared_ptr<ArbreBinaire> symb() {
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(nullptr,nullptr);//ou mettre cons..
    return arbre;
}


        //(cons) = nil construit un arbre vide 
/**
 * @return nullptr<==> feuille//Arbre vide
*/
std::shared_ptr<ArbreBinaire> cons() {
    return nullptr;
}


        // (cons A B) construit un arbre binaire ayant A pour fils gauche et B pour fils droit 
/**
 * @param left shared_ptr pointant sur un arbre  representant un fils gauche
 * @param right shared_ptr pointant sur un arbre representant un fils droit
 * @return shared_ptr pointant sur un arbre ayant pour fils gauche une copie left et comme fils droit une copie de right
*/
std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right) {
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(left,right);
    return arbre;
}


        // (cons T) = T retourne l’arbre T 
/**
 * @param arbre shared_ptr pointant sur un arbre à retourner
 * @return shared_ptr pointant sur une copie de arbre
*/
std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &arbre) {
    std::shared_ptr<ArbreBinaire> newarbre = std::make_shared<ArbreBinaire>(arbre);
    return newarbre;
}


        //(cons T1 T2 … Tn) = (cons T1 (cons T2 … (cons Tn-1 Tn) …)
        //Pas besoin ? enfin jamais on va avoir + de deux args 

        //(list) = nil construit une list vide
/**
 * @return shared_ptr pointant sur un arbre vie
*/
std::shared_ptr<ArbreBinaire> list() {
    return cons();
}


        //(list T) = (cons T nil) construit une liste a un élément
/**
 * @param element shared_ptr pointant sur un arbre à ajouter à la liste
 * @return shared_ptr pointant sur un arbre possédant comme fils gauche une copie de element, et comme fils droit un arbre nul
*/
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
/**
 * @param something shared_ptr pointant sur un arbre
 * @return shared_ptr pointant sur une copie du fils gauche s'il existe, ou un arbre vide sinon
*/
std::shared_ptr<ArbreBinaire> hd(const std::shared_ptr<ArbreBinaire> &something) {
    if(something==nullptr){
        return nullptr;
    }
    if(something->getLeft()==nullptr && something->getRight()==nullptr){
        return nullptr;
    }
    return cons(something->getLeft());//pas certian du cons ici
}

        /* (tl T) 
        o si T = (const A B) alors retourne B
        o si T = Symb alors retourne nil
        o si T = nil alors retourne nil
        Gestion erreur prise en compte
*/
/**
 * @param something shared_ptr pointant sur un arbre
 * @return shared_ptr pointant sur une copie du fils droit s'il existe, ou un arbre vide sinon
*/
std::shared_ptr<ArbreBinaire> tl(const std::shared_ptr<ArbreBinaire> &something) {
    if(something==nullptr){
        return nullptr;
    }
    if(something->getLeft()==nullptr && something->getRight()==nullptr){
        return nullptr;
    }
    return cons(something->getRight());
}

        //Noting
void nop(){
    std::cout<<"O-O"<<std::endl;
}



/**
 * Fonction permettant d'avaluer l'arbre en tant que int
 * @param arbre shared_ptr pointant sur l'arbre à évaluer
 * @return valeur de l'arbre
*/
int evalueAsInt(const std::shared_ptr<ArbreBinaire> arbre)  {// Voir dans le test des strings un exemple
    if(arbre==nullptr){
        return 0;
    }
    if (arbre->getRight() == nullptr) {
        return 1;
    } else {
        int hauteurDroit = evalueAsInt(arbre->getRight());
        return 1 + hauteurDroit;
    } 
}
/**
 * Fonction permettant d'avaluer l'arbre en tant que booléen
 * @param arbre shared_ptr pointant sur l'arbre à évaluer
 * @return true ou false
*/
bool evalueAsBool(const std::shared_ptr<ArbreBinaire> arbre){
    if(arbre==nullptr){
        return false;// Pas certain
    }
    else{
        return true; 
    }

}

/**
 * Fonction permettant d'avaluer l'arbre en tant que string
 * quand lors de la recherche récursive, on trouve un arbre feuille, alors on ajoute au string le caractere 
 * ASCII correspondant à sa hauteur. Quand on tombe sur une feuille, et donc sur un element qui ne sert pas à la représentation
 * du string, on ignore. Sinon, on parcours le fils gauche puis le fils droit de maniére à lire l'arbre de gauche à droite
 * sans oublier d'elements. 
 * 
 * @param arbre shared_ptr pointant sur l'arbre à évaluer
 * @return std::string string représenté par l'arbre  
*/
std::string evalueAsString( const std::shared_ptr<ArbreBinaire> arbre, int hauteur=0){
    std::string string= "";//la chaine de caracteres
    if(arbre==nullptr){
        return string;
    }
    else if(arbre->getLeft()==nullptr && arbre->getRight()==nullptr){
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

//En vrai tu voulais ca teDy ? 
void pp(const std::shared_ptr<ArbreBinaire> arbre,const char & type){
    switch(type){
        case 'i':
            evalueAsInt(arbre);
        case 'b':
            evalueAsBool(arbre);
        case 's':
        evalueAsString(arbre);
    }
}