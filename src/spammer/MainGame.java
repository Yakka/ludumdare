package spammer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import spammer.content.Sounds;
import backend.ui.UIRenderer;
import backend.ui.UIStateBasedGame;

public class MainGame extends UIStateBasedGame
{
	public static final int INTRO_SCREEN = 1;
	public static final int GAME_PLAY = 2;
	public static final int SCORES_SCREEN = 3;

	public MainGame(String name) {
		super(name);
		addState(new IntroScreen());
		addState(GamePlay.get());
		addState(new ScoresScreen());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainGame game = new MainGame("Spammer");
		
		try { 
		    AppGameContainer conteneur = new AppGameContainer(game);
		    conteneur.setDisplayMode(800,600,false);
		    conteneur.setTargetFrameRate(60);
		    conteneur.setShowFPS(false);
		    conteneur.start();
		    //conteneur.getInput().enableKeyRepeat();
		} catch (SlickException e) { 
		    e.printStackTrace(); 
		}

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		Sounds.get().load();
		SpammerTheme sTheme = new SpammerTheme();
		SpammerTheme.load();
		UIRenderer.setTheme(sTheme);
	}

}


