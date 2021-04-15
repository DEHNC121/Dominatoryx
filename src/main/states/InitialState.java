package main.states;

import main.graphics.Icon;
import main.graphics.Sprite;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.GamePanel;

import java.awt.*;

public class InitialState extends GameState {
    Icon playIcon;
    Icon settingsIcon;
    Icon shutDownIcon;
    public InitialState (GameStateManager gsm) {
        super(gsm);
        playIcon = new Icon("initial/play_button.png");
        settingsIcon = new Icon("initial/settings_icon.png");
        shutDownIcon = new Icon("initial/shut_down.png");
        playIcon.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2);
        settingsIcon.setPosition(20, 20);
        shutDownIcon.setPosition((int) GamePanel.width - 20 - shutDownIcon.getWidth(),20);
    }
    @Override
    public void update () {

    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (playIcon.mouseOnIcon(mouse)) {
                GameState g = new PlayState(gsm);
                gsm.setPlayState(g);
                gsm.set(g);
            }
            if (shutDownIcon.mouseOnIcon(mouse)) {
                System.exit(0); //should be written differently
            }
            if (settingsIcon.mouseOnIcon(mouse)) {
                gsm.set(new SettingsState(gsm));
            }
            mouse.setIsClicked(false);
        }
    }

    @Override
    public void render (Graphics2D g) {
        playIcon.render(g);
        settingsIcon.render(g);
        shutDownIcon.render(g);
    }

}
