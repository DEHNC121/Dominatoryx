package main.util.playStateGUI;

import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.Object2D;
import main.util.map.WorldMap;

import java.awt.*;


public class UnitAndStructureMenu {
    Rectangle rectangle;
    boolean varRender=false;
    boolean hasUnit=false;
    boolean showTab=true;
    private int tabNumber;
    UnitStructureTab tab=new UnitBuyMenu();
    MenuMinimalizer minimalizer=new MenuMinimalizer(this);
    TabSwitcher tabSwitcher=new TabSwitcher(this);

    public int getTabNumber() {
        return tabNumber;
    }

    UnitAndStructureMenu(int x, int y, int width, int height){
        tabNumber=0;
        rectangle=new Rectangle(x,y,width,height);
        hexagonSelected();
    }

    public void hexagonSelected(){

        if(WorldMap.selectedHexagon!=null){
            varRender=true;
            //changes tabs based on hexagon selection
            boolean newHasUnit=(WorldMap.selectedHexagon.unit!=null);
            if(newHasUnit!=hasUnit){
                hasUnit=newHasUnit;
                if(tab instanceof StructureBuyMenu)
                    return;
                switchToUnit();
            }

        }
        else
            varRender=false;


    }

    public void switchToUnit(){
        tabNumber=0;

        if(hasUnit)
            tab=new UnitInfoMenu();
        else
            tab=new UnitBuyMenu();
    }

    public void switchToStructure(){
        tabNumber=1;

        tab=new StructureBuyMenu();
    }

    public void render(Graphics g){
        //rendering button
        if(!varRender)
            return;
        minimalizer.render(g);
        //rendering tab
        if(showTab) {
            tabSwitcher.render(g);
            tab.render(g);
        }
    }

    public boolean isInside(int x,int y){
        if(!varRender)
            return false;
        if(!showTab)
            return minimalizer.isInside(x,y);
        return rectangle.contains(x,y);
    }

    public void input(MouseHandler mouse, KeyHandler key){
        minimalizer.input(mouse, key);
        tabSwitcher.input(mouse, key);
        tab.input(mouse, key);
    }

    public void update(){
        minimalizer.update();
        tabSwitcher.update();
        tab.update();
    }
}
