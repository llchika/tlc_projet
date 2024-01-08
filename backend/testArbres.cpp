#include "ArbreBinaire.h"
#include "RunTime.cpp"
#include <iostream>
#include <cassert>
/*
Test de création(testCreation):
    Création d'un arbre ayant pour fils gauche arbre1 et comme fils droit arbre2
    Création d'un arbre-feuille
Test Modif (testModif) :
    Création de deux arbres feuilles.
    Initialisation d'un arbre et d'un arbre de test créé à partir du premier.
    Évaluations et comparaisons en termes d'adresse, de copie, et de modification de l'arbre en verifiant la non-modification de l'arbre test.
    Utilisation de l'opérateur d'égalité (operator=).
Test d'Égalité (testEgalite) :  
    Comparaison entre deux arbres pour vérifier l'égalité en termes de nombre de feuilles, valeurs booléennes, valeurs entières
    et valeurs de chaînes. S'arrête si les deux arbres ne sont pas identiques. 
Test de EvaluateAsString(testString):
    Creation d'un arbre de 97 etages et verification de l'obtention d'un a
*/


//Fonction test dans un cas spécifique. Fichier de test initial pour limplémentation d'ArbreBinaire
int testModif() {
    std::cout << "      Test modifications "<< std::endl; 
    //Création de deux arbres feuilles;
    std::cout << "Creation d'un arbre feuille. Creation d'une copie de cet arbre"<< std::endl; 
    std::shared_ptr<ArbreBinaire> feuille = std::make_shared<ArbreBinaire>();  //arbre feuille
    std::shared_ptr<ArbreBinaire> feuille2 = std::make_shared<ArbreBinaire>();  //arbre feuille
    // Initialisation d'un arbre, et d'un arbre test crée à partir de l'arbre.
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>(nullptr, feuille); //ie 3 feuilles 
    std::shared_ptr<ArbreBinaire> arbreTest = std::make_shared<ArbreBinaire>(arbre); 
    //Affichage des adresses
    std::cout << "Adresse arbre: " << arbre.get() << std::endl; 
    std::cout << "Adresse arbre Test: " << arbreTest.get() << std::endl;
    //Test d'evaluation du int. Vérification de l'égalité des deux arbres 
    int intvalue1 = evalueAsInt(arbre);
    int intvalue2 = evalueAsInt(arbreTest);
    if(intvalue1==intvalue2 && arbre.get()!=arbreTest.get() ){//Cas de succés: arbres identiques et adresses différentes.
        std::cout <<"Copie d'arbre reussie. Valeur des arbres ="<<intvalue1<<""<<std::endl;
    }else{//Cas d'échec: arbres différents
        std::cout <<"Echec test copie"<<std::endl;
        return 0;
    }
    //Test de la modification d'arbre, vérification que l'arbre test n'est pas modifié.
    //Ie vérification de la copie profonde de l'arbre.
    arbre=nullptr;
    intvalue1 = evalueAsInt(arbre);
    intvalue2 = evalueAsInt(arbreTest);
    if(intvalue1==intvalue2){//Cas d'echec: arbres toujours identiques
        std::cout <<"Echec de modification. Valeur="<<intvalue1<<std::endl;
        return 0;
    }else{//Cas de succés: arbres différents.
        std::cout <<"Modification de l'arbre reussie. Desormais arbre="<<intvalue1<<" et arbrecopie="<<intvalue2<<std::endl;
    }
    //Test de l'opérateur d'égalite = operateur de copie en fait
    arbre= std::make_shared<ArbreBinaire>();// Il faut ici que arbre!=nullptr, car il doit pointer sur un objet existant.
    *arbre=*arbreTest;//appel de operator=
    intvalue1 = evalueAsInt(arbre);
    intvalue2 = evalueAsInt(arbreTest);
    if(intvalue1==intvalue2 && arbre.get()!=arbreTest.get() ){//Cas de succés: arbres identiques et adresses différentes.
        std::cout <<"Egalite reussie. Valeur des arbres ="<<intvalue1<<std::endl;
    }else{//Cas d'échec: arbres différents
        std::cout <<"Echec opérateur de copie"<<std::endl;
        return 0;
    }
    //FIN
    std::cout<<"Test naif passe\n"<<std::endl;
    return 1;
}
int testEgalite(const std::shared_ptr<ArbreBinaire>& arbre1 ,const std::shared_ptr<ArbreBinaire> & arbre2){
    std::cout << "      Test egalite "<< std::endl; 
    // Affichage des similarités entre les deux arbres: sont-ils identiques ? (naivement)
    bool identique=false;
    if(arbre1->compterFeuilles()==arbre2->compterFeuilles()){ //TODO afficher info si pas égaux
        std::cout<<"nombre de feuilles identiques: "<<arbre1->compterFeuilles() <<std::endl;
        if(evalueAsBool(arbre1)==evalueAsBool(arbre2)){
            std::cout<<"Booleen identique: "<<evalueAsBool(arbre1)<<std::endl;
            if(evalueAsInt(arbre1)==evalueAsInt(arbre2)){
                std::cout<<"Integer identique: "<<evalueAsInt(arbre1)<<std::endl;
                if(evalueAsString(arbre1)==evalueAsString(arbre2)){
                    std::cout<<"String identique"<<std::endl; //flemme d'afficher le string hein ! 
                    identique=true;
                }
            }
        }
    }
    if(identique){
        std::cout<<"Les deux arbres sont identiques\n"<<std::endl;
        //FIN
         std::cout<<"Test egalite passe\n"<<std::endl;
        return 1;
    }
    else{
        std::cout<<"pb, les deux arbres ne sont pas identiques\n"<<std::endl;
        return 0;
    }

}

