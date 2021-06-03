package main.util.saveLoad;

import main.util.map.Hexagon2D;

public class DataHex {
    public int position=0;
    public int playerColour=0;
    public boolean water=false;
    public boolean hasOwner=false;
    public DataStructure structure=null;
    public DataUnit unit=null;
    DataHex(){}
    DataHex(Hexagon2D hex){
        water= hex.water;
        position=hex.getIndex();
        if(hex.owner==null){
            hasOwner=false;
            playerColour=-1;
        }
        else{
            playerColour=hex.owner.getColour();
            hasOwner=true;
        }
        if(hex.unit!=null){
            unit=new DataUnit(hex.unit);
        }
        if(hex.structure!=null){
            structure=new DataStructure(hex.structure);
        }
    }
}
