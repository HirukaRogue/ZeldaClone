package Entities.Players;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {
    public static BufferedImage spritesheet;

    public static BufferedImage player_front;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        player_front = Spritesheet.getSprite(1,11, 15, 16);
    }

    public static BufferedImage getSprite(int x, int y, int witdth, int height) {
        return spritesheet.getSubimage(x, y, witdth, height);
    }

}
