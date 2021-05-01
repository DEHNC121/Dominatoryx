package main.util.playStateGUI;

import main.GamePanel;
import main.util.map.Object2D;

import java.awt.*;


public class UnitMenu {
    int x,y;
    int w,h;
    final int BUTTON_TO_WINDOW_OFFSET=20;
    final int BUTTON_HEIGHT=30;
    UnitMenu(int x,int y,int width,int height){
        this.x=x;
        this.y=y;
        w=width;
        h=height;
        window=new UnitMenuListWindow(x,y+BUTTON_HEIGHT+BUTTON_TO_WINDOW_OFFSET,
                w,h-2*(BUTTON_HEIGHT+BUTTON_TO_WINDOW_OFFSET));
    }
    public UnitMenuListWindow window;
    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(x,y,200, (int) GamePanel.width);
        window.render(g);
    }
}
