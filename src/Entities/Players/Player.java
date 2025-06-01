package Entities.Players;

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
        if (right) {
            x+=spd;
        }
        if (left) {
            x-=spd;
        }

        if (up) {
            y-=spd;
        }
        if (down) {
            y+=spd;
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

}
