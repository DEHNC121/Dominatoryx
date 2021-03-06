package main.states;

import main.GamePanel;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameStateManager
{
    public enum STATES {
        MENU,
        PLAY,
        CREATE,
        SETTINGS,
        PAUSE,
        SAVE_LOAD
    }
    private final Map<STATES,GameState> states;
    private GameState currentState;
    public static GameStyle gameStyle;

    public GameStateManager () {
        states=new HashMap<>(STATES.values().length);
        gameStyle=GameStyle.loadOrDefault();
        InitializeStates();
        set(STATES.MENU);

//        setNew(GameStateManager.STATES.PLAY,
//                new PlayState(this, GameMapSize.SMALL,
//                        1,
//                        2));

    }

    private void InitializeStates(){
        states.put(STATES.MENU,new MenuState(this));
        states.put(STATES.CREATE,new CreateGameState(this));
        states.put(STATES.PAUSE,new PauseState(this));
        states.put(STATES.SETTINGS,new SettingsState(this));
    }

    public GameStyle getGameStyle() {
        return gameStyle;
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

    public void setStyle(GameStyle gs){gameStyle=gs;}
    public void saveStyle(){gameStyle.saveStyle();}

    public void update ()
    {
        currentState.update();
    }

    public void input (MouseHandler mouse, KeyHandler key) {
        currentState.input(mouse, key);
    }

    public void render (Graphics2D g) {

        g.setColor(gameStyle.get(GameStyle.PALETTE.BACKGROUND));
        g.fillRect(0, 0, (int) GamePanel.width, (int) GamePanel.height);
        currentState.render(g);
    }

}
