package main.util.playStateGUI;

import main.util.map.Object2D;

import java.awt.*;

public class UnitMenuListWindow extends Object2D {
    int x,y;
    int w,h;
    int inListPosition;
    public UnitMenuListWindow(int x,int y,int w, int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y,w,h);
    }
}
