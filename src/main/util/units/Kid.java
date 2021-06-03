package main.util.units;

import main.graphics.GameImage;
import main.util.Player;
import main.util.map.Hexagon2D;
import main.util.saveLoad.DataUnit;

import java.util.ArrayList;
import java.util.Arrays;

public class Kid extends Unit{

    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("units/kid0.png"),
                            new GameImage("units/kid1.png"),
                            new GameImage("units/kid2.png")) );

    public Kid(Player pl, Hexagon2D hex) {
        super(pl, hex);
    }
    public Kid(DataUnit du){ super(du); }

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
        return 50;
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

    @Override
    public int getIncome() { return 0; }

    @Override
    public GameImage getImages(int i) {
        return gameImages.get(i);
    }
}
