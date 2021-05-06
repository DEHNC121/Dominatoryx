package main.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Sprite
{
    private final BufferedImage image;
    private BufferedImage[][] spriteArray;
    public int w= 64;
    public int h= 55;
    private final int wSprite;
    private final int hSprite;

    public Sprite (String file)
    {
        System.out.println("Loading: " + file + "...");
        image = loadSprite (file);

        wSprite = image.getWidth() / w;
        hSprite = image.getHeight() / h;
        loadSpriteArray();
    }

    public Sprite (String file, int w, int h)
    {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        image = loadSprite (file);

        wSprite = image.getWidth() / w;
        hSprite = image.getHeight() / h;
        loadSpriteArray();
    }

    public BufferedImage getBufferedImage () { return image; }

    private BufferedImage loadSprite (String file)
    {
        BufferedImage sprite = null;
        try
        {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
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
                spriteArray[y][x] = getSpritePart(x, y);
            }
        }
    }

    public BufferedImage getSpriteSheet () { return image; }

    private BufferedImage getSpritePart (int x, int y)
    {
        return image.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getSprite (int x, int y)
    {
        return spriteArray[x][y];
    }

}
