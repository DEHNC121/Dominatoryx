package main.util.map.structures;

import main.graphics.GameImage;
import main.util.map.Hexagon2D;

import java.awt.*;

public abstract class Structure {
    Hexagon2D hexagon2D;
    public Structure(Hexagon2D hex){
        hexagon2D=hex;
    }

    public abstract GameImage getImage();

    public void render(Graphics g){

    }
}
