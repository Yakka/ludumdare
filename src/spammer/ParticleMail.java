package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import backend.MathHelper;

public class ParticleMail extends Particle
{
	private static Image mailSprite;
	private static float SPEED = 400.f;

	public ParticleMail(float x, float y)
	{
		super(x, y);
		
		if(mailSprite == null)
		{
			try {
				mailSprite = new Image("assets/mail_small.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		sprite = mailSprite;
		vel = new Vector2f(70.f + MathHelper.randS(30.f));
		vel.scale(SPEED);
		rotation = MathHelper.randS(180);
		rotVel = MathHelper.randS(180);
		timeRemaining = 500;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
	{
		super.update(gc, game, delta);
	}

}


