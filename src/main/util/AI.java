package main.util;

import javafx.util.Pair;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.saveLoad.DataAI;
import main.util.saveLoad.DataPlayer;
import main.util.structures.StructureMenuList;
import main.util.units.UnitMenuList;

import java.util.*;

public class AI extends Player {
    private long nextMoveTime;
    public HashMap<Hexagon2D, Integer> AIHexagons; //AI hexagons -> (distance to closest enemy)
    public HashMap<Hexagon2D, Integer> AIHexagonsToEnemies;
    roundType round;
    public int nOfHexagons;
    public boolean attackMode;
    public int attackModeRounds;
    public boolean returnFlag = false;
    public Random random = new Random();
    public enum roundType {
        ATTACK, DEFENSE;
    }
    int structuresMoney, unitsMoney;
    public AI(int colour) {
        super(colour);
        nextMoveTime = 0;
        nOfHexagons = 1;
        isPlayer = false;
        AIHexagons = new HashMap<>();
        AIHexagonsToEnemies = new HashMap<>();
        attackMode = false;
        attackModeRounds = 0;
    }
    public AI(DataPlayer dp, DataAI ai){
        super(dp);
        isPlayer = false;
        nextMoveTime = 0;
        nOfHexagons=ai.nOfHexagons;
        attackMode=ai.attackMode;
        attackModeRounds=ai.attackModeRounds;
        AIHexagons=new HashMap<>();
        AIHexagonsToEnemies=new HashMap<>();
        returnFlag=ai.returnFlag;
    }
    public enum moves {
        BUY_STRUCTURE,
        BUY_UNIT,
        ATTACK,
        MOVE
    }

