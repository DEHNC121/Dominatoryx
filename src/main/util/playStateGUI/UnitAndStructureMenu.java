package main.util.playStateGUI;

import main.GamePanel;
import main.util.map.Object2D;
import main.util.map.WorldMap;

import java.awt.*;


public class UnitAndStructureMenu {
    int x,y;
    int w,h;
    boolean varRender=false;
    boolean hasUnit=false;
    boolean showTab=true;
    TabSwitcher tabSwitcher=new TabSwitcher(this);
    UnitAndStructureMenu(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        w=width;
        h=height;
        hexagonSelected();
    }
    UnitStructureTab tab;
    public void hexagonSelected(){

        if(WorldMap.selectedHexagon!=null){
            varRender=true;
            //changes tabs based on hexagon selection

        }
        else
            varRender=false;


    }
    public void switchToUnit(){

    }
    public void switchToStructure(){
        tab=new StructureBuyMenu();
    }
    public void render(Graphics g){
        //rendering button
        if(!varRender)
            return;

        //rendering tab
        if(showTab){

            tab.render(g);
        }

    }
}
