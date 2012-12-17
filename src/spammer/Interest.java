package spammer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import com.sun.org.apache.xpath.internal.compiler.Keywords;

import backend.MathHelper;
import backend.XMLTools;

public class Interest
{
	public static final String DESKTOP_DIR = "src/data/interests/";
	
	private ArrayList<String> expressions;
	private HashSet<String> words;
	
	public Interest()
	{
		expressions = new ArrayList<String>();
		words = new HashSet<String>();
	}
	
	public Interest(InterestData idata)
	{
		expressions = new ArrayList<String>();
		if(idata.expressions != null)
		{
			for(int i = 0; i < idata.expressions.length; i++)
				expressions.add(idata.expressions[i]);
		}
		
		words = new HashSet<String>();
		for(int i = 0; i < idata.words.length; i++)
			words.add(idata.words[i]);
	}
	
	public InterestData getData()
	{
		InterestData data = new InterestData();
		
		data.words = words.toArray(new String[words.size()]);
		data.expressions = expressions.toArray(new String[expressions.size()]);
		
		return data;
	}

	public boolean contains(String word)
	{
		if(words != null)
			return words.contains(word);
		return false;
	}
	
	public String getRandomExpression()
	{
		if(expressions != null)
			return expressions.get(MathHelper.randInt(0, expressions.size() - 1));
		return "I have no expression, possibly a bug :(";
	}
	
	public boolean addKeyWord(String w)
	{
		return words.add(w);
	}
	
	public void addExpression(String expr)
	{
		expressions.add(expr);
	}
	
	/**
	 * expression sur plusieurs lignes 
	 * #
	 * mot1
	 * mot2
	 * ...
	 **/
	public void readFromTextFormat(String text)
	{
		String lines[] = text.split("\n");
		int i = 0;
		
		// Expressions
		for(i = 0; i < lines.length; i++)
		{
			String line = lines[i];
			if(line.isEmpty())
				continue;
			if(line.equals("#"))
				break;
			addExpression(line);
			System.out.println(line);
		}
		
		// Words
		for(; i < lines.length; i++)
		{
			String line = lines[i];
			if(line.isEmpty())
				continue;
			if(line.contains(" "))
			{
				System.out.println("WARNING: a word contains a space");
				continue;
			}
			addKeyWord(line);
			System.out.println(line);
		}
	}
	
	public String toTextFormat()
	{
		String text = "";
		
		for(String exp : expressions)
			text += exp + '\n';
		
		text += "#\n";
		
		for(String w : words)
			text += w + '\n';
		
		return text;
	}
	
	public static ArrayList<Interest> readAllFromXML()
	{
		ArrayList<Interest> interests = new ArrayList<Interest>();
		
		File dir = new File(DESKTOP_DIR);
		File files[] = dir.listFiles(); // they are supposed to be XML files !
		
		for(File f : files)
		{
			InterestData d = (InterestData)XMLTools.decodeFromFile(f.getPath());
			interests.add(new Interest(d));
		}
		
		return interests;
	}

}



