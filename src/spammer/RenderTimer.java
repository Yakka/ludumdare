package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import backend.MathHelper;

public class RenderTimer
{
   public static void draw(GameContainer gc, Graphics gfx, float seconds)
   {
       int hs = ((int)(MathHelper.frac(seconds) * 100.f) % 100);
       int s = ((int)seconds) % 60;
       int m = ((int)seconds) / 60;
       
       String mStr = "" + m;
       String sStr = s < 10 ? "0" + s : "" + s;        
       String hsStr = hs < 10 ? "0" + hs : "" + hs;
       
       if(seconds < 60)
       {
           if(seconds < 20)
               gfx.setColor(Color.red);
       }
       else
           gfx.setColor(Color.black);
       
//       gfx.setFont(Fonts.global48);
       
       String sep = " :  ";
       int sepw = gfx.getFont().getWidth(sep);
       int dw = gfx.getFont().getWidth("0");
       int w = dw * (mStr.length() + sStr.length() + hsStr.length()) + sepw;
       
       gfx.pushTransform();
       gfx.translate(
               460,
               10);
       
       gfx.drawString(mStr, 0, 0);        gfx.translate(dw * mStr.length(), 0);
       gfx.drawString(sep, 0, 0);        gfx.translate(sepw, 0);
       gfx.drawString(sStr, 0, 0);        gfx.translate(dw * sStr.length(), 0);
       gfx.drawString(sep, 0, 0);        gfx.translate(sepw, 0);
       gfx.drawString(hsStr, 0, 0);    gfx.translate(dw * hsStr.length(), 0);

       gfx.popTransform();
   }

}