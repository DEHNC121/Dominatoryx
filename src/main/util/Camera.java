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
    private int delX;
    private int delY;
    private Object2D camera;
    private Object2D cameraBoundary; //red rectangle
    final static float zoomScale=1.2f;
    private float mouseDraggingScale = 1f;
    private final float minScale=1f;
    private final float maxScale=5f;
    private Sprite sprite;

    final private float maxCameraWidth=WorldMap.Parts[WorldMap.hexagonPartsInRow-2].getX();
    final private float maxCameraHeight=WorldMap.Parts[WorldMap.hexagonPartsInRow*
            (WorldMap.hexagonPartsInColumn-2)].getY();
    final private float minCameraWidth=maxCameraWidth/maxScale;
    final private float minCameraHeight=maxCameraHeight/maxScale;


    private int mouseX=-1;
    private int mouseY=-1;
    private boolean scrollUp=false;
    private boolean scrollDown=false;
    private boolean mouseIsDragged = false;

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
            //mouseDraggingScale=mouseDraggingScale/zoomScale;
            mouseDraggingScale=Math.max(mouseDraggingScale/zoomScale,1/maxScale);
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
            //mouseDraggingScale=mouseDraggingScale*zoomScale;
            mouseDraggingScale=Math.min(mouseDraggingScale*zoomScale,1/minScale);

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
        camera.setX(Math.min(camera.getX(), cameraBoundary.getX() + maxCameraWidth - camera.getWidth()));
        camera.setY(Math.max(camera.getY(), cameraBoundary.getY()));
        camera.setY(Math.min(camera.getY(), cameraBoundary.getY() + maxCameraHeight - camera.getHeight()));
    }


    // not working yet
    public void dragged () {
        if (mouseIsDragged) {
            camera.x -= delX * mouseDraggingScale;
            camera.y -= delY * mouseDraggingScale;
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
        dragged();
        //
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {
        mouseX=mouse.getX();
        mouseY=mouse.getY();
        mouseIsDragged = mouse.getIsDragged();
        delX = mouse.getDelX();
        delY = mouse.getDelY();
        if(mouse.getRotation()<0)
            scrollUp=true;
        if(mouse.getRotation()>0)
            scrollDown=true;
        mouse.setRotation(0);
        mouse.isDragged = false;
        mouse.setDelX(0);
        mouse.setDelY(0);
    }


    int a=0;
    int b=1;
    public void render (Graphics g)
    {
        int i=0;
        for (Hexagon2D hexagon2D:WorldMap.hexagonMap) {
            float scaleWidth = (float) GamePanel.width / camera.width; //this is currently wrong
            float scaleHeight = (float) GamePanel.height / camera.height;
            float x = (hexagon2D.x - camera.x) * scaleWidth;
            float y = (hexagon2D.y - camera.y) * scaleHeight;
            float width = hexagon2D.width * scaleWidth;
            float height = hexagon2D.height * scaleHeight;
            i++;
            if(i==420)
            hexagon2D.render(g, sprite.getSprite(a, b), (int) x, (int) y, (int) width + 2, (int) height + 2);
            b=1-b;
        }
    }
}
