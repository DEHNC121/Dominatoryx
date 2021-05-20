package main.graphics;

import main.util.MouseHandler;
import main.util.map.Object2DInt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

public class GameImage {
    private BufferedImage image;
    private final Object2DInt rectangle;

    public GameImage(String file) {
        loadImage(file);
        rectangle = new Object2DInt(0,0, image.getWidth(), image.getHeight());
    }
    public GameImage(String file, int x, int y) {
        loadImage(file);
        rectangle = new Object2DInt(x, y, image.getWidth(), image.getHeight());
    }

    public void setPosition (int positionX, int positionY) {
        rectangle.setX(positionX);
        rectangle.setY(positionY);
    }

    public void setPositionCenter(int positionCenterX, int positionCenterY) {
        rectangle.setCenterX(positionCenterX);
        rectangle.setCenterY(positionCenterY);
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
    public Object2DInt getObject2DInt () {return rectangle;}
    public int getWidth () { return rectangle.getWidth(); }
    public int getHeight () { return rectangle.getHeight(); }
    public void setWidth (int width) {rectangle.setWidth(width); }
    public void setHeight (int height) {rectangle.setHeight(height);}
    public void setSize (int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void render (Graphics g) {
        g.drawImage(image, rectangle.getX(), rectangle.getY(),
                rectangle.getWidth(), rectangle.getHeight(), null);
    }

    public void render (Graphics g,int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
    public boolean mouseOnIcon (MouseHandler mouse) {
        return rectangle.isInside(mouse.getX(), mouse.getY());
    }

}