    public int countHexagons () {
        int res = 0;
        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this) {
                res++;
            }
        }
        return res;
    }
    public boolean structuresOccupied () {
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            if (hexagon.getStructure() == null)
                return false;
        }
        return true;
    }
    public void generateRounds () {

        if (RoundManager.roundCnt > 3) {
            if (countHexagons() < nOfHexagons) {
                attackModeRounds = 3;
            }
            if (attackModeRounds > 0) {
                round = roundType.ATTACK;
            }
            else {
                if (structuresOccupied()) {
                    round = roundType.ATTACK;
                }
                else {
                    round = roundType.DEFENSE;
                }
            }
        }
        else {
            round = roundType.ATTACK;
            //round = random.nextBoolean() ? roundType.ATTACK : roundType.DEFENSE;
        }
        randomize(0.1);
    }
    public void randomize (double p) {
        Random r = new Random();
        if (r.nextDouble() < p) {
            round = (round == roundType.ATTACK ? roundType.DEFENSE : roundType.ATTACK);
        }
    }
    public int getPriority (Hexagon2D start, Hexagon2D hexagon) {
        if (hexagon.getOwner() == null)
            return 0;
        else {
            if (hexagon.getUnit() == null) {
                return  (hexagon.getStructure() == null) ? 1 : 2;
            }
            else {
                if (start.getUnit().getAttackValue() < hexagon.getUnit().getHealth() &&
                        start.getUnit().getHealth() <= hexagon.getUnit().getAttackValue()) {
                    return  (hexagon.getStructure() == null) ? -2 : -1;
                }
                else {
                    return (hexagon.getStructure() == null) ? 3 : 4;
                }
            }
        }
    }

    public void makeAIHexagons () {
        AIHexagons.clear();
        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this) {
                putHexagonOnMap(hexagon);
            }
        }
    }
    public void makeAIHexagonsToEnemies () {
        AIHexagonsToEnemies.clear();
        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this) {
                putHexagonOnMapToEnemies(hexagon);
            }
        }
    }
    public void addTakenHexagons (ArrayList<Hexagon2D> takenHexagonsList) {
        for (Hexagon2D hexagon : takenHexagonsList) {
            putHexagonOnMap(hexagon);
        }
    }
    public void putHexagonOnMap (Hexagon2D hexagon) {
        for (Map.Entry<Hexagon2D, Integer> entry :
                hexagon.getSpecialNeighbors(10000000, (hex) -> true, (hex) -> hex.getOwner() != this).entrySet()) {
            AIHexagons.put(hexagon, entry.getValue());
        }
    }
    public void putHexagonOnMapToEnemies (Hexagon2D hexagon) {
        for (Map.Entry<Hexagon2D, Integer> entry :
                hexagon.getSpecialNeighbors(10000000, (hex) -> true, (hex) -> (hex.getOwner() != this && hex.getOwner() != null)).entrySet()) {

            AIHexagons.put(hexagon, entry.getValue());
        }
    }

    public void play () {
        returnFlag = false;
        makeAIHexagons();
        generateRounds();

        if (round == roundType.ATTACK) {
            System.out.println("ATTACK ROUND");
            playAttack();
            if (returnFlag)
                return;
        }
        if (round == roundType.DEFENSE) {
            System.out.println("DEFENSE ROUND");
            playDefense();
            if (returnFlag)
                return;
        }
        nOfHexagons = countHexagons();
        attackModeRounds = Integer.max(attackModeRounds - 1, 0);
        RoundManager.passTurn();
    }

    public void playAttack () {
        attackUnits(true);
        moveUnits();
        buy (0, this.money);
    }

    public void playDefense () {
        attackUnits(false);
        moveUnits();
        //buy (0, this.money);
        buy((int) (0.5f*this.money), (int) (0.5*this.money));
    }

    public void attackUnits (boolean aggressive) {
        ArrayList<Hexagon2D> takenHexagonsList = new ArrayList<>();
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            if (hexagon.getUnit() != null) {
                if (hexagon.getUnit().getMovement() >= hexagon.getUnit().attackMovementCost()) {
                    Hexagon2D temp = getHexagonToAttack(hexagon, aggressive);
                    if (temp != null) {
                        System.out.println("ATTACK SHOULD BEGIN HERE");
                        if (!doMove(moves.ATTACK, hexagon, temp, 0)){
                            returnFlag = true;
                            return;
                        }
                        if (temp.getOwner() == this)
                            takenHexagonsList.add(temp);
                    }
                }
            }
        }
        addTakenHexagons(takenHexagonsList);
    }
    public void moveUnits () {
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            if (hexagon.getUnit() != null) {
                Hexagon2D temp = getHexagonToMove(hexagon);
                if (hexagon.getUnit().getMovement() > 0 && temp != null && temp.getUnit() == null) {
                        if (!doMove(moves.MOVE, hexagon, temp, 0)) {
                            returnFlag = true;
                            return;
                        }
                }
            }
        }
    }

    public void createSorted(ArrayList<Pair<Hexagon2D, Integer>> AIHexagonsSorted) {
        for (Hexagon2D hexagon : AIHexagons.keySet()) {
            AIHexagonsSorted.add(new Pair<>(hexagon, AIHexagons.get(hexagon)));
        }
        AIHexagonsSorted.sort(new Comparator<>() {
            @Override
            public int compare(Pair<Hexagon2D, Integer> o1, Pair<Hexagon2D, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }

    public void buy (int structuresMoney, int unitsMoney) {
        this.structuresMoney = structuresMoney;
        this.unitsMoney = unitsMoney;
        ArrayList<Pair<Hexagon2D, Integer>> AIHexagonsSorted = new ArrayList<>();
        ArrayList<Pair<Hexagon2D, Integer>> AIHexagonsToEnemiesSorted = new ArrayList<>();
        createSorted(AIHexagonsSorted);
        createSorted(AIHexagonsToEnemiesSorted);
        Collections.reverse(AIHexagonsSorted);
        buyStructures(AIHexagonsSorted);
        buyUnits(AIHexagonsToEnemiesSorted);
    }
    public void buyStructures (ArrayList<Pair<Hexagon2D, Integer>> AIHexagonsSorted) {
        System.out.println(AIHexagonsSorted);
        for (Pair<Hexagon2D, Integer> p : AIHexagonsSorted) {
            System.out.println("money: " + money);
            System.out.println("unitsMoney: " + unitsMoney);
            System.out.println("structuresMoney: " + structuresMoney);
            Hexagon2D hexagon = p.getKey();
            if (hexagon.getStructure() == null) {
                ArrayList<Integer> list = new ArrayList<>(StructureMenuList.list.keySet());
                list.sort(Collections.reverseOrder());
                for (int i : list) {
                    if (structuresMoney >= StructureMenuList.list.get(i).getCost() &&
                            !doMove(moves.BUY_STRUCTURE, hexagon, null, i)) {
                        returnFlag = true;
                        return;
                    }
                }
            }
        }
    }

    public void buyUnits (ArrayList<Pair<Hexagon2D, Integer>> AIHexagonsSorted) {
        for (Pair<Hexagon2D, Integer> p : AIHexagonsSorted) {
            System.out.println("money: " + money);
            System.out.println("unitsMoney: " + unitsMoney);
            System.out.println("structuresMoney: " + structuresMoney);
            //System.out.println(AIHexagonsSorted);
            Hexagon2D hexagon = p.getKey();
            if (hexagon.getUnit() == null) {
                ArrayList<Integer> list = new ArrayList<>(UnitMenuList.list.keySet());
                list.sort(Collections.reverseOrder());
                for (int i : list) {
                    if (unitsMoney >= UnitMenuList.list.get(i).getCost() &&
                            !doMove(moves.BUY_UNIT, hexagon, null, i)) {
                        returnFlag = true;
                        return;
                    }
                }
            }
        }
    }

    public boolean doMove (moves x, Hexagon2D hexStart, Hexagon2D hexEnd, int i) {

        if (System.currentTimeMillis()>nextMoveTime){
            System.out.println("ATTACK WHEEEEEEEEEEEEEEEEEERE IS");
            System.out.println(System.currentTimeMillis() + " " + nextMoveTime);
            if (x == moves.MOVE) {
                hexStart.getUnit().move(hexEnd);
                System.out.println("MOVE");
                //WorldMap.selectedHexagon = hexStart;
            }
            else if (x == moves.ATTACK) {
                System.out.println("ATTACK BEGINS");
                hexStart.getUnit().attack(hexEnd);
                System.out.println("ATTACK");
            }
            else if (x == moves.BUY_STRUCTURE) {
                this.buyStructure(hexStart, i);
                structuresMoney -= StructureMenuList.list.get(i).getCost();
                System.out.println("BUY_STRUCTURE");
            }
            else if (x == moves.BUY_UNIT) {
                this.buyUnit(hexStart, i);
                unitsMoney -= UnitMenuList.list.get(i).getCost();
                System.out.println("BUY_UNIT");
            }
            nextMoveTime=System.currentTimeMillis()+10;
            return true;
        }else {
            return false;
        }
    }
    public Hexagon2D getRandomFromSet (Set<Hexagon2D> set) {
        if (set.isEmpty())
            return null;
        int size = set.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for(Hexagon2D hex : set)
        {
            if (i == item)
                return hex;
            i++;
        }
        return null;
    }
    public Hexagon2D getHexagonToAttack (Hexagon2D hexagon, boolean aggressive) {
        HashMap<Hexagon2D,Integer> map;
        if (aggressive) {
            map = hexagon.getSpecialNeighbors(1,
                    (hex)-> (hex.getOwner()!=RoundManager.getCurrentPlayer()), (hex)->false);
        }
        else {
            map = hexagon.getSpecialNeighbors(1,
                    (hex)-> (hex.getOwner() == null), (hex)->false);
            return getRandomFromSet(map.keySet());
        }
        HashSet<Hexagon2D> set = new HashSet<>();
        Hexagon2D hexagonToReturn = null;
        int maxPriority = -10;
        for (Hexagon2D hex : map.keySet()) {
            int p = getPriority(hexagon, hex);
            if (p > maxPriority) {
                maxPriority = p;
            }
        }
        for (Hexagon2D hex : map.keySet()) {
            if (getPriority(hexagon, hex) == maxPriority) {
                set.add(hex);
            }
        }
        return getRandomFromSet(set);
    }

    public Hexagon2D getHexagonToMove (Hexagon2D hexagon) {
        HashMap<Hexagon2D, Integer> map = hexagon.getSpecialNeighbors(hexagon.getUnit().getMovement(), (hex)->
                (hex.getOwner() == RoundManager.getCurrentPlayer()), (hex)->false
        );
        int minSecure = Integer.MAX_VALUE;
        Hexagon2D returnHexagon = null;
        map.entrySet().removeIf(entry -> entry.getKey().getUnit() != null);
        for (Hexagon2D hexagonInMap : map.keySet()) {
            if (AIHexagons.get(hexagonInMap) < minSecure) {
                minSecure = AIHexagons.get(hexagonInMap);
                returnHexagon = hexagonInMap;
            }
        }
        return returnHexagon;
    }






    @Override
    public void update () {
        play();
    }

    public void greedy(Hexagon2D hexagon) {

    }


}
