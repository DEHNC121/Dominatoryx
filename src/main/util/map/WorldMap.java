package main.util.map;

import javafx.util.Pair;
import main.GamePanel;
import main.states.PlayState;
import main.util.Player;
import main.util.RoundManager;
import sounds.Sound;

import java.util.*;

public class WorldMap {
    public static long seed;
    public static Hexagon2D[] hexagonMap;
    public static HexagonPart2D[] Parts;
    public static int widthHexagonNumber;
    public static int heightHexagonNumber;
    public static int hexagonPartsInRow;
    public static int hexagonPartsInColumn;
    public static float hexagonPartWidth;
    public static float hexagonPartHeight;
    public static Hexagon2D selectedHexagon;
    public static HashMap<Hexagon2D,Integer> neighborsOfSelected;
    public static Set<Hexagon2D> neighborEnemies;
    public WorldMap(PlayState.GameMapSize gameMapSize) {
        selectedHexagon=null;
        neighborsOfSelected=new HashMap();
        neighborEnemies=new HashSet<>();
        //GamePanel.width,GamePanel.height;
        widthHexagonNumber=gameMapSize.size.getValue();
        heightHexagonNumber=gameMapSize.size.getKey();
        hexagonPartsInRow=(3*widthHexagonNumber+1);
        hexagonPartsInColumn=(2*heightHexagonNumber+1);
        int hexagonPartsNumber=hexagonPartsInRow*hexagonPartsInColumn;
        int hexagonNumber=widthHexagonNumber*heightHexagonNumber;

        Parts=new HexagonPart2D[hexagonPartsNumber];
        hexagonPartWidth = GamePanel.width / (float) hexagonPartsInRow;
        hexagonPartHeight = GamePanel.height / (float) hexagonPartsInColumn;
        float hexagonPartWidthDiff = GamePanel.width / (float) hexagonPartsInRow;
        float hexagonPartHeightDiff = GamePanel.height / (float) hexagonPartsInColumn;
        for(int i=0;i<hexagonPartsInRow;i++){
            for(int j=0;j<hexagonPartsInColumn;j++){
                Parts[j*hexagonPartsInRow+i]=
                        new HexagonPart2D(i*hexagonPartWidthDiff,j*hexagonPartHeightDiff,
                                hexagonPartWidth,hexagonPartHeight);
            }
        }
        hexagonMap=new Hexagon2D[hexagonNumber];
        for(int i=0;i<widthHexagonNumber;i++){
            for(int j=0;j<heightHexagonNumber;j++){
                int firstPartRow=2*j;
                int firstPartColumn=3*i;
                boolean isEven=(i%2==0);//even are higher
                if(!isEven)
                    firstPartRow++;
                int firstPartIndex=hexagonPartsInRow*firstPartRow+firstPartColumn;
                hexagonMap[i+j*widthHexagonNumber]=new Hexagon2D(Parts[firstPartIndex].getX(),Parts[firstPartIndex].getY(),
                        4*hexagonPartWidthDiff,2*hexagonPartHeightDiff)
                                .setPosition(i+j*widthHexagonNumber);
                setPartsInHexagon(hexagonMap[i+j*widthHexagonNumber],firstPartIndex);
                if(i==0 || j==0 || i==widthHexagonNumber-1 ||j==heightHexagonNumber-1)
                    hexagonMap[i+j*widthHexagonNumber].setBorder(true);
            }
        }
        generate();
    }
    static private void setPartsInHexagon(Hexagon2D hexagon,int firstPartIndex){
        //used in constructor
        Parts[firstPartIndex].setRightHexagon(hexagon).setLowerHexagon(hexagon);
        Parts[firstPartIndex+1].setAllHexagons(hexagon);
        Parts[firstPartIndex+2].setAllHexagons(hexagon);
        Parts[firstPartIndex+3].setLeftHexagon(hexagon).setLowerHexagon(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow].setRightHexagon(hexagon).setUpperHexagon(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow+1].setAllHexagons(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow+2].setAllHexagons(hexagon);
        Parts[firstPartIndex+hexagonPartsInRow+3].setLeftHexagon(hexagon).setUpperHexagon(hexagon);
    }
    static public HexagonPart2D getHexagonPart(float x,float y){
        //returns clicked HexagonPart: O(1)!!!
        int a=(int) Math.floor(x/hexagonPartWidth);
        int b=(int) Math.floor(y/hexagonPartHeight);
        return Parts[a+b*hexagonPartsInRow];
    }
    static public Hexagon2D getHexagon(float x,float y){
        return getHexagonPart(x,y).getHexagon(x,y);
    }
    static public void selectHexagon(Hexagon2D hexagon){
        //Sound.playSelectUnitMusic();
        if(hexagon.border || hexagon==selectedHexagon){
            selectedHexagon=null;
            return;
        }
        selectedHexagon=hexagon;
        if(hexagon.unit==null){
            neighborEnemies=new HashSet<>();
            neighborsOfSelected=new HashMap<>();
            return;
        }
        neighborsOfSelected= selectedHexagon.getSpecialNeighbors(hexagon.unit.getMovement(),
                (hex)->hex.owner==RoundManager.getCurrentPlayer());
        if(hexagon.unit.getMovement()>=hexagon.unit.attackMovementCost()){
            neighborEnemies=selectedHexagon.getSpecialNeighbors(1,
                    (hex)->hex.owner!=RoundManager.getCurrentPlayer()).keySet();
            System.out.println("Enemies: "+neighborEnemies.size());
        }
        else{
            neighborEnemies=new HashSet<>();
        }

    }
    static public void generate(){
        generate(new Random().nextLong());
        //generating players on map

        for(int i=0;i<widthHexagonNumber*heightHexagonNumber;i++)
            if(hexagonMap[i].water){hexagonMap[i].border=true;}
    }
    static public void generate(long seed){
        WorldMap.seed=seed;
        Random rng=new Random(seed);
        int lands=0;
        float landFraction=0.30f;
        LinkedList<Hexagon2D> list=new LinkedList<>();
        list.add(hexagonMap[(widthHexagonNumber)*(heightHexagonNumber+1)/2]);
        while(lands<landFraction*widthHexagonNumber*heightHexagonNumber){
            int chosenOne=rng.nextInt(list.size());
            Hexagon2D chosenHexagon= list.get(chosenOne);
            list.remove(chosenOne);
            chosenHexagon.water=false;
            for(Hexagon2D i: chosenHexagon.getNeighbors(1).keySet()){
                if(i.water && !list.contains(i))
                    list.add(i);
            }
            lands++;
        }
        for(Player pl: RoundManager.players){
            int chosenOne=rng.nextInt(list.size());
            Hexagon2D chosenHexagon= list.get(chosenOne);
            list.remove(chosenOne);
            chosenHexagon.water=false;
            chosenHexagon.newOwner(pl);
            for(Hexagon2D i:chosenHexagon.getNeighbors(5).keySet()){
                list.remove(i);
            }
            lands++;
        }
    }
    static public void click(Hexagon2D hexagon2D){
        if(hexagon2D.owner==RoundManager.getCurrentPlayer()){
            //System.out.println("owner checks out.");
            selectHexagon(hexagon2D);
        }
    }
    static public void target(Hexagon2D hexagon2D){
        if(selectedHexagon==null){
            return;
        }
        if(selectedHexagon.unit==null){
            return;
        }
        else{ //selectedHexagon!=null
            if(!getSelectedSet().contains(hexagon2D)){
                return;
            }
            else {
                if(neighborEnemies.contains(hexagon2D)){
                    selectedHexagon.unit.attack(hexagon2D);
                }
                else{//in neighborsOfSelected.KeySet
                    selectedHexagon.unit.move(hexagon2D);
                }
                Hexagon2D tempHexagon=selectedHexagon;
                selectHexagon(tempHexagon);
                selectHexagon(tempHexagon);
            }
        }
    }
    static public Set<Hexagon2D> getSelectedSet(){
        HashSet<Hexagon2D> set=new HashSet<>();
        if(selectedHexagon==null)
            return set;
        for(Hexagon2D i: neighborsOfSelected.keySet()){
            set.add(i);
        }
        set.addAll(neighborEnemies);
        return set;
    }
    static public void generateIncome(){
        for(Hexagon2D hex: hexagonMap){
            if(hex.owner!=null)
                hex.owner.money+=hex.getIncome();
        }
    }
    static public void refreshAndRegen(){
        for(Hexagon2D hex: hexagonMap){
            if(hex.unit!=null){
                hex.unit.regenerate();
                hex.unit.refreshMove();
            }

        }
    }
}
