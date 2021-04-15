package main.states;


import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.Graphics2D;

public class GameStateManager
{

    private GameState currentState;


    public GameStateManager () {
        currentState = new InitialState(this);
        //currentState=new PlayState(this);
    }

    public void set(GameState g){
        currentState=g;
    }

    public void update ()
    {
        currentState.update();
    }
    public void input (MouseHandler mouse, KeyHandler key) {
        currentState.input(mouse, key);
    }
    public void render (Graphics2D g) {
        currentState.render(g);
    }
}
