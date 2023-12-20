#include <string>
#include <memory>
#include <iostream>
/**
 * Classe ArbreBinaire qui permet de representer les arbres du
 * langage while. Contient un fils gauche, un fils droit.
 * Les feuilles sont représentées par des nullptr pour le moment
 * Cette classe utilise les shared_ptr afin de permettre une gestion efficace de la
 * mémoire. Toute modification d'un arbre binaire entraine automatiquement
 * la création d'un nouvel arbre afin afin de rendre impossible la modification d'un arbre.
 *
 * On se base sur des copies en profondeur lors des modifications afin d'éviter la modification d'arbres
 * qui est proscrite par le langage: si A=B
 *                                   puis B=C
 *                                   alors A!=c
 * 
 * //////////////////////////////////RALAGE/////////////:
 * Afin d'avoir un truc qui marche, j'ai test pleinssssss de fois avec pleinsssss de modif pour tenter de faire
 * mieux que de la recopie. Ca a échoué, et chatgpt il m'a fait tourner ne bourique car il disait pas vraiment la même
 * chose que la doc j'ai l'impression ( et je suis crevé). Donc vous avez un putain d'arbre qui fonctionne probablement et qui est robuste
 * à la modif. J'ai un petit code main pour test la modif pour celui qui veut...
 */

class ArbreBinaire
{
private:
        // Initialement un arbre feuille
        std::shared_ptr<ArbreBinaire> m_left = nullptr;  // Fils gauche
        std::shared_ptr<ArbreBinaire> m_right = nullptr; // Fils droit

public:
        // Constructeur
        ArbreBinaire(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right = nullptr); //Arbre= recopie gauche + droite
        ArbreBinaire(); // Arbre feuille
        ArbreBinaire(const ArbreBinaire &autre); //operateur de copie 
        // Destructeur
        ~ArbreBinaire(){}; // automatique
        // Getter
        std::shared_ptr<ArbreBinaire> getLeft() const;  // On retourne le pointeur vers l'arbre gauche
        std::shared_ptr<ArbreBinaire> getRight() const; // On retourne le pointeur vers l'arbre droit
        // Setter                                                                                                        //Car sinon on va modifier des variables et ca devient le bordel
        // Donc obligé de dupliquer par moment désolé
        void setLeft(const std::shared_ptr<ArbreBinaire> &left);     //On modifie m_left: il delaisse l'ancien arbre qui va être supprimé, et onf ait une recopie
        void setRight(const std::shared_ptr<ArbreBinaire> &right); 
        // Operator=
        void operator=(ArbreBinaire &right); 
                                        
        int compterFeuilles() const ; //renvoie nbre de feuilles car flemme de faire un truc qui dessine un arbre 
        int hauteurArbre(const std::shared_ptr<ArbreBinaire>& arbre) const;
        // Evaluateurs ca c'est chiant ! et chepaquoimettreencore
        bool evalueBool();          // TODO
        int evalueInt();            // TODO
        std::string evalueString(); // TODO
};