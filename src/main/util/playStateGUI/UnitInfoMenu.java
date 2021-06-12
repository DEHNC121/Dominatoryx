package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.GameImage;
import main.graphics.NewDrawText;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.map.Object2DInt;
import main.util.map.WorldMap;
import main.util.structures.StructureMenuList;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnitInfoMenu extends UnitStructureTab{
    int imageSize=width/2;
    int textHeight=height/20;
    int fontSize=20;
    int tabulator=width/10;

    private ArrayList<NewDrawText> fields;
    public UnitInfoMenu() {
        super();
        fields=new ArrayList<>();
        ArrayList<String> text=new ArrayList<>(Arrays.asList(
                "Attack: "+getCurrUnit().getAttackValue(),"Health: "+getCurrUnit().getHealth()+"/"+getCurrUnit().getMaxHealth()
                ,"Movement Points: "+getCurrUnit().getMovement()+"/"+getCurrUnit().getMaxMovement(),"Attack Cost: "+getCurrUnit().attackMovementCost()+"MP"));

        for(int i = 0; i< 4; i++){
            fields.add(new NewDrawText(text.get(i),
                    new Rectangle(0,y+imageSize+(i+1)*textHeight,width,textHeight),
                    1f,
                    GameStateManager.gameStyle.get(GameStyle.PALETTE.UPFRONT)
            ));
        }
    }

    @Override
    public void specialRender(Graphics g) {

        GameImage image= WorldMap.selectedHexagon.unit.getImages(2);
        image.setPosition((width-imageSize)/2+x,y);
        image.setWidth(imageSize);
        image.setHeight(imageSize);
        image.render(g);


        for (NewDrawText dt:fields){
            dt.render(g);
        }

    }
    @Override
    public Color getColour() {
        return GameStateManager.gameStyle.get(GameStyle.PALETTE.GROUND);
    }
    private Unit getCurrUnit(){return WorldMap.selectedHexagon.unit;}
    @Override
    public void update(){

    }
}
