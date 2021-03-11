package main.entity;


import main.graphics.Sprite;
import main.util.AABB;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    private int UP = 3;
    private int DOWN = 2;
    private int RIGHT = 0;
    private int LEFT = 1;
    private int RIGHT_UP = 4;
    private int LEFT_UP = 5;
    private int LEFT_DOWN = 6;
    private int RIGHT_DOWN = 7;

    //protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected int currentAnimation;


    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;


    protected boolean attack;
    protected boolean run;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected boolean isFallen;

    protected float maxSpeed = 3f;
    protected float acc = 1f;
    protected float deacc = 1f;
    float bonus=0f;

    protected AABB hitBounds;
    protected AABB bounds;

    //protected TileCollision tileCollision;

    public Entity (Sprite sprite, Vector2f orgin, int size)
    {
        this.sprite = sprite;
        pos = orgin;
        this.size = size;

        bounds = new AABB (orgin, size, size);
        hitBounds = new AABB (new Vector2f(orgin.x + (size / 2), orgin.y), size, size);

        //ani = new Animation();
        setAnimation (RIGHT, sprite.getSpriteArray(RIGHT), 10);

        //tileCollision=new TileCollision(this);
    }

    public void setSprite (Sprite sprite) { this.sprite = sprite; }
    public void setSize (int i) { size = i; }
    public void setMaxSpeed (float f) { maxSpeed = f; }

    public void setAcc (float f) { acc = f; }
    public void setDeacc (float f) { deacc = f; }

    public float getDeacc () { return deacc;}
    public float getMaxSpeed () { return maxSpeed; }

    public AABB getBounds () { return bounds; }

    public Vector2f getPos(){ return pos; }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public int getSize () { return size; }
    //public Animation getAnimation () { return ani; }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setFallen (boolean s)
    {
        isFallen = s;
    }

    public void setAnimation (int i, BufferedImage [] frames, int delay)
    {
        currentAnimation = i;
//        ani.setFrames (frames);
//        ani.setDelay (delay);
    }

    public void animate ()
    {

//
//        if (up && right && !down && !left)
//        {
//            if (currentAnimation != RIGHT_UP || ani.getDelay() == -1)
//            {
//                setAnimation(RIGHT_UP, sprite.getSpriteArray(RIGHT_UP), 8);
//            }
//        }
//        else if (up && left && !down && !right)
//        {
//            if (currentAnimation != LEFT_UP || ani.getDelay() == -1)
//            {
//                setAnimation(LEFT_UP, sprite.getSpriteArray(LEFT_UP), 8);
//            }
//        }
//        else if (down && left && !up && !right)
//        {
//            if (currentAnimation != LEFT_DOWN || ani.getDelay() == -1)
//            {
//                setAnimation(LEFT_DOWN, sprite.getSpriteArray(LEFT_DOWN), 8);
//            }
//        }
//        else if (down && right&& !up && !left)
//        {
//            if (currentAnimation != RIGHT_DOWN || ani.getDelay() == -1)
//            {
//                setAnimation(RIGHT_DOWN, sprite.getSpriteArray(RIGHT_DOWN), 8);
//            }
//        }
//        else if (up&& !down && !right&& !left)
//        {
//            if (currentAnimation != UP || ani.getDelay() == -1)
//            {
//                setAnimation(UP, sprite.getSpriteArray(UP), 5);
//            }
//        }
//        else if (down && !up && !right&& !left)
//        {
//            if (currentAnimation != DOWN || ani.getDelay() == -1)
//            {
//                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
//            }
//        }
//        else if (left && !down && !right&& !up)
//        {
//            if (currentAnimation != LEFT || ani.getDelay() == -1)
//            {
//                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
//            }
//        }
//        else if (right && !down && !up&& !left)
//        {
//            if (currentAnimation != RIGHT || ani.getDelay() == -1)
//            {
//                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
//            }
//        }
//        else
//        {
//            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
//        }
    }

    private void setHitBoxDirection ()
    {
        if (up)
        {
            hitBounds.setyOffset(-size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if (down)
        {
            hitBounds.setyOffset(size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if (left)
        {
            hitBounds.setyOffset(0);
            hitBounds.setxOffset(-size);
        }
        else if (right)
        {
            hitBounds.setyOffset(0);
            hitBounds.setxOffset(0);
        }
    }

    public void update ()
    {
        animate ();
        setHitBoxDirection ();
//        ani.update();
    }

    public abstract void render (Graphics2D g);
    public void input (KeyHandler key, MouseHandler mouse)
    {

    }

}
