package main.util;

public class Object2D {

    public int x;
    public int y;

    public int height;
    public int width;

    public Object2D(){
        x=0;
        y=0;

        height =0;
        width =0;
    }

    public Object2D(int x,int y,int w,int h){
        this.x=x;
        this.y=y;

        this.width=w;
        this.height=h;
    }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public boolean isInside(int x,int y){
        return (x>=this.x && x<= this.x+width && y>=this.y && y<=this.y+height );
    }
}
