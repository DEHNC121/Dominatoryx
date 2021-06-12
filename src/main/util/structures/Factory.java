package main.util.structures;

import main.graphics.GameImage;
import main.util.map.Hexagon2D;
import main.util.saveLoad.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;

public class Factory extends Structure {
    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("structures/House0.png"),
                    new GameImage("structures/House1.png"),
                    new GameImage("structures/House2.png")) );
    public Factory(Hexagon2D hex) {
        super(hex);
    }

    @Override
    public GameImage getImages(int i) {
        return gameImages.get(i);
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int getIncome() {
        return 7;
    }

    @Override
    public int getCost() {
        return 14;
    }
}