int testCreation(const std::shared_ptr<ArbreBinaire>& arbre1 ,const std::shared_ptr<ArbreBinaire> & arbre2){
    std::cout << "\n      Test Creation "<< std::endl; 
    //Création d'un arbre ayant pour fils gauche l'arbre 1 et comme fils droit l'arbre 2
    std::shared_ptr<ArbreBinaire> newArbre = std::make_shared<ArbreBinaire>(arbre1,arbre2);  
    //Vérification du nombre de feuille
    if(!(arbre1->compterFeuilles()+arbre2->compterFeuilles()==newArbre->compterFeuilles())){
        std::cout<<"Probleme, la creation de l'arbre a echoue"<<std::endl;
        return 0;
    }
    else{
        std::cout<<"La creation du nouvel arbre est un succes"<<std::endl;
    }


    //Test de création de l'arbre feuille
    std::shared_ptr<ArbreBinaire> feuille = std::make_shared<ArbreBinaire>();  
    if(feuille->compterFeuilles()==2){
        std::cout<<"La creation d'un arbre feuille est un succes"<<std::endl;
    }else{
        return 0;
    }


    //Test de création d'un arbre à partir d'arbre1
    std::shared_ptr<ArbreBinaire> copie1 = std::make_shared<ArbreBinaire>(arbre1);
    if(arbre1->compterFeuilles()==copie1->compterFeuilles()){
        std::cout<<"La copie d'arbre1 est un succes"<<std::endl;
    }else{std::cout<<"La copie d'arbre1 est un echec"<<std::endl;return 0;}  

    //FIN
    std::cout<<"Test Creation\n"<<std::endl;
    return 1;
}


//TODO
//Fonction test de l'évaluation d'un string
int testString() {
    std::cout << "      Test StringEvaluation "<< std::endl; 
    // Création d'un arbre binaire simple pour le test
    std::shared_ptr<ArbreBinaire> feuille = std::make_shared<ArbreBinaire>();
    std::shared_ptr<ArbreBinaire> arbre = std::make_shared<ArbreBinaire>();

    // Ajout de 97 niveaux de fils pour obtenir une hauteur de 97 qui correspond à 'a'
     std::cout << "Creation d'un arbre ayant uniquement des fils gauche sur 97 etages "<< std::endl; 
    for (int i = 0; i < 97; ++i) {
        arbre = std::make_shared<ArbreBinaire>(arbre, nullptr);
    }

    // Appel de la fonction evalueAsString
    std::string representation = " string:"+evalueAsString(arbre);
    if(evalueAsString(arbre)=="a"){
        std::cout << "Nous obtenons le caractere a"<< std::endl; 
    }else{
        std::cout << "Echec, nous obtenons "<<representation<< std::endl; 
        return 0;
    }
    //FIN
    std::cout<<"Test d evaluation d un string passe\n"<<std::endl;
    return 1;
}
//TODO
int testRunTime(){
    
}
int main(){
    //Test creation d'arbres: creation de deux arbres, fusion de ces deux arbres en un nouvel arbre.
    std::shared_ptr<ArbreBinaire> arbre1 = std::make_shared<ArbreBinaire>();  
    std::shared_ptr<ArbreBinaire> arbre2 = std::make_shared<ArbreBinaire>();
    testCreation(arbre1,arbre2);
    //Test de base: vérification basique de la création, de la copie et de l'impacte de la modification d'un arbre. Voir code pour voir ce qui est teste. 
    //Ce code a pour but de tester des actions basiques. (il a permis de résoudre un certain nombre de problèmes !) 
    testModif();
    //Test de l'égalité entre deux arbres identiques: même nombre de feuille, int, string et bool.
    std::shared_ptr<ArbreBinaire> element = std::make_shared<ArbreBinaire>();  
    std::shared_ptr<ArbreBinaire> miroir = std::make_shared<ArbreBinaire>();
    testEgalite(element, miroir);
    //Test d'evaluation d'un arbre vu comme un integer
    //testInteger();
    //Test d'evaluation d'un arbre vu comme un booleen
   // testBool();
    //Test d'evaluation d'un arbre vu comme un integer
    testString();

    //Test de la runtime
    testRunTime();
    return 1;
}

