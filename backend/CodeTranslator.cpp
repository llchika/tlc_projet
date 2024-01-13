
#include "CodeTranslator.h"
#include <iostream>
#include <string>
#include <sstream>

std::string CodeTranslator::translateToCpp(){
    //Code C++
    std::ostringstream cStream(); // Ici on peut que ecrire dedans.
    //Code intermediaire
    std::istringstream interStream(m_codeAdresse); //En gros un stream et on recupere avec >> des trucs dessus. On peut que recup
    //Stockage tmp
    std::string line="";
    //Boucle sur le stream. 
    while(std::getline(interStream,line)){
     //Faudrait que le code intermediaire ecrive déja bien le truc comme ca juste à modifier deux trois trucs ? car sinon c est chaint de differencier une variable, d'une fonction, compter les args,...
    }
}