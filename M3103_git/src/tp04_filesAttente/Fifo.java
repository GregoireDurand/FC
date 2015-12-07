package tp04_filesAttente;

public class Fifo<E> {

	// classe interne :
	private class Cellule<T>
	{
		// attributs de Cellule
		T valeur;
		Cellule <T> lien;
		
		// constructeur de Cellule
		Cellule(T valeur)
		{
			this.valeur = valeur;
			lien = null;
		}
	}
	
	// déclaration des attributs
	private Cellule<E> premier, dernier;
	
	// constructeur de Fifo :
	public Fifo()
	{
		premier = null;
		dernier = null;
	}
	
	public boolean estVide()
	{
		return(premier == null);
	}
	
	public void ajouter(E info)
	{
		Cellule<E> cellule = new Cellule<>(info);

		if(estVide())
		{
			premier = cellule;
			dernier = cellule;
		}
		else
		{
			dernier.lien = cellule;
			dernier = cellule;
		}
	}
	
	public E suppression()
	{
		if(estVide())
		{
			return null;
		}
		E val = premier.valeur;
			System.out.println("valeur à supprimer = "+premier.valeur);
		if(premier == dernier)
		{
			System.out.println("[Suppression] Etat de la liste => 1er = dernier");
			premier = null;
			dernier = null;
			if(premier!=null)System.out.println("La nouvelle tête de liste fifo est => "+premier.valeur);
		}
		else
		{
			System.out.println("[Suppression] Etat de la liste => il en reste....");
			premier=premier.lien;
			System.out.println("La nouvelle tête de liste fifo est => "+premier.valeur);
		}
		return val;
	}
	
	public String toString()
	{
		String aff ="[";
		Cellule<E> enCours = premier;
		while(enCours!= null)
		{
			aff += enCours.valeur;
			if(enCours.lien != null) aff += " | ";
			enCours=enCours.lien;
		}
		aff += "]";
		return aff;
	}
	
	public boolean contient (E info)
	{
		Cellule<E> enCours = premier;
		while(enCours != null)
		{
			if(enCours.valeur.equals(info)) return true;
			enCours=enCours.lien;
		}
		return false;
	}	
}