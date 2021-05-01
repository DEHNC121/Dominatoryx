package main.graphics;

import main.util.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Icon {
    private BufferedImage image;
    private int width;
    private int height;
    private int positionX;
    private int positionY;
    private int positionCenterX;
    private int positionCenterY;
    private String label = null;

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

    public void setLabel (String s) {
        label = s;
    }

    public String getLabel () { return label; }

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
    public void setWidth (int width) {this.width = width; }
    public void setHeight (int height) {this.height = height;}
    public void setSize (int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void render (Graphics g) {

        g.drawImage(image, positionX, positionY, width, height, null);
        if (label != null) {
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(60.0f);
            g.setFont(newFont);
            g.drawString(label, positionX + width / 2 - 80, positionY + height / 2);
        }
    }


    public boolean mouseOnIcon (MouseHandler mouse) {
        int mX = mouse.getX();
        int mY = mouse.getY();
        return (mX >= positionX && mX <= positionX + width &&
                mY >= positionY && mY <= positionY + height);
    }

}
