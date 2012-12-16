package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.MathHelper;
import backend.geom.Rectangle;

public class Character extends GameComponent
{
	private static int NB_CHAR = 0;
	
	private static final int TIME_SPEECH = 5000; //Temps entre 2 bulles
	private static final int SPEAK_PERIOD = 15000; //Temps entre 2 bulles
	
	public static final int WIDTH = 118;
	public static final int HEIGHT = 194;
	public static final int X_BY_ID[] = {50, 200, 350, 500};
	public static final int Y_BY_ID[] = {200, 200, 200, 200};
	
	private int id;
	
	private long lastSpeak;
	private String speech = "";
	private long remainingSpeech = 0;
	
	private CharacterProfile profile;
	private Image img;
	private int x, y;
	
	public Character()
	{
		profile = new CharacterProfile();
		this.id = NB_CHAR;
		NB_CHAR ++;
		lastSpeak = 5000 + MathHelper.randInt(0, 10000);
		try {
			switch(id){
			case 0:
				img = new Image("assets/character_biatch.png");
				break;
			case 1:
				img = new Image("assets/character_wesh.png");
				break;
			case 2:
				img = new Image("assets/character_biker.png");
				break;
			case 3:
				img = new Image("assets/character_nerd.png");
				break;
			default:
				img = new Image("assets/SackBoy.jpg");
				break;
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = X_BY_ID[id];
		this.y = Y_BY_ID[id];
	}

	public int getIDCharacter(){
		return id;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//Renvoie vrai si le mot est dans le centre d'interet
	public boolean isInInterest(String word){
		return profile.containsWord(word);
	}

	public String toString() {
		return profile.toString();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		img.draw(x, y);
		if(remainingSpeech > 0)
			gfx.drawString(speech, x, y-100);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getDrawOrder() {
		return 0;
	}

	@Override
	public void getRenderBounds(Rectangle range) {
		range.setOriginAndSize(0, 0, 800, 600);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		lastSpeak += delta;
		if(remainingSpeech > 0)
			remainingSpeech -= delta;
		if(lastSpeak >= SPEAK_PERIOD){
			lastSpeak = 0;
			speech = profile.getRandomExpression();
			remainingSpeech = TIME_SPEECH;
		}
	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub
		
	}

}

