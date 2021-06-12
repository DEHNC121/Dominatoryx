package main.util.units;

import main.graphics.GameImage;
import main.util.Player;
import main.util.map.Hexagon2D;
import main.util.saveLoad.DataUnit;

import java.util.ArrayList;
import java.util.Arrays;

public class TankMk2 extends Unit{
    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("units/tank2_0.png"),
                    new GameImage("units/tank2_1.png"),
                    new GameImage("units/tank2_2.png")) );
    public TankMk2(Player pl, Hexagon2D hex) {
        super(pl, hex);
    }
    public TankMk2(DataUnit du){
        super(du);
    }
    @Override
    public int getID() {
        return 3;
    }

    @Override
    public int getMaxHealth() {
        return 18;
    }

    @Override
    public int getMaxMovement() {
        return 12;
    }

    @Override
    public int attackMovementCost() {
        return 3;
    }

    @Override
    public int getRegen() {
        return 3;
    }

    @Override
    public int getAttackValue() {
        return 8;
    }

    @Override
    public int getCost() {
        return 50;
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
