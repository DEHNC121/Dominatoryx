package main.util.events;

import javafx.util.Pair;
import main.util.Player;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.structures.Structure;
import main.util.units.Unit;

public class Move extends Event{
    Pair<Hexagon2D, Hexagon2D> hexagons;
    Pair<Unit, Unit> units;
    Pair<Player, Player> owners;
    Pair<Integer, Integer> unitHealths;
    Pair<Integer, Integer> unitMovements;

    public Move (Hexagon2D hexagonFirst, Hexagon2D hexagonSecond) {
        hexagons = new Pair<>(hexagonFirst, hexagonSecond);
        units = new Pair<>(hexagonFirst.getUnit(), hexagonSecond.getUnit());
        owners = new Pair<>(hexagonFirst.getOwner(), hexagonSecond.getOwner());
        unitHealths = new Pair<>((hexagonFirst.getUnit() != null) ? hexagonFirst.getUnit().getHealth() : 0,
                (hexagonSecond.getUnit() != null) ? hexagonSecond.getUnit().getHealth() : 0);
        unitMovements = new Pair<>((hexagonFirst.getUnit() != null) ? hexagonFirst.getUnit().getMovement() : 0,
                (hexagonSecond.getUnit() != null) ? hexagonSecond.getUnit().getMovement() : 0);
    }
    @Override
    public void goBack() {
        hexagons.getKey().setOwner(owners.getKey());
        hexagons.getValue().setOwner(owners.getValue());
        hexagons.getKey().setUnit(units.getKey());
        hexagons.getValue().setUnit(units.getValue());
        if (units.getKey() != null) {
            units.getKey().setOwner(owners.getKey());
            units.getKey().setHexagon(hexagons.getKey());
            units.getKey().setMovement(unitMovements.getKey());
            units.getKey().setHealth(unitHealths.getKey());
        }
        if (units.getValue() != null) {
            units.getValue().setOwner(owners.getValue());
            units.getValue().setHexagon(hexagons.getValue());
            units.getValue().setMovement(unitMovements.getValue());
            units.getValue().setHealth(unitHealths.getValue());
        }
        WorldMap.selectHexagon(hexagons.getKey());
    }
}
