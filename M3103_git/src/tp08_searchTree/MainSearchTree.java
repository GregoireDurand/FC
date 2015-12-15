package tp08_searchTree;

public class MainSearchTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Integer [] vecteur = {10,20,30,40,50,60,70,80,100};
		SearchTree<Integer> arbre = new SearchTree<>(vecteur);
		
		System.out.println("Racine => "+arbre.racine());
		System.out.println("À gauche => "+arbre.droit());
		System.out.println("À gauche => "+arbre.gauche());
		
		
		System.out.println("Contient 50 ?"+arbre.contient(50));
		System.out.println("Contient 90 ?"+arbre.contient(90));
		
		arbre.inserer(500);
		System.out.println("L'arbre est :\n"+arbre);
	//	System.out.println("Insertion de 500. Vérif =>"+arbre.contient(500));
		
		

	}

}
