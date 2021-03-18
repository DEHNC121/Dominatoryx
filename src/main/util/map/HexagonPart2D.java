package main.util.map;

public class HexagonPart2D extends Object2D {
    public HexagonPart2D(){
        super();
    }
    public HexagonPart2D(int x,int y,int w,int h){
        super(x, y, w, h);
    }
    public enum Parts{
        UPPER,LOWER,LEFT,RIGHT,OUT
    }
    public Parts whichPart(int x,int y){
        if(!isInside(x,y))
            return Parts.OUT;
        int counter=0;
        if(y<=((height/width)*(x-this.x)+this.y))
            counter++;//UPPER or RIGHT
        if(y<=((-height/width)*(x-this.x)+this.y+height))
            counter+=2;//LEFT or UPPER
        switch (counter){
            case 1:
                return Parts.RIGHT;
            case 2:
                return Parts.LEFT;
            case 3:
                return Parts.UPPER;
            default:
                return Parts.LOWER;
        }

    }
    Hexagon2D upper=null;
    Hexagon2D lower=null;
    Hexagon2D left=null;
    Hexagon2D right=null;
    public HexagonPart2D setAllHexagons(Hexagon2D hexagon){
        upper=hexagon;
        lower=hexagon;
        left=hexagon;
        right=hexagon;
        return this;
    }
    public HexagonPart2D setUpperHexagon(Hexagon2D hexagon){
        upper=hexagon;
        return this;
    }
    public HexagonPart2D setLowerHexagon(Hexagon2D hexagon){
        lower=hexagon;
        return this;
    }
    public HexagonPart2D setLeftHexagon(Hexagon2D hexagon){
        left=hexagon;
        return this;
    }
    public HexagonPart2D setRightHexagon(Hexagon2D hexagon){
        right=hexagon;
        return this;
    }
    public Hexagon2D getHexagon(int x,int y){
        Parts part=whichPart(x,y);
        switch (part){
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case UPPER:
                return upper;
            case LOWER:
                return lower;
            default:
                return null;
        }

    }
}
