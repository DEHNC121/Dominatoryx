package main.states;

import javafx.util.Pair;
import main.GamePanel;
import main.graphics.Image;
import main.graphics.Sprite;
import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.RoundManager;
import main.util.map.WorldMap;

import java.awt.*;
import java.util.HashMap;

public class PlayState extends GameState
{
    public static HashMap<String, Image> imageMap = null;
    public static boolean isClicked;
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
    public GameMapSize gameMapSize=GameMapSize.LARGE;

    public static void setClicked (boolean isClicked) {PlayState.isClicked = isClicked; }

    public PlayState(GameStateManager gsm) {
        super(gsm);
        roundManager=new RoundManager(4);
        worldMap= new WorldMap(gameMapSize);
        camera=new Camera();
        imageMap = new HashMap<>();
        loadIcons();
        setIconsDefault();
        isClicked = false;
    }

    public void loadIcons () {
        imageMap.put("menuImage", new Image("gameicons/menu_icon.png"));
        imageMap.put("undo", new Image("gameicons/undo.png"));
        imageMap.put("end_turn", new Image("gameicons/end_turn.png"));
        imageMap.put("coin", new Image("gameicons/coin.png"));
        imageMap.get("menuImage").setSize((int) (GamePanel.width*0.03f), (int) (GamePanel.width*0.03f));
        imageMap.get("undo").setSize((int) (GamePanel.width*0.03f), (int) (GamePanel.width*0.03f));
        imageMap.get("end_turn").setSize((int) (GamePanel.width*0.03f), (int) (GamePanel.width*0.03f));
        imageMap.get("coin").setSize((int) (GamePanel.width*0.03f), (int) (GamePanel.width*0.03f));
    }

    public void setIconsDefault () {
        imageMap.get("menuImage").setPosition((int) GamePanel.width -  imageMap.get("menuImage").getWidth(), 0);
        imageMap.get("undo").setPosition(0, ((int) GamePanel.height - imageMap.get("undo").getHeight()));
        imageMap.get("end_turn").setPosition((int) GamePanel.width - imageMap.get("end_turn").getWidth(),
                (int) GamePanel.height - imageMap.get("end_turn").getObject2DInt().getHeight());
    }

    public void setIconsClicked () {
        imageMap.get("menuImage").setPosition((int) GamePanel.width - imageMap.get("menuImage").getWidth(), 0);
        imageMap.get("undo").setPosition(0, ((int) GamePanel.height - imageMap.get("undo").getHeight() - 100));
        imageMap.get("end_turn").setPosition(0,
                (int) GamePanel.height - imageMap.get("end_turn").getHeight() - 200);
        imageMap.get("coin").setPosition(0,0);
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
            if (imageMap.get("menuImage").mouseOnIcon(mouse)) {
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
        imageMap.get("menuImage").render(g);
        if (isClicked) {
            imageMap.get("undo").render(g);
            imageMap.get("end_turn").render(g);
            imageMap.get("coin").render(g);
        }
        else {
            imageMap.get("undo").render(g);
            imageMap.get("end_turn").render(g);
        }

    }
}

