package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.GameImage;
import main.graphics.DrawText;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.RoundManager;
import main.util.map.WorldMap;
import main.util.structures.Structure;
import main.util.structures.StructureMenuList;

import java.awt.*;
import java.util.ArrayList;

public class StructureBuyMenu extends UnitStructureTab{
    private ArrayList<DrawText> text;

    int structureFieldHeight= (int)(GamePanel.height*0.1)-2;
    int structureFieldYSeparator=0;
    int textHeight=(int)(structureFieldHeight*0.4);

    public StructureBuyMenu () {
        super();
        text=new ArrayList<>();
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

            int tempX=x-2;
            int tempY=y+structureFieldYSeparator+
                    i*(structureFieldYSeparator+structureFieldHeight+1);

            g.fillRect(tempX,tempY,width,structureFieldHeight);

            GameImage image= structure.getImages(2);
            image.setPosition(tempX,tempY);
            image.setWidth(structureFieldHeight);
            image.setHeight(structureFieldHeight);
            image.render(g);



            if (text.size()<(i+1)*3){
                text.add(new DrawText(""+structure.getClass().getSimpleName(),
                        new Rectangle(tempX+structureFieldHeight,tempY+textHeight/4,width-structureFieldHeight,textHeight),
                        1f,
                        GameStyle.PALETTE.BACKGROUND
                ));

                text.add(new DrawText("Cost: ",
                        new Rectangle(tempX+structureFieldHeight,tempY+(int)(1.25*textHeight),(int)((width-structureFieldHeight)*0.5),textHeight),
                        1f,
                        GameStyle.PALETTE.BACKGROUND
                ));
                text.add(new DrawText(structure.getCost()+"$",
                        new Rectangle(tempX+structureFieldHeight+(int)((width-structureFieldHeight)*0.5),tempY+(int)(1.25*textHeight),(int)((width-structureFieldHeight)*0.5),textHeight),
                        1f,
                        new Color(14, 101, 98)
                ));
            }

            for (DrawText dt:text){
                dt.render(g);
            }

            //place for structure info to render
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
            int index=realY/structureFieldHeight;
            if(!StructureMenuList.list.containsKey(index))
                return;
            boolean res= RoundManager.getCurrentPlayer().buyStructure(WorldMap.selectedHexagon,index);
            System.out.println("Buying structure " + index + " with " + res);
        }
        for (DrawText dt:text){
            dt.update();
        }

    }
}
