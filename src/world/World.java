package world;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Blocks> blocks = new ArrayList<Blocks>();

    public World() {
        for (int xa = 0; xa < 19; xa++) {
            blocks.add(new Blocks(xa*32, 0));
        }
        for (int xa = 0; xa < 19; xa++) {
            blocks.add(new Blocks(xa*32, 480-32));
        }

        for (int ya = 0; ya < 15; ya++) {
            blocks.add(new Blocks(0, ya*32));
        }
        for (int ya = 0; ya < 15; ya++) {
            blocks.add(new Blocks(640-32, ya*32));
        }

        blocks.add(new Blocks(170, 200));
        blocks.add(new Blocks(170, 400));
        blocks.add(new Blocks(80, 250));
    }

    public static boolean isFree(int x, int y) {
        for (Blocks block : blocks) {
            if (block.intersects(new Rectangle(x, y, 32, 32))){
                return false;
            }
        }

        return true;
    }

    public void render(Graphics g) {
        for (Blocks block : blocks) {
            block.render(g);
        }
    }

}
