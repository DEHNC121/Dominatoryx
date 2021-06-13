package main.graphics;

import main.GamePanel;
import main.states.GameStyle;
import main.util.MouseHandler;

import java.awt.*;

public class DrawButton extends DrawText {

    private Point mousePosition;
    float inWidth;
    public int mouseClick;

    Rectangle work;

    private void initButton(float inWidth){
        mousePosition =new Point(-1,-1);
        this.inWidth=inWidth;
        mouseClick=-1;
        work= new Rectangle(inRectangle.x- (((int) (GamePanel.width*inWidth)-inRectangle.width)/2),
                inRectangle.y+(int)(inRectangle.height*0.25), (int) (GamePanel.width*inWidth),inRectangle.height);
    }
    public void setWork(Rectangle rect){work=rect;}
    public DrawButton(String text, Rectangle outRectangle, float heightPercentages,float inWidth) {
        super(text, outRectangle, heightPercentages);
        initButton(inWidth);
    }

    public DrawButton(String text, Rectangle outRectangle, float heightPercentages, Color textColor,float inWidth) {
        super(text, outRectangle, heightPercentages, textColor);
        initButton(inWidth);
    }

    public DrawButton(String text, Rectangle outRectangle, float heightPercentages, GameStyle.PALETTE textColorStyle, float inWidth) {
        super(text, outRectangle, heightPercentages, textColorStyle);
        initButton(inWidth);
    }

    public DrawButton(String text, Rectangle outRectangle, float heightPercentages, Color textColor, String fontName,float inWidth) {
        super(text, outRectangle, heightPercentages, textColor, fontName);
        initButton(inWidth);
    }

    public void input (MouseHandler mouse) {
        mousePosition.x=mouse.getX();
        mousePosition.y=mouse.getY();
        if (work!=null && work.contains(mousePosition)){
            if (mouseClick==-1 && mouse.getButton()==1){
                mouseClick=1;
            }else if (mouseClick==1 && mouse.getButton()==-1){
                mouseClick=2;
            }

        }
    }

    @Override
    public void render(Graphics g) {
        if ( work.contains(mousePosition)){
            g.setColor(inColor);
            g.fillRect(work.x,work.y, work.width,work.height);

        }

        super.render(g);
    }
}

