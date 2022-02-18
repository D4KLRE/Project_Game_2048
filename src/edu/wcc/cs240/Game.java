package edu.wcc.cs240;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * 2048 Game
 * main method, GUI, and key interactions
 * @author Ting Gao, Cancan Huang, Jialei Lyu, Jacqueline Tan, Yushi Yao (alphabetically order of last name)
 * @version 0.1
 * 00:15 Feb/18/2022 PST
 */
public class Game extends JPanel implements KeyListener
{
    static Game game = new Game();
    static JFrame frame = new JFrame("2048 Game");
    static int gridSize = 4;
    static int windowHeight;
    static int windowWidth;

    public static void main(String[] args)
    {
        setUpGUI();
    }

    /**
     * set up GUI based on the given grid size
     */
    public static void setUpGUI()
    {
        //leave space between window border and grid border
        windowWidth = 50 * gridSize + 400;
        windowHeight = 50 * gridSize + 200;

        frame.addKeyListener(game);
        frame.getContentPane().add(game);
        frame.setSize(windowWidth, windowHeight);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * paint grid, tiles, descriptions, and buttons
     * @param g     the graphics tool
     */
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        //leave space between tiles
        int gridLength = 60 * gridSize + 10;
        g2.setColor(Color.gray);
        g2.fillRect((windowWidth - gridLength) / 2, (windowHeight - gridLength) / 2, gridLength, gridLength);

        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                drawTile(g, j * 60 + (windowWidth - gridLength) / 2 + 10,
                        i * 60 + (windowHeight - gridLength) / 2 + 10);
            }
        }
    }

    /**
     * draw a tile with corresponding color and value at designated position
     * @param g     the graphics tool
//     * @param tile     the tile to draw
     * @param x     x coordinate to draw at
     * @param y     y coordinate to draw at
     */
    //second param Tile tile
    public void drawTile(Graphics g, int x, int y)
    {
//        int tileValue = tile.getValue();
        int tileValue = 0;

        Graphics2D g2 = (Graphics2D)g;

        if (tileValue > 0)
        {
//            g2.setColor(tile.getColor());
            g2.setColor(Color.cyan);
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black);
            g.drawString("" + tileValue, x + 25, y + 25);
        }
        else
        {
            g2.setColor(Color.lightGray);
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black);
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}