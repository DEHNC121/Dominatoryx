package main.util.map;

import javafx.util.Pair;
import main.GamePanel;
import main.states.PlayState;

public class WorldMap {

    public static Hexagon2D[] hexagonMap;
    public static HexagonPart2D[] Parts;
    public static int widthHexagonNumber;
    public static int heightHexagonNumber;

    public WorldMap(PlayState.GameMapSize gameMapSize) {

        //GamePanel.width,GamePanel.height;
        widthHexagonNumber=gameMapSize.size.getKey();
        heightHexagonNumber=gameMapSize.size.getValue();
        int hexagonPartsInRow=(3*widthHexagonNumber+1);
        int hexagonPartsInColumn=(2*heightHexagonNumber+1);
        int hexagonPartsNumber=hexagonPartsInRow*hexagonPartsInColumn;
        int hexagonNumber=widthHexagonNumber*heightHexagonNumber;

        Parts=new HexagonPart2D[hexagonPartsNumber];
        int hexagonPartWidth=GamePanel.width/hexagonPartsInRow;
        int hexagonPartHeight=GamePanel.height/hexagonPartsInColumn;
        int hexagonPartWidthDiff=GamePanel.width/hexagonPartsInRow;
        int hexagonPartHeightDiff=GamePanel.height/hexagonPartsInColumn;
        for(int i=0;i<hexagonPartsInRow;i++){
            for(int j=0;j<hexagonPartsInColumn;j++){
                Parts[j*hexagonPartsInColumn+i]=
                        new HexagonPart2D(i*hexagonPartWidthDiff,j*hexagonPartHeightDiff,
                                hexagonPartWidth,hexagonPartHeight);
            }
        }
        hexagonMap=new Hexagon2D[hexagonNumber];

    }
}
