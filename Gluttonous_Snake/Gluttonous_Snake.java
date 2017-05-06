//Author: Clarence Guo
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Gluttonous_Snake extends JPanel implements Runnable, KeyListener {

    String string;
    Gift gift = new Gift(50, 60);
    SnakeList SL;
    Graphics GUB;
    Image bg;

    //create the game_thread and add the kb_listener.
    public Gluttonous_Snake() {
        Thread snake_game = new Thread(this);
        snake_game.start();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            };
            if (SL == null) {
                SL = new SnakeList(initial_list(), Direction.RIGHT);
            }
            if (SL.list.get(0).Snake_x + 5 == gift.x + 5 && SL.list.get(0).Snake_y + 5 == gift.y + 5) {
                gift = new Gift((int) (Math.random() * 50) * 10, (int) (Math.random() * 30) * 10);
                SL.list.add(new Snake(0, 0, SL.DIRECTION));
            }
            if (!isLive()) {
                Graphics gub = this.getGraphics();
                gub.setColor(Color.red);
                gub.setFont(new Font("Tahoma", Font.BOLD, 30));
                gub.drawString(string, this.getWidth() / 2 - 150, this.getHeight() / 2);
                break;
            }
            SL.move();
            repaint();
        }
    }

    //Judge the live of snake.
    public boolean isLive() {
        boolean result = true;
        for (int r = 0; r < SL.list.size(); r++) {
            if (SL.list.get(r).Snake_x < 0 || SL.list.get(r).Snake_y < 0 || SL.list.get(r).Snake_x + 10 > this.getWidth()
                    || SL.list.get(r).Snake_y + 10 > this.getHeight()) {
                string = "oh!!!!!! My head!!!!!!!!";
                result = false;
            }
            for (int l = r + 1; l < SL.list.size(); l++) {
                if (SL.list.get(r).Snake_x + 5 == SL.list.get(l).Snake_x + 5 && SL.list.get(r).Snake_y + 5 == SL.list.get(l).Snake_y + 5) {
                    string = "oh!!!! I should eat my body!!!!!!!!!";
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //add the keyListener.
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (SL.DIRECTION == Direction.RIGHT) {
                    break;
                }
                SL.DIRECTION = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if (SL.DIRECTION == Direction.LEFT) {
                    break;
                }
                SL.DIRECTION = Direction.RIGHT;
                break;
            case KeyEvent.VK_UP:
                if (SL.DIRECTION == Direction.DOWN) {
                    break;
                }
                SL.DIRECTION = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                if (SL.DIRECTION == Direction.UP) {
                    break;
                }
                SL.DIRECTION = Direction.DOWN;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e01) {
    }

    @Override
    public void keyReleased(KeyEvent e02) {
    }

    //use two buffer way for creating the move of each object.
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (bg == null) {
            bg = this.createImage(this.getWidth(), this.getHeight());
            GUB = bg.getGraphics();
        }
        GUB.setColor(getBackground());
        GUB.fillRect(0, 0, this.getWidth(), this.getHeight());
        SL.drawSnake(GUB);
        gift.drawGifrt(GUB);
        g.drawImage(bg, 0, 0, this);
    }

    public ArrayList initial_list() {
        ArrayList<Snake> list = new ArrayList();
        for (int m = 2; m >= 0; m--) {
            list.add(new Snake(m * 10, 0, Direction.RIGHT));
        }
        return list;
    }
}