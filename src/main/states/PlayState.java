package main.states;

import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.WorldMap;

import java.awt.*;
import javafx.util.Pair;
public class PlayState extends GameState
{
    public static WorldMap worldMap;
    public enum GameMapSize{
        SMALL,
        MEDIUM,
        LARGE;
        public Pair<Integer, Integer> size;
        static {
            SMALL.size=new Pair<>(20,20);
            MEDIUM.size=new Pair<>(30,30);
            LARGE.size=new Pair<>(40,40);
        }

    }
    public GameMapSize gameMapSize=GameMapSize.SMALL;



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

