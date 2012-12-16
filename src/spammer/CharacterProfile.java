package spammer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import backend.MathHelper;
import backend.XMLTools;

public class CharacterProfile
{
	public static final String PATH = "data/interests/";
	public static final int MAX_INTEREST = 2; // Nb de fichiers par profils

	private ArrayList<Interest> interests;
	
	public CharacterProfile()
	{
		interests = new ArrayList<Interest>();

		File dir = new File(PATH);
		File files[] = dir.listFiles(); // they are supposed to be XML files !
		
		if(files.length <= 3)
		{
			System.out.println(
					"ERROR: not enough interest files found " +
					"(at least " + MAX_INTEREST + " are needed)");
			return;
		}
		
		HashSet<File> interestFiles = new HashSet<File>();
		// Ce code permet de s'assurer que le personnage ait N profils differents
		for (int i = 0; i < MAX_INTEREST; i++)
		{
			int j = 0;
			do {
				j = MathHelper.randInt(0, files.length - 1);
			} while(interestFiles.contains(files[j]));
			interestFiles.add(files[j]);
		}

		for (File f : interestFiles)
		{
			InterestData idata = (InterestData) XMLTools.decodeFromFile(f.getPath());
			interests.add(new Interest(idata));
		}
	}

	public boolean containsWord(String word)
	{
		for (Interest i : interests)
		{
			if (i.contains(word))
				return true;
		}
		return false;
	}
	
	public String getRandomExpression(){
		return interests.get(MathHelper.randInt(0, interests.size() - 1)).getRandomExpression();
	}

	public String toString()
	{
		String s = "";
		for (Interest i : interests)
		{
			s += i + "-----\n";
		}
		return s;
	}
}


