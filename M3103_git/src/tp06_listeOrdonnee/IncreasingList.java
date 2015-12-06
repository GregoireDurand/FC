
package tp06_listeOrdonnee;

import java.util.List;

/*
 * IncreasingList.java
 * Version compatible Java 1.6, pas d'utilisation de l'opérateur "diamond" (<>).
 */

/**
 <pre>
 * Classe utilitaire "générique" pour la représentation et la manipulation de 
 * listes croissantes d'informations.
 * 
 * Ces outils reposent sur une implémentation dynamique sans utiliser de "vecteur" 
 * (array, tableau) ni de "Collection" Java.
 * 
 * Cette classe est adaptée pour la définition de traitements récursifs des listes.
 * 
 * Attention: dans toutes les manipulations réalisées, les éléments eux-mêmes ne 
 * sont jamais "clonés" (dupliqués).
 </pre>
 * @author Erick Timmerman <Erick.Timmerman@univ-lille1.fr>
 * @param <E>  le type des elements de la liste.
 */
public class IncreasingList <E extends Comparable <E>>
{
    
    /* Classe utilitaire pour la représentation d'une cellule comportant une valeur 
     * (tête de la Liste) et une liste (le corps, le reste de la Liste).
     */
    private class Cell <E extends Comparable <E>>
    {
        private	E                   tete;
        private	IncreasingList <E>  corps;

    /* 	
     * Création d'un cellule simple sans lien vers d'autres cellules.
     */
        Cell (E val)
        {
            tete    = val;
            corps   = new IncreasingList <E> ();
        }

    /* 	
     * Création d'un cellule avec lien vers la liste l.
     */
        Cell (E val, IncreasingList <E> l)
        {
            tete    = val;
            corps   = l;
        }
    }
    
    // attribut
    private Cell <E> firstCell;

    // constructeurs

    /** 	
     * Création d'une liste vide.
     */
    public IncreasingList() 			
    {
        firstCell = null;
    }

    /* 	
     * Création d'une liste non vide.
     */
    private IncreasingList(E tete, IncreasingList <E> corps) 
    {
        firstCell = new Cell <E>(tete, corps);
    }


    /**
     * Création d'une liste d'un seul élément (liste "singleton").
     * @param tete valeur de la liste
     */
    public IncreasingList(E tete) 	
    {
        firstCell = new Cell <E>(tete);
    }
    
//	méthodes utilitaires de base
    
    /**
     * Détermine si le liste est vide.
     * @return true ssi la liste (this) est vide
     */	
    public boolean estVide()
    {
        return  firstCell == null;
    }

    /** 	
     * Fournit la tête de la liste. La liste ne doit pas être vide.
     * @return la tête de la liste.
     */
    public E tete()
    { 
        return  firstCell.tete;
    }

    /** 	
     * Fournit le reste de la liste (le "corps", la "queue" de la liste). 
     * La liste ne doit pas être vide.
     * @return la liste formant le "corps" de la liste.
     */
    public IncreasingList <E> corps()
    {
        return  firstCell.corps;
    }

    /* 	
     * Modifie la liste (this) de façon à ce qu'elle "deviennne" l'autre.
     */
    private void setList(IncreasingList <E> autre)
    {
        firstCell = autre.firstCell;
    }

    /* 	
     * Insertion de val en tête de la liste.
     */
    private void ajouterEnTete(E val)
    {
        Cell <E> local = new Cell <E>(val);
        
        local.corps.firstCell = firstCell;
        firstCell = local;
    }

    /** 	
     * Suppression de la tête de la liste, la liste ne doit pas être vide.
     */
    public void supprimerEnTete()
    {
        setList(corps());
        //	firstCell = corps().firstCell;
    }

//  méthodes utilitaires "évoluées" (on peut maintenant oublier les "cellules").

 

    
    /**
     * Fournit le nombre d'éléments de la liste.
     * @return la longueur de cette liste.
     */
    public int longueur()
    {
        if (estVide())
            return  0;
        return  1 + corps().longueur();
    }

