package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.NewDrawText;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;

public class MenuMinimalizer {
    Rectangle body;
    int mouseClick=-1;
    UnitAndStructureMenu menu;
    NewDrawText name;

    MenuMinimalizer(UnitAndStructureMenu menu){
        this.menu=menu;
        body=new Rectangle(0,(int) (GamePanel.height*0.05),(int) (GamePanel.width*0.15),(int) (GamePanel.height*0.05));
        name=new NewDrawText("Shop",
                body,
                1,
                GameStateManager.gameStyle.get(GameStyle.PALETTE.FRONT)
                );
    }

    public void render(Graphics g){
        g.setColor(GameStateManager.gameStyle.get(GameStyle.PALETTE.BACKGROUND));
        g.fillRect(body.x,body.y,body.width,body.height);
        name.render(g);
    }

    public boolean isInside(int x,int y){
        return body.contains(x,y);
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        if (body.contains(mouseHandler.getX(),mouseHandler.getY())){
            if (mouseClick==-1 && mouseHandler.getButton()==1){
                mouseClick=1;
            }else if (mouseClick==1 && mouseHandler.getButton()==-1){
                mouseClick=2;
            }
        }
    }

    public void update(){
        if( mouseClick==2){
            menu.showTab=!menu.showTab;
            mouseClick=-1;
        }
    }
}
