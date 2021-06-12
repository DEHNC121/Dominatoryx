package main.util.units;

import main.graphics.GameImage;
import main.util.Player;
import main.util.map.Hexagon2D;
import main.util.saveLoad.DataUnit;

import java.util.ArrayList;
import java.util.Arrays;

public class Tank extends Unit {
    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("units/tank0.png"),
                    new GameImage("units/tank1.png"),
                    new GameImage("units/tank2.png")) );
    public Tank(Player pl, Hexagon2D hex) {
        super(pl, hex);
    }
    public Tank(DataUnit du){
        super(du);
    }
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public int getMaxHealth() {
        return 12;
    }

    @Override
    public int getMaxMovement() {
        return 9;
    }

    @Override
    public int attackMovementCost() {
        return 3;
    }

    @Override
    public int getRegen() {
        return 2;
    }

    @Override
    public int getAttackValue() {
        return 5;
    }

    @Override
    public int getCost() {
        return 35;
    }

    @Override
    public int getIncome() {
        return 0;
    }

    @Override
    public GameImage getImages(int i) {
        return gameImages.get(i);
    }
}
