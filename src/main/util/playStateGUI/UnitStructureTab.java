package main.util.playStateGUI;

import main.GamePanel;

import java.awt.*;

public abstract class UnitStructureTab {
    int x,y,w,h;
    UnitStructureTab(){
        x=0;
        y=((int) GamePanel.height)*3/20;
        w=((int) GamePanel.width)*3/20;
        h=((int) GamePanel.height)/10;
    }
    public abstract void render(Graphics g);
}
