package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import backend.ui.TextField;
import backend.ui.Widget;

public class SpammerTextField extends TextField {

	public SpammerTextField(Widget parent, int width, int height) {
		super(parent, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean isEnterable(char c) {
		return super.isEnterable(c) && c != ' ';
	}
	
	@Override
	public boolean mousePressed(int button, int x, int y)
	{
		return super.mousePressed(button, x, y);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		super.render(gc, gfx);
		setFocused(true);
	}

}
