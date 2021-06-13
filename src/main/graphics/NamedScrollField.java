package main.graphics;

import main.states.GameStyle;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class NamedScrollField {
    private ScrollField scrollField;
    private DrawText fieldName;
    private float scrollFieldSize=0.8f;

    void init(String name, Rectangle outRectangle, ArrayList<String> fields, GameStyle.PALETTE nameColorStyle, GameStyle.PALETTE scrollFieldColorStyle,
              int showNumberIn, float heightPercentages){

        this.fieldName =new DrawText(name,
                new Rectangle(outRectangle.x, outRectangle.y, outRectangle.width, outRectangle.height),
                heightPercentages,
                nameColorStyle);


        this.scrollField=new ScrollField(
                new Rectangle(fieldName.inRectangle.x+fieldName.inRectangle.width, outRectangle.y, (int)(outRectangle.height*scrollFieldSize), outRectangle.height),
                fields,
                scrollFieldColorStyle,
                showNumberIn
        );
    }
    public NamedScrollField(String name, Rectangle outRectangle, ArrayList<String> fields, GameStyle.PALETTE nameColorStyle, GameStyle.PALETTE scrollFieldColorStyle,
                            int showNumberIn, float heightPercentages) {
        init(name, outRectangle, fields, nameColorStyle, scrollFieldColorStyle, showNumberIn, heightPercentages);

    }

    public NamedScrollField(String name, Rectangle outRectangle, ArrayList<String> fields, GameStyle.PALETTE nameColorStyle, GameStyle.PALETTE scrollFieldColorStyle,
                            int showNumberIn, float heightPercentages, float scrollFieldSize) {
        this.scrollFieldSize=scrollFieldSize;
        init(name, outRectangle, fields, nameColorStyle, scrollFieldColorStyle, showNumberIn, heightPercentages);


    }

    public NamedScrollField(DrawText drawText, ScrollField scrollField) {

        this.fieldName =drawText;
        this.scrollField=scrollField;

    }


    public String getFieldState() {
        return scrollField.getMainField();
    }

    public void update () {
        scrollField.update();
        fieldName.update();
    }

    public void input (MouseHandler mouse, KeyHandler key) {
        scrollField.input(mouse,key);

    }
    public void render (Graphics g) {

        fieldName.render(g);
        scrollField.render(g);
//        g.setColor(Color.red);
//        g.drawRect(fieldName.outRectangle.x,fieldName.outRectangle.y,fieldName.outRectangle.width,fieldName.outRectangle.height);
//        g.setColor(Color.red);
//        g.drawRect(scrollField.outRectangle.x,scrollField.outRectangle.y,scrollField.outRectangle.width,scrollField.outRectangle.height);
    }

    public ScrollField getScrollField() {
        return scrollField;
    }

    public DrawText getFieldName() {
        return fieldName;
    }
}
