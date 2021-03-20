package main.states;

import main.graphics.Sprite;
import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.WorldMap;

import java.awt.*;
import javafx.util.Pair;
public class PlayState extends GameState
{
    public static WorldMap worldMap;
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
        camera=new Camera();
    }

    @Override
    public void update() {
        camera.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        camera.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        camera.render(g);


    }
}

