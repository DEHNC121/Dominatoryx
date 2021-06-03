package main.util.saveLoad;

import main.util.Player;
import main.util.map.Hexagon2D;
import main.util.units.Unit;
import main.util.units.UnitMenuList;
import com.fasterxml.jackson.annotation.JsonProperty;
public class DataUnit {
    @JsonProperty("hexNumber")
    public int hexNumber;
    @JsonProperty("playerColour")
    public int playerColour;
    public int health;
    public int movement;
    @JsonProperty("UnitID")
    public int id;
    public DataUnit(){}
    public DataUnit(Unit u){
        playerColour=u.getOwner().getColour();
        health=u.getHealth();
        hexNumber=u.getHex().getIndex();
        id=u.getID();
        movement=u.getMovement();
    }
    static public Unit unpackUnit(DataUnit du){
        if(du==null)
            return null;
        try{
            Unit unitExample=UnitMenuList.list.get(du.id);
            return unitExample.getClass().getConstructor(DataUnit.class).newInstance(du);
        }catch (Exception ignored){}
        return null;
    }
}
