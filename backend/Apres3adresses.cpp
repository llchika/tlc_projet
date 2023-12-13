struct TreeNode {
    TreeNode* left;
    TreeNode* right;

    TreeNode() : left(nullptr), right(nullptr) {}
};
//Il faut s'imaginer que dans le code 3 adresses, on va souvent travailler
//qu'avec 2 arbres, car par exemple pour list: 
// bah on va devoir créer un nouvel arbre qui contient le premier arbre et un deuxieme arbre qui contient toute la suite



//Code 3 adresses:   r0= cons call T
//                   r0= cons call T1 T2 avce T1 gauche et T2 droite
// ca suffit d'avoir left et right car bah ils auront déja été crée en fait ! 
//Ya pas de value je crois ? 
TreeNode* cons(TreeNode* left, TreeNode* right) {
    TreeNode* arbre=new TreeNode();
    (*arbre).left=left;//On colle l'arbre gauche
    (*arbre).right=right;// On colle l'arbre de droite
    return arbre;
}

// Fonction pour construire une liste à partir de 2 arbres (ou moins..) 
TreeNode* list(TreeNode* left,TreeNode* right) {
    return cons(left,right);
}

