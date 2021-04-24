package main.util;

public class PlayerControls {
    Player player;
    boolean passTurn=false;
    boolean surrender=false;
    PlayerControls(Player pl){
        player=pl;
    }
    public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        if(keyHandler.up.down){passTurn=true;}
        if(keyHandler.down.down){surrender=true;}
    }
    public void setToDefaults(){
        passTurn=false;
        surrender=false;
    }
    public void update(){
        if(passTurn){
            RoundManager.passTurn();
        }
        if(surrender){
            player.surrender();
        }
        setToDefaults();
    }
}
