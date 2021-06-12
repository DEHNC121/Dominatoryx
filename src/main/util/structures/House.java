package main.util.structures;

import main.graphics.GameImage;
import main.util.map.Hexagon2D;

import java.util.ArrayList;
import java.util.Arrays;

public class House extends Structure{

    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("structures/house0.png"),
                    new GameImage("structures/house1.png"),
                    new GameImage("structures/house2.png")) );

    public House(Hexagon2D hex){
        super(hex);
    }

    @Override
    public int getID() { return 0; }

    @Override
    public int getCost() { return 12; }

    @Override
    public GameImage getImages(int i) {
        return gameImages.get(i);
    }

    @Override
    public int getIncome() {
        return 3;
    }

}
