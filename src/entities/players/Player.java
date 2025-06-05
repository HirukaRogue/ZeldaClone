package entities.players;

import entities.enemies.Enemy;
import entities.projectiles.Bullet;
import world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {

    //directions
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public int aniDirection = 2;

    //player speed
    public int spd = 4;

    //animation setup
    public int curAnimation = 0;
    public int curFrames = 0;
    public int targetFrames = 15;

    //bullets
    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public static List<Bullet> pendingRemove = new ArrayList<>();

    //shoot action
    public boolean shoot = false;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        boolean moving = false;
        if (right && World.isFree(x+spd, y)) {
            x+=spd;
            moving = true;
            aniDirection = 2;
        }
        if (left && World.isFree(x-spd, y)) {
            x-=spd;
            moving = true;
            aniDirection = 4;
        }

        if (up && World.isFree(x, y-spd)) {
            y-=spd;
            moving = true;
            aniDirection = 3;
        }
        if (down && World.isFree(x, y+spd)) {
            y+=spd;
            moving = true;
            aniDirection = 1;
        }

        if (moving) {
            curFrames++;
            if (curFrames == targetFrames) {
                curAnimation++;
                curFrames = 0;
                if (curAnimation > Spritesheet.player_front.length - 1) {
                    curAnimation = 0;
                }
            }
        }

        if (shoot) {
            shoot = false;
            switch (aniDirection) {
                case 2:
                    bullets.add(new Bullet(x+width, y+(width/2), 1,0));
                    break;
                case 4:
                    bullets.add(new Bullet(x, y+(width/2), -1,0));
                    break;
                case 1:
                    bullets.add(new Bullet(x+(height/2), y+height, 0,1));
                    break;
                case 3:
                    bullets.add(new Bullet(x+(height/2), y, 0,-1));
                    break;
            }
        }

        for (Bullet bullet : bullets) {
            bullet.tick();
        }

        for (Bullet bullet : pendingRemove) {
            bullets.remove(bullet);
        }

        pendingRemove.clear();
    }

    public Enemy enemyKilled(Enemy enemy) {
        for (Bullet bullet : bullets) {
            if (bullet.hit(enemy)) {
                return enemy;
            }
        }

        return null;
    }

    public void render(Graphics g) {
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);

        switch (aniDirection) {
            case 1:
                g.drawImage(Spritesheet.player_front[curAnimation], x, y, 31, 32, null);
                break;
            case 2:
                g.drawImage(Spritesheet.player_side[curAnimation], x, y, 31, 32, null);
                break;
            case 3:
                g.drawImage(Spritesheet.player_back[curAnimation], x, y, 31, 32, null);
                break;
            case 4:
                g.drawImage(Spritesheet.player_side[curAnimation], x + 31, y, -31, 32, null);
                break;
        }

        for (Bullet bullet : bullets) {
            bullet.render(g);
        }

    }

}
