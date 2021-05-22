package main;

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame
{
    public Window () {

        //Sound.playBackgroundMusic();

        Dimension ekran=Toolkit.getDefaultToolkit().getScreenSize();
        setUndecorated(true);
        System.out.println("debug: " + ekran.getHeight() + " " + ekran.getWidth());
        setTitle ("Dominatoryx");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane (new GamePanel ((int)ekran.getWidth(), (int)ekran.getHeight()));

        pack();

        setVisible(true);
    }
}
