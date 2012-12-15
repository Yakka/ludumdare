package spammer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import backend.MathHelper;

public class CharacterProfile {

	public static final String PATH = "data/";
	public static final int MAX_INTEREST = 3; //Nb de fichiers par profils

	ArrayList<Interest> words;

	public CharacterProfile() {
		words = new ArrayList<Interest>();

		File dir = new File(PATH);
		File files[] = dir.listFiles();
		
		HashSet<File> interestID = new HashSet<File>();
		//Ce code permet de s'assurer que le personnage ait N profils differents
		for(int i = 0; i < MAX_INTEREST; i++){
			int nb;
			boolean done = false;
			do{
				nb = MathHelper.randInt(0, files.length -1);
				if(!interestID.contains(files[nb])){
					interestID.add(files[nb]);
					done = true;
				}
			}while(!done);
		}
		
		for(File f : interestID)
			words.add(new Interest(f));
		//---
	}
	
	public boolean containsWord(String word){
		for(Interest i : words){
			if(i.contains(word))
				return true;
		}
		return false;
	}
	
	public String toString(){
		String s = "";
		for(Interest i : words){
			s += i+"-----\n";
		}
		return s;
	}
}
