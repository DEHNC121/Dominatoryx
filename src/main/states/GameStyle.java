package main.states;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameStyle {

    public enum PALETTE {
        BACKGROUND,
        GROUND,
        MAIN,
        FRONT,
        UPFRONT
    }
    private final Map<PALETTE, Color> palette;

    public GameStyle(ArrayList<Color> colorList) {
        palette=new HashMap<>(PALETTE.values().length);
        int i=0;
        for (PALETTE p:PALETTE.values()){
            palette.put(p,colorList.get(i++));
        }

    }

    public Color get(PALETTE p){
        return palette.get(p);
    }
}
