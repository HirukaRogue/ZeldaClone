package Entities.Players;

import World.World;

import java.awt.*;

public class Player extends Rectangle {

    //directions
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public int aniDirection = 2;

    //animation setup
    public int curAnimation = 0;
    public int curFrames = 0;
    public int targetFrames = 15;

    //player speed
    public int spd = 4;

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

    }

}
