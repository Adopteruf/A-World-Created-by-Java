//Author: Clarence Guo
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SnakeList {

    //create a object for storing the snake and its direction.
    public SnakeList(ArrayList<Snake> list, Direction DIRECTION) {
        this.list = list;
        this.DIRECTION = DIRECTION;
    }

    //control the movement of snake.
    public void move() {
        switch (this.DIRECTION) {
            case LEFT:
                for (int MarkSign = list.size() - 1; MarkSign > 0; MarkSign--) {
                    list.get(MarkSign).Snake_x = list.get(MarkSign - 1).Snake_x;
                    list.get(MarkSign).Snake_y = list.get(MarkSign - 1).Snake_y;
                }
                list.get(0).Snake_x = list.get(0).Snake_x - 10;
                break;
            case RIGHT:
                for (int MarkSign = list.size() - 1; MarkSign > 0; MarkSign--) {
                    list.get(MarkSign).Snake_x = list.get(MarkSign - 1).Snake_x;
                    list.get(MarkSign).Snake_y = list.get(MarkSign - 1).Snake_y;
                }
                list.get(0).Snake_x = list.get(0).Snake_x + 10;
                break;
            case UP:
                for (int MarkSign = list.size() - 1; MarkSign > 0; MarkSign--) {
                    list.get(MarkSign).Snake_x = list.get(MarkSign - 1).Snake_x;
                    list.get(MarkSign).Snake_y = list.get(MarkSign - 1).Snake_y;
                }
                list.get(0).Snake_y = list.get(0).Snake_y - 10;
                break;
            case DOWN:
                for (int MarkSign = list.size() - 1; MarkSign > 0; MarkSign--) {
                    list.get(MarkSign).Snake_x = list.get(MarkSign - 1).Snake_x;
                    list.get(MarkSign).Snake_y = list.get(MarkSign - 1).Snake_y;
                }
                list.get(0).Snake_y = list.get(0).Snake_y + 10;
                break;
            default:
                break;
        }
    }

    //draw the snake to the panel.
    public void drawSnake(Graphics g) {
        for (int u = 0; u < list.size(); u++) {
            Color color = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
            g.setColor(color);
            g.fillRect(list.get(u).Snake_x, list.get(u).Snake_y, 10, 10);
        }
    }

    public ArrayList<Snake> list;
    public Direction DIRECTION;
}