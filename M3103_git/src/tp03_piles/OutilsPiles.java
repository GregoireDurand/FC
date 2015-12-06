package tp03_piles;

public class OutilsPiles {

	public static boolean BienParenthesee(String exp){
		Pile <Character> pile;
		pile = new Pile<>();
		int longueur = exp.length();
		for(int index=0 ; index < longueur ; index ++){
			char car = exp.charAt(index);
			if(estParentheseOuvrante(car)) pile.empiler(car);
			else if(estParentheseFermante(car)){
				if(pile.estVide()) return false;
				if(!memeNature(pile.depiler(),car)) return false;
			}
		}
		return pile.estVide();
	}
	
	public static boolean estParentheseOuvrante(char cara)
	{
		return(cara == '(' || cara == '[' || cara == '{');
	}
		
	public static boolean estParentheseFermante(char cara)
	{
		return(cara == ')' || cara == ']' || cara == '}');
	}
	public static boolean memeNature(char caraPile, char caraLu)
	{
		if(caraPile == '(') return(caraLu == ')');
		if(caraPile == '{') return(caraLu == '}');
		return(caraLu == ']');
	}
	
	public static boolean estOperateur(char cara)
	{
		return(cara == '+' || cara == '-' || cara == '*' || cara == '/');
	}
	
	public static boolean estOperande (char cara)
	{
		return !(estOperateur(cara) || estParentheseOuvrante(cara) || estParentheseFermante(cara));
	}
				
	
	public static void main(String[] args) {
		String test = "({})";
		System.out.println(BienParenthesee(test));
		char test2 = 'a';
		System.out.println(estOperande(test2));
		
	}

}
