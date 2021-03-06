package main.states;

import main.graphics.DrawButton;
import main.graphics.DrawText;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuState extends GameState {

    ArrayList<DrawText> textFields;

    public MenuState(GameStateManager gsm) {
        super(gsm);


        textFields=new ArrayList<>();

        float start= 0.15f;
        float heightStep =0.16f;

        ArrayList<String> text=new ArrayList<>(Arrays.asList("Dominatoryx","New game","Load","Settings","Exit"));

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
                    case 1 -> gsm.set(GameStateManager.STATES.CREATE);
                    case 2 ->  gsm.setNew(GameStateManager.STATES.SAVE_LOAD,new LoadState(gsm, LoadState.Way.LOAD));
                    case 3 -> gsm.set(GameStateManager.STATES.SETTINGS);
                    case 4 -> GamePanel.endProgram();
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

}
