package main.util.saveLoad;

import main.util.map.WorldMap;

public class DataMap {
    public long seed;
    public DataHex[] hexagonMap;
    public int widthHexagonNumber;
    public int heightHexagonNumber;
    DataMap(){

    }
    static public DataMap save(){
        DataMap out=new DataMap();
        out.seed=WorldMap.seed;
        out.widthHexagonNumber=WorldMap.widthHexagonNumber;
        out.heightHexagonNumber=WorldMap.heightHexagonNumber;
        out.hexagonMap=new DataHex[WorldMap.hexagonMap.length];
        for(int i=0;i<out.hexagonMap.length;i++){
            out.hexagonMap[i]=new DataHex(WorldMap.hexagonMap[i]);
        }
        return out;
    }
}
