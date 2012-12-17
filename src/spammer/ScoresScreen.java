package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	private Image img;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException
	{
		img.draw(0, 0);
		g.setColor(Color.black);
		g.setFont(SpammerTheme.fontBig);
		g.drawString(((Integer)score).toString(), 400, 150);
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		super.enter(container, game);
		img = new Image("assets/screen_score.png");
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
		
		// Bouton replay
		ReplayButton btn = new ReplayButton(ui, 400, 275);
		btn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				playAgain = true;
			}
		});
		ui.add(btn);
	}

	@Override
	public int getID()
	{
		return MainGame.SCORES_SCREEN;
	}

}
