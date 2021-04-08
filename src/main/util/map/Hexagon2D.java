package main.util.map;

import javafx.util.Pair;
import main.graphics.Sprite;
import main.util.map.Object2D;
import main.util.map.WorldMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Hexagon2D extends Object2D {
    int positionInWorldMapArray;
    public boolean border=false;
    public boolean water=true;
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

    public int getIndex(){ return positionInWorldMapArray; }



    public void setBorder(boolean border) {
        this.border = border;
    }

    private void getNeighborsLoop(int maxDistance, Map<Hexagon2D,Integer> list, boolean[] isVisited,
                                  Queue<Pair<Hexagon2D,Integer>> queue){
        while(!queue.isEmpty()){
            Pair<Hexagon2D,Integer> pair=queue.poll();
            isVisited[pair.getKey().positionInWorldMapArray]=true;
            if(pair.getKey().border)
                continue;
            list.put(pair.getKey(), pair.getValue());
            if(pair.getValue()==maxDistance)
                continue;

            if(!isVisited[pair.getKey().positionInWorldMapArray+WorldMap.widthHexagonNumber])
                queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                        +WorldMap.widthHexagonNumber],pair.getValue()+1));
            if(!isVisited[pair.getKey().positionInWorldMapArray-WorldMap.widthHexagonNumber])
                queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                        -WorldMap.widthHexagonNumber],pair.getValue()+1));
            if(!isVisited[pair.getKey().positionInWorldMapArray+1])
                queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                        +1],pair.getValue()+1));
            if(!isVisited[pair.getKey().positionInWorldMapArray-1] )
                queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                        -1],pair.getValue()+1));
                switch (pair.getKey().positionInWorldMapArray%2){
                    case 0:
                        if(!isVisited[pair.getKey().positionInWorldMapArray-WorldMap.widthHexagonNumber-1] )
                            queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                                    -WorldMap.widthHexagonNumber-1],pair.getValue()+1));
                        if(!isVisited[pair.getKey().positionInWorldMapArray-WorldMap.widthHexagonNumber+1] )
                            queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                                    -WorldMap.widthHexagonNumber+1],pair.getValue()+1));
                        break;
                    case 1:
                        if(!isVisited[pair.getKey().positionInWorldMapArray+WorldMap.widthHexagonNumber-1] )
                            queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                                    +WorldMap.widthHexagonNumber-1],pair.getValue()+1));
                        if(!isVisited[pair.getKey().positionInWorldMapArray+WorldMap.widthHexagonNumber+1] )
                            queue.offer(new Pair<>(WorldMap.hexagonMap[pair.getKey().positionInWorldMapArray
                                    +WorldMap.widthHexagonNumber+1],pair.getValue()+1));
                        break;
                }
        }


    }
    public HashMap<Hexagon2D,Integer> getNeighbors(int distance){
        HashMap<Hexagon2D,Integer> map=new HashMap<>();
        if(border)
            return map;
        if(distance==0)
            return map;
        Queue<Pair<Hexagon2D,Integer>> queue=new LinkedList<>();
        boolean[] isVisited=new boolean[WorldMap.widthHexagonNumber*WorldMap.heightHexagonNumber];
        int len=isVisited.length;
        for(int i=0;i<len;i++){
            isVisited[i]=false;
        }
        queue.offer(new Pair<>(this,0));
        getNeighborsLoop(distance,map,isVisited, queue);
        map.remove(this);
        return map;
    }

    public void render(Graphics g, Sprite sprite, int x, int y, int width, int height)
    {
        if(this.equals(WorldMap.selectedHexagon))
            g.drawImage(sprite.getSprite(1,0),x,y,width,height,null);
        else if(water){}
            //g.drawImage(sprite.getSprite(2,1 ),x,y,width,height,null);
        else
            g.drawImage(sprite.getSprite(0,0),x,y,width,height,null);
    }
    public void renderSelected(Graphics g, Sprite sprite, int x, int y, int width, int height)
    {
        g.drawImage(sprite.getSprite(2,0),x,y,width,height,null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hexagon2D hexagon2D = (Hexagon2D) o;
        return positionInWorldMapArray == hexagon2D.positionInWorldMapArray;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionInWorldMapArray);
    }
}
