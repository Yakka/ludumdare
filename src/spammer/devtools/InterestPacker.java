package spammer.devtools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import backend.XMLTools;

import spammer.Interest;
import spammer.InterestData;
import spammer.MainGame;

/**
 * Reads interest files and writes them into Java hardcode
 * (file loading bugs, deadline madness, you know...)
 * @author Marc
 *
 */
public class InterestPacker
{
	private static final String root = "src/data/interests/";
	
	public static void main(String args[])
	{
		ArrayList<Interest> interests = Interest.readAllFromXML();		
		String dir = "txt/";
		try
		{
			int n = 0;
			
			for(Interest i : interests)
			{
				FileOutputStream fos = new FileOutputStream(dir + Integer.toString(n) + ".txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);	
				
				bw.write(i.toTextFormat());
				n++;
				
				bw.flush();
				fos.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		textFilesToHardcodedStringArray(dir, "txt/interests.txt");
	}
			
	private static void textFilesToHardcodedStringArray(String srcDirname, String dstFilename)
	{
		File dir = new File(srcDirname);
		File files[] = dir.listFiles(); // they are supposed to be XML files !
		
		System.out.println(files.length);
		
		try {
			
			FileOutputStream fos = new FileOutputStream(dstFilename);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for(File f : files)
			{
				FileInputStream fis = new FileInputStream(f);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				
				String line;
				boolean first = true;
				while((line = br.readLine()) != null)
				{
					System.out.println(line);
					if(!first)
						bw.write(" +\n");
					else
						first = false;
					line.replace("\"", "\\\"");
					bw.write('"' + line + "\\n\"");
				}
				
				fis.close();
				bw.flush();
				
				bw.write(",\n\n");
			}
			
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
