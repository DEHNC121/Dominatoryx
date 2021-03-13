package main.states;

import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.WorldMap;

import java.awt.*;

public class PlayState extends GameState
{
    public static WorldMap worldMap;
    public enum GameMapSize{
        SMAll,
        MIDDLE,
        LARGE
    }
    public GameMapSize gameMapSize=GameMapSize.SMAll;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        worldMap= new WorldMap(gameMapSize);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
    }

    @Override
    public void render(Graphics2D g) {

    }
}

