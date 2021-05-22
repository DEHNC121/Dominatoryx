package main.util.events;

import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.structures.Structure;
import main.util.units.Unit;

public class Purchase extends Event {
    Hexagon2D hexagon;
    Unit hexagonUnit;
    Structure hexagonStructure;
    int ownerMoney;
    public Purchase (Hexagon2D hexagon) {
        this.hexagon = hexagon;
        hexagonUnit = hexagon.getUnit();
        hexagonStructure = hexagon.getStructure();
        ownerMoney = hexagon.getOwner().getMoney();
    }
    @Override
    public void goBack() {
        hexagon.setUnit(hexagonUnit);
        hexagon.setStructure(hexagonStructure);
        if (hexagon.getOwner() != null) {
            hexagon.getOwner().setMoney(ownerMoney);
        }
        else {
            System.out.println("Owner disappeared");
        }
    }
}
