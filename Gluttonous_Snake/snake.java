//Author: Clarence Guo
import java.awt.Color;
import java.awt.Graphics;
//use enumeration for set the direction.

enum Direction {
    LEFT, RIGHT, UP, DOWN
}

class Snake {

    //set a object for storing parts of snake.
    int Snake_x;
    int Snake_y;

    Snake(int Snake_x, int Snake_y, Direction direction) {
        this.Snake_x = Snake_x;
        this.Snake_y = Snake_y;
    }
}

class Gift {

    //set a object for adding the food for snake.
    int x;
    int y;

    public Gift(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawGifrt(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y, 10, 10);
    }
}