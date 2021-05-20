package main.util.playStateGUI;

import main.graphics.Image;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.Procedure;

public class Button extends Image{
    Procedure procedure;
    int mouseX=0;
    int mouseY=0;
    boolean isClicked=false;
    public Button(String file, int x,int y,int w,int h, Procedure pr) {
        super(file);
        setPosition(x,y);
        setHeight(h);
        setWidth(w);
        procedure=pr;
    }
    public void input(MouseHandler mouse, KeyHandler key){
        mouseX=mouse.getX();
        mouseY=mouse.getY();
        isClicked=mouse.getIsClicked();
    }
    public void update(){
        if(isClicked && getObject2DInt().isInside(mouseX,mouseY))
            procedure.run();
    }
    public boolean isInside(int x,int y){
        return getObject2DInt().isInside(x,y);
    }
}
