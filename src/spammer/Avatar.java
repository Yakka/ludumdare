package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;

public class Avatar extends GameComponent {

	private int x, y;
	private Image img;
	
	public Avatar(){
		x = 0;
		y = 478;
		try {
			img = new Image("assets/enter_avatar.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
