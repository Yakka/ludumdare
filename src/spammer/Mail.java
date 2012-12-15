package spammer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;


public class Mail extends GameComponent {

	private Image img;
	private int x, y;
	
	public Mail(int x, int y)
	{
		try {
			img = new Image("assets/mail.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
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
