package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.GameImage;
import main.states.GameStateManager;
import main.states.PauseState;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.RoundManager;
import main.util.map.Object2DInt;
import main.util.map.WorldMap;

import java.awt.*;
import java.util.ArrayList;

public class GUIManager {
    static public UnitAndStructureMenu unitMenu=new UnitAndStructureMenu(0,((int) GamePanel.height)/20,
            ((int) GamePanel.width)*3/20, ((int) GamePanel.height)*17/20);
    static public ArrayList<Button> buttonList;
    static public ArrayList<GameImage> imageList;
    static public GameStateManager gsm;
    static public TurnIndicator turnIndicator=new TurnIndicator((int)GamePanel.width*4/10,0,
            (int)(GamePanel.width)/5, (int) (GamePanel.height*0.03f));
    static public DrawText money=new DrawText("Arial",""+RoundManager.getCurrentPlayer().money,
            Font.PLAIN,24,new Object2DInt((int) (GamePanel.width*0.03f),
            (int) (GamePanel.width*0.02f),(int) (GamePanel.width*0.15f),(int) (GamePanel.width*0.03f)),Color.BLACK);

    static public void render(Graphics g){
        unitMenu.render(g);
        for(Button b : buttonList){
            b.render(g);
        }
        for(GameImage image : imageList){
            image.render(g);
        }
        turnIndicator.render(g);
        money.render(g);
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
        GameImage temp=new GameImage("gameicons/coin.png");
        temp.setSize((int) (GamePanel.width*0.03f), (int) (GamePanel.width*0.03f));
        temp.setPosition(0,0);
        imageList.add(temp);

    }
    static public void setGsm(GameStateManager gsm1){
        gsm=gsm1;
    }
    static public void loadButtons(){
        buttonList=new ArrayList<>();
        Button button=new Button("gameicons/menu_icon.png",
                (int) GamePanel.width -  (int) (GamePanel.width*0.03f), 0,
                (int) (GamePanel.width*0.03f),(int) (GamePanel.width*0.03f),()->{
            if(gsm!=null)
                gsm.set(new PauseState(gsm));
        });
        buttonList.add(button);
        button=new Button("gameicons/end_turn.png",
                (int) GamePanel.width -  (int) (GamePanel.width*0.03f),
                (int) GamePanel.height- (int) (GamePanel.width*0.03f),
                (int) (GamePanel.width*0.03f),(int) (GamePanel.width*0.03f),()->{
            RoundManager.passTurn();
        });
        buttonList.add(button);
        button=new Button("gameicons/undo.png",
                0, (int) GamePanel.height -  (int) (GamePanel.width*0.03f),
                (int) (GamePanel.width*0.03f),(int) (GamePanel.width*0.03f),()->{
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
        money.setText(""+RoundManager.getCurrentPlayer().money);
    }
}
