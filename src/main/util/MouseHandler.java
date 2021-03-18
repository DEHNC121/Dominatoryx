package main.util;

import main.GamePanel;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener
{

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;
    private static int mouseRot = -1;

    public MouseHandler(GamePanel game)
    {
        game.addMouseListener (this);
        game.addMouseMotionListener(this);
        game.addMouseWheelListener(this);
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

    public int getRotation () { return mouseRot; }

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

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        mouseRot = mouseWheelEvent.getWheelRotation();
    }
}
