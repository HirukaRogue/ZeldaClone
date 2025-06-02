package Entities.Players;

import World.World;

import java.awt.*;

public class Player extends Rectangle {

    //directions
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;

    //player speed
    public int spd = 4;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        if (right && World.isFree(x+spd, y)) {
            x+=spd;
        }
        if (left && World.isFree(x-spd, y)) {
            x-=spd;
        }

        if (up && World.isFree(x, y-spd)) {
            y-=spd;
        }
        if (down && World.isFree(x, y+spd)) {
            y+=spd;
        }

    }

    public void render(Graphics g) {
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);

        g.drawImage(Spritesheet.player_front, x, y, 31, 32, null);
    }

}
