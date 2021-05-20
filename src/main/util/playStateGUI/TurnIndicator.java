package main.util.playStateGUI;

import main.graphics.DrawText;
import main.graphics.Sprite;
import main.util.RoundManager;
import main.util.map.Object2DInt;

import java.awt.*;

public class TurnIndicator extends Object2DInt {
    Sprite sprite=new Sprite("map/HexagonMapV.7.png");
    TurnIndicator(int x,int y,int w,int h){
        super(x,y,w,h);

    }
    public void render(Graphics g){
        g.drawImage(sprite.getSprite(3, RoundManager.getCurrentPlayer().getColour()),
                x,y,width,height,null);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
    }
}
