package main.states;

import main.graphics.DrawText;
import main.graphics.GameImage;
import main.graphics.ScrollField;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.GamePanel;
import main.util.map.Object2DInt;
import java.util.*;
import java.awt.*;

public class MenuState extends GameState {
    GameImage playImage;
    GameImage settingsImage;
    GameImage shutDownImage;
    ScrollField sf;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playImage = new GameImage("initial/play_button.png");
        settingsImage = new GameImage("initial/settings_icon.png");
        shutDownImage = new GameImage("initial/shut_down.png");
        playImage.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2);
        settingsImage.setPosition(20, 20);
        shutDownImage.setPosition((int) GamePanel.width - 20 - shutDownImage.getWidth(),20);
        sf = new ScrollField(new Object2DInt(0, (int) GamePanel.height / 2, 100, 100),
                new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8")), "TimesRoman", Font.PLAIN, 100,
                new Color(0,0,0));
    }
    @Override
    public void update () {
        sf.update();
    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (playImage.mouseOnIcon(mouse)) {
                gsm.set(GameStateManager.STATES.CREATE);
            }
            if (shutDownImage.mouseOnIcon(mouse)) {
                System.exit(0); //todo should be written differently
            }
            if (settingsImage.mouseOnIcon(mouse)) {
                gsm.set(GameStateManager.STATES.SETTINGS);
            }
            mouse.setIsClicked(false);
        }
        sf.input(mouse, key);
    }

    @Override
    public void render (Graphics2D g) {
        playImage.render(g);
        settingsImage.render(g);
        shutDownImage.render(g);


        DrawText dt=new DrawText("TimesRoman","Hello World", Font.PLAIN, 100,new Object2DInt(100,100,0,0),new Color(0,0,0,255));
        dt.render(g);
        sf.render(g);
    }

}
