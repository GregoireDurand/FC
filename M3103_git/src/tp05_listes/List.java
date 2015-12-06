package tp05_listes;

/**
 * <pre>
 *  Classe utilitaire "générique" pour la représentation et la manipulation de
 * listes (chaînées) d'informations.
 * 
 *  Ces outils reposent sur une implémentation dynamique sans utiliser de
 * "vecteur" (array, tableau) ni de "Collection" Java.
 * 
 * <b>Cette classe est adaptée pour la définition de traitements récursifs des listes</b>.
 * 
 * N.B. dans toutes les manipulations réalisées, les éléments eux-mêmes ne sont jamais "clonés" (dupliqués).
 * </pre>
 *  @author Erick Timmerman <Erick.Timmerman@univ-lille1.fr>
 *  @param <E> 
 */
public class List<E>    // lire "List of E"
{

    /**
     * Classe utilitaire pour la représentation d'une cellule comportant une
     * valeur (tête de la Liste) et une liste (~lien vers le reste de la Liste).
    */
    private class Cell <E>
    {
        private	E       tete;
        private List<E> corps;

    	/**
     	 * Création d'une cellule "isolée".
     	 */
        Cell (E valeur)
        {
            tete    = valeur;
            corps   = new List<> (); // liste vide
        }

    	/** 	
     	 * Création d'une cellule avec lien vers une liste donnée.
    	 */
        Cell (E valeur, List<E> liste)
        {
            tete    = valeur;
            corps   = liste;	
        }
    }   // Fin de la classe interne "Cell<E>"
    

    // attribut de la classe "List<E>"
    
    private Cell <E> premiereCellule;

    // constructeurs de la classe "List<E>"

    /**
    * 	Création d'une liste vide.
    */
    public List() 			
    {
        premiereCellule = null;
    }

    /**
     * <pre>
      Création d'une liste non vide.
        La liste est construite de façon à y obtenir comme "tête": 
               la valeur fournie en 1er argument,
        et comme "liste": 
              la liste d'éléments fournie en 2ème argument.
       </pre>
     * @param valeur  valeur qui sera en tête
     * @param liste   liste qui sera le corps de la liste construite
     */
    public List(E valeur, List<E> liste) 
    {
        premiereCellule = new Cell <>(valeur, liste);
    }

    /**
     *  Création d'une liste d'un seul élément ("liste singleton").
     * @param valeur  valeur de tête
     */
    public List(E valeur) 	
    {
        premiereCellule = new Cell <>(valeur);
    }

//	méthodes utilitaires de base  de la classe "List<E>"

    /**
     * 	Retourne "vrai" ssi la liste (this) est vide
     * @return  true si la liste est vide.
     */	
    public boolean estVide()
    {
        return premiereCellule == null;
    }

    /**
     * 	Retourne la tête de la liste (this).
     *  La liste ne doit pas être vide.
     * @return  la valeur en tête de la liste
     */
    public E tete()
    { 
        return premiereCellule.tete;
    }

    /**
     * 	Retourne le reste de la liste.
     *  La liste ne doit pas être vide.
     * @return le corps de la liste 
     */
    public List<E> corps()
    {
        return  premiereCellule.corps;
    }

    /**
     * Modifie la liste (this) en la "remplaçant" par celle fournie
     * @param liste une autre liste
     */
    protected void setList(List<E> liste)
    {
        premiereCellule = liste.premiereCellule;
    }
    
    /**
     * modifie la tête de la liste
     * @param value   une valeur qui remplacera l'actuelle tête de liste
     */
    public void setTete(E value)
    {
        premiereCellule.tete = value;
    }

    /**
     * Modifie la liste en y insérant en tête l'élément fourni en argument.
     * @param value   valeur à ajouter en tête de la liste
     */
    public void ajouterEnTete(E value)
    {
        Cell <E> local = new Cell <>(value);
        
        local.corps.premiereCellule = premiereCellule;
        premiereCellule = local;
    }

    /**     Suppression de la tête de la liste.
     *       La liste ne doit pas être vide.
     */
    public void supprimerEnTete()
    {
        setList(corps());
        // ou premiereCellule = corps().premiereCellule;
    }

//  méthodes utilitaires "évoluées" (on peut maintenant oublier les "cellules").

