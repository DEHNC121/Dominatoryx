package main.states;

import javafx.util.Pair;
import main.GamePanel;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlayState extends GameState
{
    public static HashMap<String, GameImage> imageMap = null;
    public static int nOfPlayers;
    public static int size; // 0-L, 1-M, 2-H
    public static boolean isClicked;
    public static WorldMap worldMap;
    public static RoundManager roundManager;
    public GameMapSize gameMapSize;
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
    public ArrayList<GameMapSize> sizes = new ArrayList<>(Arrays.asList(GameMapSize.SMALL, GameMapSize.MEDIUM, GameMapSize.LARGE));


    public static void setClicked (boolean isClicked) {PlayState.isClicked = isClicked; }

    public PlayState(GameStateManager gsm, int size, int nOfPlayers) {
        super(gsm);
        PlayState.nOfPlayers = nOfPlayers;
        PlayState.size = size;
        roundManager=new RoundManager(nOfPlayers);
        worldMap= new WorldMap(sizes.get(size));
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

