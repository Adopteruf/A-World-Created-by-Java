import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import javax.swing.JPanel;

public class Tetris extends JPanel implements Runnable, KeyListener {

    static final int speed = 10;
    static int random;
    static ArrayList<BlockBlock> block_temp = new ArrayList();
    ArrayList<BlockBlock> block_shadown = new ArrayList();
    //here re_write compare() method for make set have a way to sort the object inside.
    TreeSet<BlockBlock> block_set = new TreeSet(new Comparator() {
        @Override
        public int compare(Object p1, Object p2) {
            BlockBlock b01 = (BlockBlock) p1;
            BlockBlock b02 = (BlockBlock) p2;
            if (b01.Block_y > b02.Block_y) {
                return 1;
            }
            if (b01.Block_y == b02.Block_y) {
                return (b01.Block_x > b02.Block_x) ? 1 : (b01.Block_x < b02.Block_x) ? -1 : 0;
            }
            return -1;
        }
    });
    ArrayList<BlockBlock> current_blocks = Setlist();
    //buffer_draw
    Image Gub;
    Graphics2D g2D;
    Graphics g_p;

    public Tetris() {
        Thread block_game = new Thread(this);
        this.setBounds(0, 0, Game.Width, Game.Height);
        this.setFocusable(true);
        this.addKeyListener(this);
        block_game.start();
    }

