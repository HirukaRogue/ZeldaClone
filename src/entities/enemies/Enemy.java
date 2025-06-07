package entities.enemies;

import Game.Game;
import entities.players.Player;
import entities.players.Spritesheet;
import entities.projectiles.Bullet;
import world.Blocks;
import world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle {

    //directions
    public int aniDirection = 2;

    //player speed
    public int spd = 2;

    //animation setup
    public int curAnimation = 0;
    public int curFrames = 0;
    public int targetFrames = 15;

    //bullets
    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public static List<Bullet> pendingRemove = new ArrayList<>();

    //shoot action
    public boolean shoot = false;

    public Enemy(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        this.chasePlayer();

        curFrames++;
        if (curFrames == targetFrames) {
            curAnimation++;
            curFrames = 0;
            if (curAnimation > Spritesheet.player_front.length - 1) {
                curAnimation = 0;
            }
        }

        if (new Random().nextInt(100) > 98) {
            shoot = true;
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

    public boolean gameover(Player player) {
        for (Bullet bullet : bullets) {
            if (bullet.hit(player)) {
                return true;
            }
        }

        return false;
    }

    public void chasePlayer(){
        Player p = Game.player;

        if (p != null) {
            if (x < p.x && World.isFree(x + spd, y)) {
                if (new Random().nextInt(100) > 50) {
                    x += spd;
                    aniDirection = 2;
                }
            }
            if (x > p.x && World.isFree(x - spd, y)) {
                if (new Random().nextInt(100) > 50) {
                    x -= spd;
                    aniDirection = 4;
                }
            }
            if (y < p.y && World.isFree(x, y + spd)) {
                if (new Random().nextInt(100) > 50) {
                    y += spd;
                    aniDirection = 3;
                }
            }
            if (y > p.y && World.isFree(x, y - spd)) {
                if (new Random().nextInt(100) > 50) {
                    y -= spd;
                    aniDirection = 1;
                }
            }
        }
    }

    public void render(Graphics g) {
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);

        switch (aniDirection) {
            case 1:
                g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 31, 32, null);
                break;
            case 2:
                g.drawImage(Spritesheet.enemy_side[curAnimation], x, y, 31, 32, null);
                break;
            case 3:
                g.drawImage(Spritesheet.enemy_back[curAnimation], x, y, 31, 32, null);
                break;
            case 4:
                g.drawImage(Spritesheet.enemy_side[curAnimation], x + 31, y, -31, 32, null);
                break;
        }

        for (Bullet bullet : bullets) {
            bullet.render(g);
        }

    }

}
