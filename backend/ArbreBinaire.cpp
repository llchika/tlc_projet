#include "ArbreBinaire.h"

// Pas encore modifié

ArbreBinaire::ArbreBinaire(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right)
{
    m_left = (left != nullptr) ? std::make_shared<ArbreBinaire>(*left) : nullptr; // reecopie en profondeur
    m_right = (right != nullptr) ? std::make_shared<ArbreBinaire>(*right) : nullptr; //idem ! 
}
ArbreBinaire::ArbreBinaire()
{
    //  arbre feuille
}
ArbreBinaire::ArbreBinaire(const ArbreBinaire &autre)
{
    // Parcours et copie recursive des arbres
    m_left = (autre.getLeft() != nullptr) ? std::make_shared<ArbreBinaire>(*(autre.getLeft())) : nullptr; //profondeur recopie
    m_right = (autre.getRight() != nullptr) ? std::make_shared<ArbreBinaire>(*(autre.getRight())) : nullptr;//recopie profondeur
}
// Getter
std::shared_ptr<ArbreBinaire> ArbreBinaire::getLeft() const
{
    return m_left; // si nullptr bah on veut le savoir
}
std::shared_ptr<ArbreBinaire> ArbreBinaire::getRight() const
{
    return m_right; // si nullptr bah on veut le savoir
}
void ArbreBinaire::setLeft(const std::shared_ptr<ArbreBinaire> &left)
{
    m_left = (left != nullptr) ? std::make_shared<ArbreBinaire>(*left) : nullptr; //profondeur
}

void ArbreBinaire::setRight(const std::shared_ptr<ArbreBinaire> &right)
{
    m_right = (right != nullptr) ? std::make_shared<ArbreBinaire>(*right) : nullptr; //profondeur 
}

void ArbreBinaire::operator=(ArbreBinaire &arbre)
{
    m_left = (arbre.m_left != nullptr) ? std::make_shared<ArbreBinaire>(*arbre.m_left) : nullptr; //profondeur
    m_right = (arbre.m_right != nullptr) ? std::make_shared<ArbreBinaire>(*arbre.m_right) : nullptr; //profondeur
}

int ArbreBinaire::compterFeuilles() const
{
    int feuillesGauche = (m_left != nullptr) ? m_left->compterFeuilles() : 1;  // Compter m_left comme feuille si c'est nullptr
    int feuillesDroit = (m_right != nullptr) ? m_right->compterFeuilles() : 1; // Compter m_right comme feuille si c'est nullptr

    return feuillesGauche + feuillesDroit;
}
int ArbreBinaire::hauteurArbre(const std::shared_ptr<ArbreBinaire>& arbre) const {
    if (arbre == nullptr) {
        // Si l'arbre est vide, sa hauteur est 0
        return 0;
    } else {
        // La hauteur de l'arbre est la plus grande hauteur entre les sous-arbres gauche et droit, plus 1 pour le nœud actuel.
        int hauteurGauche = hauteurArbre(arbre->getLeft());
        int hauteurDroit = hauteurArbre(arbre->getRight());
        return 1 + std::max(hauteurGauche, hauteurDroit);
    }
}
