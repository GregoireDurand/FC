package tp04_filesAttente;

public class FifoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Fifo<String> file = new Fifo<>();
		System.out.println("Est vide ?"+file.estVide());
		file.suppression();
		System.out.println("suppression... reste "+file);

		file.ajouter("un");
		file.ajouter("2");
		file.ajouter("trois");
		System.out.println(file);
		System.out.println("Est vide ?"+file.estVide());
		System.out.println("Contient 2 ?"+file.contient("2"));
		file.suppression();
		System.out.println("suppression...reste "+file);
		file.suppression();
		System.out.println("suppression... reste "+file);
		file.suppression();
		System.out.println("suppression... reste "+file);
		
	}

}
