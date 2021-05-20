package main.util.map;

import main.util.Camera;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.playStateGUI.GUIManager;

import java.awt.*;

public class PlayStateWindow {
    //manages Camera/GUIManager inputs, renders
    //and decides which updates during input;
    Camera camera;
    int inputX=0;
    int inputY=0;
    public PlayStateWindow(){
        camera=new Camera();
    }
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        inputX=mouseHandler.getX();
        inputY= mouseHandler.getY();
        GUIManager.input(mouseHandler, keyHandler);
        camera.input(mouseHandler, keyHandler);
    }
    public void render(Graphics g){
        camera.render(g);
        GUIManager.render(g);
    }
    public void update(){
        if(GUIManager.capture(inputX,inputY)){
           GUIManager.update();
        }
        else{
            camera.update();
        }

        //I couldn't find better place to put this in
        GUIManager.unitMenu.hexagonSelected();
    }
}
