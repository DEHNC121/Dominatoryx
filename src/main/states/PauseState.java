package main.states;

import main.GamePanel;
import main.graphics.DrawButton;
import main.graphics.GameImage;
import main.graphics.DrawText;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PauseState extends GameState{
    ArrayList<DrawText> textFields;

    public PauseState(GameStateManager gsm) {
        super(gsm);


        textFields=new ArrayList<>();

        float start= 0.15f;
        float heightStep =0.16f;

        ArrayList<String> text=new ArrayList<>(Arrays.asList("Pause","Resume","Restart","Save","Menu"));

        DrawText.setFontName("OpenSans");


        textFields.add(
                new DrawText(text.get(textFields.size()),
                        new Rectangle(0, 0, (int) GamePanel.width,(int)(GamePanel.height*0.3)),
                        1f,
                        GameStyle.PALETTE.FRONT));
        while (textFields.size()< text.size()){

            textFields.add(
                    new DrawButton(text.get(textFields.size()),
                            new Rectangle(0, (int) (GamePanel.height*(start+heightStep*textFields.size())), (int) GamePanel.width,(int)(GamePanel.height*0.15)),
                            1f,
                            GameStyle.PALETTE.UPFRONT,0.4f));
        }

    }
    @Override
    public void update () {
        for (int i=1;i<textFields.size();i++){

            if (((DrawButton)textFields.get(i)).mouseClick==2) {
                switch (i) {
                    case 1 -> gsm.set(GameStateManager.STATES.PLAY);
                    case 2 ->  gsm.setNew(GameStateManager.STATES.PLAY,
                            new PlayState(gsm));
                    case 3 -> gsm.setNew(GameStateManager.STATES.SAVE_LOAD,
                                    new LoadState(gsm, LoadState.Way.SAVE));
                    case 4 -> gsm.set(GameStateManager.STATES.MENU);
                    default -> System.out.println("No function added");
                }

                ((DrawButton)textFields.get(i)).mouseClick=-1;
            }
        }

        for (DrawText dt:textFields){
            dt.update();
        }
    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {

        for (DrawText dt:textFields){
            if (dt instanceof DrawButton){
                ((DrawButton)dt).input(mouse);
            }
        }
    }

    @Override
    public void render (Graphics2D g) {

        for (DrawText dt:textFields){
            dt.render(g);
        }

    }
    GameImage resume;
    GameImage restart;
    GameImage save;
    GameImage menu;

//    public PauseState (GameStateManager gsm) {
//        super(gsm);
//        resume = new GameImage("pauseicons/back_icon.png");
//        restart = new GameImage("pauseicons/back_icon.png");
//        save = new GameImage("pauseicons/back_icon.png");
//        menu = new GameImage("pauseicons/back_icon.png");
//        int w = resume.getHeight();
//        resume.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 - 3 * w / 2);
//        restart.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 - w / 2);
//        save.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 + w / 2);
//        menu.setPositionCenter((int) GamePanel.width / 2, (int) GamePanel.height / 2 + 3 * w / 2);
//        //resume.setLabel("Resume");
//        //restart.setLabel("Restart");
//        //save.setLabel("Save");
//        //menu.setLabel("Menu");
//
//    }
//
//    @Override
//    public void update () {
//
//    }
//
//    @Override
//    public void input (MouseHandler mouse, KeyHandler key) {
//        if (mouse.getIsClicked()) {
//            if (resume.mouseOnIcon(mouse)) {
//                gsm.set(GameStateManager.STATES.PLAY);
//            }
//            if (restart.mouseOnIcon(mouse)) {
//                gsm.setNew(GameStateManager.STATES.PLAY,
//                        new PlayState(gsm, PlayState.gameMapSize, PlayState.numberOfPlayers));
//            }
//            if (save.mouseOnIcon(mouse)) {
//                gsm.setNew(GameStateManager.STATES.SAVE_LOAD,
//                        new LoadState(gsm, LoadState.Way.SAVE));
//            }
//            if (menu.mouseOnIcon(mouse)) {
//                gsm.set(GameStateManager.STATES.MENU);
//            }
//            mouse.setIsClicked(false);
//        }
//    }
//
//    @Override
//    public void render (Graphics2D g) {
//        resume.render(g);
//        restart.render(g);
//        save.render(g);
//        menu.render(g);
//    }
}
