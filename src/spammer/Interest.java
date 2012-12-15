package spammer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import backend.XMLTools;

public class Interest extends ArrayList<String> {
	
	public Interest(File file){
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bf = new BufferedReader(reader);
			String str = "";
			
			while((str = bf.readLine()) != null){
					System.out.print(str);
				str.toLowerCase();
				add(str);
			}
			
			XMLTools.encodeToFile(this, "test.xml");
			bf.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		      e.printStackTrace();
	    }
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String toString(){
		String s = "";
		for(String sc : this){
			s += sc+"\n";
		}
		
		return s;
	}

}
