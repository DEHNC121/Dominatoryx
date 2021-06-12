package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.GameImage;
import main.graphics.NewDrawText;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.RoundManager;
import main.util.map.Object2DInt;

import java.awt.*;
import java.util.ArrayList;

public class GUIManager {
    public UnitAndStructureMenu unitMenu=new UnitAndStructureMenu(0,((int) GamePanel.height)/20,
            ((int) GamePanel.width)*3/20, ((int) GamePanel.height)*17/20);
    public ArrayList<Button> buttonList;
    public ArrayList<GameImage> imageList;
    public GameStateManager gsm;
    public TurnIndicator turnIndicator=new TurnIndicator((int)GamePanel.width*4/10,0,
            (int)(GamePanel.width)/5, (int) (GamePanel.height*0.03f));

    public NewDrawText money=new NewDrawText(""+RoundManager.getCurrentPlayer().money,
            new Rectangle((int) (GamePanel.width*0.03f),0,
                    (int) (GamePanel.width*0.03f),(int) (GamePanel.width*0.03f)),
            1f,
            Color.white);

    public final int coinSize=(int)(GamePanel.width*0.03f);
    public final int buttonSize=(int)(GamePanel.width*0.05f);

    public void render(Graphics g){
        unitMenu.render(g);
        for(Button b : buttonList){
            b.render(g);

//            g.setColor(Color.red);
//            g.drawRect(b.getRectangle().x,b.getRectangle().y,
//                    b.getRectangle().width,b.getRectangle().height);
        }
        for(GameImage image : imageList){
            image.render(g);

        }
        turnIndicator.render(g);
        money.render(g);

    }

    public boolean capture(int x,int y){
        for(Button b:buttonList){
            if(b.isInside(x,y))
                return true;
        }
        return unitMenu.isInside(x,y);
    }

    public void loadIcons(){
        imageList=new ArrayList<>();
        GameImage temp=new GameImage("gameicons/coin.png");
        temp.setSize(coinSize, coinSize);
        temp.setPosition(0,0);
        imageList.add(temp);

    }
    public void setGsm(GameStateManager gsm1){
        gsm=gsm1;
    }
    public void loadButtons(){
        buttonList=new ArrayList<>();
        Button button=new Button("gameicons/menu_icon.png",
                (int) GamePanel.width -  buttonSize, 0,
                buttonSize,buttonSize,()->{
            if(gsm!=null)
                gsm.set(GameStateManager.STATES.PAUSE);
        });
        buttonList.add(button);
        button=new Button("gameicons/end_turn.png",
                (int) GamePanel.width -  buttonSize,
                (int) GamePanel.height- buttonSize,
                buttonSize,buttonSize,()->{
            RoundManager.passTurn();
        });
        buttonList.add(button);
        button=new Button("gameicons/undo.png",
                0, (int) GamePanel.height -  buttonSize,
                buttonSize,buttonSize,()->{
            RoundManager.goBack();
        });
        buttonList.add(button);
    }

    {
        loadIcons();
        loadButtons();
    }

    public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        unitMenu.input(mouseHandler, keyHandler);
        for(Button b:buttonList){
            b.input(mouseHandler, keyHandler);
        }
    }

    public void moneyUpdate(){
        money.setText(""+RoundManager.getCurrentPlayer().money);
    }

    public void update(){
        unitMenu.update();
        for(Button b:buttonList){
            b.update();
        }
        moneyUpdate();
    }
}
