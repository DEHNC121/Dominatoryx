package main.util;

import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.structures.StructureMenuList;
import main.util.units.UnitMenuList;

import java.util.Random;

public class AI extends Player {
    long nextMoveTime = 0;
    public AI(int colour) {
        super(colour);
        isPlayer = false;
    }

    public enum moves {
        BUY_STRUCTURE,
        BUY_UNIT,
        ATTACK,
        MOVE
    }

    public void play () {
        //AI buys everything
        System.out.println("play");

        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this && hexagon.getUnit() != null) {
                if (hexagon.getUnit().getMovement() >= hexagon.getUnit().attackMovementCost()) {
                    Hexagon2D temp = hexagon.getRandomEnemyNeighbour();
                    if (temp != null) {
                        if (!doMove(moves.ATTACK, hexagon, temp, 0)){
                            return;
                        }
                    }
                }
            }
        }

        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this && hexagon.getUnit() != null) {
                if (hexagon.getUnit().getMovement() > 0) {
                    Hexagon2D temp = hexagon.getRandomNeighbour(hexagon.getUnit().getMovement());
                    if (temp != null && temp.getUnit() == null) {
                        if (!doMove(moves.MOVE, hexagon, temp, 0))
                            return;
                    }
                }
            }
        }

        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            //System.out.println("sieema");
            if (hexagon.getOwner() == this) {
                if (hexagon.getUnit() == null) {
                    for (int i : UnitMenuList.list.keySet()) {
                        if (hexagon.getOwner().getMoney() >= UnitMenuList.list.get(i).getCost() &&
                            !doMove(moves.BUY_UNIT, hexagon, null, i))
                            return;
                    }
                }
                if (hexagon.getStructure() == null) {
                    for (int i : StructureMenuList.list.keySet()) {
                        if (hexagon.getOwner().getMoney() >= StructureMenuList.list.get(i).getCost() &&
                                !doMove(moves.BUY_STRUCTURE, hexagon, null, i))
                            return;
                    }
                }
            }
        }

        System.out.println("yo");
        RoundManager.passTurn();
    }

    public boolean doMove (moves x, Hexagon2D hexStart, Hexagon2D hexEnd, int i) {

        if (System.currentTimeMillis()>nextMoveTime){
            System.out.println(System.currentTimeMillis() + " " + nextMoveTime);
            if (x == moves.MOVE) {
                hexStart.getUnit().move(hexEnd);
                System.out.println("MOVE");
                WorldMap.selectedHexagon = hexStart;
            }
            else if (x == moves.ATTACK) {
                hexStart.getUnit().attack(hexEnd);
                System.out.println("ATTACK");
            }
            else if (x == moves.BUY_STRUCTURE) {
                this.buyStructure(hexStart, i);
                System.out.println("BUY_STRUCTURE");
            }
            else if (x == moves.BUY_UNIT) {
                this.buyUnit(hexStart, i);
                System.out.println("BUY_UNIT");
            }
            nextMoveTime=System.currentTimeMillis()+10;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void update () {
        play();
    }

    public void greedy(Hexagon2D hexagon) {

    }


}
