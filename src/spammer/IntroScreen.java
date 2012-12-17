package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.UIBasicGameState;

public class IntroScreen extends UIBasicGameState {
	private int imageIndex;
	private Image images[];

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		String dir = "assets/";
		images = new Image[3];
		images[0] = new Image(dir + "screen_start.png");
		images[1] = new Image(dir + "screen_tuto_01.png");
		images[2] = new Image(dir + "screen_tuto_02.png");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		System.out.println("Entering intro...");

		super.enter(container, game);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		System.out.println("Leaving intro...");
		super.leave(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics gfx) throws SlickException {
		if (imageIndex < images.length) {
			Image i = images[imageIndex];
			if (images[imageIndex] != null) {
				// Draws the image centered
				gfx.drawImage(i, (i.getWidth() - container.getWidth()) / 2,
						(i.getHeight() - container.getHeight()) / 2);
				gfx.setColor(Color.black);
				gfx.setFont(SpammerTheme.fontBig);
				switch(imageIndex){
				case 0:
					gfx.drawString("PRESS ENTER TO START", 240, 460);
					break;
				case 1:
					gfx.drawString("Press enter to continue", 450, 500);
					break;
				case 2:
					gfx.setColor(Color.red);
					gfx.drawString("Press enter to start!", 250, 400);
					break;
				}
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (imageIndex >= images.length) {
			// if(container.getInput().isKeyDown(Input.KEY_SPACE))
			game.enterState(MainGame.GAME_PLAY);
		} else if (images[imageIndex] == null)
			imageIndex++;
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if (key == Input.KEY_SPACE || key == Input.KEY_ENTER)
			imageIndex++;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		imageIndex++;
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public int getID() {
		return MainGame.INTRO_SCREEN;
	}

}
