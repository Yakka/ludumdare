package spammer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

import backend.MathHelper;
import backend.XMLTools;
import backend.ui.UIRenderer;
import backend.ui.UIStateBasedGame;

public class MainGame extends UIStateBasedGame
{
	public static final int GAME_PLAY = 2;

	public MainGame(String name) {
		super(name);
		BasicGameState gs = new GamePlay();
		addState(gs);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MainGame game = new MainGame("Pouet");
		
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
		sTheme.font = gc.getDefaultFont();
		UIRenderer.setTheme(sTheme);
	}

}


