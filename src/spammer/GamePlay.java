package spammer;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spammer.content.Sounds;

import backend.GameComponentMap;
import backend.geom.Rectangle;
import backend.ui.IActionListener;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;
import backend.ui.Widget;

public class GamePlay extends UIBasicGameState {
	private static final int COEF_SCORE = 50;
	//private static final long END_TIME = 120; // seconds
	private static final long END_TIME = 5; // seconds
	private static GamePlay instance;
	
	public static GamePlay get()
	{
		if(instance == null)
			instance = new GamePlay();
		return instance;
	}

	public ArrayList<Character> characters;
	public FireWall fireWall;
	private int score = 0;
	private long timeBeforeEnd; // milliseconds
	public GameComponentMap gcm;
	private Image scoreUI, fieldUI, timeUI, firewallUI, logo; //UI
	private Music music;
	public Avatar avatar;

	public int getScore(){
		return score;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame stg)
			throws SlickException {
		//UI
		scoreUI = new Image("assets/score.jpg");
		fieldUI = new Image("assets/enter_field.png");
		timeUI = new Image("assets/time.jpg");
		firewallUI = new Image("assets/blacklist_bg.jpg");
		music = new Music("assets/music/spammer_v1.ogg", true);
		logo = new Image("assets/logo.png");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		
		characters = new ArrayList<Character>();
		Character c1 = new Character();
		Character c2 = new Character();
		Character c3 = new Character();
		Character c4 = new Character();
		characters.add(c1);
		characters.add(c2);
		characters.add(c3);
		characters.add(c4);
		fireWall = new FireWall();
		gcm = new GameComponentMap();
		gcm.stageComponent(fireWall);
		gcm.stageComponent(c1);
		gcm.stageComponent(c2);
		gcm.stageComponent(c3);
		gcm.stageComponent(c4);
		avatar = new Avatar();
		gcm.stageComponent(avatar);
		
		timeBeforeEnd = END_TIME * 1000;
		
		//container.setMusicOn(false);
		music.play(1, 0.2f);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setFont(SpammerTheme.font);
		g.setBackground(Color.gray);
		scoreUI.draw(0, 0);
		fieldUI.draw(0, 478);
		timeUI.draw(442, 0);
		firewallUI.draw(620, 0);
		gcm.renderAll(gc, sbg, g, new Rectangle(0, 0, 800, 600), true);
		logo.draw(275, -10);
		g.setColor(Color.black);
		g.setFont(SpammerTheme.fontBig);
		g.drawString("Score: " + score, 20, 20);

		RenderTimer.draw(gc, g, (float) timeBeforeEnd / 1000.f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException 
	{
		timeBeforeEnd -= delta;
		if(timeBeforeEnd <= 0){
			sbg.enterState(MainGame.SCORES_SCREEN);
		}
				
		gcm.updateAll(gc, sbg, delta);
	}

	@Override
	public int getID() {
		return MainGame.GAME_PLAY;
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException {
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran

		// Champ pour le texte
		final SpammerTextField field = new SpammerTextField(ui, 165, 28);
		field.setPosition(190, 512);
		field.addValidateListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				sendMessage(field, field.getText());
			}
		});
		ui.add(field);

		// Bouton envoyer
		SpamButton btn = new SpamButton(ui, 560, 500);
		btn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				sendMessage(field, field.getText());
			}
		});
		ui.add(btn);

	}

	public void sendMessage(SpammerTextField field, String _word) {
		String word = _word.toLowerCase();
		if(word == "")
			return;
		
		MailGroup g = new MailGroup(characters.size());
		for (Character c : characters) {
			gcm.stageComponent(new Mail(word, c.getIDCharacter(), g));
		}
		
		Sounds.get().play("send", 1, 0.5f);
		
		field.setText("");
	}

	public void increaseScore(int i)
	{
		score += i * COEF_SCORE;
	}

}
