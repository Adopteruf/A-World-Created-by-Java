import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class BlockBlock {

    int Block_x;
    int Block_y;
    int cenX = Game.Width / 2;
    int cenY = 10;
    static final int Block_length = 10;

    public BlockBlock(int x, int y) {
        this.Block_x = x;
        this.Block_y = y;
    }
    
    public void Rote() {
        int temp_x = this.Block_x, temp_y = this.Block_y;
        Block_x = cenX - Block_length / 2 - (temp_y + Block_length / 2 - cenY);
        Block_y = cenY - Block_length / 2 + (temp_x + Block_length / 2 - cenX);
    }

    @Override
    public String toString() {
        return "(" + Block_x + " " + Block_y + ")";
    }
    
    public void drawBlock(Graphics2D g) {
        Color color = (Tetris.random == 0) ? new Color(78, 238, 148) : (Tetris.random == 1) ? new Color(134, 134, 78)
                : (Tetris.random == 2) ? new Color(205, 115, 115) : (Tetris.random == 3) ? new Color(205, 115, 115)
                                : (Tetris.random == 4) ? new Color(238, 59, 59) : (Tetris.random == 5) ? new Color(238, 59, 59)
                                                : new Color(225, 106, 106);
        Rectangle2D rect = new Rectangle2D.Double(Block_x, Block_y, Block_length, Block_length);
        g.setColor(color);
        g.fill(rect);
        g.setColor(Color.BLACK);
        g.draw(rect);
    }
    
    public void drawBlock_shadawn(Graphics2D g) {
        Rectangle2D rect = new Rectangle2D.Double(Block_x, Block_y, Block_length, Block_length);
        g.setColor(new Color(139, 126, 102));
        g.fill(rect);
    }
}