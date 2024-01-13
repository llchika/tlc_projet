# Explication du backend
Ce répertoire contient l'ensemble du backend de notre compilator. La runtime ainsi que le traducteur de code 3 adresses en code C++

## Explication de la runTime
La runtime est composée de 2 éléments : une classe arbreBinaire consultable dans les fichiers ArbreBinaire.h et ArbreBinaire.cpp ; ainsi qu'une liste de fonctions permettant de traduire le concept "while" en C++.

### ArbreBinaire
ArbreBinaire est la représentation d'un arbre binaire dans le langage C++. Son utilisation est étroitement liée à celle des shared_ptr, qui sont des pointeurs permettant de faciliter la gestion de la mémoire. Lorsqu'un arbre binaire est créé à l'aide d'un shared_ptr, un compteur permet de connaître combien de pointeurs pointent sur l'arbre. Quand l'arbre n'est plus référencé, il est alors automatiquement détruit. 

Deux paramétres privés:
    std::shared_ptr<ArbreBinaire> left qui représente le fils gauche
    std::shared_ptr<ArbreBinaire> right qui représente le fils droit
Les feuilles sont représentées par des nullptr. Un arbre nul sera représenté par un nullptr, et ne nécessitera pas l'utilisation de cette classe.


Cette classe a été conçue pour être la plus résistante possible. Ainsi, lorsqu'un arbre est créé, il est impossible de le modifier. 
Le seul moyen pour y parvenir est de créer un nouvel arbre. Nous travaillons uniquement avec des shared_ptr, c'est-à-dire que les méthodes et fonctions de la runtime en prennent en paramètre.

Méthodes disponibles:
    - ArbreBinaire(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right) qui permet de créer un arbre ayant pour fils gauche left et comme fils droit right qui sont tout deux des shared_ptr<ArbreBinaire>. Ce constructeur utilise une copie des deux paramétres afin d'éviter que la modification de left ou right puisse nuire à cet arbre.

    - ArbreBinaire() qui permet de créer un arbre feuille.

    - ArbreBinaire(const std::shared_ptr<ArbreBinaire> &autre) operateur de copie. L'arbre crée est une copie en profondeur de l'arbre autre. 

    - void operator=(const ArbreBinaire &right)  même principe, copie en profondeur.

    - ~ArbreBinaire() destructeur automatique.

    - std::shared_ptr<ArbreBinaire> getLeft() getter du fils gauche

    - std::shared_ptr<ArbreBinaire> getRight() getter du fils droit

    -int compterFeuilles() retourne le nombre de feuilles d'un arbre. Attention, l'utilisation de cette méthode est risquée. L'appliquer dans un arbre, sur une feuille ne retourne rien.

### fonctions de la runtime
les fonctions de la runtime sont toutes basée sur la classe décrire précedemment.

- std::shared_ptr<ArbreBinaire> symb() retourne un shared_ptr sur un arbre feuille

- std::shared_ptr<ArbreBinaire> cons() retourne un nullptr

- std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &left, const std::shared_ptr<ArbreBinaire> &right) retourne un arbre ayant pour fils gauche left et pour fils droit right (copie profonde)

- std::shared_ptr<ArbreBinaire> cons(const std::shared_ptr<ArbreBinaire> &arbre) retourne une copie de arbre

- std::shared_ptr<ArbreBinaire> list() retourne un nullptr

- std::shared_ptr<ArbreBinaire> list(const std::shared_ptr<ArbreBinaire> &element) retourne un arbre ayant pour fils gauche element et comme fils droit nullptr

- std::shared_ptr<ArbreBinaire> hd(const std::shared_ptr<ArbreBinaire> &something) retourne si possible une copie du fils gauche, sinon un nullptr

- std::shared_ptr<ArbreBinaire> tl(const std::shared_ptr<ArbreBinaire> &something) retourne si possible une copie du fils droit, sinon un nullptr

- void nop() nothing 

- int evalueAsInt(const std::shared_ptr<ArbreBinaire> arbre) retourne l'évaluation d'un arbre vu comme un integer. pour cela on regarde la hauteur de la feuille la plus à droite de l'arbre.

- bool evalueAsBool(const std::shared_ptr<ArbreBinaire> arbre) retourne l'évaluation d'un arbre vu comme un booleen. true si l'arbre n'est pas null, false sinon.

- std::string evalueAsString( const std::shared_ptr<ArbreBinaire> arbre, int hauteur=0) Retourne l'évaluation d'un arbre vu comme un std::string. Pour cela, on examine les différentes branches de l'arbre. Quand on arrive sur un sous-arbre qui possède comme fils gauche et droit un nullptr, alors on retourne le caractère associé au code ASCII, formant ainsi le string final.


## Couverture de test
Une couverture de test naive a été établie afin de verifier le bon fonctionnement des diverses fonctionalitées du backend. La couverture va de la création d'un arbre binaire, à son evalution, et le test de la runtime.

- Test de création(testCreation):
    Création d'un arbre ayant pour fils gauche arbre1 et comme fils droit arbre2
    Création d'un arbre-feuille
- Test Modif (testModif) :
    Création de deux arbres feuilles.
    Initialisation d'un arbre et d'un arbre de test créé à partir du premier.
    Évaluations et comparaisons en termes d'adresse, de copie, et de modification de l'arbre en verifiant la non-modification de l'arbre test.
    Utilisation de l'opérateur d'égalité (operator=).
- Test d'Égalité (testEgalite) :  
    Comparaison entre deux arbres pour vérifier l'égalité en termes de nombre de feuilles, valeurs booléennes, valeurs entières
    et valeurs de chaînes. S'arrête si les deux arbres ne sont pas identiques. 
- Test de EvaluateAsString(testString):
    Creation d'un arbre de 97 etages et verification de l'obtention d'un a
- Test d'évaluation d'un arbre comme un booleen (testBool)
    Test du true ou false
- Test d'évaluation d'un arbre comme un integer(testInteger)
    Verification qu'un arbre null correspond à 0
    Verification que hauteur de l'arbre = le bon integer
- Test de la runtime() testRunTime
    Verification dans l'ordre de la spécification de l'ensemble des fonctions disponibles.