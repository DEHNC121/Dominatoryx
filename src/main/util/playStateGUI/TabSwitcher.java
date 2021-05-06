package main.util.playStateGUI;

import main.GamePanel;

import java.awt.*;

public class TabSwitcher {
    int x,y,w,h;
    UnitAndStructureMenu menu;
    TabSwitcher(UnitAndStructureMenu menu1){
        x=0;
        y=((int) GamePanel.height)/10;
        w=((int) GamePanel.width)*3/20;
        h=((int) GamePanel.height)/20;
        menu=menu1;
    }
    public void render(Graphics g){

    }
}
