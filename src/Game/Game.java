package Game;

import entities.enemies.Enemy;
import entities.players.Player;
import entities.players.Spritesheet;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Game extends Canvas implements Runnable, KeyListener {

    //screen dimensions
    public static int WIDTH = 640;
    public static int HEIGHT = 480;

    //Scale
    public static int SCALE = 3;

    //Entities
    public Player player;
    public List<Enemy> enemies = new ArrayList<>();
    public List<Enemy> deadEnemies = new ArrayList<>();

    //World
    public World world;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //sprite load
        new Spritesheet();

        //entities
        //player
        player = new Player(32,32);
        //enemies
        enemies.add(new Enemy(64,64));
        enemies.add(new Enemy(64,128));
        enemies.add(new Enemy(32,128));

        //world
        world = new World();
    }

    public void tick() {
        //entity tick
        player.tick();

        for (Enemy enemy : enemies) {
            enemy.tick();
            Enemy dead = player.enemyKilled(enemy);
            if (dead != null) {
                deadEnemies.add(dead);
            }
        }

        for (Enemy dead : deadEnemies) {
            enemies.remove(dead);
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //cleaner
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);

        //entities
        //player
        player.render(g);
        //enemies
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }

        //world
        world.render(g);

        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Zelda Clone");
        frame.pack();

        //controls
        frame.addKeyListener(game);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {

        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                player.right = true;
                break;
            case KeyEvent.VK_LEFT:
                player.left = true;
                break;
            case KeyEvent.VK_UP:
                player.up = true;
                break;
            case KeyEvent.VK_DOWN:
                player.down = true;
                break;
            case KeyEvent.VK_X:
                player.shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                player.right = false;
                break;
            case KeyEvent.VK_LEFT:
                player.left = false;
                break;
            case KeyEvent.VK_UP:
                player.up = false;
                break;
            case KeyEvent.VK_DOWN:
                player.down = false;
                break;
        }
    }
}