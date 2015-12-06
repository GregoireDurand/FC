package cooAvance;

public class Dessin {

	public static void dessine(Figure f){
		for(int i = 0; i< f.getHeight() ; i++){
			for(int j=0; j< f.getWidth() ; j++){
				System.out.print(f.getChar(i,j));
			}
			System.out.println();
		}
	}
		
	public static void main (String [] args){

		dessine(new Rectangle(8,6));
		System.out.println();
		dessine(new Triangle(8,6));
		
		
		dessine(new Inverse(new Triangle(8,6)));
	}
}
