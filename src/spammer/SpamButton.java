package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import backend.ui.PushButton;
import backend.ui.Widget;

public class SpamButton extends PushButton
{
	private static SpriteSheet sprites;
	
	public SpamButton(Widget parent, int x, int y)
	{
		super(parent, x, y, 50, 41, "");
		
		if(sprites == null)
		{
			try {
				sprites = new SpriteSheet("assets/enter_button.png", 50, 41);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		gfx.drawImage(sprites.getSprite(0, isPressed() ? 1 : 0), 
				getAbsoluteX(), getAbsoluteY());
	}
	
}
