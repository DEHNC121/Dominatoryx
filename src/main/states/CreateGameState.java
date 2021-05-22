package main.states;

import main.GamePanel;
import main.graphics.DrawText;
import main.graphics.ScrollField;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.map.Object2DInt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class CreateGameState extends GameState{
    public static ArrayList<String> playersListH = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
    public static ArrayList<String> playersListM = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
    public static ArrayList<String> playersListL = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
    ScrollField playersSF, mapSizeSF;
    DrawText back, play, players, mapSize;
    Object2DInt backObject, playObject;
    public CreateGameState (GameStateManager gsm) {
        super(gsm);
        backObject = new Object2DInt(0, 0, 300, 100);
        playObject = new Object2DInt((int) (GamePanel.width * 0.4f), (int) (GamePanel.height * 0.9f) - 100, 300, 100);
        back = new DrawText("BACK",
                new Object2DInt(0, 100, 300, 100));
        play = new DrawText("PLAY",
                new Object2DInt((int) (GamePanel.width * 0.4f), (int) (GamePanel.height * 0.9f), 300, 100));
        mapSize = new DrawText("Map size",
                new Object2DInt((int) (GamePanel.width*0.3f), (int) (GamePanel.height * 0.25f),0,0));
        players = new DrawText("Players",
                new Object2DInt((int) (GamePanel.width*0.3f), (int) (GamePanel.height * 0.65f),0,0));

//      mapSizeSF = new ScrollField(new Object2DInt(0, (int) GamePanel.height / 2, 100, 100),
//                new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8")), "TimesRoman", Font.PLAIN, 100,
//                new Color(0,0,0));

        mapSizeSF = new ScrollField(new Object2DInt((int) (GamePanel.width * 0.6f), (int) (GamePanel.height * 0.25f) - 200,100,100),
                new ArrayList<>(Arrays.asList("L", "M", "H")),
                new Color(0,0,0));
        playersSF = new ScrollField(new Object2DInt((int) (GamePanel.width * 0.6f), (int) (GamePanel.height * 0.65f) - 200,100,100),
               playersListH, new Color(0,0,0,255));
    }
    public int size (String s) {
        if (s.equals("L"))
            return 0;
        if (s.equals("M"))
            return 1;
        if (s.equals("H"))
            return 2;
        return -1;
    }
    @Override
    public void update() {
        mapSizeSF.update();
        if (mapSizeSF.getEntry() == "L")
            playersSF.setArray(playersListL);
        if (mapSizeSF.getEntry() == "M")
            playersSF.setArray(playersListM);
        if (mapSizeSF.getEntry() == "H")
            playersSF.setArray(playersListH);
        playersSF.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (backObject.isInside(mouse.getX(), mouse.getY())) {
                gsm.set(GameStateManager.STATES.MENU);
            }
            if (playObject.isInside(mouse.getX(), mouse.getY())) {
                gsm.setNew(GameStateManager.STATES.PLAY,
                        new PlayState(gsm, size(mapSizeSF.getEntry()), Integer.parseInt(playersSF.getEntry())));
            }
            mouse.setIsClicked(false);
        }
        mapSizeSF.input(mouse, key);
        playersSF.input(mouse, key);
        mouse.setRotation(0);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(100,0,0,100));
        g.fillRect(backObject.getX(), backObject.getY(), backObject.getWidth(), backObject.getHeight());
        g.fillRect(playObject.getX(), playObject.getY(), playObject.getWidth(), playObject.getHeight());
        back.render(g);
        play.render(g);
        mapSize.render(g);
        players.render(g);
        mapSizeSF.render(g);
        playersSF.render(g);

    }
}
