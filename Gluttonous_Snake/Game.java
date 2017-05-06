//Author: Clarence Guo
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        //build the size of panel.
        this.setContentPane(new Gluttonous_Snake());
        this.setBounds(500, 60, 600, 400);
        this.setTitle("My SnakeGame");
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        //start the game.
        Game game = new Game();
        //set the close button for ending the running.
        game.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}