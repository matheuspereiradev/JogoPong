
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import res.FolhadeImg;

public class Game extends Canvas implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    public static int WIDTH = 130;
    public static int HEIGHT = 170;
    public static int SCALE = 3;
    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public static int pontJog;
    public static int pontAdv;
    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB),fundo,linha;//onde vai renderizar
    public static FolhadeImg folha;
    
    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);
        folha = new FolhadeImg("../res/sprites.png");
        player = new Player(WIDTH / 2, HEIGHT - 5);//subtrai 5 pq é a altura ai renderiza de cima
        enemy = new Enemy(WIDTH / 2, 0);
        ball = new Ball(WIDTH / 2, HEIGHT / 2 - 1);
        
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);// componente canvas
        frame.pack();
        frame.setLocationRelativeTo(null);// tem q ser dps do add pq ele tem q calcular dnv as domensoes
        frame.setVisible(true);

        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        fundo = folha.getSprite(48, 0, 16, 16);//corta uma imagem aq é como se tivesse uma imagem
        for (int x = 0; x < WIDTH; x+=16) {
            for (int y = 0; y < HEIGHT; y+=16) {
                g.drawImage(fundo, x, y, null);
            }
        }
        linha = folha.getSprite(0, 8, 1, 4);
        for (int x = 0; x < WIDTH; x++) {
                g.drawImage(linha, x, HEIGHT/2, null);
            
        }
        player.render(g);
        enemy.render(g);
        ball.render(g);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Pontos: "+pontAdv,20, 20);
        g.drawString("Pontos: "+pontJog, 20, HEIGHT-20);
        
        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();//tudo foi renderizado mas sem isso não exibe e isso nao deve ser renderizdo sem um buffreerd image layer
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();
            try {

                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
