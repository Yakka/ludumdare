package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import spammer.content.Sounds;

import backend.GameComponent;
import backend.MathHelper;
import backend.geom.Rectangle;

public class Character extends GameComponent
{
	private static int NB_CHAR = 0;

	private static final int TIME_SPEECH = 5000; //Temps de l'etat
	private static final int TIME_DAFUQ = 2000; 
	private static final int TIME_SPAMMED = 2000; 
	private static final int SPEAK_PERIOD = 8000; //Temps entre 2 bulles
	
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
	
	private enum State{
		NOTHING, SPEAKING, SPAMMED, DAFUQ
	}
	private State state;
	
	private long lastSpeak;
	private long remainingSpeech;
	private long remainingDafuq;
	private long remainingSpammed;
	private Bubble bubble;
	
	private CharacterProfile profile;
	private Image dafuq, spam;
	private SpriteSheet img;
	private int x, y;
	
	public Character(int id)
	{
		profile = new CharacterProfile();
		this.id = id;
		lastSpeak = MathHelper.randInt(0, 10000);
		try {
			img = new SpriteSheet(new Image(IMAGE_BY_ID[id]), 100, 175);
			dafuq = new Image("assets/character_dafuq.png");
			spam = new Image("assets/character_spam.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = X_BY_ID[id];
		this.y = Y_BY_ID[id];
		state = State.NOTHING;
		bubble = new Bubble(id);
		
		initEveryTimes();
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

	public void receiveSpam(){
		state = State.SPAMMED;
		initEveryTimes();
		remainingSpammed = TIME_SPAMMED;
		bubble.setVisible(true);
		bubble.setTextOn(false);
	}
	
	public void receiveWrongMail(){
		state = State.DAFUQ;
		initEveryTimes();
		remainingDafuq = TIME_DAFUQ;
		bubble.setVisible(true);
		bubble.setTextOn(false);
	}
	
	private void initEveryTimes(){
		remainingSpeech = 0;
		remainingDafuq = 0;
		remainingSpammed = 0;
	}
	
	public String toString() {
		return profile.toString();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		bubble.render(gc, game, gfx);
		switch(state){
		case NOTHING:
			img.getSubImage(0, 0).draw(x, y);
			break;
		case SPEAKING:
			img.getSubImage(0, 0).draw(x, y);
			break;
		case DAFUQ:
			img.getSubImage(1, 0).draw(x, y);
			dafuq.draw(x+25, y-100);
			break;
		case SPAMMED:
			img.getSubImage(2, 0).draw(x, y);
			spam.draw(x+25, y-100);
			break;
		}
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
		
		State nextState = State.NOTHING;
		switch(state){
		case NOTHING:
			bubble.setVisible(false);
			bubble.setTextOn(false);
			lastSpeak += delta;
			if(lastSpeak >= SPEAK_PERIOD){
				lastSpeak = 0;
				bubble.setText(profile.getRandomExpression());
				remainingSpeech = TIME_SPEECH + MathHelper.randInt(0, 2000);
				nextState = State.SPEAKING;
			}
			else
				nextState = State.NOTHING;
			break;
		case SPEAKING:
			bubble.setVisible(true);
			bubble.setTextOn(true);
			remainingSpeech -= delta;
			if(remainingSpeech <= 0)
				nextState = State.NOTHING;
			else
				nextState = State.SPEAKING;
			break;
		case DAFUQ:
			remainingDafuq -= delta;
			bubble.setTextOn(false);
			bubble.setVisible(true);
			if(remainingDafuq <= 0)
				nextState = State.NOTHING;
			else
				nextState = State.DAFUQ;
			break;
		case SPAMMED:
			remainingSpammed -= delta;
			bubble.setTextOn(false);
			bubble.setVisible(true);
			if(remainingSpammed <= 0)
				nextState = State.NOTHING;
			else
				nextState = State.SPAMMED;
			break;
		}
		state = nextState;
		bubble.update(gc, game, delta);
	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub
	}

	public boolean receiveMail(Mail mail)
	{
		if(isInInterest(mail.getKeyWord()))
		{
			receiveSpam();
			Sounds.get().play("spam", 1, 0.6f);
			GamePlay.get().increaseScore(1);
			GamePlay.get().fireWall.addSpecial(mail.getKeyWord());
			return true;
		}
		else
		{
			receiveWrongMail();
			Sounds.get().play("dafuq", 1, 0.15f);
			if(!GamePlay.get().fireWall.contains(mail.getKeyWord()))
				GamePlay.get().fireWall.add(mail.getKeyWord());
			return false;
		}
	}

}

