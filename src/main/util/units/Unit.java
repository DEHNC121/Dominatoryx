package main.util.units;

import main.util.Player;
import main.util.map.Hexagon2D;

import java.util.Map;
import java.util.Objects;

public abstract class Unit {
    Player owner;
    Hexagon2D hexagon;
    Unit(Player pl,Hexagon2D hex){
        hexagon=hex;
        owner=pl;
        health=getMaxHealth();
        movement=0;
    }

    int health;
    int movement;
    public void refreshMove(){
        movement=getMaxMovement();
    }
    public void regenerate(){
        health=Math.min(health+getRegen(),getMaxHealth());
    }
    public void move(Hexagon2D newHexagon){
        if(newHexagon.unit!=null || newHexagon.owner!=owner)
            return;
        Map<Hexagon2D,Integer> neighbors=hexagon.getNeighbors(movement);
        if(!neighbors.containsKey(hexagon))
            return;
        hexagon.unit=null;
        newHexagon.unit=this;
        hexagon=newHexagon;
        movement-=neighbors.get(newHexagon);
    }
    public void attack(Hexagon2D newHexagon){
        if(attackMovementCost()>movement)
            return;
        Map<Hexagon2D,Integer> neighbors=hexagon.getNeighbors(1);
        if(!neighbors.containsKey(newHexagon))
            return;
        if(newHexagon.unit==null){
            movement-=attackMovementCost();
            newHexagon.newOwner(owner);
        } else {//newHexagon.unit!=null
            //TODO: consult, what should I do in that case
        }
    }
    public void takeDamage(int dmg){
        health-=dmg;
        if(health<=0){
            owner.unitList.remove(this);
        }
        hexagon.unit=null;
    }
    abstract public int getID();
    abstract public int getMaxHealth();
    abstract public int getMaxMovement();
    abstract public int attackMovementCost();
    abstract public int getRegen();
    abstract public int getAttackValue();
    abstract public int getCost();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(hexagon, unit.hexagon);
    }
    public void render(){

    }

}
