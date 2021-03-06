package main.util;


import main.GamePanel;
import main.graphics.Sprite;
import main.states.PlayState;
import main.util.map.Hexagon2D;
import main.util.map.Object2D;

import java.awt.*;

import main.util.map.WorldMap;
import main.util.playStateGUI.GUIManager;


public class Camera
{
    private float delX;
    private float delY;

    private final Object2D camera;
    private final Object2D cameraBoundary; //red rectangle

    final static float zoomScale=1.2f;
    private final float minScale=1f;
    private final float maxScale=5f;
    private float mouseDraggingScale = 1f;

    float minCameraWidth;
    float minCameraHeight;
    float maxCameraWidth;
    float maxCameraHeight;

    private final Sprite mapSprite;
    private float scale;
    private float scrollScale;

    private float mouseXOnMap=-1;
    private float mouseYOnMap =-1;
    private int mouseXOnScreen = -1;
    private int mouseYOnScreen = -1;
    private boolean scrollUp=false;
    private boolean scrollDown=false;
    private boolean mouseIsDragged = false;
    private boolean mouseIsLClicked = false;
    private boolean mouseIsRClicked = false;

    public int pause;
    public boolean paused;



    public Camera ()
    {
        maxCameraWidth=WorldMap.Parts[WorldMap.hexagonPartsInRow-2].getX();
        maxCameraHeight=WorldMap.Parts[WorldMap.hexagonPartsInRow*(WorldMap.hexagonPartsInColumn-2)].getY();

        cameraBoundary=new Object2D(WorldMap.Parts[0].getWidth(),WorldMap.Parts[0].getHeight(), maxCameraWidth, maxCameraHeight);

        if (maxCameraWidth * GamePanel.height > maxCameraHeight * GamePanel.width)
            maxCameraWidth = maxCameraHeight *  GamePanel.width /  GamePanel.height;
        else
            maxCameraHeight = maxCameraWidth *  GamePanel.height /  GamePanel.width;

        minCameraWidth=maxCameraWidth/maxScale;
        minCameraHeight=maxCameraHeight/maxScale;
        //camera has to be created AFTER WorldMap

        camera=new Object2D(WorldMap.Parts[0].getWidth(),WorldMap.Parts[0].getHeight(),maxCameraWidth, maxCameraHeight);
        mapSprite =new Sprite("map/HexagonMapV.7.png");

        scale = GamePanel.width / camera.width;
        scrollScale = 1.0f;
    }

    public void updateScale () {
        scale = GamePanel.width / camera.width;
        scrollScale = maxCameraWidth / camera.width;
    }

    public void setCameraZoomIn (float zoom) {
        camera.setX(camera.x + ( mouseXOnMap - camera.x) * (1 - 1 / zoom)); //mouseOnMap
        camera.setY(camera.y + ( mouseYOnMap - camera.y) * (1 - 1 / zoom)); //mouseOnMap

    }

    public void setCameraZoomOut (float zoom) {
        camera.setX(camera.x + ( mouseXOnMap - camera.x) * (1 - 1 / zoom)); //mouseOnMap
        camera.setY(camera.y + ( mouseYOnMap - camera.y) * (1 - 1 / zoom));
    }

    public void zoomIn(){
        if(scrollUp) {

            scrollUp = false;
            float wOld = camera.getWidth();
            float hOld = camera.getHeight();

            float wNew = Math.max(camera.getWidth() / zoomScale, minCameraWidth);
            float hNew = Math.max(camera.getHeight() / zoomScale, minCameraHeight);

            camera.setWidth(wNew);
            camera.setHeight(hNew);

            mouseDraggingScale = Math.max(mouseDraggingScale / zoomScale, 1 / maxScale);

            if (wOld != minCameraWidth && hOld != minCameraHeight) {
                updateScale();
                setCameraZoomIn(wOld / wNew);
            }
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
            mouseDraggingScale=Math.min(mouseDraggingScale*zoomScale,1/minScale);
            if (wOld != maxCameraWidth && hOld != maxCameraHeight) {
                updateScale();
                setCameraZoomOut(wOld / wNew);
            }
        }
    }

