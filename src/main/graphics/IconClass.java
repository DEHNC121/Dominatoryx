package main.graphics;

import main.util.map.Object2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public abstract class IconClass {
    private BufferedImage image;
    private Object2D object2D;

    public IconClass (String file) {
        image = loadImage(file);
        object2D = createObject2D ();
    }

    public abstract Object2D createObject2D();

    public BufferedImage loadImage (String file) {
        BufferedImage imageToReturn = null;
        try {
            imageToReturn = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        }
        catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return imageToReturn;
    }
}
