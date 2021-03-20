package main.util;


import main.GamePanel;
import main.states.PlayState;
import main.util.map.Object2D;

import java.awt.*;
import main.util.map.WorldMap;

public class Camera
{
    private Object2D camera;
    private Object2D cameraBoundary; //red rectangle
    final static float zoomScale=1.2f;

    final private float maxCameraWidth=WorldMap.Parts[WorldMap.hexagonPartsInRow-2].getX();
    final private float maxCameraHeight=WorldMap.Parts[WorldMap.hexagonPartsInRow*
            (WorldMap.hexagonPartsInColumn-2)].getY();
    final private float minCameraWidth=maxCameraWidth/5f;
    final private float minCameraHeight=maxCameraHeight/5f;
    //to be changed if things look bad

    private int mouseX=-1;
    private int mouseY=-1;
    private boolean scrollUp=false;
    private boolean scrollDown=false;

    public int pause;
    public boolean paused;



    public Camera ()
    {
        //camera has to be created AFTER WorldMap
        camera=new Object2D(WorldMap.Parts[0].getWidth(),WorldMap.Parts[0].getHeight(),
                maxCameraWidth, maxCameraHeight);
        cameraBoundary=new Object2D(camera.getX(),camera.getY(),0f,0f);
    }
    public void updateCameraBoundary(){
        cameraBoundary.setWidth(maxCameraWidth-camera.getWidth());
        cameraBoundary.setHeight(maxCameraHeight-camera.getHeight());
    }
    public void zoomIn(){
        if(scrollUp){
            scrollUp=false;
            camera.setWidth(Math.max(camera.getWidth()/zoomScale,minCameraWidth));
            camera.setHeight(Math.max(camera.getHeight()/zoomScale,minCameraHeight));
            updateCameraBoundary();
        }

    }
    public void zoomOut(){
        if(scrollDown){
            scrollDown=false;
            camera.setWidth(Math.max(camera.getWidth()*zoomScale,maxCameraWidth));
            camera.setHeight(Math.max(camera.getHeight()*zoomScale,maxCameraHeight));
            updateCameraBoundary();
            //when zooming out if colliding,then it ,might be necessary to change x,y
            if(camera.getX()>cameraBoundary.getX()+cameraBoundary.getWidth())
                camera.setX(cameraBoundary.getX()+cameraBoundary.getWidth());
            if(camera.getY()>cameraBoundary.getY()+cameraBoundary.getHeight())
                camera.setY(cameraBoundary.getY()+cameraBoundary.getHeight());
        }


    }
    private void moveCamera(){
        if(mouseX<25){
            camera.setX(Math.max(cameraBoundary.getX(),camera.getX()-25));
        }
        if(mouseY<25){
            camera.setY(Math.max(cameraBoundary.getY(),camera.getY()-25));
        }
        if(GamePanel.width-mouseX<25){
            camera.setX(Math.min(cameraBoundary.getX()+cameraBoundary.getWidth(),camera.getX()+25));
        }
        if(GamePanel.width-mouseY<25){
            camera.setY(Math.min(cameraBoundary.getY()+cameraBoundary.getHeight(),camera.getY()+25));
        }

    }


    public void update ()
    {
        move ();

    }

    public void move ()
    {
        moveCamera();
        zoomIn();
        zoomOut();
        //
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {
        mouseX=mouse.getX();
        mouseY=mouse.getY();
        //swap these if
        if(mouse.getRotation()>0)
            scrollUp=true;
        if(mouse.getRotation()<0)
            scrollDown=true;
    }



    public void render (Graphics g)
    {
        g.setColor (Color.blue);
        //g.drawRect ((int) collisionCam.getPos ().x, (int) collisionCam.getPos ().y, (int)collisionCam.getWidth (), (int)collisionCam.getHeight ());
    }
}
