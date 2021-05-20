package main.util.playStateGUI;

import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.graphics.Image;
import main.util.RoundManager;
import main.util.map.WorldMap;

import java.awt.*;
import java.util.ArrayList;

public class GUIManager {
    static public UnitAndStructureMenu unitMenu=new UnitAndStructureMenu(0,((int) GamePanel.height)/20,
            ((int) GamePanel.width)*3/20, ((int) GamePanel.height)*17/20);
    static public ArrayList<Button> buttonList;
    static public ArrayList<Image> imageList;
    static public void render(Graphics g){
        unitMenu.render(g);
        for(Button b : buttonList){
            b.render(g);
        }
        for(Image image : imageList){
            image.render(g);
        }
    }
    static public boolean capture(int x,int y){
        for(Button b:buttonList){
            if(b.isInside(x,y))
                return true;
        }
        return unitMenu.isInside(x,y);
    }
    static public void loadIcons(){
        imageList=new ArrayList<>();
        Image temp=new Image("gameicons/coin.png");
        temp.setSize((int) (GamePanel.width*0.05f), (int) (GamePanel.width*0.05f));
        temp.setPosition(0,0);
        imageList.add(temp);

    }
    static public void loadButtons(){
        buttonList=new ArrayList<>();
        Button button=new Button("gameicons/menu_icon.png",
                (int) GamePanel.width -  (int) (GamePanel.width*0.05f), 0,
                (int) (GamePanel.width*0.05f),(int) (GamePanel.width*0.05f),()->{
                //TODO: how to change state in GSM from there???
        });
        buttonList.add(button);
        button=new Button("gameicons/end_turn.png",
                (int) GamePanel.width -  (int) (GamePanel.width*0.05f),
                (int) GamePanel.height- (int) (GamePanel.width*0.05f),
                (int) (GamePanel.width*0.05f),(int) (GamePanel.width*0.05f),()->{
            RoundManager.passTurn();
        });
        buttonList.add(button);
        button=new Button("gameicons/undo.png",
                0, (int) GamePanel.height -  (int) (GamePanel.width*0.05f),
                (int) (GamePanel.width*0.05f),(int) (GamePanel.width*0.05f),()->{
            //TODO: implement undo function via stack
        });
        buttonList.add(button);
    }

    static{
        loadIcons();
        loadButtons();
    }

    static public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        unitMenu.input(mouseHandler, keyHandler);
        for(Button b:buttonList){
            b.input(mouseHandler, keyHandler);
        }
    }
    static public void update(){
        unitMenu.update();
        for(Button b:buttonList){
            b.update();
        }
    }
}
