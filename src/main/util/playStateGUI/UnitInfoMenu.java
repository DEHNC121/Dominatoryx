package main.util.playStateGUI;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.GameImage;
import main.states.GameStateManager;
import main.states.GameStyle;
import main.util.map.Object2DInt;
import main.util.map.WorldMap;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.awt.*;

public class UnitInfoMenu extends UnitStructureTab{
    int imageSize=width/2;
    int textHeight=height/20;
    int fontSize=20;
    int tabulator=width/10;

    public UnitInfoMenu() {
        super();
    }

    @Override
    public void specialRender(Graphics g) {
        GameImage image= WorldMap.selectedHexagon.unit.getImages(2);
        image.setPosition((width-imageSize)/2+x,y);
        image.setWidth(imageSize);
        image.setHeight(imageSize);
        image.render(g);
        printAttack(g,1);
        printHP(g,2);
        printMovement(g,3);
        printAttackCost(g,4);
    }
    public void printAttack(Graphics g,int line){
        DrawText drawText=new DrawText("Attack: "+getCurrUnit().getAttackValue(),
                new Object2DInt(x+tabulator,y+imageSize+(line+1)*textHeight,width,textHeight));
        drawText.setSize(fontSize);
        drawText.render(g);
    }
    public void printHP(Graphics g,int line){
        DrawText drawText=new DrawText("Health: "+getCurrUnit().getHealth()+
                "/"+getCurrUnit().getMaxHealth(),
                new Object2DInt(x+tabulator,y+imageSize+(line+1)*textHeight,width,textHeight));
        drawText.setSize(fontSize);
        drawText.render(g);
    }
    public void printMovement(Graphics g,int line){
        DrawText drawText=new DrawText("Movement Points: "+getCurrUnit().getMovement()+
                "/"+getCurrUnit().getMaxMovement(),
                new Object2DInt(x+tabulator,y+imageSize+(line+1)*textHeight,width,textHeight));
        drawText.setSize(fontSize);
        drawText.render(g);
    }
    public void printAttackCost(Graphics g,int line){
        DrawText drawText=new DrawText("Attack Cost: "+getCurrUnit().attackMovementCost()+"MP",
                new Object2DInt(x+tabulator,y+imageSize+(line+1)*textHeight,width,textHeight));
        drawText.setSize(fontSize);
        drawText.render(g);
    }
    @Override
    public Color getColour() {
        return GameStateManager.gameStyle.get(GameStyle.PALETTE.GROUND);
    }
    private Unit getCurrUnit(){return WorldMap.selectedHexagon.unit;}
    @Override
    public void update(){}
}
