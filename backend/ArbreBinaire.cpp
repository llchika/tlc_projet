#include "ArbreBinaire.h"



ArbreBinaire::ArbreBinaire(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right)
{
    m_left = (left != nullptr) ? std::make_shared<ArbreBinaire>(*left) : nullptr; 
    m_right = (right != nullptr) ? std::make_shared<ArbreBinaire>(*right) : nullptr; 
}
ArbreBinaire::ArbreBinaire()
{
//Arbre feuille
}
ArbreBinaire::ArbreBinaire(const ArbreBinaire &autre)
{
    m_left = (autre.getLeft() != nullptr) ? std::make_shared<ArbreBinaire>(*(autre.getLeft())) : nullptr; //profondeur recopie
    m_right = (autre.getRight() != nullptr) ? std::make_shared<ArbreBinaire>(*(autre.getRight())) : nullptr;//recopie profondeur
}
// Getters
std::shared_ptr<ArbreBinaire> ArbreBinaire::getLeft() const
{
    return m_left; 
}
std::shared_ptr<ArbreBinaire> ArbreBinaire::getRight() const
{
    return m_right; // 
}
void ArbreBinaire::setLeft(const std::shared_ptr<ArbreBinaire> &left)
{
    m_left = (left != nullptr) ? std::make_shared<ArbreBinaire>(*left) : nullptr; 
}

void ArbreBinaire::setRight(const std::shared_ptr<ArbreBinaire> &right)
{
    m_right = (right != nullptr) ? std::make_shared<ArbreBinaire>(*right) : nullptr;
}

void ArbreBinaire::operator=(ArbreBinaire &arbre)
{
    m_left = (arbre.m_left != nullptr) ? std::make_shared<ArbreBinaire>(*arbre.m_left) : nullptr; 
    m_right = (arbre.m_right != nullptr) ? std::make_shared<ArbreBinaire>(*arbre.m_right) : nullptr; 
}

int ArbreBinaire::compterFeuilles() const
{
    int feuillesGauche = (m_left != nullptr) ? m_left->compterFeuilles() : 1; 
    int feuillesDroit = (m_right != nullptr) ? m_right->compterFeuilles() : 1; 

    return feuillesGauche + feuillesDroit;
}
int ArbreBinaire::evalueInt() const {
    if (m_right == nullptr) {
        return 1;
    } else {
        int hauteurDroit = m_right->evalueInt();
        return 1 + hauteurDroit;
    }
   
 
}
