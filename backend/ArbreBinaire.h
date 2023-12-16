#include <string>

class ArbreBinaire{
    private:
            ArbreBinaire* m_left;     //Fils gauche de l'arbre
            ArbreBinaire* m_right;    //Fils droit de l'arbre

    public:
        //Constructeur, Destructeur
        ArbreBinaire(ArbreBinaire * left, ArbreBinaire * right=0);
        ~ArbreBinaire(){if(m_left){delete m_left;}if(m_right){delete m_right;}} //Destruction des enfants si nécessaire.
        //Getter
        ArbreBinaire * getLeft();
        ArbreBinaire * getRight();
        //Setter
        void setLeft(ArbreBinaire* left);
        void setRight(ArbreBinaire* right);
        //Evaluateurs
        bool evalueBool();//TODO
        int evalueInt();//TODO
        std::string evalueString()//TODO
};