package main.util.saveLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.structures.Structure;
import main.util.structures.StructureMenuList;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

public class DataStructure {
    public int hexNumber=-1;
    @JsonProperty("StructureID")
    public int id=-1;
    DataStructure(){}
    public DataStructure(Structure s){
        hexNumber=s.getHex().getIndex();
        id=s.getID();
    }
    static public Structure unpackStructure(DataStructure ds){
        if(ds==null)
            return null;
        try{
            Structure structureExample= StructureMenuList.list.get(ds.id);
            return structureExample.getClass().getConstructor(Hexagon2D.class).newInstance(WorldMap.hexagonMap[ds.hexNumber]);
        }catch (Exception ignored){}
        return null;
    }
}
