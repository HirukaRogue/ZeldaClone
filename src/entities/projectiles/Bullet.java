package entities.projectiles;

import entities.enemies.Enemy;
import entities.players.Player;
import world.Blocks;

import java.awt.*;

public class Bullet extends Rectangle {

    public int xdir;
    public int ydir;
    public int speed = 8;

    public int frames = 0;

    public Bullet(int x, int y, int xdir, int ydir) {
        super(x, y, 6, 6);
        this.xdir = xdir;
        this.ydir = ydir;
    }

    public void tick() {
        x+= speed*xdir;
        y+= speed*ydir;

        frames++;

        if (frames > 60) {
            Player.pendingRemove.add(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
    }

    public boolean hit(Enemy enemy) {
        return enemy.intersects(this);
    }

}
