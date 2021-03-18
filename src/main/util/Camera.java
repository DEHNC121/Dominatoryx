package main.util;


import java.awt.*;
import java.util.concurrent.Executor;

public class Camera
{


    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private float dx;
    private float dy;
    private float maxSpeed = 4f;
    private float acc = 1f;
    private float deacc = 0.3f;
    public int stamina;
    public int pause;
    private Executor staminaThread;
    public boolean paused;
    private int widthLimit;
    private int heightLimit;
    protected boolean attack;
    protected boolean run;
    float bonus=0f;


    private Entity e;

    public Camera (AABB collisionCam)
    {
        this.collisionCam = collisionCam;
    }

    public void setLimit (int widthLimit, int heightLimit)
    {
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;
    }
    public AABB getBounds ()
    {
        return collisionCam;
    }
    public void update ()
    {
        move ();
        PlayState.map.x += dx;
        PlayState.map.y += dy;
    }

    public void move ()
    {
        if(run){ bonus=3f; }
        else { bonus=0f; }

        // up_left
        if (up && !down && left&& !right) {
            dy -= acc+(bonus/2);
            dx -= acc+(bonus/2);
            if (dy < -maxSpeed-(bonus/2)) { dy = -maxSpeed-(bonus/2); }
            if (dx < -maxSpeed-(bonus/2)) { dx = -maxSpeed-(bonus/2); }
        }

        // up_right
        else if (up && !down && !left&& right) {
            dy -= acc+(bonus/2);
            dx += acc+(bonus/2);
            if (dy < -maxSpeed-(bonus/2)) { dy = -maxSpeed-(bonus/2); }
            if (dx > maxSpeed+(bonus/2)) { dx = maxSpeed+(bonus/2); }
        }

        // down_left
        else if (!up && down && left&& !right) {
            dy += acc+(bonus/2);
            dx -= acc+(bonus/2);
            if (dy > maxSpeed+(bonus/2)) { dy = maxSpeed+(bonus/2); }
            if (dx < -maxSpeed-(bonus/2)) { dx = -maxSpeed-(bonus/2); }
        }

        // down_right
        else if (!up && down && !left&& right) {
            dy += acc+(bonus/2);
            dx += acc+(bonus/2);
            if (dy > maxSpeed+(bonus/2)) { dy = maxSpeed+(bonus/2); }
            if (dx > maxSpeed+(bonus/2)) { dx = maxSpeed+(bonus/2); }
        }

        // up
        else if (up && !down && !left&& !right) {
            dy -= acc+bonus;
            if (dy < -maxSpeed-bonus) { dy = -maxSpeed-bonus; }
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
            else if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }

        //down
        else if (down && !up && !left&& !right) {
            dy += acc+bonus;
            if (dy > maxSpeed+bonus) { dy = maxSpeed+bonus; }
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
            else if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }


        //left
        else if (!down && !up &&left && !right) {
            dx -= acc+bonus;
            if (dx < -maxSpeed-bonus) { dx = -maxSpeed-bonus; }
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
            else if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
        }


        //right
        else if (!down && !up &&right && !left) {
            dx += acc+bonus;
            if (dx > maxSpeed+bonus) { dx = maxSpeed+bonus; }
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
            else if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
        }

        //stop
        else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
            else if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
            else if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {
        if (e == null)
        {
            if (key.pause.down && !paused)
            {
                PlayState.pause ();
                paused = true;
            }
            if (!paused)
            {
                boolean moving = false;
                if (mouse.getButton () == 1)
                {
                    System.out.println ("Player: " + pos.x + ", " + pos.y);
                }
                if (key.up.down)
                {
                    up = true;
                    moving = true;
                }
                else
                {
                    up = false;
                }
                if (key.down.down)
                {
                    down = true;
                    moving = true;
                }
                else
                {
                    down = false;
                }
                if (key.left.down)
                {
                    left = true;
                    moving = true;
                }
                else
                {
                    left = false;
                }
                if (key.right.down)
                {
                    right = true;
                    moving = true;
                }
                else
                {
                    right = false;
                }
                if (key.run.down && moving)
                {
                    run = true;
                }
                else
                {
                    run = false;
                }
                if (key.attack.down && !key.run.down)
                {
                    attack = true;
                }
                else
                {
                    attack = false;
                }
            }
        }
    }

    public void stamina() {
        staminaThread.execute(()->{
            try {
                Thread.sleep(400);
                if (stamina > 0 && run) stamina--;
                else if (stamina < 10) stamina++;
            } catch (Exception e) {}
        });
    }

    public void target (Entity e)
    {
        this.e = e;
        deacc = e.getDeacc ();
        maxSpeed = e.getMaxSpeed ();
    }

    public void render (Graphics g)
    {
        g.setColor (Color.blue);
        //g.drawRect ((int) collisionCam.getPos ().x, (int) collisionCam.getPos ().y, (int)collisionCam.getWidth (), (int)collisionCam.getHeight ());
    }
}
