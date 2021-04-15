package main.states;

import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;

public abstract class GameState
{

    protected GameStateManager gsm;

    public GameState (GameStateManager g)
    {
        gsm = g;
    }

    public abstract void update ();
    public abstract void input (MouseHandler mouse, KeyHandler key);
    public abstract void render (Graphics2D g);

}
