package res;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FolhadeImg {
	
	private BufferedImage folha;
	
	public FolhadeImg(String local) {
		try {
		folha = ImageIO.read(getClass().getResource(local));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return folha.getSubimage(x, y, width, height);
	}

}
