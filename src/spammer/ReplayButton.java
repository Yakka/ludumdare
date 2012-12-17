package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.PushButton;
import backend.ui.Widget;

public class ReplayButton extends PushButton
{
	private static Image img;
	
	public ReplayButton(Widget parent, int x, int y)
	{
		super(parent, x, y, 233, 155, "");
		
		if(img == null)
		{
			try {
				img = new Image("assets/replay.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		img.draw(super.getX(), super.getY());
	}
	
}
