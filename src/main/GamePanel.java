package main;

import main.states.GameStateManager;
import main.util.KeyHandler;
import main.util.MouseHandler;

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

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

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

    public void init ()
    {
        running = true;

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }

    @Override
    public void run()
    {
        init ();

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time before Update
        final int MUBR = 5; // Must update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS; // Total Time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;
            while ((now - lastUpdateTime) > TBU && (updateCount < MUBR))
            {
                update ();
                input (mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU)
            {
                lastUpdateTime = now - TBU;
            }

            input (mouse, key);
            render ();
            draw ();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
                if (frameCount != oldFrameCount)
                {
                    // System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while ( (now - lastRenderTime) < TTBR && (now - lastUpdateTime) < TBU )
            {
                Thread.yield();
                try
                {
                    Thread.sleep (1);
                }
                catch (Exception e)
                {
                    System.out.println("ERROR: yealding thread");
                }
                now = System.nanoTime();
            }

        }
    }



    public void update ()
    {
        gsm.update();
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {
        gsm.input(mouse, key);
    }

    public void render ()
    {
        if (g != null)
        {
            g.setColor(new Color (22, 104, 4));
            g.fillRect(0, 0, width, height);
            gsm.render(g);
        }
    }
    public void draw ()
    {
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }

}