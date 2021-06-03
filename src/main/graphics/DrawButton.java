package main.graphics;

import main.GamePanel;
import main.util.MouseHandler;

import java.awt.*;

public class DrawButton extends NewDrawText{

    private Point mousePosition;
    float inWidth;
    public int mouseClick;

    Rectangle work;

    private void initButton(){
        mousePosition =new Point(-1,-1);
        inWidth=0.4f;
        mouseClick=-1;
        work=null;
    }

    public DrawButton(String text, Rectangle outRectangle, int height) {
        super(text, outRectangle, height);
        initButton();
    }

    public DrawButton(String text, Rectangle outRectangle, int height, Color textColor) {
        super(text, outRectangle, height, textColor);
        initButton();
    }

    public DrawButton(String text, Rectangle outRectangle, int height, Color textColor, String fontName) {
        super(text, outRectangle, height, textColor, fontName);
        initButton();
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
        if (work!=null && work.contains(mousePosition)){
            g.setColor(inColor);
            g.fillRect(work.x,work.y, work.width,work.height);

        }

        super.render(g);
        if (work==null){
            work= new Rectangle(inRectangle.x- (((int) (GamePanel.width*inWidth)-inRectangle.width)/2),
                    inRectangle.y+(int)(inRectangle.height*0.25), (int) (GamePanel.width*inWidth),inRectangle.height);
        }
    }
}

