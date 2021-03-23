package main.util;

import main.GamePanel;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener
{

    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;
    private int mouseRot = 0; //1 - scroll down, -1 - scroll up
    private boolean isDragged = false;

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

    public boolean getIsDragged () { return isDragged; }

    public void setRotation(int mouseRot) { this.mouseRot = mouseRot; }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mouseB = mouseEvent.getButton();
        isDragged = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {
        mouseB = -1;
        isDragged = false;
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
        //System.out.println(mouseRot);
    }
}
