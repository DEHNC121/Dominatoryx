package main.graphics;

import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class NamedScrollField {
    private ScrollField scrollField;
    private final NewDrawText fieldName;


    public NamedScrollField(String name, Rectangle outRectangle, ArrayList<String> fields, Color nameColor, Color scrollFieldColor, int showNumberIn, float heightPercentages) {

        this.fieldName =new NewDrawText(name,
                new Rectangle(outRectangle.x, outRectangle.y, outRectangle.width, outRectangle.height),
                heightPercentages,
                nameColor);


        scrollField=new ScrollField(
                new Rectangle(fieldName.inRectangle.x+fieldName.inRectangle.width, outRectangle.y, (int)(outRectangle.height*0.8), outRectangle.height),
                fields,
                scrollFieldColor,
                showNumberIn
                );

    }

    public NamedScrollField(NewDrawText drawText,ScrollField scrollField) {

        this.fieldName =drawText;


        this.scrollField=scrollField;

    }


    public String getFieldState() {
        return scrollField.getMainField();
    }

    public void update () {
        scrollField.update();

    }

    public void input (MouseHandler mouse, KeyHandler key) {
        scrollField.input(mouse,key);

    }
    public void render (Graphics g) {

        fieldName.render(g);
        scrollField.render(g);
//        g.setColor(Color.red);
//        g.drawRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }

    public ScrollField getScrollField() {
        return scrollField;
    }

    public NewDrawText getFieldName() {
        return fieldName;
    }
}
