package main.util.playStateGUI;

import main.GamePanel;
import main.util.map.WorldMap;

import java.awt.*;

public class GUIManager {
    static public UnitMenu unitMenu=new UnitMenu(0,0,200, (int) GamePanel.height);
    static public void render(Graphics g){
            if(WorldMap.selectedHexagon!=null)
                unitMenu.render(g);
    }
}
