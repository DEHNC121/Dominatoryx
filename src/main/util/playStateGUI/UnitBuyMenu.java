package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.GameImage;
import main.graphics.DrawText;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.RoundManager;
import main.util.map.WorldMap;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.awt.*;
import java.util.ArrayList;

public class UnitBuyMenu extends UnitStructureTab{

    private ArrayList<DrawText> text;

    int unitFieldHeight= (int)(GamePanel.height*0.1)-2;
    int unitFieldYSeparator=0;
    int textHeight=(int)(unitFieldHeight*0.4);

    public UnitBuyMenu(){
        super();
        text=new ArrayList<>();
    }

    @Override
    public void specialRender(Graphics g) {
        for(int i = 0; i< UnitMenuList.list.size(); i++){
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

            int tempX=x-2;
            int tempY=y+unitFieldYSeparator+
                    i*(unitFieldYSeparator+unitFieldHeight+1);

            g.fillRect(tempX,tempY,width,unitFieldHeight);

            GameImage image= unit.getImages(2);
            image.setPosition(tempX+3,tempY);
            image.setWidth(unitFieldHeight);
            image.setHeight(unitFieldHeight);
            image.render(g);

            if (text.size()<(i+1)*3){
                text.add(new DrawText(""+unit.getClass().getSimpleName(),
                        new Rectangle(tempX+unitFieldHeight,tempY+textHeight/4,width-unitFieldHeight,textHeight),
                        1f,
                        GameStyle.PALETTE.BACKGROUND
                ));

                text.add(new DrawText("Cost: ",
                        new Rectangle(tempX+unitFieldHeight,tempY+(int)(1.25*textHeight),(int)((width-unitFieldHeight)*0.5),textHeight),
                        1f,
                        GameStyle.PALETTE.BACKGROUND
                ));
                text.add(new DrawText(unit.getCost()+"$",
                        new Rectangle(tempX+unitFieldHeight+(int)((width-unitFieldHeight)*0.5),tempY+(int)(1.25*textHeight),(int)((width-unitFieldHeight)*0.5),textHeight),
                        1f,
                        new Color(14, 101, 98)
                ));
            }

            for (DrawText dt:text){
                dt.render(g);
            }

        }

    }

    @Override
    public Color getColour() {
        return GameStateManager.gameStyle.get(GameStyle.PALETTE.GROUND);
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
        for (DrawText dt:text){
            dt.update();
        }
    }
}
