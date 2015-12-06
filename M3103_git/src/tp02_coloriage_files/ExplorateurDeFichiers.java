package tp02_coloriage_files;

import java.io.File;

public class ExplorateurDeFichiers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File fichier = new File("/home/infoetu/durandg/Documents/FC2");
		if(fichier.exists() && fichier.isDirectory())
			explorer(fichier);
		
	}

	
	public static void explorer(File repertoire)
	{
		System.out.println(repertoire);
		if(repertoire.isDirectory()){
			File[] fils = repertoire.listFiles();
			for(File f :fils){
				explorer(f);
			}
		}
	}
	
}
