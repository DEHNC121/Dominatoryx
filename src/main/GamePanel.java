package main;

import main.states.GameStateManager;
import main.util.KeyHandler;
import main.util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class GamePanel extends JPanel implements Runnable
{
    public static float width;
    public static float height;
    public static int oldFrameCount;

    private Thread thread;
    private static boolean running = false;

    private MouseHandler mouse;
    private KeyHandler keyHandler;

    private GameStateManager gsm;

    private BufferedImage image;
    private Graphics2D graphics2D;

    public GamePanel (int width, int height)
    {
        GamePanel.width = width;
        GamePanel.height = height;
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

        image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) image.getGraphics();

        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fonts/font.otf")));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(f);
            for (String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
                //System.out.println(s);
            }
            graphics2D.setFont(f);
        } catch (IOException |FontFormatException e) {
            e.printStackTrace();
        }

        mouse = new MouseHandler(this);
        keyHandler = new KeyHandler(this);

        gsm = new GameStateManager();

    }

    @Override
    public void run()
    {
        init();


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

        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;
            while ((now - lastUpdateTime) > TBU && (updateCount < MUBR))
            {
                update ();
                input (mouse, keyHandler);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU)
            {
                lastUpdateTime = now - TBU;
            }

            input (mouse, keyHandler);
            render ();
            draw ();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
                if (frameCount != oldFrameCount)
                {
                    //System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
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
        System.exit(0);
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
        gsm.render(graphics2D);
    }
    public void draw ()
    {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, (int) width, (int) height, null);
        g2.dispose();
    }

    public static void endProgram(){
        running=false;
    }

}