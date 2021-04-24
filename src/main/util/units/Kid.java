package main.util.units;

import main.util.Player;
import main.util.map.Hexagon2D;

public class Kid extends Unit{
    Kid(Player pl, Hexagon2D hex) {
        super(pl, hex);
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int getMaxHealth() {
        return 1;
    }

    @Override
    public int getMaxMovement() {
        return 2;
    }

    @Override
    public int attackMovementCost() {
        return 2;
    }

    @Override
    public int getRegen() {
        return 0;
    }

    @Override
    public int getAttackValue() {
        return 1;
    }
    @Override
    public int getCost(){
        return 8;
    }
}
