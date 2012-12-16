package spammer;

import java.util.HashSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;
import backend.geom.Vector2i;

public class FireWall extends GameComponent {

	public static final float BLOCK_LINE_Y = 450;
	
	private HashSet<String> words;
	private HashSet<String> specialWords;
	public Vector2i pos;
	public Vector2i size;

	public FireWall() {
		words = new HashSet<String>();
		specialWords = new HashSet<String>();
		pos = new Vector2i(620, 0);
		size = new Vector2i(180, 600);
	}

	public boolean contains(String s) {
		return words.contains(s) || specialWords.contains(s);
	}

	public void add(String s) {
		words.add(s);
	}
	
	public void addSpecial(String s){
		specialWords.add(s);
	}

	@Override
	public String toString() {
		String str = "";
		for (String s : specialWords) {
			str += s + "\n";
		}
		for (String s : words) {
			str += s + "\n";
		}
		return str;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		gfx.setColor(Color.red);
		int i = 0;
		for (String s : specialWords) {
			i++;
			gfx.drawString(s, 645, 25 + i * 20);
		}
		for (String s : words) {
			i++;
			gfx.drawString(s, 645, 25 + i * 20);
		}
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
		range.setOriginAndSize(0, 0, 800, 600);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {

	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub

	}

	/**
	 * Tests if the mail may pass or not.
	 * Destroys the mail if it is assumed evil.
	 * @param mail
	 */
	public void checkMail(Mail mail)
	{
		if(contains(mail.getKeyWord()))
		{
			mail.dispose(); // destroy the mail
			// TODO play fire animation
		}
	}

}
