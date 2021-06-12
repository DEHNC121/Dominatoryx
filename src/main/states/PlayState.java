package main.states;

import main.graphics.GameImage;
import main.graphics.Sprite;
import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.RoundManager;
import main.util.map.PlayStateWindow;
import main.util.map.WorldMap;
import main.util.playStateGUI.GUIManager;
import main.util.saveLoad.DataPack;

import java.awt.*;
import java.util.HashMap;

public class PlayState extends GameState
{
    public static HashMap<String, GameImage> imageMap = null;

    public static int numberOfPlayers;
    public static int numberOfAi;
    public static GameMapSize gameMapSize;

    public static boolean isClicked;

    private WorldMap worldMap;
    private RoundManager roundManager;
    PlayStateWindow psWindow;

    public static void setClicked (boolean isClicked) {PlayState.isClicked = isClicked; }

    public void init(GameStateManager gsm) {
        roundManager=new RoundManager(PlayState.numberOfPlayers,PlayState.numberOfAi);
        worldMap= new WorldMap(PlayState.gameMapSize);
        psWindow=new PlayStateWindow(gsm);
        isClicked = false;
        RoundManager.checkAI();
    }

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init(gsm);
    }
    public PlayState(GameStateManager gsm, GameMapSize size, int numberOfPlayers,int numberOfAi) {
        super(gsm);
        PlayState.numberOfPlayers = numberOfPlayers;
        PlayState.numberOfAi = numberOfAi;
        PlayState.gameMapSize = size;
        init(gsm);
    }

    public PlayState(GameStateManager gsm, DataPack dp){
        super(gsm);
        RoundManager.loadData(dp.drm);
        worldMap=new WorldMap(dp.dm);
        psWindow=new PlayStateWindow(gsm);
        isClicked=false;
    }

    @Override
    public void update() {
        psWindow.update();
        RoundManager.update();
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

