#include <string>
#include <memory>
#include <iostream>
/**
 * Classe ArbreBinaire qui permet de representer les arbres du
 * langage while. Contient un fils gauche, un fils droit.
 * Les feuilles sont représentées par des nullptr
 * Cette classe utilise les shared_ptr afin de permettre une gestion efficace de la
 * mémoire. Toute modification d'un arbre binaire entraine automatiquement
 * la création d'un nouvel arbre afin afin de rendre impossible sa modification.
 *
 * On se base sur des copies en profondeur lors des modifications afin d'éviter la modification d'arbres
 * qui est proscrite par le langage: si A=B
 *                                   puis B=C
 *                                   alors A!=C
 */

class ArbreBinaire
{
private:
        // Initialement un arbre feuille
        std::shared_ptr<ArbreBinaire> m_left = nullptr;  // Fils gauche
        std::shared_ptr<ArbreBinaire> m_right = nullptr; // Fils droit

public:
        // Constructeurs
        /**
         * @param left: fils gauche
         * @param right: fils droit
        */
        ArbreBinaire(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right = nullptr);

        ArbreBinaire(); // Arbre feuille

        /**
         * @param autre: arbre à copier. Copie pronfonde
        */
        ArbreBinaire(const ArbreBinaire &autre);

        // Destructeur
        ~ArbreBinaire(){}; // automatique

        // Getters
        std::shared_ptr<ArbreBinaire> getLeft() const;  
        std::shared_ptr<ArbreBinaire> getRight() const; 

        // Setter                                   Copie profonde                                                                     
        void setLeft(const std::shared_ptr<ArbreBinaire> &left);     
        void setRight(const std::shared_ptr<ArbreBinaire> &right); 

        // Operator=        Copie profonde
        void operator=(ArbreBinaire &right); 

        /**
         * @return le nombre de feuilles dans l'arbre
        */          
        int compterFeuilles() const ; 



        // Evaluation d'un arbre
        /**
         * @return: true ou false
        */
        bool evalueBool();          // TODO

        /**
         * @return: valeur de l'arbre
        */
        int evalueInt()const;            

        /**
         * @return: chaine de caractére 
        */
        std::string evalueString(); // TODO
};