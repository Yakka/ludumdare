package spammer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;

public class Avatar extends GameComponent {

	private enum State{
		NOTHING, TROLL, CASH, ANGRY
	}
	
	private int x, y;
	private static Image img;
	private static Image bbTroll;
	private static Animation bbCash;
	private static Animation bbAngry;
	private static State state = State.NOTHING;
	
	private static final long BUBBLE_TIME = 1000; //Temps d'affichage d'une bulle
	private long remainingTime = 0;
	
	public Avatar(){
		x = 0;
		y = 478;
		try {
			if(img == null)
				img = new Image("assets/enter_avatar.png");
			if(bbTroll == null)
				bbTroll = new Image("assets/bad_guy_troll.png");
			if(bbCash == null){
				bbCash = new Animation(
							new SpriteSheet(
								new Image("assets/bad_guy_combo.png"), 
								42, 39), 
							100);
			}
			if(bbAngry == null)
				bbAngry = new Animation(
							new SpriteSheet(
								new Image("assets/bad_guy_Angry.png"), 
								42, 39), 
							100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	//CHANGEMENTS d'ETATS
	public void gettingTrolling(){
	}
	
	public void gettingCash(){
		
	}
	
	public void gettingAngry(){
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		img.draw(x, y);
		switch(state){
		case NOTHING:
			break;
		case TROLL:
			bbTroll.draw(100, 540);
			break;
		case ANGRY:
			bbAngry.draw(100, 540);
			break;
		case CASH:
			bbCash.draw(100, 540);
			break;
		}

	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getDrawOrder() {
		// TODO Auto-generated method stub
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
