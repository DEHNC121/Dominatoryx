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
    public static boolean isClicked;
    public static WorldMap worldMap;
    public static RoundManager roundManager;
    Sprite sprite;
    Camera camera;
    Icon menuIcon, undo, end_turn, flag, coin;
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

    public static void setClicked (boolean isClicked) {PlayState.isClicked = isClicked; }

    public PlayState(GameStateManager gsm) {
        super(gsm);
        roundManager=new RoundManager(4);
        worldMap= new WorldMap(gameMapSize);
        camera=new Camera();
        loadIcons();
        setIconsDefault();
        isClicked = false;
    }

    public void loadIcons () {
        menuIcon = new Icon("gameicons/menu_icon.png");
        undo = new Icon("gameicons/undo.png");
        end_turn = new Icon("gameicons/end_turn.png");
        flag = new Icon ("gameicons/flag.png");
        coin = new Icon("gameicons/coin.png");
        menuIcon.setSize(50, 50);
        undo.setSize(50,50);
        end_turn.setSize(50, 50);
        flag.setSize(50, 50);
        coin.setSize(50, 50);

    }

    public void setIconsDefault () {
        menuIcon.setPosition((int) GamePanel.width - menuIcon.getWidth(), 0);
        undo.setPosition(0, ((int) GamePanel.height - undo.getHeight()));
        end_turn.setPosition((int) GamePanel.width - end_turn.getWidth(),
                (int) GamePanel.height - end_turn.getHeight());
    }

    public void setIconsClicked () {
        menuIcon.setPosition((int) GamePanel.width - menuIcon.getWidth(), 0);
        undo.setPosition(0, ((int) GamePanel.height - undo.getHeight() - 100));
        end_turn.setPosition(0,
                (int) GamePanel.height - end_turn.getHeight() - 200);
        flag.setPosition(0, (int) GamePanel.height - end_turn.getHeight() - 300);
        coin.setPosition(0,0);
    }

    @Override
    public void update() {
        camera.update();
        RoundManager.update();
        if (isClicked)
            setIconsClicked();
        else
            setIconsDefault();
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
        if (isClicked) {
            undo.render(g);
            end_turn.render(g);
            flag.render(g);
            coin.render(g);
        }
        else {
            undo.render(g);
            end_turn.render(g);
        }


    }
}

