package tp03_piles;

public class EcpConvert {

	String expr = "";
	
	public static String EcpConvert(String expression)
	{
		if(OutilsPiles.BienParenthesee(expression)) return "Erreur de parenthésage";
		String converti = "";
		Pile<Character> pileECP = new Pile<>();
		int taille = expression.length();
		char operande = ' ';
		for(int index = 1; index <= taille; index ++)
		{
			char temp = expression.charAt(index);
			if(OutilsPiles.estOperande(temp))
			{
				converti += temp;
			}
			if(OutilsPiles.estOperateur(temp))
			{
				pileECP.empiler(temp);
			}
			if(OutilsPiles.estParentheseFermante(temp))
			{
				converti += pileECP.depiler();
			}
			
			
		}
		
		return converti;
	}
	
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
	}

}
