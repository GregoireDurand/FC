
package tp08_searchTree;

/**
 * Classe utilitaire pour la repr�sentation et la manipulation d'un "arbre de recherche"
 * (ou "arbre binaire ordonn�").
 * Impl�mentation purement dynamique adapt�e aux traitements r�cursifs.
 * L'�quilibrage des arbres n'est g�n�ralement pas pris en compte ici.
 * 
 * @author Erick Timmerman
 * @param <E> type des �l�ments de l'arbre
 */
public class SearchTree<E extends Comparable<E>>
{
    private class Node<T extends Comparable<T>>
    {
        T value;
        SearchTree<T> left;
        SearchTree<T> right;

        Node(T value) 	// cr�ation d'un noeud isol�.
        {
            this.value = value;
            this.left = new SearchTree<>();
            this.right = new SearchTree<>();
        }

        Node(T value, SearchTree<T> left, SearchTree<T> right)
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }  // fin de la classe interne "Node"
    
    private Node<E> root;                 // attribut

    public SearchTree()                   // construction d'un arbre vide
    {
        root = null;
    }

    public SearchTree(E valeur)          // construction d'un arbre feuille
    {
        root = new Node<>(valeur);
    }

    /**
     * Construit un arbre de recherche �quilibr� comportant toutes les valeurs
     * apparaissant dans un vecteur ordonn� fourni.
     * Ce vecteur doit être ordonn� (ordre croissant) aucune v�rification n'est faite ici.
     */
    public SearchTree(E[] elements)
    {
        setTree(makeFrom(elements, 0, elements.length - 1));
    }

    private SearchTree<E> makeFrom(E[] v, int deb, int fin)
    {
        /* 
         * Construction d'un arbre de recherche �quilibr� à partir d'une portion
         * de vecteur ordonn�.
         * La valeur de la racine est choisie au milieu de la portion de vecteur.
         */
        if (deb > fin) 	// portion de vecteur vide.
            return new SearchTree<>();

        int milieu = (deb + fin) / 2;
        // Contruction des sous-arbres.
        SearchTree<E> g = makeFrom(v, deb, milieu - 1);
        SearchTree<E> d = makeFrom(v, milieu + 1, fin);
        return new SearchTree<>(v[milieu], g, d);
    }
    
    private SearchTree(E valeur, SearchTree<E> gauche, SearchTree<E> droit)
    {
        root = new Node<>(valeur, gauche, droit);
    }

    public boolean estVide()
    {
        return root == null;
    }

    public E racine()
    {
        return root.value;
    }

    public SearchTree<E> gauche()
    {
        return root.left;
    }

    public SearchTree<E> droit()
    {
        return root.right;
    }

    private void setRacine(E val)
    {
        root.value = val;
    }

    private void setTree(SearchTree<E> tree)
    {
        root = tree.root;
    }
    
    // On peut dès à pr�sent oublier la classe interne Node!

    public boolean estUneFeuille()
    {
        return gauche().estVide() && droit().estVide();
    }

    /** 
     * Calcule l'expression de type "fonctionnelle" correspondant à l'arbre.
     */
    @Override
    public String toString() // 
    {
        if (estVide())
            return "";
        if (estUneFeuille())
            return "" + racine();
        return racine() + "(" + gauche() + "," + droit() + ")";
    }

    /** 
     * Calcule la suite "infixe" des valeurs de l'arbre.
     * Cette suite est naturellement ordonn�e (croissante) si l'arbre est
     * effectivement un arbre de recherche.
     */
    public String infixe() 		// suite infixe ou GRD
    {
        if (estVide())
            return "";
        return gauche().infixe() + racine() + " " + droit().infixe();
    }
    
    /**    
     * D�termine si l'arbre contient la valeur fournie.
     */
    public boolean contient(E val)
    {
    	if(this.estVide()) return false;
        int comp = val.compareTo(this.racine()); 
        if(comp==0) return true;
        if(comp<0)
        	return this.gauche().contient(val);
        else
        	return this.droit().contient(val);
    }

    /** 
     * Insère la valeur fournie dans l'arbre.
     */
    public void inserer(E val)
    {
        if(this.estVide()) this.setTree(new SearchTree<E>(val));
        else
        {
        int comp = val.compareTo(this.racine());
        if(comp<0)
        	this.gauche().inserer(val);
        else
        	this.droit().inserer(val);
        }
    }

    /**  
        Supprime de l'arbre une occurrence de la valeur fournie.
     */
    public void supprimer(E val)
    {
        if(!this.estVide())
        {
            int comp = val.compareTo(this.racine());
            if(comp<0) this.gauche().supprimer(val);
            else if(comp>0) this.droit().supprimer(val);
            else
            {
            	if(this.gauche().estVide()) this.setTree(this.droit());
            	else if (this.droit().estVide()) this.setTree(this.gauche());
            	else this.max();
            }
        }
    }
    
    public E max()
    {
    	if(!this.droit().estVide()) return this.droit().max();
    	else return this.racine();
    }

    /** 
     * D�termine si l'arbre (this) est effectivement un arbre de recherche.
     */
    public boolean isValid()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
