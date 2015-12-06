
package tp03_piles;
/**
 * Classe générique pour la représentation et la manipulation d'une "pile"
 * avec ses opérations primitives usuelles.
 * Représentation purement dynamique sans utiliser "d'outils" Java prédéfinis. 
 * 
 * @param <E> "type" générique. 
 * @author Erick Timmerman
 */
public class Pile<E>                // voir la classe "Stack" (dans java.util")
{   

    // classe interne pour la représentation d'un couple (valeur, lien).
    private class Cell<T>
    {
        T valeur;           // attributs (fields)
        Cell<T> lien;
        Cell(T data)        // constructeur
        {
            valeur = data;
            lien = null;
        }
    }   // fin de la classe interne
    
    private Cell<E> sommet;             // attribut de la classe Pile

    /** Creates a new instance of Pile */
    public Pile()                       // Inutile, = constructeur par défaut!
    {
        sommet = null;
    }

    /**
     * Détermine si la pile est vide ou non. 
     * @return true ssi la pile est vide.
     */
    public boolean estVide()            // cf. empty
    {
        return sommet == null;
    }

    /**
     * Fournit la valeur située au "sommet" de la pile, 
     * la pile ne doit pas être vide.
     * @return la valeur située au sommet de la pile.
     */
    public E sommet()                   // cf. peek
    {
        return sommet.valeur;
    }

    /**
     * Ajoute à la pile (au sommet) une valeur fournie.
     * @param aEmpiler valeur à empiler.
     */
    public void empiler(E aEmpiler)     // cf. push
    {
        Cell<E> nouveau = new Cell<>(aEmpiler);
        nouveau.lien = sommet;
        sommet = nouveau;
    }

    /**
     * Supprime et retourne une valeur de la pile (celle située au sommet),
     * la pile ne doit pas être vide.
     * @return la valeur qui a été supprimée de la pile.
     */
    public E depiler()                  // cf. pop
    {
        Cell<E> temp = sommet;
        sommet = sommet.lien;
        return temp.valeur;
    }

    /**
     * Fournit une chaîne de caractères comportant toutes les valeurs présentes 
     * dans la pile. 
     * <pre> 
     *      Format choisi: "[valeur à la base| ... |valeur au sommet|".
     * </pre>
     * @return a String representation of the values in this "pile".
     */
    @Override
    public String toString()
    {
        String result = "";
        Cell<E> link = sommet;
        while (link != null)
        {
            result = link.valeur + "|" + result;
            link = link.lien;
        }
        return "[" + result;
    }

    /**
     * Détermine si un élément donné est ou non présent dans la pile.
     * @param element l'élément recherché.
     * @return true ssi l'élément fourni apparait dans la pile.
     */
    public boolean contient(E element)      // cf. ~search
    {
        Cell<E> link = sommet;
        while (link != null)
            if (element.equals(link.valeur))
                return true;
            else
                link = link.lien;
        return false;
    }
}