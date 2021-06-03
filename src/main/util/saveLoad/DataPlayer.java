package main.util.saveLoad;

import main.util.Player;

public class DataPlayer {
    public int colour;
    public int money;
    DataPlayer(){
        colour=0;
        money=0;
    }
    DataPlayer(Player pl){
        colour=pl.getColour();
        money= pl.getMoney();
    }
}
