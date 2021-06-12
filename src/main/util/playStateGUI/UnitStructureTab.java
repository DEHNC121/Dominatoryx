package main.util.playStateGUI;

import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.Object2DInt;

import java.awt.*;

public abstract class UnitStructureTab extends Object2DInt {
    int mouseX=0;
    int mouseY=0;
    boolean isClicked=false;
    UnitStructureTab(){
        super(0, ((int)GamePanel.height)*3/20,
                ((int)GamePanel.width)*3/20,((int) GamePanel.height)*15/20);
    }
    public abstract Color getColour();
    public abstract void specialRender(Graphics g);

    public void render(Graphics g){
        g.setColor(getColour());
        g.fillRect(x,y,width,height);
        specialRender(g);
    }

    public void input(MouseHandler mouse, KeyHandler key){
        mouseX=mouse.getX();
        mouseY=mouse.getY();
        isClicked=mouse.getIsClicked();
    }
    public abstract void update();
}
