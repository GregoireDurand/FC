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
		if(premier == dernier)
		{
			premier.lien = null;
		}
		else
		{
			premier=premier.lien;
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