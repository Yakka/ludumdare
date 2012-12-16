package spammer;

import java.awt.Font;
import java.util.HashSet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;
import backend.geom.Vector2i;

public class FireWall extends GameComponent {

	public static final float BLOCK_LINE_Y = 450;
	private static Animation bumpEffect;
	
	private HashSet<String> words;
	private HashSet<String> specialWords;
	public Vector2i pos;
	public Vector2i size;

	public FireWall()
	{
		if(bumpEffect == null)
		{
			try {
				SpriteSheet img = new SpriteSheet("assets/firewall_explosion.png", 620, 64);
				// Note : vertical anim should work if there is only one sprite by row
				bumpEffect = new Animation(img, 100);
				bumpEffect.setLooping(false);
				bumpEffect.stop();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
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

		int i = 0;
		gfx.setColor(Color.red);
		for (String s : specialWords) {
			i++;
			if(s.length() > 18)
				s=s.substring(0, 15)+"...";
			if(i < 28)
				gfx.drawString(s, 645, 25 + i * 20);
			else
				break;
		}
		gfx.setColor(Color.black);
		for (String s : words) {
			i++;
			if(s.length() > 18)
				s=s.substring(0, 15)+"...";
			if(i < 28)
				gfx.drawString(s, 645, 25 + i * 20);
			else
				break;
		}
		
		if(!bumpEffect.isStopped())
		{
			gfx.drawAnimation(bumpEffect, 0, BLOCK_LINE_Y - 32);
			
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

	public boolean checkMail(Mail mail)
	{
		if(contains(mail.getKeyWord()))
		{
			// Fire animation
			bumpEffect.restart();
			return true;
		}
		else
			return false;
	}

}
