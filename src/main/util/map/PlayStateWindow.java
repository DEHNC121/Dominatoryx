package main.util.map;

import main.states.GameStateManager;
import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.playStateGUI.GUIManager;

import java.awt.*;

public class PlayStateWindow {
    //manages Camera/GUIManager inputs, renders
    //and decides which updates during input;
    Camera camera;
    GUIManager guiManager;
    int inputX=0;
    int inputY=0;
    public PlayStateWindow(GameStateManager gsm){
        guiManager=new GUIManager();
        camera=new Camera();
        guiManager.setGsm(gsm);
    }
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        inputX=mouseHandler.getX();
        inputY= mouseHandler.getY();
        guiManager.input(mouseHandler, keyHandler);
        camera.input(mouseHandler, keyHandler);
    }
    public void render(Graphics g){
        camera.render(g);
        guiManager.render(g);
    }
    public void update(){
        guiManager.update();
        if(!guiManager.capture(inputX,inputY)){
            camera.update();
        }
        guiManager.moneyUpdate();
        //I couldn't find better place to put this in
        guiManager.unitMenu.hexagonSelected();
    }
}
