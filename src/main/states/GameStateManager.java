package main.states;


import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager
{

    private ArrayList <GameState> states;

    private GameState currentState;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public GameStateManager () {
        currentState=new PlayState(this);
        //currentState=new MenuState(this,800,1150);
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
