
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
      //Il existe des methodes comme .find pour traiter en fonction de ce qu'il y a
    }
}