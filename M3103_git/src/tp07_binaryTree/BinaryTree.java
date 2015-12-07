package tp07_binaryTree;

import tp03_piles.Pile;
import tp04_filesAttente.Fifo;

/*
 * BinaryTree.java
 */

/**
 * Classe générique pour la représentation et la manipulation d'un arbre binaire.
 * Implémentation purement dynamique adaptée aux traitements récursifs des arbres.
 * 
 * @param <E>
 * @author Erick Timmerman
 */
public class BinaryTree<E>
{
    // classe utilitaire interne pour la représentation d'un triplet:
    //  (valeur, arbre binaire, arbre binaire)
    private class Node<T>
    {

        T value;                    // valeur de la racine (de type générique T)
        BinaryTree<T> left;         // sous-arbre gauche, un arbre binaire
        BinaryTree<T> right;        // sous-arbre droit, un arbre binaire

        Node(T value)               // création d'un noeud simple (une feuille)
        {
            this.value = value;
            // Création des sous-arbres "gauche" et "droit" vides
            this.left = new BinaryTree<>();
            this.right = new BinaryTree<>();
        }

        Node(T value, BinaryTree<T> left, BinaryTree<T> right)
        {
            /* Création d'un noeud pour pouvoir créer un arbre dont on connait
             * la valeur à placer à la racine ainsi que les 2 sous-arbres.
             */
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }    // Fin de la classe interne "Node"

    private Node<E> root;               // attribut de la classe "BinaryTree""

    // Constructeurs

    /**
     * Création d'un arbre vide.
     */
    public BinaryTree()
    {
        root = null;    // pas de noeud, affectation inutile = valeur par défaut.
    }

    /**
     * Création d'un arbre "feuille" (arbre réduit à un seul "noeud").
     *
     * @param valeur
     */
    public BinaryTree(E valeur) 	// construction d'une feuille
    {
        root = new Node<>(valeur);     // un noeud "simple"
    }

    /**
     * Création d'un arbre binaire dont on connait la valeur à placer à la
     * racine ainsi que les 2 sous-arbres.
     *
     * @param valeur
     * @param gauche
     * @param droit
     */

    public BinaryTree(E valeur, BinaryTree<E> gauche, BinaryTree<E> droit)
    {
        root = new Node<>(valeur, gauche, droit);
    }

    // Méthodes utilitaires basiques.

    /**
     * détermine si l'arbre (this) est vide ou non.
     * @return true ssi l'arbre est vide.
     */
    public boolean estVide()
    {
        return root == null;
    }

    /**
     * Fournit la "racine" de l'arbre, l'arbre ne doit pas être vide.
     * @return la valeur à la racine de l'arbre.
     */
    public E racine()
    {
        return root.value;
    }

    /**
     * Fournit le sous arbre gauche de l'arbre, l'arbre ne doit pas être vide.
     * @return le sous arbre gauche de l'arbre.
     */
    public BinaryTree<E> gauche()
    {
        return root.left;
    }

    /**
     * Fournit le sous arbre droit de l'arbre, l'arbre ne doit pas être vide.
     * @return le sous arbre droit de l'arbre.
     */
    public BinaryTree<E> droit()
    {
        return root.right;
    }

    /*
     *   Modifie la valeur à la racine de l'arbre, l'arbre ne doit pas être vide.
     */
    private void setRacine(E val)
    {
        root.value = val;
    }

    /*
     *   Modifie l'arbre de façon qu'il "devienne" celui fourni en argument.
     */
    private void setTree(BinaryTree<E> tree)
    {
        root = tree.root;
    }

    // On peut dès à présent "oublier" la classe interne "Node".

     
    /**
     * Constructeur, construction à partir d'un "vecteur" (array) d'éléments.
     * 
     * Construction d'un arbre "parfaitement balancé" ayant pour valeurs
     * les éléments du vecteur; la racine est choisie comme étant le 1er élément.
     * 
     * Ceci permettra de réaliser des tests avec des arbres non "triviaux".

     * @param elts
     */
    public BinaryTree(E[] elts)
    {
        setTree(makePBTWith(elts, 0, elts.length - 1));
    }

    private BinaryTree<E> makePBTWith(E[] elts, int indexDebut, int indexFin)
    {
        if (indexDebut > indexFin)    // portion de vecteur vide
            return new BinaryTree<>();

        int nb = (indexFin + indexDebut + 1) / 2;

        // Construction des sous-arbres.
        BinaryTree<E> g = makePBTWith(elts, indexDebut + 1, nb);
        BinaryTree<E> d = makePBTWith(elts, nb + 1, indexFin);

        return new BinaryTree<>(elts[indexDebut], g, d);
    }           
    
    /**
     * @return la suite "préfixe" des valeurs de l'arbre.
     */
    public String prefixe()        // préfixe ou "RGD"
    {
        if(this.estVide()) return "";
        return this.racine()+this.gauche().prefixe()+this.droit().prefixe();
    }

    /**
     * @return la suite "infixe" des valeurs de l'arbre.
     */

