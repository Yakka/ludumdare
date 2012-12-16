package spammer.devtools;

import java.util.Scanner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import backend.XMLTools;

import spammer.Interest;
import spammer.MainGame;

public class InterestEditor
{
	private final String ROOT_DIR = "data/interests/";
	private String filename = "";
	private Interest interest = new Interest();
	private Scanner scan = new Scanner(System.in);
	private boolean running;
	
	public static void main(String[] args) {
		
		(new InterestEditor()).run();

	}
	
	public void run()
	{
		running = true;
		
		System.out.println("====== Interest editor ======");
		System.out.println("Simply enter words concerning a topic.");
		System.out.println("Type '/<command>' to enter a command.");
		
		while(running)
		{
			System.out.print("$: ");
			String str = scan.nextLine();
			
			if(str.startsWith("/"))
			{
				String cmd = str.substring(1);
				command(cmd);
			}
			else
				word(str);
		}
	}
	
	private void command(String cmd)
	{
		if(cmd.equals("exit"))
			running = false;
		else if(cmd.equals("save"))
			commandSave();
		else if(cmd.equals("close"))
			commandClose();
		else if(cmd.equals("open"))
			commandOpen();
		else if(cmd.equals("help"))
			commandHelp();
		else
			System.out.println("Unrecognized command. /help for command list.");
	}
	
	private boolean save()
	{
		if(filename.isEmpty())
			return false;		
		return XMLTools.encodeToFile(interest, filename);
	}
	
	private void commandOpen()
	{
		System.out.print("filename: ");
		String newFilename = ROOT_DIR+scan.nextLine();
		if(!newFilename.endsWith(".xml"))
			newFilename += ".xml";
		Object obj = XMLTools.decodeFromFile(newFilename);
		if(obj != null)
		{
			if(!filename.isEmpty())
				save();
			interest = (Interest)obj;
			filename = newFilename;
			System.out.println("file opened.");
		}
		else
			System.out.println("the file don't exist or cannot be read.");
	}

	private void commandClose()
	{
		if(!filename.isEmpty())
		{
			save();
			System.out.println("file closed.");
		}
		interest = new Interest();
	}

	private void commandSave()
	{
		String saveFilename = filename;
		if(saveFilename.isEmpty())
		{
			System.out.print("filename: ");
			saveFilename = ROOT_DIR+scan.nextLine();
			if(!saveFilename.endsWith(".xml"))
				saveFilename += ".xml";
		}
		
		if(XMLTools.encodeToFile(interest, saveFilename))
		{
			filename = saveFilename;
			System.out.println("file saved as " + filename + ".");
		}
		else
		{
			System.out.println("couldn't write " + saveFilename + ".");
		}
	}
	
	private void commandHelp()
	{
		System.out.println("Commands list :");
		System.out.println("/exit : quits the program");
		System.out.println("/open : opens an existing file (saves current)");
		System.out.println("/save : saves the current file");
		System.out.println("/close : saves and closes the current file");
		System.out.println("/help : displays help contents");
	}
	
	private void word(String str)
	{
		if(str.contains(" "))
			System.out.println("Contains spaces !");
		else if(!interest.addKeyWord(str))
			System.out.println("Word already set.");
	}

}



