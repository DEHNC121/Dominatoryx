package main.util.map;

import main.GamePanel;
import main.states.PlayState;

public class WorldMap {

    public static Hexagon2D[] hexagonMap;
    public static HexagonPart2D[] Parts;

    public WorldMap(PlayState.GameMapSize gameMapSize) {

        GamePanel.width,GamePanel.height;

        int hexagonPartsNumber=(6*widthHexagonNumber+1)*(2*heightheightHexagonNumber+1);
        int hexagonNumber=widthHexagonNumber*heightheightHexagonNumber;

        Parts=new HexagonPart2D[hexagonPartsNumber];
        hexagonMap=new Hexagon2D[hexagonNumber];

    }
}
