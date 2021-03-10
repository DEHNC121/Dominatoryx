package main;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable
{
    public static int width;
    public static int height;
    public static int oldFrameCount;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    public GamePanel (int w, int h)
    {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify ()
    {
        super.addNotify();
        if (thread == null)
        {
            thread = new Thread (this, "GameThread");
            thread.start();
        }
    }


    @Override
    public void run()
    {

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time before Update
        final int MUBR = 5; // Must update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS; // Total Time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        oldFrameCount = 0;

    }


}