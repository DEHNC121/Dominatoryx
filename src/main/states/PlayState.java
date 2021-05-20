package main.states;

import javafx.util.Pair;
import main.graphics.GameImage;
import main.graphics.Sprite;
import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.RoundManager;
import main.util.map.PlayStateWindow;
import main.util.map.WorldMap;
import main.util.playStateGUI.GUIManager;

import java.awt.*;
import java.util.HashMap;

public class PlayState extends GameState
{
    public static HashMap<String, GameImage> imageMap = null;
    public static boolean isClicked;
    public static WorldMap worldMap;
    public static RoundManager roundManager;

    Sprite sprite;
    Camera camera;
    PlayStateWindow psWindow;
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
    public GameMapSize gameMapSize=GameMapSize.LARGE;

    public static void setClicked (boolean isClicked) {PlayState.isClicked = isClicked; }

    public PlayState(GameStateManager gsm) {
        super(gsm);
        roundManager=new RoundManager(4);
        worldMap= new WorldMap(gameMapSize);
        psWindow=new PlayStateWindow();
        GUIManager.setGsm(gsm);
        isClicked = false;
    }


    @Override
    public void update() {
        psWindow.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        psWindow.input(mouse, key);
        mouse.setIsClicked(false);
    }

    @Override
    public void render(Graphics2D g) {
        psWindow.render(g);

    }
}

