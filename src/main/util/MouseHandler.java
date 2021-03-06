package main.util;

import main.GamePanel;

import javax.swing.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private int delX = 0;
    private int delY = 0;
    private int mousePressedX = -1;
    private int mousePressedY = -1;
    private int mouseXFromLastDragged=0;
    private int mouseYFromLastDragged=0;
    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;
    private int mouseRot = 0; //1 - scroll down, -1 - scroll up
    public boolean isDragged = false;
    //public boolean isClicked = false;
    public boolean isLClicked=false;
    public boolean isRClicked=false;

    private int leftClickHandler=-1;

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

    public int getButton (){ return mouseB; }

    public int getRotation () { return mouseRot; }

    public boolean getIsDragged () { return isDragged; }

    public boolean getIsClicked() { return isLClicked || isRClicked; }

    public void setRotation(int mouseRot) { this.mouseRot = mouseRot; }

    public int getDelX () { return delX; }

    public int getDelY () { return delY; }

    public void setDelX (int delX) { this.delX = delX; }

    public void setDelY (int delY) { this.delY = delY; }

    public void setIsLClicked (boolean isClicked) { this.isLClicked = isClicked; }
    public void setIsRClicked (boolean isClicked) {isRClicked=isClicked;}
    public void setIsClicked(boolean isClicked){
        setIsRClicked(isClicked);
        setIsLClicked(isClicked);
    }

    public boolean getIsLClicked() { return isLClicked; }
    public boolean getIsRClicked() { return isRClicked; }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        mouseX=mouseEvent.getX();
        mouseY=mouseEvent.getY();
        isLClicked= SwingUtilities.isLeftMouseButton(mouseEvent);
        isRClicked= SwingUtilities.isRightMouseButton(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mouseB = mouseEvent.getButton();
        mousePressedX = mouseEvent.getX();
        mousePressedY = mouseEvent.getY();
        delX=0;
        delY=0;
        mouseXFromLastDragged=mousePressedX;
        mouseYFromLastDragged=mousePressedY;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent){
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent){}

    @Override
    public void mouseExited(MouseEvent mouseEvent){ }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
        delX += mouseX - mouseXFromLastDragged;
        delY += mouseY - mouseYFromLastDragged;
        mouseXFromLastDragged=mouseX;
        mouseYFromLastDragged=mouseY;
        isDragged = true;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent)
    {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();

        mousePressedX = -1;
        mousePressedY = -1;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        mouseRot = mouseWheelEvent.getWheelRotation();
        //System.out.println(mouseRot);
    }
}
