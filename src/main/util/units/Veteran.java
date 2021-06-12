package main.util.units;

import main.graphics.GameImage;
import main.util.Player;
import main.util.map.Hexagon2D;

import java.util.ArrayList;
import java.util.Arrays;

public class Veteran extends Unit{

    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("units/man0.png"),
                    new GameImage("units/man1.png"),
                    new GameImage("units/man2.png")) );

    public Veteran(Player pl, Hexagon2D hex) {
        super(pl, hex);
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public int getMaxHealth() {
        return 8;
    }

    @Override
    public int getMaxMovement() {
        return 6;
    }

    @Override
    public int attackMovementCost() {
        return 2;
    }

    @Override
    public int getRegen() {
        return 1;
    }

    @Override
    public int getAttackValue() {
        return 3;
    }

    @Override
    public int getCost(){
        return 20;
    }

    @Override
    public int getIncome() { return -2; }

    @Override
    public GameImage getImages(int i) {
        return gameImages.get(i);
    }
}
