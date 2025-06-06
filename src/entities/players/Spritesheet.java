package entities.players;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Spritesheet {
    public static BufferedImage spritesheet;

    //entities
    //player
    public static BufferedImage[] player_front;
    public static BufferedImage[] player_side;
    public static BufferedImage[] player_back;
    //enemy
    public static BufferedImage[] enemy_front;
    public static BufferedImage[] enemy_side;
    public static BufferedImage[] enemy_back;

    //tiles
    public static BufferedImage tile_wall;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResource("/spritesheet.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //entities
        //player
        player_front = new BufferedImage[2];
        player_front[0] = Spritesheet.getSprite(1,11,15,16);
        player_front[1] = Spritesheet.getSprite(17,11,15,16);

        player_side = new BufferedImage[2];
        player_side[0] = Spritesheet.getSprite(34,11,15,16);
        player_side[1] = Spritesheet.getSprite(50,11,15,16);

        player_back = new BufferedImage[2];
        player_back[0] = Spritesheet.getSprite(68,11,15,16);
        player_back[1] = Spritesheet.getSprite(86,11,15,16);

        //enemy
        enemy_front = new BufferedImage[2];
        enemy_front[0] = Spritesheet.getSprite(271,294,15,16);
        enemy_front[1] = Spritesheet.getSprite(287,294,15,16);

        enemy_side = new BufferedImage[2];
        enemy_side[0] = Spritesheet.getSprite(304,294,15,16);
        enemy_side[1] = Spritesheet.getSprite(320,294,15,16);

        enemy_back = new BufferedImage[2];
        enemy_back[0] = Spritesheet.getSprite(338,294,15,16);
        enemy_back[1] = Spritesheet.getSprite(356,294,15,16);

        //tiles
        tile_wall = Spritesheet.getSprite(267, 241, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int witdth, int height) {
        return spritesheet.getSubimage(x, y, witdth, height);
    }

}
