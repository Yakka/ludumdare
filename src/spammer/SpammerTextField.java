package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import backend.ui.TextField;
import backend.ui.Widget;

public class SpammerTextField extends TextField {

	public SpammerTextField(Widget parent, int width, int height) {
		super(parent, width, height);
		this.setColor(Color.black);
		this.setFont(SpammerTheme.fontBig);
	}
	
	@Override
	protected boolean isEnterable(char c) {
		return super.isEnterable(c) && c != ' ';
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		super.render(gc, gfx);
		setFocused(true);
	}

}
