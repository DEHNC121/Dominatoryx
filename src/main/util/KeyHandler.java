package main.util;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener
{

    public static List<Key> keys = new ArrayList<>();

    public static class Key
    {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key ()
        {
            keys.add (this);
        }
        public void toggle (boolean pressed)
        {
            if (pressed != down)
            {
                down = pressed;
            }
            if (pressed)
            {
                presses++;
            }
        }
        public void tick ()
        {
            if (absorbs < presses)
            {
                absorbs++;
                clicked = true;
            }
            else
            {
                clicked = false;
            }
        }
    }


    public Key up = new Key();
    public Key down = new Key();
//    public Key left = new Key();
//    public Key right = new Key();
//    public Key attack = new Key();
//    public Key menu = new Key();
//    public Key enter = new Key();
//    public Key escape = new Key();
//    public Key run = new Key();
//    public Key fps = new Key();
//    public Key pause= new Key();
//    public Key do_it= new Key();

    public KeyHandler (GamePanel game)
    {
        game.addKeyListener(this);
        keys.add(up);
    }

    public void releaseAll ()
    {
        for (Key key : keys) {
            key.down = false;
        }
    }
    /*
    TODO: make somewhere clock that ticks, because it is not easy to
        debug using just down values
     */
    public void tick ()
    {
        for (Key key : keys) {
            key.tick();
        }
    }

    public void toggle (KeyEvent e, boolean pressed)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_K) run.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_F) fps.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_P) pause.toggle(pressed);
//        if (e.getKeyCode() == KeyEvent.VK_E) do_it.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent)
    {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        toggle(keyEvent, true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent)
    {
        toggle(keyEvent, false);
    }
}