    /**
     * <pre>Fournit une chaîne de caractères selon le format:
     *    "()"    si la liste est vide,
     *    "(e1,e2, ..., en)" pour une liste de n éléments.
     * </pre>
     * @return  une chaîne de caractères comportant les éléments de la liste
     */
    @Override 
    public String toString()
    {
    	if(this.estVide()) return "()";
        else return "(" + contenu() + ")";
    }
    
    public String contenu()
    {
    	if(this.corps().estVide()) return tete().toString();
    	return tete()+","+corps().contenu();
  	}
    
    /**
     * Retourne le nombre d'éléments de la liste.
     * @return  la longueur (nombre d'éléments) de la liste
     */
    public int longueur()
    {
        if(this.estVide()) return 0;
        return 1+corps().longueur();
    }

    /**
     * Détermine si la liste contient la valeur fournie en argument.
     * @param value valeur recherchée
     * @return true iff la liste contient la valeur fournie en argument
     */
    public boolean contient(E value)
    {
        if(this.estVide()) return false;
        if(this.tete().equals(value)) return true;
        return(this.corps().contient(value));
    }
    
    /**
     * Version itérative de la recherche d'une valeur dans la liste.
     * @param value valeur recherchée
     * @return true iff la liste contient la valeur fournie en argument
    */
    public boolean contientIt(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
                
    }
    
    /**
     * Détermine si la liste (this) est identique à celle fournie en argument.
     * @param autre     liste d'éléments de "type" E
     * @return  true if les 2 listes sont identiques.
     */
    public boolean equals(List<E> autre)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Modifie le liste en y ajoutant en fin la valeur fournie en argument.
     * @param value  valeur à ajouter en fin de la liste
     */
    public void ajouterEnFin(E value)
    {
        if(this.estVide()){ this.ajouterEnTete(value); }
        else corps().ajouterEnFin(value);
    }
      	
    /**
     * Renvoie la partie de la liste (éventuellement vide) débutant à la 1ère
     * occurrence de la valeur fournie en argument.
     * @param value   valeur recherchée
     * @return      la 1ère "sous-liste" débutant par la valeur fournie,
     *              une liste vide si cette valeur n'apparait pas dans la liste
     */
    public List<E> sublistBeginningWith(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }  
     	
    
    /**
     * <pre>Supprime de la liste la 1ère occurrence de la valeur fournie en 
     * argument.
     * Rien n'est fait si cette valeur n'apparait pas dans la liste.
     * </pre>
     * @param value   valeur à supprimer
     */
    public void supprimerFirst(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Supprime de la liste toutes les occurrences de la valeur fournie en 
     * argument.
     * @param value   valeur à supprimer
     */
    public void	supprimerAll(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
            
    /**
     * Renvoie une copie ("clone") de la liste. 
     * Les éléments eux-mêmes ne sont pas clonés.
     * @return  une nouvelle liste, copie conforme de la liste (this).
     */
    public List<E> copie()
    {
        if(this.estVide()) return new List<>();
        return new List<>(this.tete(), this.corps().copie());
    }

    /**
     * Modifie la liste en y concaténant celle fournie en argument.
     * @param autre liste à concaténer à la liste
     */
    public void concatener(List<E> autre)	/*	modification de la liste (this)
    										 	minimum d'un point de vue logique
    										 	d'un pt de vue efficacité,
    										 	il faut vérifier que autre non vide dès le départ.
    										*/
    {
       if(this.estVide()) this.setList(autre);
       else corps().concatener(autre);
    }

    /**
     * Fournit une nouvelle liste indépendante formée des éléments de la liste 
     * suivis de ceux de la liste fournie en argument.
     * Remarque: les éléments eux-mêmes ne sont pas "clonés".
     * @param  autre     liste d'éléments de"type" E
     * @return      une nouvelle liste comportant les valeurs de la liste (this) 
     *              suivies de celles de l'autre liste.
     */
    public List<E> concatenation(List<E> autre)
    // création d'une nouvelle liste: concaténation de this et de l'autre liste
    {
        if(this.estVide()) return autre.copie();
        return new List<>(this.tete(),this.corps().concatenation(autre));
    }

    /**
     * Fournit une nouvelle liste "miroir" des éléments de la liste.
     * Remarque: les éléments ne sont pas clonés.
     * @return  une nouvelle liste, le "miroir" de la liste.
     */
    public List<E> miroir()
    {
        if(this.estVide()) return new List<>();
        // à finir
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<E> miroirIteraif()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

}