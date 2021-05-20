package main.util.structures;

import main.graphics.GameImage;
import main.util.map.Hexagon2D;

import java.awt.*;

public abstract class Structure {
    Hexagon2D hexagon2D;
    public Structure(Hexagon2D hex){
        hexagon2D=hex;
    }

    public abstract GameImage getImage();

    public void render(Graphics g, int x, int y, int width, int height, float scale){
        int textureNumb=-1;
        if (scale<=2){
            textureNumb=0;
        }else if (2<scale && scale<4){
            textureNumb=1;
        }else{
            textureNumb=2;
        }
//        getImages(textureNumb).render(g,x,y,width,height);
    }
}
