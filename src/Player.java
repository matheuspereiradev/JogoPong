import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import res.FolhadeImg;

public class Player {

	public boolean left, right;
	public int x,y,width,height;
	
        BufferedImage jog ;
        
	public Player(int x, int y) {
		this.x=x;
		this.y=y;
		this.width=40;
		this.height = 5;
                jog = Game.folha.getSprite(8, 0, width, height);
	}
	
	public void tick() {
		if(left) {
			x--;
		}else if (right) {
			x++;
		}
		
		if(x+width > Game.WIDTH ) {
			x = Game.WIDTH - width;
		}if(x< 0 ) {
			x = 0;
		}
	}
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
                g.drawImage(jog, x, y, null);
	}

}
