package main.util.map;

public class Object2DInt {

    public int x;
    public int y;

    public int height;
    public int width;

    public Object2DInt(){
        x=0;
        y=0;

        height =0;
        width =0;
    }

    public Object2DInt(int x, int y, int w, int h){
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

    public int getCenterX () { return x + width / 2;}
    public int getCenterY () { return y + height / 2;}

    public void setCenterX (int centerX) {this.x = centerX - width / 2;}
    public void setCenterY (int centerY) {this.y = centerY - height / 2;}

    public void setCenter (int centerX, int centerY) {
        setCenterX(centerX);
        setCenterY(centerY);
    }

    public boolean isInside(int x,int y){
        return (x>=this.x && x<= this.x+width && y>=this.y && y<=this.y+height );
    }
}
