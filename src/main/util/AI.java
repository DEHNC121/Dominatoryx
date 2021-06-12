package main.util;

import javafx.util.Pair;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.saveLoad.DataPlayer;
import main.util.structures.StructureMenuList;
import main.util.units.UnitMenuList;

import java.util.*;

public class AI extends Player {
    long nextMoveTime = 0;
    public AI(int colour) {
        super(colour);
        isPlayer = false;
    }
    public AI(DataPlayer dp){
        super(dp);
        isPlayer = false;
    }
    public enum moves {
        BUY_STRUCTURE,
        BUY_UNIT,
        ATTACK,
        MOVE
    }

    public void play () {
        //AI buys everything
        System.out.println("play");
        HashMap<Hexagon2D, Pair<Hexagon2D,Integer>> AIHexagons = new HashMap<>();
        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this) {
                for (Map.Entry<Hexagon2D, Integer> entry :
                        hexagon.getSpecialNeighbors(10000000,
                                (hex) -> true, (hex) -> (hex.getOwner() != this && hex.getOwner() != null)).entrySet()) {
                    AIHexagons.put(hexagon, new Pair<>(entry.getKey(), entry.getValue()));
                }
            }
        }

        ArrayList<Hexagon2D> takenHexagonsList = new ArrayList<>();
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            if (hexagon.getUnit() != null) {
                if (hexagon.getUnit().getMovement() >= hexagon.getUnit().attackMovementCost()) {
                    Hexagon2D temp = getHexagonToAttack(hexagon);
                    if (temp != null) {
                        if (!doMove(moves.ATTACK, hexagon, temp, 0)){
                            return;
                        }
                        if (temp.getOwner() == this)
                            takenHexagonsList.add(temp);
                    }
                }
            }
        }
        for (Hexagon2D hexagon : takenHexagonsList) {
            for (Map.Entry<Hexagon2D, Integer> entry :
                    hexagon.getSpecialNeighbors(10000000, (hex) -> true, (hex) -> hex.getOwner() != this).entrySet()) {
                AIHexagons.put(hexagon, new Pair<>(entry.getKey(), entry.getValue()));
            }
        }
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            if (hexagon.getUnit() != null) {
                Hexagon2D temp = getHexagonToMove(AIHexagons, hexagon);
                if (hexagon.getUnit().getMovement() > 0) {
                    if (temp != null && temp.getUnit() == null) {
                        if (!doMove(moves.MOVE, hexagon, temp, 0))
                            return;
                    }
                }
            }
        }
        ArrayList<Pair<Hexagon2D, Integer>> AIHexagonsSorted = new ArrayList<>();
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            AIHexagonsSorted.add(new Pair<Hexagon2D, Integer>(hexagon, AIHexagons.get(hexagon).getValue()));
        }
        AIHexagonsSorted.sort(new Comparator<>() {
            @Override
            public int compare(Pair<Hexagon2D, Integer> o1, Pair<Hexagon2D, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Pair<Hexagon2D, Integer> p : AIHexagonsSorted) {
            Hexagon2D hexagon = p.getKey();
            if (hexagon.getUnit() == null) {
                ArrayList<Integer> list = new ArrayList<>(UnitMenuList.list.keySet());
                list.sort(Collections.reverseOrder());
                for (int i : list) {
                    if (hexagon.getOwner().getMoney() >= UnitMenuList.list.get(i).getCost() &&
                            !doMove(moves.BUY_UNIT, hexagon, null, i))
                        return;
                }
            }
        }
        Collections.reverse(AIHexagonsSorted);
        for (Pair<Hexagon2D, Integer> p : AIHexagonsSorted) {
            Hexagon2D hexagon = p.getKey();
            if (hexagon.getStructure() == null) {
                ArrayList<Integer> list = new ArrayList<>(StructureMenuList.list.keySet());
                list.sort(Collections.reverseOrder());
                for (int i : list) {
                    if (hexagon.getOwner().getMoney() >= StructureMenuList.list.get(i).getCost() &&
                            !doMove(moves.BUY_STRUCTURE, hexagon, null, i))
                        return;
                }
            }
        }
        System.out.println("yo");
        RoundManager.passTurn();
    }

    public boolean doMove (moves x, Hexagon2D hexStart, Hexagon2D hexEnd, int i) {

        if (System.currentTimeMillis()>nextMoveTime){
            System.out.println(System.currentTimeMillis() + " " + nextMoveTime);
            if (x == moves.MOVE) {
                hexStart.getUnit().move(hexEnd);
                System.out.println("MOVE");
                //WorldMap.selectedHexagon = hexStart;
            }
            else if (x == moves.ATTACK) {
                hexStart.getUnit().attack(hexEnd);
                System.out.println("ATTACK");
            }
            else if (x == moves.BUY_STRUCTURE) {
                this.buyStructure(hexStart, i);
                System.out.println("BUY_STRUCTURE");
            }
            else if (x == moves.BUY_UNIT) {
                this.buyUnit(hexStart, i);
                System.out.println("BUY_UNIT");
            }
            nextMoveTime=System.currentTimeMillis()+10;
            return true;
        }else {
            return false;
        }
    }

    public Hexagon2D getHexagonToAttack (Hexagon2D hexagon) {
        return hexagon.getRandomEnemyNeighbour();
    }

    public Hexagon2D getHexagonToMove (HashMap<Hexagon2D, Pair<Hexagon2D,Integer>> AIHexagons, Hexagon2D hexagon) {
        HashMap<Hexagon2D, Integer> map = hexagon.getSpecialNeighbors(hexagon.getUnit().getMovement(), (hex)->
                (hex.getOwner() == RoundManager.getCurrentPlayer()), (hex)->false
        );
        System.out.println(map);
        int minSecure = Integer.MAX_VALUE;
        Hexagon2D returnHexagon = null;
        //map.entrySet().removeIf(entry -> entry.getKey().getUnit() != null);
        for (Hexagon2D hexagonInMap : map.keySet()) {
            if (AIHexagons.get(hexagonInMap).getValue() < minSecure) {
                minSecure = AIHexagons.get(hexagonInMap).getValue();
                returnHexagon = hexagonInMap;
            }
        }
        System.out.println(returnHexagon);

        return returnHexagon;

        //return hexagon.getRandomNeighbour(hexagon.getUnit().getMovement());
    }






    @Override
    public void update () {
        play();
    }

    public void greedy(Hexagon2D hexagon) {

    }


}
