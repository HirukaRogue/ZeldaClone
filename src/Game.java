import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Game extends Canvas implements Runnable {

    //screen dimensions
    public static int WIDTH = 480;
    public static int HEIGHT = 480;



    public Game() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Zelda Clone");
        frame.setLocationRelativeTo(null);
        frame.pack();
    }

    @Override
    public void run() {

    }
}