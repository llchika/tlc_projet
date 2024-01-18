#include "ArbreBinaire.h"
#include <iostream>
#ifndef CODETRANSLATOR_H
#define CODETRANSLATOR_H
class CodeTranslator{
public:
    /**
     * @param codeAdresse code 3 adresses stocké à traduire
    */
    CodeTranslator(std::string & codeAdresse)
    :m_codeAdresse(codeAdresse) {}

    /**
     * @return code C++
    */
    std::string translateToCpp();


private:
    std::string m_codeAdresse;
};

#endif 