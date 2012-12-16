package spammer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.geom.Rectangle;

public class Bubble extends GameComponent {

	public static final String IMAGE_BY_ID[] = {
	"assets/bubble_ext_left.png",
	"assets/bubble_mid_left.png",
	"assets/bubble_mid_right.png",
	"assets/bubble_ext_right.png"};
	public static final int X_BY_ID[] = {0, 160, 320, 480};
	public static final int Y_BY_ID[] = {100, 100, 100, 100};
	
	private String text;
	private int x, y;
	private Image img;
	private boolean visible;
	
	public Bubble(int id){
		this.x = X_BY_ID[id];
		this.y = Y_BY_ID[id];
		text = "";
		try{
			img = new Image(IMAGE_BY_ID[id]);
		}catch(SlickException e){
			e.printStackTrace();
		}
		visible = false;
		
	}
	
	public void setVisible(boolean v){
		visible = v;
	}
	
	public void setText(String s){
		text = s;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		if(isVisible()){
			img.draw(x, y);
			gfx.drawString(text, x, y);
			
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getDrawOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getRenderBounds(Rectangle range) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub

	}

}
