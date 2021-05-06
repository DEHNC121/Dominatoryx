package main.states;

import main.graphics.DrawText;
import main.graphics.Image;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.GamePanel;
import main.util.map.Object2DInt;

import java.awt.*;

public class MenuState extends GameState {
    Image playImage;
    Image settingsImage;
    Image shutDownImage;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        playImage = new Image("initial/play_button.png");
        settingsImage = new Image("initial/settings_icon.png");
        shutDownImage = new Image("initial/shut_down.png");
        playImage.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2);
        settingsImage.setPosition(20, 20);
        shutDownImage.setPosition((int) GamePanel.width - 20 - shutDownImage.getWidth(),20);
    }
    @Override
    public void update () {

    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (playImage.mouseOnIcon(mouse)) {
                GameState g = new PlayState(gsm);
                gsm.setPlayState(g);
                gsm.set(g);
            }
            if (shutDownImage.mouseOnIcon(mouse)) {
                System.exit(0); //should be written differently
            }
            if (settingsImage.mouseOnIcon(mouse)) {
                gsm.set(new SettingsState(gsm));
            }
            mouse.setIsClicked(false);
        }
    }

    @Override
    public void render (Graphics2D g) {
        playImage.render(g);
        settingsImage.render(g);
        shutDownImage.render(g);


        DrawText dt=new DrawText("TimesRoman","Hello World", Font.PLAIN, 100,new Object2DInt(100,100,0,0),new Color(0,0,0,255));
        dt.render(g);
    }

}
