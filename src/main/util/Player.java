package main.util;

public class Player {
    public boolean isDefeated;
    private int colour;
    public int money=10;
    PlayerControls controls;
    public Player(int colour){
        isDefeated=false;
        this.colour=colour;
        controls=new PlayerControls(this);
    }
    public int getColour(){return colour;}
    public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        controls.input(mouseHandler, keyHandler);
    }
    public void update(){controls.update();}
}
