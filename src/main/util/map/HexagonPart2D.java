package main.util.map;

public class HexagonPart2D extends Object2D {
    public HexagonPart2D(){
        super();
    }
    public HexagonPart2D(int x,int y,int w,int h){
        super(x, y, w, h);
    }
    public enum Parts{
        UPPER,LOWER,LEFT,RIGHT
    }
    public Parts whichPart(int x,int y){
        int counter=0;
        if(y<=((height/width)*(x-this.x)+this.y))
            counter++;//LOWER or LEFT
        if(y<=((-height/width)*(x-this.x)+this.y+height))
            counter+=2;//RIGHT or LOWER
        switch (counter){
            case 1:
                return Parts.LEFT;
            case 2:
                return Parts.RIGHT;
            case 3:
                return Parts.LOWER;
            default:
                return Parts.UPPER;
        }


    }
}
