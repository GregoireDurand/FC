package tp02_coloriage_files;

import java.io.*;

/**
 *
 * @author Erick Timmerman
 */
public class Colorier
{

    private static char[][] dessin;

    public static void main(String[] args)
    {
        try
        {
            BufferedReader inputFile = new BufferedReader(new FileReader("dessin.txt"));
            
            int nbLignes = 0;
            while (inputFile.readLine() != null)
            {
                nbLignes++;
            }
            inputFile.close();

            dessin = new char[nbLignes][];

            int lig = 0;
            inputFile = new BufferedReader(new FileReader("dessin.txt"));
            
            String ligne = inputFile.readLine();
            while (ligne != null)
            {
                dessin[lig] = ligne.toCharArray();
                /* ou:
                 *      dessin[lig] = new char[ligne.length()];
                 *      int lineLength = ligne.length();
                 *      for (int col = 0; col < lineLength; col++)
                 *          dessin[lig][col]= ligne.charAt(col); 
                 */
                lig++;
                ligne = inputFile.readLine();
            }
            inputFile.close();

            afficher();
            colorier(16, 22);
            afficher();
        }
        catch (FileNotFoundException fnfe)// Le fichier de données n'est pas trouvé.
        {
            System.out.println("Erreur fichier! " + fnfe);
        }
        catch (IOException ioe)         // Problème de lecture fichier.
        {
            System.out.println("Erreur de traitement de fichier! " + ioe);
        }
    }

    public static void afficher()
    {
        System.out.println();
        for (char[] dessin1 : dessin)
        {
            System.out.println(new String(dessin1));
        }
    }

    public static void colorier(int lig, int col)
    {
    	if(lig>=0 && lig<dessin.length && col>=0 && col<dessin[lig].length)
    	{
    		if(dessin[lig][col]=='.')
    		{
    			dessin[lig][col]='0';
    			colorier(lig+1,col);
    			colorier(lig-1,col);
    			colorier(lig,col+1);
    			colorier(lig,col-1);
		 	}
    	}
    }
}

/*
run:

.....................+++++++....................................................
.....................+......+++++......+++++++++++..............................
.....................+...........+.....+..........+.............................
.....................+............+....+..........+.............................
.....................++............++++...........+.............................
......................+.................+.........+.............................
......................+.................+.+++++++++.............................
......................+++++++++++++++++++.+.....................................
........................................+..++++++++++++++++++++++++++++.........
....................++++++++++++++++++++++.............................+........
....................+..................................................+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++.+........
....................+................................................+.+........
....................+..............................................+...+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++++........
................................................................................
................................................................................


.....................+++++++....................................................
.....................+oooooo+++++......+++++++++++..............................
.....................+ooooooooooo+.....+oooooooooo+.............................
.....................+oooooooooooo+....+oooooooooo+.............................
.....................++oooooooooooo++++ooooooooooo+.............................
......................+ooooooooooooooooo+ooooooooo+.............................
......................+ooooooooooooooooo+o+++++++++.............................
......................+++++++++++++++++++o+.....................................
........................................+oo++++++++++++++++++++++++++++.........
....................++++++++++++++++++++++ooooooooooooooooooooooooooooo+........
....................+oooooooooooooooooooooooooooooooooooooooooooooooooo+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++o+........
....................+oooooooooooooooooooooooooooooooooooooooooooooooo+o+........
....................+oooooooooooooooooooooooooooooooooooooooooooooo+ooo+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++++........
................................................................................
................................................................................

BUILD SUCCESSFUL (total time: 0 seconds)
*/