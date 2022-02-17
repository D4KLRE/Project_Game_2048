package edu.wcc.cs240;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends WindowAdapter
{
    Frame f;

    MainWindow()
    {
        f = new Frame();
        f.addWindowListener(this);

        Button b = new Button("Click Me!!");

        // setting button position on screen
        b.setBounds(30,100,80,30);

        // adding button into frame
        f.add(b);

        // frame size 300 width and 300 height
        f.setSize(300,300);

        // setting the title of Frame
        f.setTitle("This is our basic AWT example");

        // no layout manager
        f.setLayout(null);

        // now frame will be visible, by default it is not visible
        f.setVisible(true);
    }

    public void windowClosing (WindowEvent e)
    {
        f.dispose();
    }
}