    //start the game.
    @Override
    public void run() {
        Loop01:
        while (true) {
            for (BlockBlock temp : block_set) {
                if (temp.Block_y <= BlockBlock.Block_length) {
                    break Loop01;
                }
            }
            //make the block move speed per pause_time.
            current_blocks.stream().map((temp) -> {
                temp.Block_y += speed;
                return temp;
            }).forEach((temp) -> {
                temp.cenY += speed;
            });
            //judge whether the block reach the end.
            if (!isBottom()) {
                current_blocks.stream().forEach((temp) -> {
                    block_set.add(temp);
                });
                current_blocks = Setlist();
            }
            //remove the blocks
            remove_block();
            createShadown();
            //thread sleep_pause time.
            try {
                Thread.sleep(90);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //paint all of the item.
            repaint();
        }
    }

  //give a random type of the control_block.
    public ArrayList Setlist() {
        block_shadown.clear();
        ArrayList<BlockBlock> list = new ArrayList();
        while (true) {
            if ((random = (int) (7 * Math.random())) < 7) {
                break;
            }
        }
        switch (random) {
            case 0:
                //square
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, 0));
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2, BlockBlock.Block_length));
                break;
            case 1:
                //Line 
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length * 2, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, 0));
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2 + BlockBlock.Block_length, 0));
                break;
            case 2:
                //Z_Left 
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2 + BlockBlock.Block_length, 0));
                break;
            case 3:
                //Z_Right 
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length * 2, BlockBlock.Block_length));
                break;
            case 4:
                //L_Left
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, 0));
                list.add(new BlockBlock(Game.Width / 2, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2, BlockBlock.Block_length * 2));
                break;
            case 5:
                //L_Right 
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, 0));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, BlockBlock.Block_length * 2));
                break;
            case 6:
                //iIi.
                list.add(new BlockBlock(Game.Width / 2, 0));
                list.add(new BlockBlock(Game.Width / 2, BlockBlock.Block_length));
                list.add(new BlockBlock(Game.Width / 2 - BlockBlock.Block_length, 0));
                list.add(new BlockBlock(Game.Width / 2 + BlockBlock.Block_length, 0));
                break;
            default:
                break;
        }
        return list;
    }
    
    public void createShadown() {
        block_shadown.clear();
        int longest = Math.abs((Game.Height - 60 - BlockBlock.Block_length) - Lowest().Block_y);
        int incr = 0;
        loop_s01:
        while (incr < longest) {
            incr += BlockBlock.Block_length;
            for (BlockBlock temp_s01 : current_blocks) {
                block_shadown.add(new BlockBlock(temp_s01.Block_x, temp_s01.Block_y + incr));
            }
            loop_s02:
            for (BlockBlock temp_s02 : block_shadown) {
                for (BlockBlock temp_s03 : block_set) {
                    if (temp_s02.Block_x == temp_s03.Block_x && temp_s02.Block_y + BlockBlock.Block_length == temp_s03.Block_y || incr == longest) {
                        break loop_s01;
                    }
                }
            }
            block_shadown.clear();
        }
        if (block_shadown.isEmpty()) {
            int len = (Game.Height - 60 - BlockBlock.Block_length) - Lowest().Block_y;
            for (BlockBlock temp_s01 : current_blocks) {
                block_shadown.add(new BlockBlock(temp_s01.Block_x, len + temp_s01.Block_y));
            }
        }
    }

    //remove the blocks in the sameline(when the nummber reach a certain number).
    public void remove_block() {
        block_temp.clear();
        BlockBlock temp = null;
        for (BlockBlock Temp : block_set) {
            temp = (temp == null) ? Temp : temp;
            if (temp.Block_y != Temp.Block_y) {
                block_temp.clear();
                temp = Temp;
            }
            if (temp.Block_y == Temp.Block_y) {
                block_temp.add(Temp);
                if (block_temp.size() == 22) {
                    block_set.removeAll(block_temp);
                    for (BlockBlock TEMP : block_set) {
                        TEMP.Block_y += (TEMP.Block_y < block_temp.get(0).Block_y) ? BlockBlock.Block_length : 0;
                    }
                    break;
                }
            }
        }
    }

    //find the control_block's lowest part.
    public BlockBlock Lowest() {
        BlockBlock temp = null;
        for (BlockBlock Temp : current_blocks) {
            temp = (temp == null) ? Temp : temp;
            temp = (temp.Block_y < Temp.Block_y) ? Temp : temp;
        }
        return temp;
    }

    
    
    
    
    
    //judge whether the blocks reach the end.
    public boolean isBottom() {
        for (BlockBlock temp : current_blocks) {
            if (temp.Block_y + BlockBlock.Block_length >= Game.Height - 60) {
                return false;
            }
            for (BlockBlock Temp : block_set) {
                if (temp.Block_x == Temp.Block_x && temp.Block_y + BlockBlock.Block_length >= Temp.Block_y) {
                    return false;
                }
            }
        }
        return true;
    }

    //control the block inside the window.
    public boolean isInside_Left() {
        return current_blocks.stream().noneMatch((temp) -> (temp.Block_x <= 0));
    }

    public boolean isInside_Right() {
        return current_blocks.stream().noneMatch((temp) -> (temp.Block_x + BlockBlock.Block_length >= 220));
    }

    
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Gub = (Gub == null) ? this.createImage(this.getWidth(), this.getHeight()) : Gub;
        g_p = (g_p == null) ? Gub.getGraphics() : g_p;
        g2D = (g2D == null) ? (Graphics2D) g_p : g2D;
        g2D.setColor(this.getBackground());
        g2D.fillRect(0, 0, getWidth(), getHeight());
        //draw the table of block.
        g2D.setColor(Color.GRAY);
        for (int i = 0; i < 23; i++) {
            g2D.drawLine(i * BlockBlock.Block_length, 0, i * BlockBlock.Block_length, Game.Height - 60);
        }
        for (int n = 0; n < 61; n++) {
            g2D.drawLine(0, n * BlockBlock.Block_length, 220, n * BlockBlock.Block_length);
        }
        current_blocks.stream().forEach((temp01) -> {
            temp01.drawBlock(g2D);
        });
        try {
            block_set.stream().forEach((temp02) -> {
                temp02.drawBlock(g2D);
            });
        } catch (Exception e_set) {
        }
        try {
            block_shadown.stream().forEach((Temp) -> {
                Temp.drawBlock_shadawn(g2D);
            });
        } catch (Exception e_shadown) {
        }
        g.drawImage(Gub, 0, 0, this);
    }

    
    //when player press "Enter" make the block reach the bottom.
    public void done() {
        if (!block_shadown.isEmpty()) {
            current_blocks.clear();
            current_blocks.addAll(block_shadown);
            for (BlockBlock temp : current_blocks) {
                temp.Block_y -= BlockBlock.Block_length;
            }
        } else {
            int len = (Game.Height - 60 - BlockBlock.Block_length) - Lowest().Block_y;
            for (BlockBlock temp : current_blocks) {
                temp.Block_y += len - BlockBlock.Block_length;
            }
            System.out.print(current_blocks);
        }
    }
    
    //some keyListener for control.
    @Override
    public void keyPressed(KeyEvent e01) {
        switch (e01.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                block_shadown.clear();
                if (isInside_Left()) {
                    current_blocks.stream().map((temp) -> {
                        temp.Block_x -= BlockBlock.Block_length;
                        return temp;
                    }).forEach((temp) -> {
                        temp.cenX -= BlockBlock.Block_length;
                    });
                }
                break;
            case KeyEvent.VK_RIGHT:
                block_shadown.clear();
                if (isInside_Right()) {
                    current_blocks.stream().map((Temp) -> {
                        Temp.Block_x += BlockBlock.Block_length;
                        return Temp;
                    }).forEach((Temp) -> {
                        Temp.cenX += BlockBlock.Block_length;
                    });
                }
                break;
            case KeyEvent.VK_SPACE:
                block_shadown.clear();
                current_blocks.stream().forEach((temP) -> {
                    temP.Rote();
                });
                break;
            case KeyEvent.VK_ENTER:
                done();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e01) {
    }

    @Override
    public void keyReleased(KeyEvent e01) {
    }
}