    private void updateCamera () {
        camera.setX(Math.max(camera.getX(), cameraBoundary.getX()));
        camera.setX(Math.min(camera.getX(), cameraBoundary.getX() + cameraBoundary.getWidth() - camera.getWidth()));
        camera.setY(Math.max(camera.getY(), cameraBoundary.getY()));
        camera.setY(Math.min(camera.getY(), cameraBoundary.getY() + cameraBoundary.getHeight() - camera.getHeight()));
    }

    public void dragged () {
        if (mouseIsDragged) {
            camera.x -= delX * mouseDraggingScale;
            camera.y -= delY * mouseDraggingScale;
        }
    }

    public void clicked(){
        if(mouseIsLClicked){
            Hexagon2D selected=getHexagon();
            if(selected!=null) WorldMap.click(selected);
            else System.out.println("NULL hexagon selected");
            //System.out.println(WorldMap.neighborsOfSelected.size());
            //System.out.println(WorldMap.getSelectedSet().size());
            PlayState.setClicked(!PlayState.isClicked);
        }
        if(mouseIsRClicked){
            Hexagon2D target=getHexagon();
            System.out.println("RClick initiated");
            if(target!=null) WorldMap.target(target);
            else System.out.println("NULL hexagon targeted");
            //System.out.println(WorldMap.neighborsOfSelected.size());
            //System.out.println(WorldMap.getSelectedSet().size());
            PlayState.setClicked(!PlayState.isClicked);
        }

    }
    public void update ()
    {

        updateScale();
        move ();
        updateCamera();
        clicked();
    }

    public void move ()
    {
        zoomIn();
        zoomOut();
        dragged();
    }

    public void computeMouseOnMap () {
        mouseXOnMap = camera.x + (float) mouseXOnScreen / scale; // + (int) cameraBoundary.getX();
        mouseYOnMap = camera.y + (float) mouseYOnScreen / scale; // + (int) cameraBoundary.getY();
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {
        mouseXOnScreen = mouse.getX();
        mouseYOnScreen = mouse.getY();
        computeMouseOnMap();
        mouseIsDragged = mouse.getIsDragged();
        mouseIsLClicked = mouse.getIsLClicked();
        mouseIsRClicked=mouse.getIsRClicked();
        delX = mouse.getDelX();
        delY = mouse.getDelY();
        if(mouse.getRotation()<0)
            scrollUp=true;
        if(mouse.getRotation()>0)
            scrollDown=true;
        mouse.setRotation(0);
        mouse.isDragged = false;
        /*
        if(mouseIsClicked){
            mouse.setIsClicked(false);
        }

         */
        mouse.setDelX(0);
        mouse.setDelY(0);
    }
    public Hexagon2D getHexagon(){
        return WorldMap.getHexagon(mouseXOnMap,mouseYOnMap);
    }
    public int d = 0;
    public void render (Graphics g)
    {
        for (Hexagon2D hexagon2D:WorldMap.hexagonMap) {

            float x = (hexagon2D.x - camera.x) * scale;
            float y = (hexagon2D.y - camera.y) * scale;
            float width = hexagon2D.width * scale;
            float height = hexagon2D.height * scale;

            if (!(hexagon2D.x + hexagon2D.width < camera.x || hexagon2D.x > camera.x + camera.width ||
                    hexagon2D.y + hexagon2D.height < camera.y || hexagon2D.y > camera.y + camera.height)) {

                    hexagon2D.render(g, mapSprite,  Math.round(x),  Math.round(y), (int) Math.ceil(width), (int) Math.ceil(height),scale);
            }
        }
    }

    public void debug() {
        System.out.println("mouseXOnScreen: " + mouseXOnScreen + " mouseYOnScreen: " + mouseYOnScreen);
        System.out.println("mouseXOnMap: " + mouseXOnMap + " mouseYOnMap: " + mouseYOnMap);
        System.out.println("camera.x: " + camera.x + " camera.y: " + camera.y + " camera.width: " + camera.width +
                " camera.height: " + camera.height);
        System.out.println("scale: " + scale);
        System.out.println("mouseDraggingScale: " + mouseDraggingScale);
        System.out.println("scrollScale: " + scrollScale);
        System.out.println("maxCameraHeight: " + maxCameraHeight + " maxCameraWidth: " + maxCameraWidth);

    }
}
