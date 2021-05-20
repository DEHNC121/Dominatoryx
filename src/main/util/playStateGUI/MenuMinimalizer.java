package main.util.playStateGUI;

import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;

public class MenuMinimalizer {
    int x,y,w,h;
    int mouseX=0;
    int mouseY=0;
    boolean isClicked=false;
    UnitAndStructureMenu menu;
    MenuMinimalizer(UnitAndStructureMenu menu1){
        menu=menu1;
        x=0;
        y=((int) GamePanel.height)/20;
        w=((int) GamePanel.width)*3/20;
        h=y;
    }
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y,w,h);
    }
    public boolean isInside(int x,int y){
        return x>this.x && y>this.y && x<this.x+w && y<this.y+h;
    }
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        mouseX=mouseHandler.getX();
        mouseY=mouseHandler.getY();
        isClicked= mouseHandler.getIsClicked();
    }
    public void update(){
        if(isClicked && isInside(mouseX,mouseY)){
            menu.showTab=!menu.showTab;
        }
    }
}
