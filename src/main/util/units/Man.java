package main.util.units;

import main.graphics.GameImage;
import main.util.Player;
import main.util.map.Hexagon2D;

import java.util.ArrayList;
import java.util.Arrays;

public class Man extends Unit{

    static private ArrayList<GameImage> gameImages=new ArrayList<>(
            Arrays.asList(  new GameImage("units/man0.png"),
                    new GameImage("units/man1.png"),
                    new GameImage("units/man2.png")) );

    public Man(Player pl, Hexagon2D hex) {
        super(pl, hex);
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public int getMaxHealth() {
        return 2;
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
        return 0;
    }

    @Override
    public int getAttackValue() {
        return 1;
    }

    @Override
    public int getCost(){
        return 16;
    }

    @Override
    public int getIncome() { return 0; }

    @Override
    public GameImage getImages(int i) {
        return gameImages.get(i);
    }
}
