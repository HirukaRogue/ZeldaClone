package world;

import entities.players.Spritesheet;

import java.awt.*;

public class Blocks extends Rectangle {

    public Blocks(int x, int y){
        super(x, y, 32, 32);
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.tile_wall, x, y, 32, 32, null);
    }
}
