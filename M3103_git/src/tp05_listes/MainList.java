package tp05_listes;

public class MainList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> liste1 = new List<>("Tête");
		List<String> liste2 = new List<>("Epaule", liste1);
		List<String> liste3 = new List<>("Genoux", liste2);
		List<String> liste4 = new List<>("Pieds", liste3);
		List<String> liste5 = new List<>("Mains");
		
		
		System.out.println("liste 4 = "+liste4);
		System.out.println(liste4.contient("Genoux"));
		System.out.println("Taille de la liste : "+liste4.longueur()+" éléments");
		
		liste4.ajouterEnFin("Genoux Pieds");
		System.out.println(liste4);
		liste4.concatener(liste5);
		System.out.println("Concaténer :"+liste4);
		liste1.concatenation(liste5);
		System.out.println("Concaténation :"+liste1);

	}

}
