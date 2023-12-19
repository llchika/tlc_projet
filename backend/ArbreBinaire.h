#include <string>
#include <memory>
/**
 * Classe ArbreBinaire qui permet de representer les arbres du 
 * langage while. Contient un fils gauche, un fils droit.
 * Les feuilles sont représentées par des nullptr pour le moment
 * Cette classe utilise les shared_ptr afin de permettre une gestion efficace de la 
 * mémoire. Toute modification d'un arbre binaire entraine automatiquement
 * la création d'un nouvel arbre afin afin de rendre impossible la modification d'un arbre.
 * 
 * std::shared_ptr<ArbreBinaire> A = std::make_shared<ArbreBinaire>();// construit un arbre binaire et il est referencé par un shared_ptr A
 * Quand std::shared_ptr<ArbreBinaire> A est passé sans references dans une fonction, ca crée un nouveau ptr
 * qui pointe sur le même objet: ATTENTION DONC.
 * 
 * 
 * Tout repose ici sur 
 * std::shared_ptr<ArbreBinaire> A = std::make_shared<ArbreBinaire>(); -1 au compteur de l'arbre actuellement pointé,  Création d'un nouvel arbre, on pointe sur lui.
 * std::shared_ptr<ArbreBinaire> B = A;/:On pointe sur le même arbre jusqu'à la modification de ce dernier
*/
class ArbreBinaire{
    private:
            //Initialement un arbre feuille
            std::shared_ptr<ArbreBinaire> m_left=nullptr;     //Fils gauche      
            std::shared_ptr<ArbreBinaire> m_right=nullptr;    //Fils droit 

    public:
        //Constructeur
        ArbreBinaire(std::shared_ptr<ArbreBinaire> & left, std::shared_ptr<ArbreBinaire> & right); //Ici notre nouvel arbre il va vraiment pointer vers deux arbres (ie peut y avoir plusieurs pointeurs sur le même arbre)
        ArbreBinaire();//Arbre feuille  
        //Destructeur
        ~ArbreBinaire(){};  //automatique
        //Getter
        std::shared_ptr<ArbreBinaire> getLeft(); //On retourne le pointeur vers l'arbre gauche
        std::shared_ptr<ArbreBinaire> getRight();//On retourne le pointeur vers l'arbre droit
        //Setter                                                                                                        //Car sinon on va modifier des variables et ca devient le bordel
                                                                                                                        //Donc obligé de dupliquer par moment désolé
        void setLeft(std::shared_ptr<ArbreBinaire> & left);//Iici on va créer un nouveau shared_ptr pour le left, donc on abandonne l'ancien left, qui disparait si perosnne le pointe
        void setRight(std::shared_ptr<ArbreBinaire>& right);//ici on va créer un nouveau shared_ptr pour le right, donc on abandonne l'ancien right, qui disparait si perosnne le pointe
        //Operator=
        void operator=(std::shared_ptr<ArbreBinaire> right);//Alors je suis pas sur mais pas besoin de faire une copie profonde ici             
                                                                //En fait les deux vont être égaux jusqu'à ce que l'un deux change (donc pas de duplication car peut être qu'ils vont pas changer et ca serait perdre de la performance),     
                                                                //et dans ce cas si l'un d'eux doit changer bah il va devenir autre chose et les 2 seront bien distrincts
        //Evaluateurs ca c'est chiant !
        bool evalueBool();//TODO
        int evalueInt();//TODO
        std::string evalueString();//TODO
};