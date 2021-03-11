package main.util;

import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener
{

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public MouseHandler(GamePanel game)
    {
        game.addMouseListener (this);
    }

    public int getX ()
    {
        return mouseX;
    }

    public int getY ()
    {
        return mouseY;
    }

    public int getButton ()
    {
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        mouseB = mouseEvent.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent)
    {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }
}
