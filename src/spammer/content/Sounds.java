package spammer.content;

import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds
{
	private static final int MIN_DELAY = 50; // milliseconds (between the same sound))
	private static Sounds instance;
	
	private String dir = "assets/sounds/";
	private HashMap<String, SoundEntry> sounds = new HashMap<String, SoundEntry>();
	
	public static Sounds get()
	{
		if(instance == null)
			instance = new Sounds();
		return instance;
	}
	
	public void load() throws SlickException
	{
		loadSound("spam", "spam.ogg");
		loadSound("score", "score.ogg");
		loadSound("send", "send.ogg");
		loadSound("dafuq", "dafuq.ogg");
		loadSound("firewall", "firewall.ogg");
	}
	
	public void play(String id, float pitch, float volume)
	{
		SoundEntry e = sounds.get(id);
		if(e != null)
		{
			long time = System.currentTimeMillis();
			if(time - e.lastTimePlayed > MIN_DELAY)
			{
				e.lastTimePlayed = time;
				e.sound.play(pitch, volume);
			}
		}
	}
	
	private void loadSound(String id, String filename) throws SlickException
	{
		SoundEntry e = new SoundEntry();
		e.sound = new Sound(dir + filename);
		sounds.put(id, e);
	}
	
	private class SoundEntry
	{
		public Sound sound;
		public long lastTimePlayed;		
	}
	
}



