package spammer;

import java.util.HashSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;

public class FireWall extends GameComponent {

	HashSet<String> words;

	public FireWall() {
		words = new HashSet<String>();
	}

	public boolean contains(String s) {
		return words.contains(s);
	}

	public void add(String s) {
		words.add(s);
	}

	@Override
	public String toString() {
		String str = "";
		for (String s : words) {
			str += s + "\n";
		}
		return str;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		gfx.setColor(Color.red);
		int i = 0;
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

}
