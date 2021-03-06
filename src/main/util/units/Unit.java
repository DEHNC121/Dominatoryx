package main.util.units;

import main.graphics.GameImage;
import main.util.Player;
import main.util.RoundManager;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.saveLoad.DataUnit;

import java.awt.*;
import java.util.Map;
import java.util.Objects;

public abstract class Unit {
    Player owner;
    Hexagon2D hexagon;

    int health;
    int movement;

    public Unit(Player pl,Hexagon2D hex){
        hexagon=hex;
        owner=pl;
        health=getMaxHealth();
        movement=0;
    }
    public Unit(DataUnit du){
        owner=RoundManager.getFromColour(du.playerColour);
        hexagon=WorldMap.hexagonMap[du.hexNumber];
        health= du.health;
        movement= du.movement;
    }
    public int getMovement(){return movement;}
    public Player getOwner(){return owner;}
    public Hexagon2D getHex(){return hexagon;}
    public void setHealth(int health) { this.health = health; }
    public void setMovement(int movement) { this. movement = movement; }
    public void setHexagon(Hexagon2D hexagon) {this.hexagon = hexagon; }
    public void setOwner(Player owner) { this.owner = owner; }
    public void refreshMove(){
        movement=getMaxMovement();
    }

    public void regenerate(){
        health=Math.min(health+getRegen(),getMaxHealth());
    }

    public void move(Hexagon2D newHexagon){
        System.out.println("Move initialized");
        if(newHexagon.unit!=null || newHexagon.owner!=owner){
            System.out.println("Owner check");
            return;

        }

        Map<Hexagon2D,Integer> neighbors=hexagon.getSpecialNeighbors(movement,
                hex->hex.owner == RoundManager.getCurrentPlayer(), (hex)->false);
        if(!neighbors.containsKey(newHexagon)){
            System.out.println("Range check");
            return;
        }

        hexagon.unit=null;
        newHexagon.unit=this;
        hexagon=newHexagon;
        movement-=neighbors.get(newHexagon);
        WorldMap.selectHexagon(newHexagon);
    }

    public void attack(Hexagon2D newHexagon){
        System.out.println("Attack initialized");
        if(attackMovementCost()>movement){
            System.out.println("movement: "+movement);//debug
            return;
        }

        Map<Hexagon2D,Integer> neighbors=hexagon.getNeighbors(1);
        if(!neighbors.containsKey(newHexagon)){
            System.out.println("neighbors");
            return;
        }
        movement-=attackMovementCost();
        if(newHexagon.unit==null){
            hexagon.unit=null;
            newHexagon.newOwner(owner);
            hexagon=newHexagon;
            newHexagon.unit=this;
            WorldMap.selectHexagon(newHexagon);
        } else {//newHexagon.unit!=null
            //MTG first strike attacker/blocker rules
            System.out.println("Fight");
            newHexagon.unit.takeDamage(getAttackValue());
            if(newHexagon.unit==null){
                System.out.println("He dead");
                newHexagon.newOwner(owner);
                hexagon.unit=null;
                newHexagon.unit=this;
                hexagon=newHexagon;
                WorldMap.selectHexagon(newHexagon);
            }
            else{
                takeDamage(newHexagon.unit.getAttackValue());
            }
        }
    }

    public void takeDamage(int dmg){
        health-=dmg;
        if(health<=0){
            hexagon.unit=null;
            //hexagon = null;
        }
    }
    public int getHealth(){return health;}
    abstract public int getID();
    abstract public int getMaxHealth();
    abstract public int getMaxMovement();
    abstract public int attackMovementCost();
    abstract public int getRegen();
    abstract public int getAttackValue();
    abstract public int getCost();
    abstract public int getIncome();
    abstract public GameImage getImages(int i);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(hexagon, unit.hexagon);
    }
    public void render(Graphics g, int x, int y, int width, int height, float scale){
        int textureNumb=-1;
        if (scale<=2){
            textureNumb=0;
        }else if (2<scale && scale<4){
            textureNumb=1;
        }else{
            textureNumb=2;
        }
        getImages(textureNumb).render(g,x+(int)(1.5*scale),y,width,height);
    }

}
