package spammer;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;

import data.Interests;

import backend.MathHelper;
import backend.XMLTools;

public class CharacterProfile
{
	public static final int MAX_INTEREST = 2; // Nb de fichiers par profils
	
	private ArrayList<Interest> interests;
	
	public CharacterProfile()
	{
		interests = new ArrayList<Interest>();
		
		try
		{
			System.out.println("Loading profile...");
			if(MainGame.isApplet())
				loadFromHardcode();
			else
				loadFromFiles();
			System.out.println("Done.");
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadFromFiles()
	{
		System.out.println("Loading from files");
		
		String root = Interest.DESKTOP_DIR; // Doesn't works in an applet
		String filenames[] = null;
		
		File dir = new File(root);
		File files[] = dir.listFiles(); // they are supposed to be XML files !
		String fn[] = new String[files.length];
		for(int i = 0; i < files.length; i++)
			fn[i] = files[i].getPath();
		filenames = fn;
		
		if(filenames == null || filenames.length <= 3)
		{
			System.out.println(
					"ERROR: not enough interest files found " +
					"(at least " + MAX_INTEREST + " are needed)");
			return;
		}
		
		System.out.println("Files fetched.");

		HashSet<String> choosenFilenames = new HashSet<String>();
		// Ce code permet de s'assurer que le personnage ait N profils differents
		for (int i = 0; i < MAX_INTEREST; i++)
		{
			int j = 0;
			do {
				j = MathHelper.randInt(0, filenames.length - 1);
			} while(choosenFilenames.contains(filenames[j]));
			choosenFilenames.add(filenames[j]);
		}

		for (String f : choosenFilenames)
		{
			InterestData idata = null;
			idata = (InterestData) XMLTools.decodeFromFile(f);
			interests.add(new Interest(idata));
		}
	}
	
	private void loadFromHardcode()
	{
		System.out.println("Loading hardcoded");
		
		HashSet<Integer> choosen = new HashSet<Integer>();
		// Ce code permet de s'assurer que le personnage ait N profils differents
		for (int i = 0; i < MAX_INTEREST; i++)
		{
			int j = 0;
			do {
				j = MathHelper.randInt(0, Interests.dataXML.length - 1);
			} while(choosen.contains(j));
			choosen.add(j);
		}
		
		for(Integer i : choosen)
		{
//			InterestData idata = null;
//			idata = (InterestData) XMLTools.decodeFromString(Interests.dataXML[i]);
//			interests.add(new Interest(idata));
			Interest interest = new Interest();
			interest.readFromTextFormat(Interests.dataText[i]);
			interests.add(interest);
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
		if(!interests.isEmpty())
			return interests.get(MathHelper.randInt(0, interests.size() - 1)).getRandomExpression(); 
		return "I can't find my text ! There is a bug in the game :(";
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


