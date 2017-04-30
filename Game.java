import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame {

    static int Width = 300;
    static int Height = 660;

    public Game() {
        Container RUN_GAME = new Tetris();
        this.setContentPane(RUN_GAME);
        this.setBounds(500, 20, Width, Height);
        this.setTitle("My Tetris");
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}