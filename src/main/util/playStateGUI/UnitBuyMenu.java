package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.GameImage;
import main.util.RoundManager;
import main.util.map.Object2DInt;
import main.util.map.WorldMap;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.awt.*;

public class UnitBuyMenu extends UnitStructureTab{
    int unitFieldHeight= ((int)GamePanel.height)/10;
    int unitFieldYSeparator=0;
    int fontSize=20;
    int textHeight=unitFieldHeight/4;
    public UnitBuyMenu(){
        super();
    }

    @Override
    public void specialRender(Graphics g) {
        //TODO: this will render over map with too many units to buy
        //  Find a way to not render/partially render those outside of menu (incl. info, unit texture etc.)
        for(int i = 0; i< UnitMenuList.list.size(); i++){
            //System.out.println("iterating as it should");
            Unit unit=UnitMenuList.list.get(i);
            if(unit ==null){
                System.out.println("Unit null for i="+i);
                continue;
            }
            if(unit.getCost()> RoundManager.getCurrentPlayer().money){
                g.setColor(Color.GRAY);
            }
            else{
                g.setColor(Color.PINK);
            }
            int tempX=x;
            int tempY=y+unitFieldYSeparator+
                    i*(unitFieldYSeparator+unitFieldHeight);
            g.fillRect(tempX,tempY,width,unitFieldHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x,y+unitFieldYSeparator+
                    i*(unitFieldYSeparator+unitFieldHeight),width,unitFieldHeight);
            GameImage image= unit.getImages(2);
            image.setPosition(tempX,tempY);
            image.setWidth(unitFieldHeight);
            image.setHeight(unitFieldHeight);
            image.render(g);
            DrawText drawText=new DrawText(""+unit.getClass().getSimpleName(),
                    new Object2DInt(tempX+unitFieldHeight,tempY+1*textHeight,width,textHeight));
            drawText.setSize(fontSize);
            drawText.render(g);
            drawText= new DrawText("Cost: "+unit.getCost()+"$",
                    new Object2DInt(tempX+unitFieldHeight,tempY+2*textHeight,width,textHeight));
            drawText.setSize(fontSize);
            drawText.render(g);
            //place for unit info to render
        }

    }

    @Override
    public Color getColour() {
        return Color.RED;
    }
    @Override
    public void update() {
        if(isClicked){
            if(!isInside(mouseX,mouseY))
                return;
            int realX=mouseX-x;
            int realY=mouseY-y;
            if(realX>width)
                return;
            int index=realY/unitFieldHeight;
            if(!UnitMenuList.list.containsKey(index))
                return;
            boolean res=RoundManager.getCurrentPlayer().buyUnit(WorldMap.selectedHexagon,index);
            System.out.println("Buying unit "+index+" with "+res);
        }
    }
}
