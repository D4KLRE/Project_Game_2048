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
 * @version 0.4
 * added different color for different values of tile and spawn method
 * 21:46 Feb/18/2022 PST
 */
public class Game extends JPanel implements KeyListener
{
    static Game game;
    static JFrame frame;
    static Grid grid;
    /** \　　/
     * 【 ﾟ∀ﾟ】< Change the initial grid size here
     */
    static int gridSize = 4;
    static int windowHeight;
    static int windowWidth;

    public static void main(String[] args)
    {
        setup();
    }

    /**
     * set up grid based on the given grid size, tiles, and buttons
     */
    public static void setup()
    {
        game = new Game();
        frame = new JFrame("2048 Game");
        grid = new Grid(gridSize);
        /** \　　/
         * 【 ﾟ∀ﾟ】< Test by manually changing tile value here, the line below is an example
         * first zero(x coordinate) stands for the leftmost column,
         * second zero(y coordinate) stands for the uppermost line
         */
        //grid.tileArray[3][3] = 2;

        //leave space between window border and grid border
        windowWidth = 50 * gridSize + 500;
        windowHeight = 50 * gridSize + 200;

        frame.addKeyListener(game);
        frame.getContentPane().add(game);
        frame.setSize(windowWidth, windowHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        addRestartButton();
        addDestroyButton();
        addDoubleButton();

        grid.spawn();

//        JTextField textField = new JTextField("" + gridSize);
//        textField.setBounds(25,140,100,30);
//        frame.add(textField);
    }

    public static void addRestartButton()
    {
        JButton buttonRestart = new JButton("Restart");
        buttonRestart.setBounds(15,100,150,30);
        //make the button not focusable so key listener work properly
        buttonRestart.setFocusable(false);
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

                //In case the user press 'cancel' or input value smaller than 4 in the dialog box
                if (resultS != null)
                {
                    int newGridSize = Integer.parseInt(resultS);
                    if (newGridSize >= 4)
                    {
                        //close the old window before setting up a new one
                        frame.dispose();

                        gridSize = newGridSize;
                        setup();

                        /** \　　/
                         * 【 ﾟ∀ﾟ】< You can tell whether the restart button is working properly by checking the console
                         */
                        System.out.println("Restarted");
                    }
                }
            }
        });
        frame.add(buttonRestart);
    }

    public static void addDestroyButton()
    {
        JButton buttonDestroy = new JButton("Destroy a tile");
        buttonDestroy.setBounds(15,140,150,30);
        //make the button not focusable so key listener work properly
        buttonDestroy.setFocusable(false);
        buttonDestroy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String resultXS = (String)JOptionPane.showInputDialog
                        (
                                frame,
                                "Input the x coordinate of tile",
                                "Destroy a tile",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                ""
                        );

                String resultYS = (String)JOptionPane.showInputDialog
                        (
                                frame,
                                "Input the y coordinate of tile",
                                "Destroy a tile",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                ""
                        );

                //In case the user press 'cancel' or input value(s) out of bounds in the dialog box
                if (resultXS != null && resultYS != null)
                {
                    int x = Integer.parseInt(resultXS);
                    int y = Integer.parseInt(resultYS);

                    if (x >= 0 && x < gridSize && y >= 0 && y < gridSize)
                    {
                        /** \　　/
                         * 【 ﾟ∀ﾟ】< Cancan, please remove '//' in front of grid.destroy(x, y) after you finish it in
                         * Grid.java
                         * You can tell whether the destroy methods has been called by checking the console, 'Destroyed'
                         * will be printed
                         */
                        //grid.destroy(x, y);

                        //Hide the button after using the function for once
                        buttonDestroy.setVisible(false);
                        frame.repaint();

                        System.out.println("Destroyed");
                    }
                }
            }
        });
        frame.add(buttonDestroy);
    }

    public static void addDoubleButton()
    {
        JButton buttonDestroy = new JButton("Double a tile");
        buttonDestroy.setBounds(15,180,150,30);
        //make the button not focusable so key listener work properly
        buttonDestroy.setFocusable(false);
        buttonDestroy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String resultXS = (String)JOptionPane.showInputDialog
                        (
                                frame,
                                "Input the x coordinate of tile",
                                "Double a tile",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                ""
                        );

                String resultYS = (String)JOptionPane.showInputDialog
                        (
                                frame,
                                "Input the y coordinate of tile",
                                "Double a tile",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                ""
                        );

                //In case the user press 'cancel' or input value(s) out of bounds in the dialog box
                if (resultXS != null && resultYS != null)
                {
                    int x = Integer.parseInt(resultXS);
                    int y = Integer.parseInt(resultYS);

                    if (x >= 0 && x < gridSize && y >= 0 && y < gridSize)
                    {
                        //grid.double(x, y);

                        //Hide the button after using the function for once
                        buttonDestroy.setVisible(false);
                        frame.repaint();

                        System.out.println("Doubled");
                    }
                }
            }
        });
        frame.add(buttonDestroy);
    }

    /**
     * paint grid and tiles
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
                drawTile(g, grid.tileArray[i][j], i * 60 + (windowWidth - gridLength) / 2 + 10,
                        j * 60 + (windowHeight - gridLength) / 2 + 10);
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
            /** \　　/
             * 【 ﾟ∀ﾟ】< Yushi, please remove g2.setColor(Color.cyan); and '//' in front of g2.setColor(colorOf(value));
             * after you finish colorOf method in this class
             */
            Color color = new Color(255,255,255);
            switch(value)
            {
                case 2: color = new Color(255, 235, 205);
                    break;
                case 4: color = new Color(252, 230, 201);
                    break;
                case 8: color = new Color(245, 222, 179);
                    break;
                case 16: color = new Color(227, 207, 87);
                    break;
                case 32: color = new Color(255, 215, 0);
                    break;
                case 64: color = new Color(255, 255, 0);
                    break;
                case 128: color = new Color(255, 153, 18);
                    break;
                case 256: color = new Color(235, 142, 85);
                    break;
                case 512: color = new Color(237, 145, 33);
                    break;
                case 1024: color = new Color(255, 128, 0);
                    break;
                case 2048: color = new Color(255, 0, 0);
                    break;
            }
            g2.setColor(color);
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

    /**
     * not required
     * @param e     n/a
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * press w, s, a, d to move tiles around
     * @param e     key press event
     */

    /** \　　/
     * 【 ﾟ∀ﾟ】< Jacqueline, please remove '//' in front of grid.up(), grid.down(), grid.left(), grid.right() after you
     * finish them in Grid.java
     */

    /** \　　/
     * 【 ﾟ∀ﾟ】< Jialei, please remove '//' in front of grid.spawn() after you finish it in Grid.java
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        char keyPressed = e.getKeyChar();
        switch (keyPressed)
        {
            case 'w':
                //grid.up();
                grid.spawn();
                frame.repaint();
                System.out.println("w");
                break;
            case 's':
                //grid.down();
                grid.spawn();
                frame.repaint();
                System.out.println("s");
                break;
            case 'a':
                //grid.left();
                grid.spawn();
                frame.repaint();
                System.out.println("a");
                break;
            case 'd':
                //grid.right();
                grid.spawn();
                frame.repaint();
                System.out.println("d");
                break;
        }
    }

    /**
     * not required
     * @param e     n/a
     */
    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}