    public String infixe()         // infixe ou "GRD"
    {
    	String grd ="";
        if(!this.estVide())
        {
        	grd += this.gauche().infixe();
        	grd += this.racine();
        	grd += this.droit().infixe();
        }
        return grd;
    }

    /**
     * @return la suite "suffixe" des valeurs de l'arbre.
     */
    public String suffixe()        // suffixe ou "postfixe" ou "GDR"
    {
    	String gdr ="";
        if(!this.estVide())
        {
        	gdr += this.gauche().suffixe();
        	gdr += this.droit().suffixe();
        	gdr += this.racine();
        }
        return gdr;
    }

    /**
     * Fournit l'expression de type "fonctionnelle" représentant l'arbre.
     * @return a string representation of the tree.
     */
    @Override
    public String toString() // retourne l'expression de type "fonctionnelle"
    {
    	if(this.estVide()) return "";
    	if(this.estUneFeuille()) return racine().toString();
    	return racine()+"("+this.gauche()+","+this.droit()+")"; // implicitement on utilise toString
    }

    /**
     * Détermine si l'arbre est réduit à une feuille (un seul noeud).
     * L'arbre ne doit pas être vide.
     * @return true ssi l'arbre est une feuille.
     */
    public boolean estUneFeuille()
    {
        return (gauche().estVide() && droit().estVide());
    }

    /**
     * Fournit le nombre de noeuds de l'arbre.
     * @return le nombre de noeuds de l'arbre.
     */
    public int nbNoeuds()
    {
        if(estVide()) return 0;
        return 1+droit().nbNoeuds()+gauche().nbNoeuds();
    }

    /**
     * Détermine la "hauteur" (nombre de noeuds sur la branche la plus longue)
     * de l'arbre.
     * @return la hauteur de l'arbre.
     */
    public int hauteur()   // nombre de noeuds sur la branche la plus longue
    {
        if(estVide()) return 0;
        return 1+Math.max(gauche().hauteur(), droit().hauteur());
     }

    /**
     * Détermine si l'arbre est "binaire pur", ie: chaque noeud est soit une
     * feuille, soit aucun de ses sous arbres n'est vide.
     * @return true ssi l'arbre est binaire pur.
     */
    public boolean estBinairePur()
    {
       if(estVide()) return true;
       if(estUneFeuille()) return true;
       if(this.gauche().estVide() || this.droit().estVide()) return false;
       return gauche().estBinairePur() && droit().estBinairePur();
    }

    
    public String prefixeIteratif() // RGD
    {
    	Pile<BinaryTree<E>> pile = new Pile<>();
    	BinaryTree<E> arbreLocal = this;
    	String str="";
    	while(!arbreLocal.estVide()||!pile.estVide())
    	{
    		if(arbreLocal.estVide()) arbreLocal = pile.depiler();
    		else
    		{
    			str+=arbreLocal.racine();
    			if(!arbreLocal.droit().estVide()) pile.empiler(arbreLocal.droit());
    			arbreLocal=arbreLocal.gauche();
    		} 		
    	}
    	return str;    
    }
        
    public String infixeIteratif() // GRD
    {
        Pile<BinaryTree<E>> pile = new Pile<>();
        BinaryTree<E> arbreLocal = this;
        String str="";
        while(!arbreLocal.estVide()||!pile.estVide())
        {
        	if(arbreLocal.estVide())
        	{
        		arbreLocal=pile.depiler();
        		str+=arbreLocal.racine();
        		arbreLocal=arbreLocal.droit();
        	}
        	else
        	{
        		pile.empiler(arbreLocal);
        		arbreLocal=arbreLocal.gauche();
        	}
        	
        }
        return str;
    }
        
        

    public String parNiveau() throws InterruptedException	// = stratégie en largeur
    {
        Fifo<BinaryTree<E>> file = new Fifo<>();
        String str="";
        BinaryTree<E> arbreLocal = this;
        if(arbreLocal.estVide()) return "Pas d'arbre";
						System.out.println("ArbreLocal INITIAL = "+arbreLocal);
		

        while(arbreLocal!=null||!file.estVide())
        {
        				System.out.println("Début d'une boucle - ArbreLocal = \n\t["+arbreLocal+"]");


        	if(!arbreLocal.gauche().estVide())
        	{
        				System.out.println("G pas vide - ajout");
        		file.ajouter(arbreLocal.gauche());
        				System.out.println("File d'attente : "+file);

        	}
        	if(!arbreLocal.droit().estVide())
        	{
        				System.out.println("D pas vide - ajout");
        		file.ajouter(arbreLocal.droit());
        				System.out.println("File d'attente : "+file);

        	}
        	str+=arbreLocal.racine();        	
        				System.out.println("Fin d'une boucle - ajout ("+str+")");

    		Thread.sleep(100);
        	arbreLocal=file.suppression();
    					System.out.println("FIN DE BOUCLE : File d'attente : "+file);
        				System.out.println("FIN DE BOUCLE : ArbreLocal = \t["+arbreLocal+"]");
        				System.out.println("File vide ?"+file.estVide());
        				System.out.println("Arbre vide ?"+(arbreLocal==null));



        }
    	return str;
    }
    
    public String suffixeIteratif()
    {
       // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

}




