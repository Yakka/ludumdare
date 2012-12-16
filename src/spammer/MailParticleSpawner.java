package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;

public class MailParticleSpawner extends GameComponent
{
	private static final int DURATION = 1000;
	
	private int remainingTime;
	private Vector2f pos;
	
	public MailParticleSpawner(float x, float y)
	{
		pos = new Vector2f(x, y);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
	}

	@Override
	public boolean isVisible()
	{
		return false;
	}

	@Override
	public int getDrawOrder()
	{
		return 0;
	}

	@Override
	public void getRenderBounds(Rectangle range)
	{
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
	{
		GamePlay.get().gcm.stageComponent(new ParticleMail(pos.x, pos.y));
		remainingTime -= delta;
		if(remainingTime < 0)
			dispose();
	}

	@Override
	public void onDestruction()
	{
	}
	
}


