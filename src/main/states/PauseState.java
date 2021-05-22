package main.states;

import main.GamePanel;
import main.graphics.GameImage;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;

public class PauseState extends GameState{

    GameImage resume;
    GameImage restart;
    GameImage save;
    GameImage menu;

    public PauseState (GameStateManager gsm) {
        super(gsm);
        resume = new GameImage("pauseicons/back_icon.png");
        restart = new GameImage("pauseicons/back_icon.png");
        save = new GameImage("pauseicons/back_icon.png");
        menu = new GameImage("pauseicons/back_icon.png");
        int w = resume.getHeight();
        resume.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 - 3 * w / 2);
        restart.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 - w / 2);
        save.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 + w / 2);
        menu.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 + 3 * w / 2);
        //resume.setLabel("Resume");
        //restart.setLabel("Restart");
        //save.setLabel("Save");
        //menu.setLabel("Menu");

    }

    @Override
    public void update () {

    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {
        if (mouse.getIsClicked()) {
            if (resume.mouseOnIcon(mouse)) {
                gsm.set(GameStateManager.STATES.PLAY);
            }
            if (restart.mouseOnIcon(mouse)) {
                gsm.setNew(GameStateManager.STATES.PLAY,
                        new PlayState(gsm, PlayState.size, PlayState.nOfPlayers));
            }
            if (save.mouseOnIcon(mouse)) {
                //todo save

            }
            if (menu.mouseOnIcon(mouse)) {
                gsm.set(GameStateManager.STATES.MENU);
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
