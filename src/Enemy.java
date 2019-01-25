
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy {

    public double x, y;
    public int width, height;
    
    BufferedImage inimigo;
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
        inimigo = Game.folha.getSprite(8, 6, width, height);//recortando a imagem d afolha
    }

    public void tick() {
        x += (Game.ball.x - x - 6) * 0.09;//tira 60% do total ele fica pelo caminho

    }

    public void render(Graphics g) {
        g.drawImage(inimigo,(int) x,(int) y, null);
        
    }

}
