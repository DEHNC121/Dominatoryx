package main.util.playStateGUI;

import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;

public class TabSwitcher {
    int x,y,w,h;
    int mouseX=0;
    int mouseY=0;
    boolean isClicked=false;
    UnitAndStructureMenu menu;

    TabSwitcher(UnitAndStructureMenu menu1){
        x=0;
        y=((int) GamePanel.height)/10;
        w=((int) GamePanel.width)*3/20;
        h=((int) GamePanel.height)/20;
        menu=menu1;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x,y,w/2,h);
        g.setColor(Color.orange);
        g.fillRect(x+w/2,y,w/2,h);
    }

    public boolean isInside(int x,int y){
        return x>this.x && y>this.y && x<this.x+w && y<this.y+h;
    }

    public void input(MouseHandler mouse, KeyHandler key){
        mouseX=mouse.getX();
        mouseY=mouse.getY();
        isClicked= mouse.getIsClicked();
    }

    public void update(){
        if(isClicked && isInside(mouseX,mouseY)){
            if(mouseX<w/2){
                menu.switchToUnit();
            }
            else{
                menu.switchToStructure();
            }
        }
    }
}
