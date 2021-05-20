package main.states;


import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.Graphics2D;

public class GameStateManager
{

    private GameState currentState;
    private GameState playState = null;
    private GameState menuState = null;

    public GameStateManager () {
        currentState = new MenuState(this);
        //currentState=new PlayState(this);
    }

    public GameState getPlayState () { return playState; }
    public GameState getMenuState () { return menuState; }
    public void set(GameState g){
        currentState=g;
    }
    public void setPlayState (GameState playState) { this.playState = playState; }
    public void setMenuState (GameState menuState) { this.menuState = menuState; }

    public void update ()
    {
        currentState.update();
    }
    public void input (MouseHandler mouse, KeyHandler key) {
        currentState.input(mouse, key);
    }
    public void render (Graphics2D g) {
        //System.out.println(currentState);
        currentState.render(g);
    }
}
