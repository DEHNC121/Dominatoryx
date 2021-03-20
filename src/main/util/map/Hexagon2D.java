package main.util.map;

import main.util.map.Object2D;
import main.util.map.WorldMap;

import java.awt.*;
import java.util.ArrayList;

public class Hexagon2D extends Object2D {
    int positionInWorldMapArray;
    public Hexagon2D(){
        super();
    }
    public Hexagon2D(float x,float y,float w,float h){
        super(x, y, w, h);
    }
    public Hexagon2D setPosition(int i){
        positionInWorldMapArray=i;
        return this;
    }
    private void recursiveGetNeighbors(int distance,ArrayList<Hexagon2D> list,boolean[] isVisited){
        if(distance==0)
            return;

    }
    public ArrayList<Hexagon2D> getNeighbors(int distance){
        ArrayList<Hexagon2D> list=new ArrayList<>();
        if(distance==0)
            return list;
        boolean isVisited[]=new boolean[WorldMap.widthHexagonNumber*WorldMap.heightHexagonNumber];
        int len=isVisited.length;
        for(int i=0;i<len;i++){
            isVisited[i]=false;
        }
        isVisited[positionInWorldMapArray]=true;
        return list;
    }

    public void render (Graphics g)
    {

    }
}
