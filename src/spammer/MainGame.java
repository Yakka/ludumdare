package spammer;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

import spammer.devtools.InterestEditor;

import backend.MathHelper;
import backend.XMLTools;
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
		SpammerTheme sTheme = new SpammerTheme();
		SpammerTheme.load();
		UIRenderer.setTheme(sTheme);
	}

}


