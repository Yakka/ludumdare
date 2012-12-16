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
	public static final int X_BY_ID[] = {40, 190, 340, 490};
	public static final int Y_BY_ID[] = {180, 180, 180, 180};
	public static final String IMAGE_BY_ID[] = {
		"assets/character_biatch.png",
		"assets/character_wesh.png",
		"assets/character_biker.png",
		"assets/character_nerd.png"};
	
	private int id;
	
	private long lastSpeak;
	private long remainingSpeech = 0;
	private Bubble bubble;
	
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
			img = new Image(IMAGE_BY_ID[id]);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = X_BY_ID[id];
		this.y = Y_BY_ID[id];
	
		bubble = new Bubble(id);
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
		bubble.render(gc, game, gfx);
		if(remainingSpeech > 0)
			bubble.setVisible(true);
		else
			bubble.setVisible(false);
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
			bubble.setText(profile.getRandomExpression());
			remainingSpeech = TIME_SPEECH + MathHelper.randInt(0, 2000);
		}
		
		bubble.update(gc, game, delta);
	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub
		
	}

}

