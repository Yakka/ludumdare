package spammer;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponent;
import backend.MathHelper;
import backend.Path;
import backend.geom.Rectangle;

public class Mail extends GameComponent {

	private static final int NB_MAILS = 10; //Nombre d'e-mails affiches
	private final float SPEED = 400.f; // pixels/second
	private static Image sprite;
	private static Vector2f startPos = new Vector2f(350, 500);

	public static final int UNCHECKED = 0;
	public static final int CHECKED = 1;
	public static final int ARRIVED = 2;
	public static final int BLOCKED = 3;

	private Image img;
	private float posX, posY;
	private float rot;
	private float destX, destY;
	private int destID;
	private String keyWord;
	private int state;
	private MailGroup groupRef;

	public Mail(String keyWord, int id, MailGroup g)
	{
		if(sprite == null)
		{
			try {
				img = new Image("assets/mail_small.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		groupRef = g;
		this.keyWord = keyWord;
		posX = startPos.x + MathHelper.randInt(-50, 50);
		posY = startPos.y + MathHelper.randInt(-50, 50);
		rot = MathHelper.randInt(-45, 45);
		state = UNCHECKED;
		if(id >= 0) {
			destX = Character.X_BY_ID[id];
			destY = Character.Y_BY_ID[id];
		}
		else {
			destX = 700 + MathHelper.randS(10);
			destY = 300 + MathHelper.randS(10);
		}
		
		destID = id;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx) {
		
		gfx.pushTransform();
		gfx.translate(posX, posY);
		gfx.rotate(5, 4, rot);
		gfx.drawImage(img, 0, 0);
		gfx.popTransform();

	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getDrawOrder() {
		return 0;
	}

	@Override
	public void getRenderBounds(Rectangle range) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
	{
		float dt = (float)delta / 1000.f;
		float speed = SPEED * dt;
		
		if(state == UNCHECKED)
		{
			if(posY > FireWall.BLOCK_LINE_Y)
				posY -= speed;
			else
			{
				if(GamePlay.get().fireWall.checkMail(this))
				{
					dispose();
					state = BLOCKED;
					groupRef.remaining--;
					groupRef.blockedCount++;
				}
				else
					state = CHECKED;
			}
		}
		else if(state == CHECKED)
		{
			Vector2f u = new Vector2f(destX - posX, destY - posY);
			u.normalise();
			posX += speed * u.x;
			posY += speed * u.y;
			if(posY < destY)
			{
				state = ARRIVED;
				// destID should be valid
				groupRef.remaining--;
				if(GamePlay.get().characters.get(destID).receiveMail(this))
				{
					groupRef.spammedCount++;
					GamePlay.get().gcm.stageComponent(
						new MailParticleSpawner(posX, posY));
				}
				dispose();
			}
		}

		if(groupRef.remaining == 0)
		{
			groupRef.notifyAvatar();
		}
	}

	@Override
	public void onDestruction() {
		// TODO Auto-generated method stub

	}

	public String getKeyWord()
	{
		return keyWord;
	}

}


