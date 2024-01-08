#include "ArbreBinaire.h"
#include <iostream>
#ifndef CODETRANSLATOR_H
#define CODETRANSLATOR_H
class CodeTranslator{
public:
    //Constructeur
    CodeTranslator(){};

    /**
     * @param codeAdresse le contenu du fichier contenant le code 3 adresses
    */
    std::string translateToCpp(const std::string & codeAdresse);


private:



};

#endif