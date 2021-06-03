package main.util;

import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.structures.StructureMenuList;
import main.util.units.UnitMenuList;

import java.util.Random;

public class AI extends Player {

    public AI(int colour) {
        super(colour);
        isPlayer = false;
    }

    public void play () {
        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            //System.out.println("sieema");
            if (hexagon.getOwner() == this) {
                while (true) {
                    boolean stop = true;
                    for (int i : UnitMenuList.list.keySet()) {
                        if (this.buyUnit(hexagon, i)) {
                            stop = false;
                        }
                    }
                        if (stop)
                            break;

                }
                while (true) {
                    boolean stop = true;
                    for (int i : StructureMenuList.list.keySet()) {
                        if (this.buyStructure(hexagon, i)) {
                            stop = false;
                        }
                    }
                    if (stop)
                        break;

                }
            }
        }

        for (Hexagon2D hexagon : WorldMap.hexagonMap) {
            if (hexagon.getOwner() == this && hexagon.getUnit() != null) {
                return;

            }
        }

        controls.passTurn = true;
    }

    public void greedy(Hexagon2D hexagon) {

    }


}
