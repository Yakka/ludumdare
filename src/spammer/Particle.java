package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.MathHelper;
import backend.geom.Rectangle;

public class Particle extends GameComponent
{
	protected Image sprite;
	protected Vector2f pos;
	protected Vector2f vel;
	protected float rotation;
	protected float rotVel;
	protected int timeRemaining;
	
	public Particle(float x, float y)
	{
		pos = new Vector2f(x, y);
		vel = new Vector2f(-(float)Math.PI/2.f + MathHelper.randS(0.3f * (float)Math.PI));
		rotation = MathHelper.randS((float)Math.PI);
		rotVel = MathHelper.randS((float)Math.PI);	
		timeRemaining = 1000;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		gfx.pushTransform();
		gfx.translate(pos.x, pos.y);
		gfx.rotate(0, 0, rotation);
		
		if(sprite != null)
		{
			gfx.drawImage(sprite,
					-sprite.getWidth() / 2, -sprite.getHeight() / 2, 
					sprite.getWidth() / 2, sprite.getHeight() / 2,
					0, 0, sprite.getWidth(), sprite.getHeight());
		}
		else
		{
			gfx.setColor(Color.red);
			gfx.drawRect(pos.x, pos.y, 20, 20);
		}
		
		gfx.popTransform();
	}

	@Override
	public boolean isVisible()
	{
		return true;
	}

	@Override
	public int getDrawOrder()
	{
		return 1;
	}

	@Override
	public void getRenderBounds(Rectangle range)
	{
		if(sprite != null)
			range.setCenterAndSize(pos.x, pos.y, sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
	{
		float dt = (float)delta / 1000.f;
		pos.x += vel.x * dt;
		pos.y += vel.y * dt;
		rotation += rotVel * dt;
		
		timeRemaining -= delta;
		if(timeRemaining < 0)
			dispose();
	}

	@Override
	public void onDestruction()
	{
	}

}



