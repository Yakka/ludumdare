package spammer;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

import backend.ui.DefaultTheme;

public class SpammerTheme extends DefaultTheme
{
	public static Font font;
	public static Font fontBig;
	
	public static void load() throws SlickException
	{
		font = new AngelCodeFont(
				"assets/fonts/tahoma16.fnt",
				"assets/fonts/tahoma16_0.png");
		
		fontBig = new AngelCodeFont(
				"assets/fonts/tahoma32.fnt",
				"assets/fonts/tahoma32_0.png");
	}
	
	public Font getFont(){
		return font;
	}

}
