#include "ArbreBinaire.h"





//Pas encore modifié 





ArbreBinaire::ArbreBinaire(ArbreBinaire *left, ArbreBinaire *right)
{
    *m_left=*left; //Copy et donc pas de pb 
    *m_right=*right; //Copy et donc pas de pb 
}

// Getter
ArbreBinaire * ArbreBinaire::getLeft()
{
         return m_left;//si nullptr bah on veut le savoir
}
ArbreBinaire * ArbreBinaire::getRight()
{
        return m_right;   //si nullptr bah on veut le savoir
}
void ArbreBinaire::setLeft(ArbreBinaire *left)
{
    *m_left = *left; //Copy et donc pas de pb 
}
void ArbreBinaire::setRight(ArbreBinaire *right)
{
    *m_right = *right; //Copy et donc pas de pb 
}

void ArbreBinaire::operator=(ArbreBinaire *arbre){
    if(this!=arbre){
        if(m_left!=nullptr){
            delete m_left;
        }
        if(m_right!=nullptr){
            delete m_right;
        }
    *m_left=*(arbre->getLeft());
    *m_right=*(arbre->getRight());
    *m_left =  new ArbreBinaire(*(arbre->getLeft()));
    m_right = (arbre->getRight()) ? new ArbreBinaire(*(arbre->getRight())) : nullptr;
    }
}