package main.graphics;

import main.util.map.Object2DInt;

import java.awt.*;

public class DrawText
{
    private String name;
    private String text;

    private int style;
    private int size;
    private Object2DInt position;

    public DrawText(String name, String text, int style, int size, Object2DInt position) {
        this.name = (name != null) ? name : "Default";
        this.text = text;
        this.style = style;
        this.size = size;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getStyle() {
        return style;
    }

    public int getSize() {
        return size;
    }

    public Object2DInt getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPosition(Object2DInt position) {
        this.position = position;
    }

    public void render (Graphics g) {

//        g.drawImage(image, position.x, position.y, position.width, position.height, null);
        Font font = new Font(name, style, size);

        g.setFont(font);
        g.drawString(text,position.x, position.y);
    }
}