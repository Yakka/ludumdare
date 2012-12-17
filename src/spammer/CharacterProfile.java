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
		String root = (MainGame.isApplet() ? "" : "src/") + PATH;
		
		try
		{
			System.out.println("Loading profile...");
			
			String filenames[] = null;
			
			if(MainGame.isApplet())
			{
				// TODO HARDCODED because File doesn't works on unsigned applets
				String fn[] =
				{
					"clothes.xml",
					"computers.xml",
					"culture.xml",
					"food.xml",
					"games.xml",
					"kittens.xml",
					"love.xml",
					"pron.xml",
					"sport.xml"
				};
				for(int i = 0; i < fn.length; i++)
					fn[i] = root + fn[i];
				filenames = fn;
			}
			else
			{
				File dir = new File(root);
				File files[] = dir.listFiles(); // they are supposed to be XML files !
				String fn[] = new String[files.length];
				for(int i = 0; i < files.length; i++)
					fn[i] = files[i].getPath();
				filenames = fn;
			}
			
			// FIXME File doesn't work in an applet context

			
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
//				if(MainGame.isApplet())
//				{
//					InputStream is = 
//					idata = (InterestData)XMLTools.decodeFromStream(is);
//				}
//				else
					idata = (InterestData) XMLTools.decodeFromFile(f);
				interests.add(new Interest(idata));
			}
			
			System.out.println("Done.");
		}
		catch(Throwable e)
		{
			e.printStackTrace();
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


