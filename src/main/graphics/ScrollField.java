package main.graphics;

import main.states.GameStyle;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;

import java.util.ArrayList;

public class ScrollField {

    ArrayList<String> fields;

    private Point mousePosition;
    private int rotation;

    Rectangle outRectangle;
    Rectangle wholeRectangle;

    ArrayList<DrawText> textFields;

    private int middleIndex;
    int showNumber;

    public ScrollField(Rectangle outRectangle, ArrayList<String> fieldsIN, Color color, int showNumberIn) {
        textFields=new ArrayList<>();

        this.outRectangle = outRectangle;
        if (fieldsIN==null){

            this.showNumber= showNumberIn;
            ArrayList<String> dataList=new ArrayList<>();
            for (int i=0; i<=showNumber;i++){
                dataList.add(String.valueOf(i));
            }

            this.fields = dataList;
        }else {

            this.fields = fieldsIN;
            this.showNumber= Math.min(showNumberIn, fields.size());
        }
        if (showNumber % 2==0){
            showNumber-=1;
        }

        wholeRectangle = new Rectangle(outRectangle.x, (int)(outRectangle.y-(outRectangle.height*showNumber/2)), outRectangle.width , outRectangle.height*showNumber);


        middleIndex = fields.size()/2;
        for (int i=showNumber/2;i>=-showNumber/2 && showNumber>0;i--){
            int number=i+showNumber/2;
            textFields.add(
                    new DrawText(fields.get(number),
                            new Rectangle(outRectangle.x, (int)(outRectangle.y+i*(outRectangle.height*0.7)), outRectangle.width , outRectangle.height),
                            1f,
                            new Color(color.getRed(),color.getGreen(),color.getBlue(),255-(Math.abs(i)*80))));
        }

    }

    public ScrollField(Rectangle outRectangle, ArrayList<String> fieldsIN, GameStyle.PALETTE colorStyle, int showNumberIn) {
        textFields=new ArrayList<>();

        this.outRectangle = outRectangle;
        if (fieldsIN==null){

            this.showNumber= showNumberIn;
            ArrayList<String> dataList=new ArrayList<>();
            for (int i=0; i<=showNumber;i++){
                dataList.add(String.valueOf(i));
            }

            this.fields = dataList;
        }else {

            this.fields = fieldsIN;
            this.showNumber= Math.min(showNumberIn, fields.size());
        }
        if (showNumber % 2==0){
            showNumber-=1;
        }

        wholeRectangle = new Rectangle(outRectangle.x, (int)(outRectangle.y-(outRectangle.height*showNumber/2)), outRectangle.width , outRectangle.height*showNumber);


        middleIndex = fields.size()/2;
        for (int i=showNumber/2;i>=-showNumber/2 && showNumber>0;i--){
            int number=i+showNumber/2;
            textFields.add(
                    new DrawText(fields.get(number),
                            new Rectangle(outRectangle.x, (int)(outRectangle.y+i*(outRectangle.height*0.7)), outRectangle.width , outRectangle.height),
                            1f,
                            colorStyle,
                            255-(Math.abs(i)*80)));
        }

    }

    public ScrollField(ScrollField scrollField) {
        textFields=scrollField.textFields;

        this.outRectangle = scrollField.outRectangle;
        this.fields = scrollField.fields;
        this.showNumber= scrollField.showNumber;

        wholeRectangle = scrollField.wholeRectangle;


        middleIndex = scrollField.middleIndex;
    }

    public String getMainField() {
        return fields.get(middleIndex);
    }

    public void setArray (ArrayList<String> entries) {
        if (this.fields != entries) {
            this.fields = entries;
            if ( middleIndex >entries.size()){
                middleIndex = entries.size()-1;
            }
        }
    }

    public void setMiddleIndex(int middleIndex) {
        this.middleIndex = middleIndex;
    }

    public int getMiddleIndex() {
        return middleIndex;
    }

    public void update () {
        movePosition();

        for (int i=-showNumber/2;i<=showNumber/2 && showNumber>0;i++){
            if (middleIndex +i>=0 && middleIndex +i<fields.size()){
                textFields.get(i+showNumber/2).text= fields.get(middleIndex +i);
            }else {
                textFields.get(i+showNumber/2).text= "";
            }

        }
        for (DrawText dt:textFields){
            dt.update();
        }

    }

    public void input (MouseHandler mouse, KeyHandler key) {
        mousePosition=new Point(mouse.getX(), mouse.getY());
        if (rotation==0 && wholeRectangle.contains(mousePosition)){
            rotation=mouse.getRotation();
        }

    }

    public void render (Graphics g) {
        for (DrawText dt:textFields){
            dt.render(g);
        }
    }

    public void ifScrollUp () {
        if (middleIndex < fields.size() - 1) {
            middleIndex++;
        }
    }

    public void ifScrollDown () {
        if (middleIndex > 0) {
            middleIndex--;
        }
    }

    public void movePosition () {
        if (!wholeRectangle.contains(mousePosition)) {
            return;
        }

        if (rotation<0) {
            ifScrollUp();
        }else if(rotation>0){
            ifScrollDown();
        }
        rotation=0;

    }
}
