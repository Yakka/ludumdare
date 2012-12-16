package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.UIBasicGameState;

public class ScoresScreen extends UIBasicGameState
{
	private int score;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		score = GamePlay.get().getScore();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException
	{
		g.setColor(Color.gray);
		g.drawRect(0, 0, 800, 600);
		g.setColor(Color.black);
		g.drawString("Score: "+((Integer)score).toString(), 400, 300);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getID()
	{
		return 3;
	}

}
