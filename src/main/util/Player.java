package main.util;

import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.units.Unit;
import main.util.units.UnitMenuList;

import java.util.LinkedList;
import java.util.List;

public class Player {

    public boolean isDefeated;
    public int controlledFields;
    private int colour;
    public int money=10;
    PlayerControls controls;

    public Player(int colour){
        isDefeated=false;
        this.colour=colour;
        controls=new PlayerControls(this);
    }

    public int getColour(){return colour;}

    public boolean deathCheck(){
        if(controlledFields==0){
            die();
            return true;
        }
        return false;
    }

    public void die(){
        isDefeated=true;
//        unitList.clear();
    }

    public void surrender(){
        die();
        RoundManager.passTurn();
    }

    public boolean buyUnit(Hexagon2D hexagon2D,int unitID){
        if(hexagon2D.owner!=this ||hexagon2D.unit!=null)
            return false;
        if(!UnitMenuList.list.containsKey(unitID))
            return false;
        Unit unitExample=UnitMenuList.list.get(unitID);
        if(unitExample.getCost()<money)
            return false;
        try{
            Object[] args=new Object[2];
            args[0]=this;
            args[1]=hexagon2D;
            Unit newUnit= unitExample.getClass().getConstructor(Player.class,Hexagon2D.class).newInstance(args);
//            unitList.add(newUnit);
            hexagon2D.unit=newUnit;
            return true;
        }catch (Exception ignored){}
        return false;
    }

    public void refreshAndRegenUnit(){
//        for(Unit u:unitList){
//            u.refreshMove();
//            u.regenerate();
//        }
    }

    public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        controls.input(mouseHandler, keyHandler);
    }

    public void update(){controls.update();}
}
