package main.util.map;

public class Object2D {

    public float x;
    public float y;

    public float height;
    public float width;

    public Object2D(){
        x=0;
        y=0;

        height =0;
        width =0;
    }

    public Object2D(float x,float y,float w,float h){
        this.x=x;
        this.y=y;

        this.width=w;
        this.height=h;
    }

    public float getY() {
        return y;
    }
    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isInside(float x,float y){
        return (x>=this.x && x<= this.x+width && y>=this.y && y<=this.y+height );
    }
}
