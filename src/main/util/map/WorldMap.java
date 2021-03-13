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

        GamePanel.width,GamePanel.height;
        widthHexagonNumber=gameMapSize.size.getKey();
        heightHexagonNumber=gameMapSize.size.getValue();

        int hexagonPartsNumber=(6*widthHexagonNumber+1)*(2*heightHexagonNumber+1);
        int hexagonNumber=widthHexagonNumber*heightHexagonNumber;

        Parts=new HexagonPart2D[hexagonPartsNumber];


        hexagonMap=new Hexagon2D[hexagonNumber];

    }
}
