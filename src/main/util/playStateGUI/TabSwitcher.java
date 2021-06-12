package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawButton;
import main.graphics.NewDrawText;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class TabSwitcher {
    Rectangle body;

    ArrayList<NewDrawText> switcher;
    int mouseClick;
    UnitAndStructureMenu menu;

    TabSwitcher(UnitAndStructureMenu menu){
        switcher=new ArrayList<>();
        mouseClick=-1;
        body =new Rectangle(0,(int) (GamePanel.height*0.1),(int)( GamePanel.width*0.16),(int)( GamePanel.height*0.05));
        this.menu=menu;
        float textHeight=0.8f;

        switcher.add(new NewDrawText("Units",
                new Rectangle(body.x, body.y,body.width/2,body.height),
                textHeight,
                GameStateManager.gameStyle.get(GameStyle.PALETTE.UPFRONT)
                ));

        switcher.add(new NewDrawText("Structures",
                new Rectangle(body.x+body.width/2,body.y,body.width/2,body.height),
                textHeight,
                GameStateManager.gameStyle.get(GameStyle.PALETTE.UPFRONT)
                ));
    }

    public void render(Graphics g){
        if (menu.getTabNumber()==0){
            g.setColor(GameStateManager.gameStyle.get(GameStyle.PALETTE.GROUND));
            g.fillRect(body.x,body.y,body.width/2,body.height);
            g.setColor(GameStateManager.gameStyle.get(GameStyle.PALETTE.BACKGROUND));
            g.fillRect(body.x+body.width/2,body.y,body.width/2,body.height);
        }else {
            g.setColor(GameStateManager.gameStyle.get(GameStyle.PALETTE.BACKGROUND));
            g.fillRect(body.x,body.y,body.width/2,body.height);
            g.setColor(GameStateManager.gameStyle.get(GameStyle.PALETTE.GROUND));
            g.fillRect(body.x+body.width/2,body.y,body.width/2,body.height);
        }

        for (NewDrawText dt:switcher){
            dt.render(g);
        }
    }

    public boolean isInside(int x,int y){
        return body.contains(x,y);
    }

    public void input(MouseHandler mouseHandler, KeyHandler key){
        for (NewDrawText dt:switcher){
            if (dt instanceof DrawButton){
                ((DrawButton)dt).input(mouseHandler);
            }
        }

        if (body.contains(mouseHandler.getX(),mouseHandler.getY())){
            if (mouseClick==-1 && mouseHandler.getButton()==1){
                mouseClick=1;
            }else if (mouseClick==1 && mouseHandler.getButton()==-1){
                if (mouseHandler.getX()<body.width/2){
                    mouseClick=2;
                }else{
                    mouseClick=3;
                }
            }
        }
    }

    public void update(){
        if (mouseClick==2) {
            menu.switchToUnit();
            mouseClick=-1;
        }else if (mouseClick==3){
            menu.switchToStructure();
            mouseClick=-1;
        }
    }
}
