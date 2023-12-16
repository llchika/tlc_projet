#include "ArbreBinaire.h"

ArbreBinaire::ArbreBinaire(ArbreBinaire *left, ArbreBinaire *right)
{
    m_left=left;
    m_right=right;
}

// Getter
ArbreBinaire * ArbreBinaire::getLeft()
{
    if(m_left!=nullptr){
         return m_left;
    }
   
}
ArbreBinaire * ArbreBinaire::getRight()
{
    if(m_right!=nullptr){
        return m_right;
    }
    
}
void ArbreBinaire::setLeft(ArbreBinaire *left)
{
    m_left = left;
}
void ArbreBinaire::setRight(ArbreBinaire *right)
{
    m_right = right;
}