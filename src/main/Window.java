package main;


import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame
{
    public Window () {
        Dimension ekran=Toolkit.getDefaultToolkit().getScreenSize();
        setUndecorated(true);

        setTitle ("Dominatoryx");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane (new GamePanel ((int)ekran.getWidth(), (int)ekran.getHeight()));
        pack();


//        setAlwaysOnTop(true);
//        setResizable(false);
//        setLocationRelativeTo(null);
        setVisible(true);
    }
}
