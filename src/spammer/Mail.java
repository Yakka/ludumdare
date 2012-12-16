package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.MathHelper;
import backend.geom.Rectangle;

public class Mail extends GameComponent {

	private static final int NB_MAILS = 10; //Nombre d'e-mails affiches
	
	private final float SPEED = 0.5f;
	private Image img;
	private int x, y;
	private int rot;
	private int destX, destY;

	public Mail(int id)
	{
		try {
			img = new Image("assets/mail_small.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = 500 + MathHelper.randInt(-50, 50);
		y = 500 + MathHelper.randInt(-50, 50);
		rot = MathHelper.randInt(-45, 45);
		if(id >= 0){
			destX = Character.X_BY_ID[id];
			destY = Character.Y_BY_ID[id];
		}
		else{
			destX = 700;
			destY = 300;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		
		gfx.pushTransform();
		gfx.translate(x, y);
		gfx.rotate(5, 4, rot);
		gfx.drawImage(img, 0, 0);
		gfx.popTransform();

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
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
		float deltaSpeed = SPEED * delta;
		if (x < destX) {
			x += deltaSpeed;
			if (x > destX)
				x = destX;
		} else if (x > destX) {
			x -= deltaSpeed;
			if (x < destX)
				x = destX;
		}
		if (y < destY) {
			y += deltaSpeed;
			if (y > destY)
				y = destY;
		} else if (y > destY) {
			y -= deltaSpeed;
			if (y < destY)
				y = destY;
		}

	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub

	}

}
