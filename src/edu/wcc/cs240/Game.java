package edu.wcc.cs240;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 2048 Game
 * main method, GUI, and key interactions
 * @author Ting Gao, Cancan Huang, Jialei Lyu, Jacqueline Tan, Yushi Yao (alphabetically order of last name)
 * @version 0.1
 * 00:15 Feb/18/2022 PST
 */
public class Game extends JPanel implements KeyListener
{
    static Game game;
    static JFrame frame;
    static Grid grid;
    static int gridSize = 4;
    static int windowHeight;
    static int windowWidth;

    public static void main(String[] args)
    {
        setup();
    }

    /**
     * set up GUI based on the given grid size
     */
    public static void setup()
    {
        game = new Game();
        frame = new JFrame("2048 Game");
        grid = new Grid(gridSize);
//        grid.tileArray[0][0] = 2;

        //leave space between window border and grid border
        windowWidth = 50 * gridSize + 400;
        windowHeight = 50 * gridSize + 200;

        frame.addKeyListener(game);
        frame.getContentPane().add(game);
        frame.setSize(windowWidth, windowHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        addRestartButton();
        addDestroyButton();

//        JTextField textField = new JTextField("" + gridSize);
//        textField.setBounds(25,140,100,30);
//        frame.add(textField);
    }

    public static void addRestartButton()
    {
        JButton buttonRestart = new JButton("Restart");
        buttonRestart.setBounds(25,100,100,30);
        buttonRestart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String resultS = (String)JOptionPane.showInputDialog
                (
                    frame,
                    "Input the size of grid",
                    "Restart",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null, "4"
                );

                frame.dispose();

                gridSize = Integer.parseInt(resultS);
                setup();
            }
        });
        frame.add(buttonRestart);
    }

    public static void addDestroyButton()
    {
        JButton buttonDestroy = new JButton("Destroy a tile");
        buttonDestroy.setBounds(25,140,100,30);
        buttonDestroy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String resultS = (String)JOptionPane.showInputDialog
                        (
                                frame,
                                "Input the x coordinate of tile",
                                "Destroy a tile",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                ""
                        );

                buttonDestroy.setVisible(false);
                frame.repaint();
            }
        });
        frame.add(buttonDestroy);
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
                drawTile(g, grid.tileArray[i][j], j * 60 + (windowWidth - gridLength) / 2 + 10,
                        i * 60 + (windowHeight - gridLength) / 2 + 10);
            }
        }
    }

    /**
     * draw a tile with corresponding color and value at designated position
     * @param g     the graphics tool
     * @param value     the value of tile
     * @param x     x coordinate to draw at
     * @param y     y coordinate to draw at
     */
    public void drawTile(Graphics g, int value, int x, int y)
    {
        Graphics2D g2 = (Graphics2D)g;

        if (value > 0)
        {
//            g2.setColor(tile.getColor());
            g2.setColor(Color.cyan);
            g2.fillRect(x, y, 50, 50);
            g2.setColor(Color.black);
            g.drawString("" + value, x + 20 - Integer.toString(value).length() * 2, y + 30);
        }
        else
        {
            g2.setColor(Color.lightGray);
            g2.fillRect(x, y, 50, 50);
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