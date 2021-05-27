package main.util.map;

import javafx.util.Pair;
import main.graphics.Sprite;
import main.util.Player;
import main.util.map.Object2D;
import main.util.map.WorldMap;
import main.util.structures.House;
import main.util.structures.Structure;
import main.util.units.Kid;
import main.util.units.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.function.Predicate;

public class Hexagon2D extends Object2D {
    int positionInWorldMapArray;
    public boolean border=false;
    public boolean water=true;
    public Player owner=null;
    public Unit unit=null;
    public Structure structure=null;
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
    public Player getOwner () { return owner; }
    public Unit getUnit () { return unit; }
    public Structure getStructure () { return structure; }
    public void setOwner (Player owner) { this.owner = owner; }
    public void setUnit (Unit unit) { this.unit = unit; }
    public void setStructure (Structure structure) { this.structure = structure; }
    public int getIndex(){ return positionInWorldMapArray; }

    public void setBorder(boolean border) {
        this.border = border;
    }

    private void getNeighborsLoop(int maxDistance, Map<Hexagon2D,Integer> list, boolean[] isVisited,
                                  Queue<Pair<Hexagon2D,Integer>> queue,Predicate<Hexagon2D> test){
        while(!queue.isEmpty()){
            Pair<Hexagon2D,Integer> pair=queue.poll();
            isVisited[pair.getKey().positionInWorldMapArray]=true;
            if(pair.getValue()>0 && !test.test(pair.getKey()))
                continue;
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
        return getSpecialNeighbors(distance,(hex)->true);
    }

    public HashMap<Hexagon2D,Integer> getSpecialNeighbors(int distance, Predicate<Hexagon2D> test){
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
        getNeighborsLoop(distance,map,isVisited, queue,test);
        map.remove(this);
        return map;
    }


    public void render(Graphics g, Sprite sprite, int x, int y, int width, int height,float scale)
    {
        int textureX=0;
        int textureY=0;
        if(owner!=null)
            textureY=owner.getColour();
        if(this.equals(WorldMap.selectedHexagon)){
            textureX=2;
        }else if(WorldMap.getSelectedSet().contains(this) && WorldMap.selectedHexagon!=null){
            textureX=1;
        }

        if(!water){
            g.drawImage(sprite.getSprite(textureX,textureY),x,y,width,height,null);
        }
//        if(structure!=null)
        /*
        if (textureX==2)
            new House(this).render(g,x,  y,  width,  height, scale);

         */
        if (structure != null)
            structure.render(g, x, y, width, height, scale);
        if(unit!=null)
            unit.render(g,x,  y,  width,  height, scale);
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

    public int getIncome(){
        //to be expanded later with addition of buildings/
        int income = 1;
        if (structure != null)
            income += structure.getIncome();
        if (unit != null)
            income += unit.getIncome();
        return income;
    }

    public void abandonField(){
        unit=null;
    }

    public void newOwner(Player pl){
        abandonField();
        owner=pl;
    }
}
