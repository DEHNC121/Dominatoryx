package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.GameImage;
import main.util.RoundManager;
import main.util.map.Object2DInt;
import main.util.map.WorldMap;
import main.util.structures.Structure;
import main.util.structures.StructureMenuList;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.awt.*;

public class StructureBuyMenu extends UnitStructureTab{
    int structureFieldHeight= ((int) GamePanel.height)/10;
    int structureFieldYSeparator=0;
    int fontSize=20;
    int textHeight = structureFieldHeight/4;

    public StructureBuyMenu () {
        super();
    }

    @Override
    public void specialRender(Graphics g) {
        for(int i = 0; i< StructureMenuList.list.size(); i++){
            Structure structure = StructureMenuList.list.get(i);
            if(structure == null){
                System.out.println("Structure null for i= " + i);
                continue;
            }
            if(structure.getCost()> RoundManager.getCurrentPlayer().money){
                g.setColor(Color.GRAY);
            }
            else{
                g.setColor(Color.PINK);
            }
            int tempX=x;
            int tempY=y+structureFieldYSeparator+
                    i*(structureFieldYSeparator+structureFieldHeight);
            g.fillRect(tempX,tempY,width,structureFieldHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x,y+structureFieldYSeparator+
                    i*(structureFieldYSeparator+structureFieldHeight),width,structureFieldHeight);
            GameImage image= structure.getImages(2);
            image.setPosition(tempX,tempY);
            image.setWidth(structureFieldHeight);
            image.setHeight(structureFieldHeight);
            image.render(g);
            DrawText drawText=new DrawText(""+structure.getClass().getSimpleName(),
                    new Object2DInt(tempX+structureFieldHeight,tempY+1*textHeight,width,textHeight));
            drawText.setSize(fontSize);
            drawText.render(g);
            drawText= new DrawText("Cost: "+structure.getCost()+"$",
                    new Object2DInt(tempX+structureFieldHeight,tempY+2*textHeight,width,textHeight));
            drawText.setSize(fontSize);
            drawText.render(g);
            //place for unit info to render
        }
    }

    @Override
    public Color getColour() {
        return Color.ORANGE;
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
            int index=realY/structureFieldHeight;
            if(!StructureMenuList.list.containsKey(index))
                return;
            boolean res= RoundManager.getCurrentPlayer().buyStructure(WorldMap.selectedHexagon,index);
            System.out.println("Buying structure " + index + " with " + res);
        }
    }
}
