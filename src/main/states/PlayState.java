package main.states;

import javafx.util.Pair;
import main.GamePanel;
import main.graphics.Icon;
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
    Icon menuIcon;
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
        roundManager=new RoundManager(4);
        worldMap= new WorldMap(gameMapSize);
        camera=new Camera();
        loadIcons();
        setIcons();
    }

    public void loadIcons () {
        menuIcon = new Icon("gameicons/menu_icon.png");
    }

    public void setIcons () {
        menuIcon.setPosition((int) GamePanel.width - menuIcon.getWidth(), 0);

    }

    @Override
    public void update() {
        camera.update();
        RoundManager.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (menuIcon.mouseOnIcon(mouse)) {
                gsm.set(new PauseState(gsm));
            }
        }
        camera.input(mouse, key);
        RoundManager.input(mouse, key);
        mouse.setIsClicked(false);
    }

    @Override
    public void render(Graphics2D g) {
        camera.render(g);
        menuIcon.render(g);


    }
}

