package main.util;

public class PlayerControls {
    Player player;
    boolean passTurn=false;
    PlayerControls(Player pl){
        player=pl;
    }
    public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        if(keyHandler.up.down){passTurn=true;}
    }
    public void update(){
        if(passTurn){
            RoundManager.passTurn();
            passTurn=false;
        }
    }
}
