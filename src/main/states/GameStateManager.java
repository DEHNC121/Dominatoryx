package main.states;

import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class GameStateManager
{
    public enum STATES {
        MENU,
        PLAY,
        CREATE,
        SETTINGS,
        PAUSE
    }
    private final Map<STATES,GameState> states;
    private GameState currentState;

    public GameStateManager () {
        states=new HashMap<>(STATES.values().length);
        InitializeStates();
        set(STATES.MENU);
    }

    private void InitializeStates(){
        states.put(STATES.MENU,new MenuState(this));
        states.put(STATES.CREATE,new CreateGameState(this));
        states.put(STATES.PAUSE,new PauseState(this));
    }

    public void set(STATES state){
        if (states.get(state)!=null){
            currentState=states.get(state);
        }else {
            System.out.println("Error: Null set");
        }
    }

    public void setNew(STATES state,GameState gameState){
        states.put(state,gameState);
        set(state);
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
