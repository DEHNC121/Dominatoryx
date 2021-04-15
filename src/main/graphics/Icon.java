package main.graphics;

import main.util.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Icon {
    private BufferedImage image;
    private final int width;
    private final int height;
    private int positionX;
    private int positionY;
    private int positionCenterX;
    private int positionCenterY;

    public Icon (String file) {
        loadImage(file);
        width = image.getWidth();
        height = image.getHeight();
    }

    public Icon (String file, int positionCenterX, int positionCenterY) {
        loadImage(file);
        width = image.getWidth();
        height = image.getHeight();
        this.positionCenterX = positionCenterX;
        this.positionCenterY = positionCenterY;
        setPositionFromCenter();
    }

    public void setPosition (int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setPositionCenter(int positionCenterX, int positionCenterY) {
        this.positionCenterX = positionCenterX;
        this.positionCenterY = positionCenterY;
        setPositionFromCenter();
    }

    public void setPositionFromCenter () {
        positionX = positionCenterX - width / 2;
        positionY = positionCenterY - height / 2;
    }

    public void loadImage (String file) {
        image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        }
        catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
    }
    public int getWidth () { return width; }
    public int getHeight () { return height; }

    public void render (Graphics g) {
        g.drawImage(image, positionX, positionY, null);
    }

    public boolean mouseOnIcon (MouseHandler mouse) {
        int mX = mouse.getX();
        int mY = mouse.getY();
        return (mX >= positionX && mX <= positionX + width &&
                mY >= positionY && mY <= positionY + height);
    }

}
