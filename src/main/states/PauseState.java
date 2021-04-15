package main.states;

import main.GamePanel;
import main.graphics.Icon;
import main.util.KeyHandler;
import main.util.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class PauseState extends GameState{

    Icon resume;
    Icon restart;
    Icon save;
    Icon menu;

    public PauseState (GameStateManager gsm) {
        super(gsm);
        resume = new Icon("pauseicons/back_icon.png");
        restart = new Icon("pauseicons/back_icon.png");
        save = new Icon("pauseicons/back_icon.png");
        menu = new Icon("pauseicons/back_icon.png");
        int w = resume.getHeight();
        resume.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 - 3 * w / 2);
        restart.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 - w / 2);
        save.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 + w / 2);
        menu.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 + 3 * w / 2);
        resume.setLabel("Resume");
        restart.setLabel("Restart");
        save.setLabel("Save");
        menu.setLabel("Menu");

    }

    @Override
    public void update () {

    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (resume.mouseOnIcon(mouse)) {
                gsm.set(gsm.getPlayState());
            }
            if (restart.mouseOnIcon(mouse)) {
                GameState g = new PlayState(gsm);
                gsm.setPlayState(g);
                gsm.set(g);
            }
            if (save.mouseOnIcon(mouse)) {

            }
            if (menu.mouseOnIcon(mouse)) {
                gsm.set(new InitialState(gsm));
            }
            mouse.setIsClicked(false);
        }
    }

    @Override
    public void render (Graphics2D g) {
        resume.render(g);
        restart.render(g);
        save.render(g);
        menu.render(g);
    }
}