   /**
     * Détermine si la liste contient l'élément fourni en argument.
     * @param val la valeur recherchée.
     * @return true ssi la valeur est présente dans la liste.
     */    
    public boolean contient(E val)
    {
        if(this.estVide()) return false;
        int res = val.compareTo(tete());
        if(res<0) return false;
        if(res==0) return true;
        return corps().contient(val);
    }

     /**
     * <pre>Fournit une chaîne de caractères selon le format:
     *    "()"    si la liste est vide,
     *    "(e1,e2, ..., en)" pour une liste de n éléments. </pre>
     * @return a string representation of this list.
     */   
    @Override
    public String toString()
    {
        if (estVide())
            return "()";
        return  "(" + contenu() + ")";
    }
    
    private String contenu()
    {
        if (corps().estVide())
                return  "" + tete();
        return  tete() + "," + corps().contenu();
    }
    
   /**
     * <pre>Supprime de la liste la 1ère occurrence de l'élément fourni en argument.
     * Rien n'est fait si cette valeur n'apparait pas dans la liste.
     * </pre>
     * @param val la valeur à supprimer de la liste.
     */
    public void supprimer(E val)
    {
       if(!this.estVide())
       {
    	   int res;
    	   res = val.compareTo(this.tete());
    	   if(res==0) supprimerEnTete();
    	   if(res>0) corps().supprimer(val);
       }
    }
    
    /**
     * Supprime de la liste toutes les occurrences de l'élément fourni en argument.
     * @param val la valeur à supprimer de la liste.
     */ 
    public void	supprimerAll(E val)
    {
    	 if(!this.estVide())
         {
      	   int res;
      	   res = val.compareTo(this.tete());
      	   if(res==0){
      		   supprimerEnTete();
      		   corps().supprimerAll(val);
      	   }
      	   if(res>0) corps().supprimer(val);
         }
    }
 
    /**
     * Fournit une copie ("clone") de la liste, les éléments eux-mêmes n'étant pas clonés.
     * @return une liste copie conforme de la liste.
     */    
    public IncreasingList <E> copie()
    {
        if (estVide())
            return new IncreasingList <E>();
        return new IncreasingList <E>(tete(), corps().copie());
    }

   /**
     * Détermine si la liste est identique à celle fournie en argument.
     * @param autre une autre liste
     * @return true ssi la liste et l'autre sont identiques.
     */    
    public boolean equals(IncreasingList <E> autre)
    {
        if ( estVide())
            return autre.estVide();
        if (! tete().equals(autre.tete()))
            return false;
        return corps().equals (autre.corps());
    }
    /**
     * <PRE>
     * Modifie la liste (croissante) en y insérant l'élément fourni en argument.
     * </PRE>
     * @param val valeur à insérer.
     */
    public void inserer(E val) 
    {
        if(estVide()) ajouterEnTete(val);
        else
        {
        	int res;
        	res = val.compareTo(tete());
        	if(res<=0) this.ajouterEnTete(val);
        	else corps().inserer(val);
        }
    }

    /**
     * Fournit une nouvelle liste croissante comportant tous les éléments de liste 
     * ainsi que ceux de celle fournie en argument.
     * @param autre une autre liste
     * @return une nouvellle liste résultnat de la fasion de la liste et de l'autre.
     */
    public IncreasingList <E> fusion(IncreasingList <E> autre) 
    {   
        // to do!
        return  null;
    }

    /**
     * Modifie la liste en la fusionnant avec celle fournie en argument.
     * @param autre une autre liste.
     */
    public void fusionner(IncreasingList <E> autre)
    {
        // to do!
    }  
    
    /**
     * Fournit un vecteur (array) d'objets ("Object") comportant tous les éléments 
     * de la liste (dans le même ordre).
     * Remarque: les éléments ne sont pas clonés.
     * @return un vecteur (array) contenant les éléments de la liste.
     */    
    public Object[] toArray()
    {
        // to do!
        return null;
    }

    public List<E> toList()
    {
        // to do!
        return null;
    }
}
