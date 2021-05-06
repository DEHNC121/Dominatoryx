package main.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite
{

    private BufferedImage SPRITESHEET = null;
    private BufferedImage [][] spriteArray;
    public int w= 64;
    public int h= 55;
    private int wSprite;
    private int hSprite;

    public Sprite (String file)
    {
        System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite (file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }

    public Sprite (String file, int w, int h)
    {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite (file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }

    public void setSize (int width, int height)
    {
        setWidth (width);
        setHeight (height);
    }

    public void setWidth (int i)
    {
        w = i;
        wSprite = SPRITESHEET.getWidth() / w;
    }

    public void setHeight (int i)
    {
        h = i;
        hSprite = SPRITESHEET.getHeight() / h;
    }
    public int getWidth () { return w; }
    public int getHeight () { return h; }

    public BufferedImage getBufferedImage () { return SPRITESHEET; }

    private BufferedImage loadSprite (String file)
    {
        BufferedImage sprite = null;
        try
        {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }
        catch (Exception e)
        {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray ()
    {
        spriteArray = new BufferedImage[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++)
        {
            for (int x = 0; x < wSprite; x++)
            {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSpriteSheet () { return SPRITESHEET; }

    public BufferedImage getSprite (int x, int y)
    {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public void renderAtPoint (Graphics g, int x, int y) {
        g.drawImage(SPRITESHEET, x - wSprite / 2, y - hSprite / 2, null);

    }



}
