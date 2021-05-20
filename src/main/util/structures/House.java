package main.util.structures;

import main.graphics.GameImage;
import main.util.map.Hexagon2D;

public class House extends Structure{
    static GameImage image;
    public House(Hexagon2D hex){
        super(hex);
    }

    @Override
    public GameImage getImage() {
        return image;
    }
}
