package tp06_listeOrdonnee;

public class MainIncreasingList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IncreasingList<Integer> liste = new IncreasingList<>(9);
		liste.inserer(14);
		liste.inserer(2);
		liste.inserer(256);
		liste.inserer(8);
		liste.inserer(16);
		
		System.out.println(liste);
		
		
		
	}

}
