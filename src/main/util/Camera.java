package main.util;


import main.GamePanel;
import main.states.PlayState;
import main.util.map.Object2D;

import java.awt.*;
import java.util.concurrent.Executor;

public class Camera
{
    private Object2D object;
    final static float zoomScale=1.2f;
    private boolean scrollUp=false;
    private boolean scrollDown=false;

    private int mouseX=-1;
    private int mouseY=-1;
    private float maxSpeed = 4f;
    private float acc = 1f;
    private float deacc = 0.3f;
    public int stamina;
    public int pause;
    public boolean paused;
    private int widthLimit;
    private int heightLimit;
    float bonus=0f;


    //private Entity e;

    public Camera (Object2D object)
    {
        this.object=object;
    }
    public void zoomIn(){


    }
    public void zoomOut(){
        //TODO: when zooming out if colliding,then it ,might be neccesary to change x,y

    }
    private void moveCamera(){
        if(mouseX<25){
            object.setX(Math.max(0,object.getX()-25));
        }
        if(mouseY<25){
            object.setY(Math.max(0,object.getY()-25));
        }
        if(GamePanel.width-mouseX<25){
            object.setX(Math.min(GamePanel.width-1-object.getWidth(),object.getX()+25));
        }
        if(GamePanel.width-mouseY<25){
            object.setY(Math.min(GamePanel.width-1-object.getWidth(),object.getY()+25));
        }

    }

    public void setLimit (int widthLimit, int heightLimit)
    {
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;
    }
    /*
    public AABB getBounds ()
    {
        return collisionCam;
    }

     */
    public void update ()
    {
        move ();
        //PlayState.map.x += dx;
        //PlayState.map.y += dy;
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
    }



    public void render (Graphics g)
    {
        g.setColor (Color.blue);
        //g.drawRect ((int) collisionCam.getPos ().x, (int) collisionCam.getPos ().y, (int)collisionCam.getWidth (), (int)collisionCam.getHeight ());
    }
}
