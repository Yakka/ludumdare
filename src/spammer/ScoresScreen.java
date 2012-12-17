package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.IActionListener;
import backend.ui.PushButton;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;
import backend.ui.Widget;

public class ScoresScreen extends UIBasicGameState
{
	private int score;
	private boolean playAgain;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
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
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		super.enter(container, game);
		
		score = GamePlay.get().getScore();
		playAgain = false;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(playAgain)
		{
			game.enterState(MainGame.GAME_PLAY);
		}
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran
		
		PushButton retryBtn = new PushButton(ui, 0, 0, 300, 64, "Play again");
		retryBtn.setAlign(Widget.ALIGN_CENTER, Widget.ALIGN_BOTTOM, 0, 200);
		retryBtn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				playAgain = true;
			}
		});
		ui.add(retryBtn);
	}

	@Override
	public int getID()
	{
		return MainGame.SCORES_SCREEN;
	}

}
