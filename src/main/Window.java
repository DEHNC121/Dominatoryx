package main;


import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame
{
    public static String panelTitle;
    public Window () {
        panelTitle="Dominatoryx";
        Dimension ekran=Toolkit.getDefaultToolkit().getScreenSize();
        setUndecorated(true);

        setTitle (panelTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane (new GamePanel ((int)ekran.getWidth(), (int)ekran.getHeight()));
        pack();


//        setAlwaysOnTop(true);
//        setResizable(false);
//        setLocationRelativeTo(null);
        setVisible(true);
    }
}
