package main.util.playStateGUI;

import main.GamePanel;
import main.util.RoundManager;
import main.util.map.WorldMap;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.awt.*;

public class UnitBuyMenu extends UnitStructureTab{
    int unitFieldHeight= ((int)GamePanel.height)/10;
    int unitFieldYSeparator=0;
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

            g.fillRect(x,y+unitFieldYSeparator+
                    i*(unitFieldYSeparator+unitFieldHeight),width,unitFieldHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x,y+unitFieldYSeparator+
                    i*(unitFieldYSeparator+unitFieldHeight),width,unitFieldHeight);


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
