package main.graphics;

import main.util.map.Object2DInt;

import java.awt.*;

public class DrawText
{
    private Font font;
    private String text;

    private Object2DInt position;
    private Color color;

    public DrawText(String text, Object2DInt position) {
        this.font = new Font("Times New Roman", Font.PLAIN, 100);
        this.text = text;
        this.position = position;
        this.color = new Color(0,0,0,255);
    }
    public DrawText(String text, Object2DInt position, Color color) {
        this.font = new Font("Times New Roman", Font.PLAIN, 100);
        this.text = text;
        this.position = position;
        this.color = color;
    }

    public DrawText(String text, Object2DInt position, int size) {
        this.font = new Font("Times New Roman", Font.PLAIN, size);
        this.text = text;
        this.position = position;
        this.color = new Color(0,0,0,255);
    }

    public DrawText(String name, String text, int style, int size, Object2DInt position, Color color) {

        this.font = new Font(name, style, size);
        this.text = text;
        this.position = position;
        this.color = color;
    }

    public String getName() {
        return font.getName();
    }

    public String getText() {
        return text;
    }

    public int getStyle() {
        return font.getStyle();
    }

    public int getSize() {
        return font.getSize();
    }

    public Object2DInt getPosition() {
        return position;
    }

    public void setName(String name) {
        font=new Font(name, getStyle(),getSize());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStyle(int style) {
        font=new Font(getName(), style,getSize());
    }

    public void setSize(int size) {
        font=new Font(getName(), getStyle(),size);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Object2DInt position) {
        this.position = position;
    }

    public void render (Graphics g) {

        g.setColor(color);
        g.setFont(font);
        g.drawString(text,position.x, position.y);
    }
}