package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;

public class Character extends GameComponent
{
	public static final int WIDTH = 118;
	public static final int HEIGHT = 194;
	public static final int X_BY_ID[] = {100, 200, 300, 400};
	public static final int Y_BY_ID[] = {50, 50, 50, 50};
	
	private int id;
	
	private CharacterProfile profile;
	private Image img;
	private int x, y;
	
	public Character(int id)
	{
		profile = new CharacterProfile();
		this.id = id;
		try {
			img = new Image("assets/SackBoy.jpg");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub
		
	}

}

