package main.graphics;

import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.Object2DInt;
import java.awt.*;

import java.util.ArrayList;

public class ScrollField {
    String entry;
    ArrayList<String> entries;
    Object2DInt field;
    Object2DInt wholeField;
    DrawText textUp, textMiddle, textBottom;
    int upIndex, middleIndex, bottomIndex;
    boolean scrollUp, scrollDown;
    public ScrollField(Object2DInt field, ArrayList<String> entries, Color color) {
        this.field = field;
        this.entries = entries;
        wholeField = new Object2DInt(field.getX(), field.getY(), field.getWidth(), 3 * field.getHeight());
        middleIndex = 0;
        bottomIndex = -1;
        upIndex = (entries.size() > 1) ? 1 : -1;
        textUp = new DrawText(stringFromIndex(upIndex),
                new Object2DInt(field.getX(), field.getY() + field.getHeight(), field.getWidth(), field.getHeight()),
                new Color(color.getRed(),color.getGreen(),color.getBlue(),40));
        textMiddle = new DrawText(stringFromIndex(middleIndex),
                new Object2DInt(field.getX(), field.getY() + 2*field.getHeight(), field.getWidth(), field.getHeight()),
                new Color(color.getRed(),color.getGreen(),color.getBlue(),255));
        textBottom = new DrawText(stringFromIndex(upIndex),
                new Object2DInt(field.getX(), field.getY() + 3 * field.getHeight(), field.getWidth(), field.getHeight()),
                new Color(color.getRed(),color.getGreen(),color.getBlue(),40));
        entry = stringFromIndex(middleIndex);
    }
    public ScrollField(Object2DInt field, ArrayList<String> entries, String name, int style, int size, Color color) {
        this.field = field;
        this.entries = entries;
        wholeField = new Object2DInt(field.getX(), field.getY(), field.getWidth(), 3 * field.getHeight());
        middleIndex = 0;
        bottomIndex = -1;
        upIndex = (entries.size() > 1) ? 1 : -1;

        textUp = new DrawText(name,stringFromIndex(upIndex), style, size,
                new Object2DInt(field.getX(), field.getY() + field.getHeight(), field.getWidth(), field.getHeight()),
                new Color(color.getRed(),color.getGreen(),color.getBlue(),40));

        textMiddle = new DrawText(name,stringFromIndex(middleIndex), style, size,
                new Object2DInt(field.getX(), field.getY() + 2*field.getHeight(), field.getWidth(), field.getHeight()),
                new Color(color.getRed(),color.getGreen(),color.getBlue(),255));
        textBottom = new DrawText(name,stringFromIndex(upIndex), style, size,
                new Object2DInt(field.getX(), field.getY() + 3 * field.getHeight(), field.getWidth(), field.getHeight()),
                new Color(color.getRed(),color.getGreen(),color.getBlue(),40));
        entry = stringFromIndex(middleIndex);
    }
    public String getEntry () {
        return this.entry;
    }

    public void setArray (ArrayList<String> entries) {
        if (this.entries != entries) {
            this.entries = entries;
            middleIndex = 0;
            bottomIndex = -1;
            upIndex = (entries.size() > 1) ? 1 : -1;
        }
    }
    public String stringFromIndex (int i) {
        if (i == -1)
            return null;
        return entries.get(i);
    }

    public void update () {
        if (scrollUp) {
            scrollUp = false;
            ifScrollUp();
        }
        if (scrollDown) {
            scrollDown = false;
            ifScrollDown();
        }
        textUp.setText(stringFromIndex(upIndex));
        textBottom.setText(stringFromIndex(bottomIndex));
        textMiddle.setText(stringFromIndex(middleIndex));
        entry = stringFromIndex(middleIndex);
    }

    public void input (MouseHandler mouse, KeyHandler key) {
        if (isMouseOn(mouse)) {
            if (mouse.getRotation() < 0)
                scrollUp = true;
            if (mouse.getRotation() > 0)
            scrollDown = true;
        }
    }
    public void render (Graphics g) {
        g.setColor(Color.GRAY);
        if (textUp.getText() != null)
            textUp.render(g);
        if (textMiddle.getText() != null)
            textMiddle.render(g);
        if (textBottom.getText() != null)
            textBottom.render(g);
    }

    public void ifScrollUp () {
        if (middleIndex < entries.size() - 1) {
            bottomIndex = middleIndex;
            middleIndex = upIndex;
            if (upIndex < (entries.size() - 1))
                upIndex++;
            else
                upIndex = -1;
        }
    }
    public void ifScrollDown () {
        if (middleIndex > 0) {
            upIndex = middleIndex;
            middleIndex = bottomIndex;
            bottomIndex = bottomIndex - 1;
        }
    }

    public boolean isMouseOn (MouseHandler mouse) {
        return wholeField.isInside(mouse.getX(), mouse.getY());
    }
}
