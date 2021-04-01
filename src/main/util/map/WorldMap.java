package main.util.map;

import javafx.util.Pair;
import main.GamePanel;
import main.states.PlayState;

public class WorldMap {

    public static Hexagon2D[] hexagonMap;
    public static HexagonPart2D[] Parts;
    public static int widthHexagonNumber;
    public static int heightHexagonNumber;
    public static int hexagonPartsInRow;
    public static int hexagonPartsInColumn;
    public static float hexagonPartWidth;
    public static float hexagonPartHeight;
    public WorldMap(PlayState.GameMapSize gameMapSize) {

        //GamePanel.width,GamePanel.height;
        widthHexagonNumber=gameMapSize.size.getValue();
        heightHexagonNumber=gameMapSize.size.getKey();
        hexagonPartsInRow=(3*widthHexagonNumber+1);
        hexagonPartsInColumn=(2*heightHexagonNumber+1);
        int hexagonPartsNumber=hexagonPartsInRow*hexagonPartsInColumn;
        int hexagonNumber=widthHexagonNumber*heightHexagonNumber;

        Parts=new HexagonPart2D[hexagonPartsNumber];
        hexagonPartWidth = (float) GamePanel.width / (float) hexagonPartsInRow;
        hexagonPartHeight = (float) GamePanel.height / (float) hexagonPartsInColumn;
        float hexagonPartWidthDiff = (float) GamePanel.width / (float) hexagonPartsInRow;
        float hexagonPartHeightDiff= (float) GamePanel.height / (float) hexagonPartsInColumn;
        for(int i=0;i<hexagonPartsInRow;i++){
            for(int j=0;j<hexagonPartsInColumn;j++){
                Parts[j*hexagonPartsInRow+i]=
                        new HexagonPart2D(i*hexagonPartWidthDiff,j*hexagonPartHeightDiff,
                                hexagonPartWidth,hexagonPartHeight);
            }
        }
        hexagonMap=new Hexagon2D[hexagonNumber];
        for(int i=0;i<widthHexagonNumber;i++){
            for(int j=0;j<heightHexagonNumber;j++){
                int firstPartRow=2*j;
                int firstPartColumn=3*i;
                boolean isEven=(i%2==0);//even are higher
                if(!isEven)
                    firstPartRow++;
                int firstPartIndex=hexagonPartsInRow*firstPartRow+firstPartColumn;
                hexagonMap[i+j*widthHexagonNumber]=new Hexagon2D(Parts[firstPartIndex].getX(),Parts[firstPartIndex].getY(),
                        4*hexagonPartWidthDiff,2*hexagonPartHeightDiff)
                                .setPosition(i+j*widthHexagonNumber);
                setPartsInHexagon(hexagonMap[i+j*widthHexagonNumber],firstPartIndex);
                if(i==0 || j==0 || i==widthHexagonNumber-1 ||j==heightHexagonNumber-1)
                    hexagonMap[i+j*widthHexagonNumber].setBorder(true);
            }
        }

    }
    static private void setPartsInHexagon(Hexagon2D hexagon,int firstPartIndex){
        //used in constructor
        Parts[firstPartIndex].setRightHexagon(hexagon).setLowerHexagon(hexagon);
        Parts[firstPartIndex+1].setAllHexagons(hexagon);
        Parts[firstPartIndex+2].setAllHexagons(hexagon);
        Parts[firstPartIndex+3].setLeftHexagon(hexagon).setLowerHexagon(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow].setRightHexagon(hexagon).setUpperHexagon(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow+1].setAllHexagons(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow+2].setAllHexagons(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow+3].setLeftHexagon(hexagon).setUpperHexagon(hexagon);
    }
    static public HexagonPart2D getHexagonPart(float x,float y){
        //returns clicked HexagonPart: O(log(Parts.len()))
        int a=(int) Math.floor(x/hexagonPartWidth);
        int b=(int) Math.floor(y/hexagonPartHeight);
        return Parts[a+b*hexagonPartsInRow];
    }
    static public Hexagon2D getHexagon(float x,float y){
        return getHexagonPart(x,y).getHexagon(x,y);
    }
}
