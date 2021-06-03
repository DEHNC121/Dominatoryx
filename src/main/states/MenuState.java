package main.states;

import main.graphics.DrawButton;
import main.graphics.NewDrawText;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuState extends GameState {

    ArrayList<NewDrawText> textFields;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        textFields=new ArrayList<>();

        float start= 0.25f;
        float heightStep =0.16f;

        ArrayList<String> text=new ArrayList<>(Arrays.asList("Dominatoryx","New game","Load","Settings","Exit"));

        NewDrawText.setFontName("OpenSans");


        textFields.add(
                new NewDrawText(text.get(textFields.size()),
                        new Rectangle(0, (int) (GamePanel.height*0.15), (int) GamePanel.width,0),
                (int)(GamePanel.height*0.3),
                        gsm.getGameStyle().get(GameStyle.PALETTE.FRONT)));

        while (textFields.size()< text.size()){

            textFields.add(
                    new DrawButton(text.get(textFields.size()),
                            new Rectangle(0, (int) (GamePanel.height*(start+heightStep*textFields.size())), (int) GamePanel.width,0),
                            (int)(GamePanel.height*0.15),
                            gsm.getGameStyle().get(GameStyle.PALETTE.UPFRONT)));
        }

    }
    @Override
    public void update () {
        if (((DrawButton)textFields.get(1)).mouseClick) {
            gsm.set(GameStateManager.STATES.CREATE);
            ((DrawButton)textFields.get(1)).mouseClick=false;
        }
        if (((DrawButton)textFields.get(2)).mouseClick) {
            gsm.setNew(GameStateManager.STATES.SAVE_LOAD,new LoadState(gsm, LoadState.Way.LOAD));
            ((DrawButton)textFields.get(2)).mouseClick=false;
        }
        if (((DrawButton)textFields.get(3)).mouseClick) {
            gsm.set(GameStateManager.STATES.SETTINGS);
            ((DrawButton)textFields.get(3)).mouseClick=false;
        }
        if (((DrawButton)textFields.get(4)).mouseClick) {
            System.exit(0); //todo should be written differently
            ((DrawButton)textFields.get(4)).mouseClick=false;
        }
    }

    @Override
    public void input (MouseHandler mouse, KeyHandler key) {

        for (NewDrawText dt:textFields){
            if (dt instanceof DrawButton){
                ((DrawButton)dt).input(mouse);
            }
        }
    }

    @Override
    public void render (Graphics2D g) {

        for (NewDrawText dt:textFields){
            dt.render(g);
        }

    }

}
