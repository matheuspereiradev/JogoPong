import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ball {

	public double x, y;
	public int width, height;

	public double dx, dy;
	public double speed = 1.8;
        
        BufferedImage bola;
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 6;
		this.height = 6;

		int angulo = new Random().nextInt(120-45)+46;//gerar um angulo aleatorio entre 120 e 45 somado a 45 fica entre 120 e 45
		dx = Math.cos(Math.toRadians(angulo));
		dy = Math.sin(Math.toRadians(angulo));
                bola = Game.folha.getSprite(0, 0, width, height);
	}

	public void tick() {
		// colisao com a parede
		if (x + (dx * speed) >= Game.WIDTH) {
			dx*=-1;//muda o sinal prtanto a direçao
		}else if(x + (dx*speed)<0) {
			dx*=-1;
		}
		//pontos
		if(y>= Game.HEIGHT) {
			//ponto do inimigo
			Game.pontAdv ++;
			new Game();
		}else if(y < 0) {
                        
			Game.pontJog ++;
			new Game();
			//ponto do jogador
		}
		//colisão com o jogador
		Rectangle bola = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
		
		Rectangle jogador = new Rectangle(Game.player.x, Game.player.y,Game.player.width, Game.player.height);
		Rectangle inimigo = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y,Game.enemy.width, Game.enemy.height);//tem qu econverter para int pq la é double o x e oy
		
		if(bola.intersects(jogador)) {
			int angulo = new Random().nextInt(120-45)+46;//gerar um angulo aleatorio entre 120 e 45 somado a 45 fica entre 120 e 45
			dx = Math.cos(Math.toRadians(angulo));
			dy = Math.sin(Math.toRadians(angulo));
			if(dy>0) {
			dy*=-1;}
		}else if(bola.intersects(inimigo)) {
			int angulo = new Random().nextInt(120-45)+46;//gerar um angulo aleatorio entre 120 e 45 somado a 45 fica entre 120 e 45
			dx = Math.cos(Math.toRadians(angulo));
			dy = Math.sin(Math.toRadians(angulo));
			if(dy<0) {
			dy*=-1;}
		}
		
		// andando a bola
		x += dx * speed;
		y += dy * speed;

	}

	public void render(Graphics g) {
		g.drawImage(bola, (int)x,(int) y, null);
	}

}