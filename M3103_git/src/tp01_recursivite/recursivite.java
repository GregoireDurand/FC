package tp01_recursivite;

import java.util.Scanner;

public class recursivite {
	
	static Scanner clavier = new Scanner(System.in);

	public static int factorielle(int n)
	{
		if(n == 0) return 1;
		return n*factorielle(n-1);
	}
	
	public static int sommeDesPremiersEntiers(int n)
	{
		if(n==0) return 0;
		return n+sommeDesPremiersEntiers(n-1);
	}
	
	public static int pgcd(int a, int b) //a,b>0
	{
		if(a==b) return a;
		if(a>b) return pgcd(a-b,b);
		return pgcd(a,b-a);
	}
	
	public static int fibronnacci(int n)
	{
		if(n<2) return 1;
		return fibronnacci(n-1)+fibronnacci(n-2);
	}
	
	public static void inverse()
	{
		String mot = clavier.nextLine();
		if(!mot.equals(""))
		{
			inverse();
			System.out.println(mot);
		}
	}
	
	public static void hanoi(int nb, String s1, String s2, String s3)
	{
		// à compléter
	}
	
	public static int persistance(int n)
	{
		if(n<10) return 0;
		return 1+persistance(produitDesChiffres(n));
	}
	
	public static int produitDesChiffres(int n)
	{
		if(n<10) return n;
		return ((n%10)*produitDesChiffres(n/10));
	}
	
		// Affichage de la table binaire : on appelle la méthode récursive à partir d'une méthode simple (1 arg) :
	public static void tableBinaire(int nbBits)
	{
		tableBinaire(nbBits, "");
	}
	
	// La méthode récursive qui est appelée
	public static void tableBinaire(int nbBits, String prefixe)
	{
		if(nbBits==0)
			System.out.println(prefixe);
		else
		{
			tableBinaire(nbBits-1, prefixe+"0");
			tableBinaire(nbBits-1, prefixe+"1");
		}
	}
	
	public static void affMinterms(String terme)
	{
		int posX=terme.indexOf('X');
		if(posX==-1)
			System.out.println(terme);
		else
		{
			String pref, suf;
			pref = terme.substring(0, posX);
			suf = terme.substring(posX+1);
			affMinterms(pref+"0"+suf);
			affMinterms(pref+"1"+suf);
		}
	}
	
	/* 
	 * Le filtre = mot avec des '?' ('?' représente un caractère quelconque)
	 * Le mot = suite de caractères compatible ou non avec le filtre
	 */
	public static boolean filtrageSimple(String filtre, String mot)
	{
		if(filtre.isEmpty()&&mot.isEmpty()) return true;
		if(filtre.isEmpty()||mot.isEmpty()) return false;
		if(mot.charAt(0)!=filtre.charAt(0)&&filtre.charAt(0)!='?') return false;
		return filtrageSimple(filtre.substring(1), mot.substring(1));
	}
	
	/*
	 * Filtrage prenant en compte les "*" (1 suite de caractères qui peut être nulle)
	 */
	public static boolean estCompatible(String filtre, String mot)
	{
		if(filtre.isEmpty()&&mot.isEmpty()) return true;
		if(filtre.isEmpty()) return false;
		if(mot.isEmpty()) return queDesEtoiles(filtre);
		if(mot.charAt(0)==filtre.charAt(0)||filtre.charAt(0)=='?')
			return estCompatible(filtre.substring(1), mot.substring(1));
		if(filtre.charAt(0)!='*') return false;
		if(estCompatible(filtre.substring(1),mot)) return true;
		return estCompatible(filtre, mot.substring(1));
	}
	
	public static boolean queDesEtoiles(String filtre)
	{
		if(filtre.isEmpty()) return true;
		if(filtre.charAt(0)!='*') return false;
		return queDesEtoiles(filtre.substring(1));
	}
	
	public static void main (String []args){
	
		System.out.println("Factorielle de 8 = "+factorielle(8));
		System.out.println("Somme des 5 permiers entiers = "+sommeDesPremiersEntiers(5));
		System.out.println("pgcd de 8 et 16 = "+pgcd(8,16));
		System.out.println("Fibronnacci de 30 = "+fibronnacci(30));
//		System.out.println("entrez suite de mots");
//		inverse();
		System.out.println("Produit des chiffres de 753 = "+produitDesChiffres(753));
		System.out.println("Persistance de 77 ="+persistance(77));
		System.out.println("Tableau Binaire de 4 =");
 		tableBinaire(4);
		affMinterms("001X00101011100");
		if(filtrageSimple("A?B", "AOOB")) System.out.println("ok");
		else System.out.println("pas ok");
		if(estCompatible("*?A","456456dqfs484fg68qg4vA")) System.out.println("ok");
		else System.out.println("pas ok");
	}
}