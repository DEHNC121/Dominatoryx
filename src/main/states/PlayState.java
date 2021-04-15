package main.states;

import javafx.util.Pair;
import main.graphics.Sprite;
import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.RoundManager;
import main.util.map.WorldMap;

import java.awt.*;
public class PlayState extends GameState
{
    public static WorldMap worldMap;
    public static RoundManager roundManager;
    Sprite sprite;
    Camera camera;
    public enum GameMapSize{
        SMALL,
        MEDIUM,
        LARGE;
        public Pair<Integer, Integer> size;
        static {
            SMALL.size=new Pair<>(20,40);
            MEDIUM.size=new Pair<>(30,60);
            LARGE.size=new Pair<>(40,80);
        }

    }
    public GameMapSize gameMapSize=GameMapSize.SMALL;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        worldMap= new WorldMap(gameMapSize);
        roundManager=new RoundManager(4);
        camera=new Camera();
    }

    @Override
    public void update() {
        camera.update();
        RoundManager.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        camera.input(mouse, key);
        RoundManager.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        camera.render(g);


    }
}

