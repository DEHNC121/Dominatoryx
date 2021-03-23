package main.util;


import main.GamePanel;
import main.graphics.Sprite;
import main.states.PlayState;
import main.util.map.Hexagon2D;
import main.util.map.Object2D;

import java.awt.*;
import main.util.map.WorldMap;

public class Camera
{
    private Object2D camera;
    private Object2D cameraBoundary; //red rectangle
    final static float zoomScale=1.2f;
    private final float mouseDraggingScale = 0.3f;
    private Sprite sprite;

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
    private boolean draggedFlag = true;
    private boolean mouseIsDragged = false;
    private int mouseBeforeShiftX = -1;
    private int mouseBeforeShiftY = -1;

    public int pause;
    public boolean paused;



    public Camera ()
    {
        //camera has to be created AFTER WorldMap
        System.out.println(WorldMap.Parts[0].getWidth() + " " + WorldMap.Parts[0].getHeight());
        camera=new Object2D(WorldMap.Parts[0].getWidth(),WorldMap.Parts[0].getHeight(),maxCameraWidth, maxCameraHeight);
        cameraBoundary=new Object2D(camera.getX(),camera.getY(), maxCameraWidth, maxCameraHeight);
        sprite=new Sprite("map/HexagonMap.png");
    }

//    public void updateCameraBoundary(){
//        cameraBoundary.setWidth(maxCameraWidth-camera.getWidth());
//        cameraBoundary.setHeight(maxCameraHeight-camera.getHeight());
//    }

    public void zoomIn(){
        if(scrollUp){
            scrollUp=false;
            float wOld = camera.getWidth();
            float hOld = camera.getHeight();
            float wNew = Math.max(camera.getWidth()/zoomScale,minCameraWidth);
            float hNew = Math.max(camera.getHeight()/zoomScale,minCameraHeight);
            camera.setWidth(wNew);
            camera.setHeight(hNew);
            camera.setX(camera.x + (hOld - hNew) / 2);
            camera.setY(camera.y + (wOld - wNew) / 2);
        }

    }

    public void zoomOut(){
        if(scrollDown){
            scrollDown=false;
            float wOld = camera.getWidth();
            float hOld = camera.getHeight();
            float wNew = Math.min(camera.getWidth()*zoomScale,maxCameraWidth);
            float hNew = Math.min(camera.getHeight()*zoomScale,maxCameraHeight);
            camera.setWidth(wNew);
            camera.setHeight(hNew);
            camera.setX(camera.x + (hOld - hNew) / 2);
            camera.setY(camera.y + (wOld - wNew) / 2);

//            updateCameraBoundary();
            //when zooming out if colliding,then it ,might be necessary to change x,y
//            if(camera.getX()>cameraBoundary.getX()+cameraBoundary.getWidth())
//                camera.setX(cameraBoundary.getX()+cameraBoundary.getWidth());
//            if(camera.getY()>cameraBoundary.getY()+cameraBoundary.getHeight())
//                camera.setY(cameraBoundary.getY()+cameraBoundary.getHeight());
        }


    }
    private void moveCamera(){
//        if(mouseX<25){
//            camera.setX(Math.max(cameraBoundary.getX(),camera.getX()-25));
//        }
//        if(mouseY<25){
//            camera.setY(Math.max(cameraBoundary.getY(),camera.getY()-25));
//        }
//        if(GamePanel.width-mouseX<25){
//            camera.setX(Math.min(cameraBoundary.getX()+cameraBoundary.getWidth(),camera.getX()+25));
//        }
//        if(GamePanel.width-mouseY<25){
//            camera.setY(Math.min(cameraBoundary.getY()+cameraBoundary.getHeight(),camera.getY()+25));
//        }

    }
    // not working properly yet
    private void updateCamera () {
        camera.setX(Math.max(camera.getX(), cameraBoundary.getX()));
        camera.setX(Math.min(camera.getX(), cameraBoundary.getX() + cameraBoundary.getHeight() - camera.getHeight()));
        camera.setY(Math.max(camera.getY(), cameraBoundary.getY()));
        camera.setY(Math.min(camera.getY(), cameraBoundary.getY() + cameraBoundary.getWidth() - camera.getWidth()));
    }


    // not working yet
    public void dragged () {
        if (draggedFlag && mouseIsDragged) {
            draggedFlag = false;
            mouseBeforeShiftX = mouseX;
            mouseBeforeShiftY = mouseY;
        }
        if (mouseIsDragged) {
            camera.x -= (mouseX - mouseBeforeShiftX) * mouseDraggingScale;
            camera.y -= (mouseY - mouseBeforeShiftY) * mouseDraggingScale;
        }
    }

    public void update ()
    {
        move ();
        updateCamera();

    }

    public void move ()
    {
        moveCamera();
        zoomIn();
        zoomOut();
        //dragged();
        //
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {
        mouseX=mouse.getX();
        mouseY=mouse.getY();
        mouseIsDragged = mouse.getIsDragged();
        if (!mouseIsDragged)
            draggedFlag = true;
        //swap these if
        if(mouse.getRotation()<0)
            scrollUp=true;
        if(mouse.getRotation()>0)
            scrollDown=true;
        mouse.setRotation(0);
    }



    public void render (Graphics g)
    {
        for (Hexagon2D hexagon2D:WorldMap.hexagonMap) {
            float scaleWidth = (float) GamePanel.width / camera.width; //this is currently wrong
            float scaleHeight = (float) GamePanel.height / camera.height;
            float x = (hexagon2D.x - camera.x) * scaleHeight;
            float y = (hexagon2D.y - camera.y) * scaleWidth;
            float width = hexagon2D.width * scaleWidth;
            float height = hexagon2D.height * scaleHeight;
            hexagon2D.render(g,sprite.getSprite(0,0),(int) x,(int) y,(int)width,(int)height);
        }
        g.setColor (Color.blue);
    }
}
