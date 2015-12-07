package tp07_binaryTree;

public class MainBinaryTree {

	public static void main(String[] args) {
		
//		Character [] elts = {'A','B','C','D','E','F','G','H','I','J','K','L'};
		Character [] elts = {'A','B','C','D','E','F','G'};
//		Character [] elts = {'A','B','C'};
//		Character [] elts = {'A'};
		
		BinaryTree<Character> sequoia = new BinaryTree<>(elts);
		
/*		System.out.print("Est une feuille ?.... ");
		if(sequoia.estUneFeuille()) System.out.println("OUI");
		else System.out.println("NON");
		
		if(sequoia.estBinairePur()) System.out.println("Est binaire pur !!!");
		else System.out.println("N'est PAS binaire pur");
		
		System.out.println(sequoia);
		System.out.println("Préfixe RGD : "+sequoia.prefixe());
		System.out.println("Préfixe Itératif RGD : "+sequoia.prefixeIteratif());

		System.out.println("Suffixe GDR : "+sequoia.suffixe());
		
		System.out.println("Infixe GRD : "+sequoia.infixe());
		System.out.println("Infixe Itératif RGD : "+sequoia.infixeIteratif());
		
		System.out.println("Nombre de noeuds =   "+sequoia.nbNoeuds());
		System.out.println("Hauteur =   "+sequoia.hauteur());*/
		
		try {
			System.out.println("Affichage par niveau : "+sequoia.parNiveau());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
